package score.board.models;

import org.junit.jupiter.api.Test;
import score.board.models.types.MatchStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchDefaultPropertiesAfterCreation() {
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );

        assertEquals(0, matches.get(1).getHomeTeamScore());
        assertEquals(0, matches.get(1).getAwayTeamScore());
        assertEquals(MatchStatus.NOT_STARTED, matches.get(1).getMatchStatus());
    }

    @Test
    void testStartMatch() {
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );

        matches.get(2).startMatch();
        assertEquals(MatchStatus.LIVE, matches.get(2).getMatchStatus());
    }

    @Test
    void testFinishMatch() {
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );

        matches.get(0).finishMatch();
        assertEquals(MatchStatus.FINISHED, matches.get(0).getMatchStatus());
    }

    @Test
    void testSetHomeTeamScore() {
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );

        matches.get(0).setHomeTeamScore(8);
        assertEquals(8, matches.get(0).getHomeTeamScore());
    }

    @Test
    void testSetAwayTeamScore() {
        List<Match> matches = Arrays.asList(
                new Match(new Team("TeamA", 1), new Team("TeamB", 2), 1),
                new Match(new Team("TeamC", 3), new Team("TeamD", 4), 2),
                new Match(new Team("TeamE", 4), new Team("TeamF", 5), 3)
        );

        matches.get(2).setAwayTeamScore(5);
        assertEquals(5, matches.get(2).getAwayTeamScore());
    }
}