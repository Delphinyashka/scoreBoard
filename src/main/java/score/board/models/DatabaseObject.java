package score.board.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseObject {
    @JsonProperty
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getFilteredMatches(int totalScore) {
        return matches.stream()
                .filter(match -> match.getHomeTeamScore() + match.getAwayTeamScore() == totalScore)
                .sorted(Comparator.comparing(Match::getMatchAddedDate))
                .collect(Collectors.toList());
    }

    public Match findMatchById(int id) {
        return matches.stream()
                .filter(match -> match.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }

    @Override
    public String toString() {
        return "DatabaseObject{" +
                "matches=" + matches +
                '}';
    }
}
