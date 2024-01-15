package score.board.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import score.board.models.types.MatchStatus;

import java.util.Date;

public class Match {
    @JsonProperty
    private int id;
    @JsonProperty
    private MatchStatus matchStatus;
    @JsonProperty
    private Team homeTeam;
    @JsonProperty
    private Team awayTeam;
    @JsonProperty
    private int homeTeamScore;
    @JsonProperty
    private int awayTeamScore;
    @JsonProperty
    private Date matchAddedDate;

    public Match() {
    }
    public Match(Team homeTeam, Team awayTeam, int id) {
        this.id = id;
        this.matchStatus = MatchStatus.NOT_STARTED;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.matchAddedDate = new Date();
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    private void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public void startMatch() {
        this.setMatchStatus(MatchStatus.LIVE);
    }

    public void finishMatch() {
        this.setMatchStatus(MatchStatus.FINISHED);
    }

    public int getId() {
        return id;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public Date getMatchAddedDate() {
        return matchAddedDate;
    }
}
