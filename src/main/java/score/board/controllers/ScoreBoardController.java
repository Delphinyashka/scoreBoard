package score.board.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import score.board.models.Match;

import java.util.List;

import static score.board.ScoreBoard.DATABASE_OBJECT;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ScoreBoardController {
    private final HttpHeaders headers = new HttpHeaders();

    @GetMapping("/scoreBoard")
    public ResponseEntity<List<Match>> sendFeed() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(DATABASE_OBJECT.getMatches(), headers, HttpStatus.OK);
    }

    @GetMapping("/scoreBoardFiltered/{totalScore}")
    public ResponseEntity<List<Match>> getScoreBoardFiltered(@PathVariable int totalScore) {
        return new ResponseEntity<>(DATABASE_OBJECT.getFilteredMatches(totalScore), headers, HttpStatus.OK);
    }

    @PostMapping("/updateHomeTeamScore/")
    public ResponseEntity<List<Match>> updateHomeTeamScore(@RequestBody Match updatedMatch) {
        Match match = DATABASE_OBJECT.findMatchById(updatedMatch.getId());
        match.setHomeTeamScore(updatedMatch.getHomeTeamScore());
        return new ResponseEntity<>(DATABASE_OBJECT.getMatches(), headers, HttpStatus.OK);
    }

    @PostMapping("/updateAwayTeamScore/")
    public ResponseEntity<List<Match>> updateAwayTeamScore(@RequestBody Match updatedMatch) {
        Match match = DATABASE_OBJECT.findMatchById(updatedMatch.getId());
        match.setAwayTeamScore(updatedMatch.getAwayTeamScore());
        return new ResponseEntity<>(DATABASE_OBJECT.getMatches(), headers, HttpStatus.OK);
    }

    @PostMapping("/startMatch/{matchId}")
    public ResponseEntity<List<Match>> startMatch(@PathVariable int matchId) {
        Match match = DATABASE_OBJECT.findMatchById(matchId);
        match.startMatch();
        return new ResponseEntity<>(DATABASE_OBJECT.getMatches(), headers, HttpStatus.OK);
    }

    @PostMapping("/finishMatch/{matchId}")
    public ResponseEntity<List<Match>> finishMatch(@PathVariable int matchId) {
        Match match = DATABASE_OBJECT.findMatchById(matchId);
        match.finishMatch();
        return new ResponseEntity<>(DATABASE_OBJECT.getMatches(), headers, HttpStatus.OK);
    }
}