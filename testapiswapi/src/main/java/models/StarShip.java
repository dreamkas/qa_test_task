package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarShip {

    List<String> pilots;

    String name;

    String manufacturer;

    @JsonProperty("cost_in_credits")
    int costInCredits;

    int length;

    @JsonProperty("max_atmosphering_speed")
    String maxAtmospheringSpeed;

    int crew;

    int passengers;

    @JsonProperty("cargo_capacity")
    int cargoCapacity;

    String consumables;

    @JsonProperty("hyperdrive_rating")
    double hyperdrive_rating;

    String MGLT;

    @JsonProperty("starship_class")
    String starshipClass;

}
