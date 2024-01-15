package score.board.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseObjectTest {

    @Test
    public void testGetFilteredMultipleMatches() {
        DatabaseObject databaseObject = new DatabaseObject();
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 5), new Team("TeamF", 6), 3)
        );
        databaseObject.setMatches(matches);

        matches.get(0).setHomeTeamScore(8);
        matches.get(0).setAwayTeamScore(1);

        matches.get(1).setHomeTeamScore(5);
        matches.get(1).setAwayTeamScore(2);

        matches.get(2).setHomeTeamScore(3);
        matches.get(2).setAwayTeamScore(6);

        List<Match> filteredMatches = databaseObject.getFilteredMatches(9);
        List<Match> filteredMatchesEmpty = databaseObject.getFilteredMatches(2);

        assertEquals(2, filteredMatches.size());
        assertEquals(0, filteredMatchesEmpty.size());
    }

    @Test
    public void testGetFilteredSingleMatch() {
        DatabaseObject databaseObject = new DatabaseObject();
        List<Match> matches = List.of(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1)
        );
        databaseObject.setMatches(matches);

        matches.get(0).setHomeTeamScore(4);
        matches.get(0).setAwayTeamScore(3);

        List<Match> filteredMatches = databaseObject.getFilteredMatches(7);
        List<Match> filteredMatchesEmpty = databaseObject.getFilteredMatches(0);

        assertEquals(1, filteredMatches.size());
        assertEquals(0, filteredMatchesEmpty.size());
    }

    @Test
    public void testGetFilteredNoMatches() {
        DatabaseObject databaseObject = new DatabaseObject();
        List<Match> matches = new ArrayList<>();
        databaseObject.setMatches(matches);

        List<Match> filteredMatches = databaseObject.getFilteredMatches(2);

        assertEquals(0, filteredMatches.size());
    }

    @Test
    public void testFindMatchById() {
        DatabaseObject databaseObject = new DatabaseObject();
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );
        databaseObject.setMatches(matches);

        Match foundMatch = databaseObject.findMatchById(2);

        assertNotNull(foundMatch);
        assertEquals(2, foundMatch.getId());
    }

    @Test
    public void testFindMatchByIdMatchNotFound() {
        DatabaseObject databaseObject = new DatabaseObject();
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );
        databaseObject.setMatches(matches);

        Throwable exception = assertThrows(RuntimeException.class, () -> databaseObject.findMatchById(4));
        assertEquals("Match not found", exception.getMessage());
    }
}
