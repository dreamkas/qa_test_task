package com.qa;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.*;

import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

import sun.rmi.runtime.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BasicTest {

    public static final Logger LOG = Logger.getLogger(BasicTest.class);

    @Test
    public void howManyFilms() {
        String filmsUri = "https://swapi.co/api/films/";
        Response resp = RestAssured.get(filmsUri);
        int filmsCount = resp.path("count");
        LOG.info(filmsCount);
        //вот тут не совсем понятно - я как бы считаю все уже вышедшие. Если же считать информацию  с сайта достоверной, то конечно должна быть иная проверка.
        Assert.assertEquals(filmsCount, 9, "Some mistake in api response. Where are only " + filmsCount + " films in swapi.co, but we know 9 films");
    }

    @Test
    public void infoAbout4thFilm() throws Exception {

        String filmsUri = "https://swapi.co/api/films/1/";
        Response resp = RestAssured.get(filmsUri);
        LOG.info("Validate response");
        resp.then().statusCode(200).and().assertThat().body("title", equalTo("A New Hope")); //проверяем имя фильма.
        resp.then().assertThat().body("director", equalTo("George Lucas")); //проверяем режисёра

        String release_date = resp.path("release_date");
        Date dateApi = convertStringToDate(release_date, "yyyy-MM-dd");
        Calendar calendarActual = Calendar.getInstance();
        calendarActual.setTime(dateApi);

        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        Calendar calendarExpected = Calendar.getInstance(tz);
        calendarExpected.set(1977, Calendar.MAY, 25);

        Assert.assertEquals(calendarActual.get(Calendar.YEAR), calendarExpected.get(Calendar.YEAR), "Year from api is not equal with expected"); //Проверяем год выхода
    }
    @Test
    public void secondEpisodePlanets() {
        String filmsUri = "https://swapi.co/api/films/5/";
        Response resp = RestAssured.get(filmsUri);
        resp.then().statusCode(200).and().assertThat().body("title",equalTo("Attack of the Clones"));
        List<String> planets = resp.path("planets");
        LOG.info(planets);
        List<String> actualPlanets = new ArrayList<>();
        LOG.info("Second episode planets is:");
        for(String planetUri:planets)
        {
            Response planet = RestAssured.get(planetUri);
            String name = planet.path("name");
            actualPlanets.add(name);
            LOG.info(name);
        }
        List<String> expectedPlanets = new ArrayList<>();
        expectedPlanets.add("Naboo");
        expectedPlanets.add("Coruscant");
        expectedPlanets.add("Kamino");
        expectedPlanets.add("Geonosis");
        expectedPlanets.add("Tatooine");

        Assert.assertEquals(actualPlanets,expectedPlanets, "Some planet name wrong");
    }

    @Test
    public void LukeHomeWorld() {
        String filmsUri = "https://swapi.co/api/people/1/";
        Response resp = RestAssured.get(filmsUri);
        resp.then().statusCode(200).and().assertThat().body("name",equalTo("Luke Skywalker"));
        LOG.info("Name is " + resp.path("name"));
        String homeWorldUri = resp.path("homeworld");
        Response respHomeWorld = RestAssured.get(homeWorldUri);
        respHomeWorld.then().statusCode(200).and().assertThat().body("name",equalTo("Tatooine"));
        LOG.info("And homeworld is " + respHomeWorld.path("name"));
    }

    @Test
    public void ObiWanThirdFilmShips() {
        Response resp;
        String pilotUri = "https://swapi.co/api/people/?search=obi-wan";
        List<String> starShips = new ArrayList<>();

        resp = RestAssured.get(pilotUri);
        LOG.info("Check name");
        resp.then().statusCode(200).and().assertThat().body("results[0].name", equalTo("Obi-Wan Kenobi"));

        starShips = resp.path("results[0].starships");
        LOG.info("here are all obi starships \n" +starShips);

        List<Response> anotherShips = new ArrayList<>();
        boolean isFound=false;
        //search for main ship
        for (String ship : starShips) {
            Response shipResp = RestAssured.get(ship);
            List<String> films = shipResp.path("films");
            for (String film : films) {
                Response filmResp = get(film);
                String filmName = filmResp.path("title");

                if (filmName.contains("Revenge of the Sith")) { //проверяем, что принадлежит третьему фильму.
                    String shipName = shipResp.path("name");
                    if (shipName.contains("Jedi starfighter")) {
                        LOG.info("This is main Obi ship. Here are specifications");
                        LOG.info(shipResp.prettyPrint());
                        isFound=true; //если нашли в результатах основной корабль -все ок.
                    } else {
                        anotherShips.add(shipResp);
                    }
                }
            }
        }
        Assert.assertTrue(isFound,"Main obi ship not in search results");
        LOG.info("here are another ships = where ObiWan is in pilots BLock");
        for (Response ship : anotherShips) {
            LOG.info(ship.prettyPrint());
        }

        LOG.info("Also on wookiee obi-wan is");
        resp = RestAssured.get("https://swapi.co/api/people/10/?format=wookiee");
        LOG.info(resp.path("whrascwo"));
    }

    @Test
    public void is100Exist(){
        String filmsUri = "https://swapi.co/api/people/100/";
        Response resp = RestAssured.get(filmsUri);
        resp.then().assertThat().statusCode(equalTo(404)); //нет сотого персонажа
        LOG.info("no people/100/");
        int peopleCount = 87;
        String allUri = "https://swapi.co/api/people/";
        resp = RestAssured.get(allUri);
        resp.then().statusCode(200).and().assertThat().body("count",equalTo(peopleCount));
        LOG.info("There are only " +peopleCount+ " people");
    }


    public static boolean isStringContainsInList(String searchString, List<String> strings) {  //можно переписать и по другому, используя 2 return.
        boolean isFound = false;
        for (String s : strings) {
            if (s.contains(searchString))
                isFound = true;
        }
        return isFound;
    }

    public static Date convertStringToDate(String dateString, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(dateFormat);          //"d MMMM yyyy г."
        Date date = format.parse(dateString);
        return date;
    }


}
