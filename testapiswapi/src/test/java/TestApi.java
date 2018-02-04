import models.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestApi {

    private static RestTemplate restTemplate;
    private static HttpHeaders headers;
    private static HttpEntity<String> entity;
    private static ResponseEntity<Films> responseFilms;
    private static ResponseEntity<Film> responseFilm;
    private static ResponseEntity<People> responsePeople;
    private static ResponseEntity<PeopleList> responsePeopleList;

    @BeforeClass
    public static void setUp() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<>("parameters", headers);
    }

    @Test
    public void testCountFilms() {
        responseFilms = restTemplate.exchange("https://swapi.co/api/films", HttpMethod.GET, entity, Films.class);
        int actualCountFilms = responseFilms.getBody().getCount();
        assertEquals(7, actualCountFilms);
    }

    @Test
    public void testDateFilmFourEpisode() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/1", HttpMethod.GET, entity, Film.class);
        String actualCountFilms = responseFilm.getBody().getReleaseDate();
        assertEquals("1977-05-25", actualCountFilms);
    }

    @Test
    public void testDirectorFilmFourEpisode() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/1", HttpMethod.GET, entity, Film.class);
        String actualDirector = responseFilm.getBody().getDirector();
        assertEquals("George Lucas", actualDirector);
    }

    @Test
    public void testTitleFilmFourEpisode() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/1", HttpMethod.GET, entity, Film.class);
        String actualTitle = responseFilm.getBody().getTitle();
        assertEquals("A New Hope", actualTitle);
    }

    @Test
    public void testNamePlanetsInEpisodeTwo() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/5", HttpMethod.GET, entity, Film.class);
        List<String> planetsUrl = responseFilm.getBody().getPlanets();
        List<String> actualPlanetsName = new ArrayList<>();
        for (String planetName : planetsUrl) {
            ResponseEntity<Planet> responsePlanet = restTemplate.exchange(planetName, HttpMethod.GET, entity, Planet.class);
            actualPlanetsName.add(responsePlanet.getBody().getName());
        }
        ArrayList expectedPlanetNames = new ArrayList<String>() {{
            add("Naboo");
            add("Coruscant");
            add("Kamino");
            add("Geonosis");
            add("Tatooine");
        }};
        assertEquals(expectedPlanetNames, actualPlanetsName);
    }

    @Test
    public void testHomePlanetLukeSkywalker() {
        responsePeople = restTemplate.exchange("https://swapi.co/api/people/1/", HttpMethod.GET, entity, People.class);
        String urlHomeWorldLuke = responsePeople.getBody().getHomeworld();
        ResponseEntity<Planet> responsePlanet = restTemplate.exchange(urlHomeWorldLuke, HttpMethod.GET, entity, Planet.class);
        String actualHomeWorldName = responsePlanet.getBody().getName();
        assertEquals("Tatooine", actualHomeWorldName);
    }

    @Test
    public void testStarshipObiInThirdEpisode() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/6", HttpMethod.GET, entity, Film.class);
        List<String> starShipsList = responseFilm.getBody().getStarships();
        String actualNameStarShip = getStarShipByObi(starShipsList).getName();
        assertEquals("Jedi starfighter", actualNameStarShip);
    }

    @Test
    public void testParametrsStarShipByObiInThirdEpisode() {
        responseFilm = restTemplate.exchange("https://swapi.co/api/films/6", HttpMethod.GET, entity, Film.class);
        List<String> starShipsList = responseFilm.getBody().getStarships();
        StarShip actualStarShip = getStarShipByObi(starShipsList);
        assertEquals("Kuat Systems Engineering", actualStarShip.getManufacturer());
        assertEquals(180000, actualStarShip.getCostInCredits());
        assertEquals(1, actualStarShip.getCrew());
        assertEquals(60, actualStarShip.getCargoCapacity());
    }

    @Test
    public void testIsThereAHundredthPerson() {
        responsePeopleList = restTemplate.exchange("https://swapi.co/api/people/", HttpMethod.GET, entity, PeopleList.class);
        boolean isIsThereAHundredthPerson = false;
        int countPeople = responsePeopleList.getBody().getCount();
        if (countPeople >= 100){
            isIsThereAHundredthPerson = true;
        }
        assertEquals(false, isIsThereAHundredthPerson);
    }


    private StarShip getStarShipByObi(List<String> starShipsList) {
        for (String starship : starShipsList) {
            ResponseEntity<StarShip> responseStarship = restTemplate.exchange(starship, HttpMethod.GET, entity, StarShip.class);
            List<String> listPilots = responseStarship.getBody().getPilots();
            if (isPilotObiThisShip(listPilots)) {
                return responseStarship.getBody();
            }
        }
        return null;
    }

    private Boolean isPilotObiThisShip(List<String> listPilots) {
        for (String pilot : listPilots) {
            ResponseEntity<People> responsePilot = restTemplate.exchange(pilot, HttpMethod.GET, entity, People.class);
            String namePilot = responsePilot.getBody().getName();
            if (namePilot.equals("Obi-Wan Kenobi")) {
                return true;
            }
        }
        return false;
    }

}
