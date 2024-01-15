package score.board.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;

    public Team() {
    }
    public Team(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
