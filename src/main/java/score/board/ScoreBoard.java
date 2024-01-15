package score.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import score.board.models.DatabaseObject;
import score.board.models.Team;
import score.board.models.Match;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ScoreBoard {

	public static DatabaseObject DATABASE_OBJECT = new DatabaseObject();

	private static void setDataBaseObject() {
		List<Team> teams = Arrays.asList(
				new Team("Flora", 1),
				new Team("FCI Levadia", 2),
				new Team("Tallinna Kalev", 3),
				new Team("Paide Linnameeskond", 4),
				new Team("Nõmme Kalju", 5),
				new Team("Varpus", 6),
				new Team("Kuressaare", 7),
				new Team("Narva Trans", 8),
				new Team("Tammeka", 9));

		List<Match> matches = Arrays.asList(
				new Match(teams.get(0), teams.get(2), 1),
				new Match(teams.get(1), teams.get(3), 2),
				new Match(teams.get(8), teams.get(6), 3),
				new Match(teams.get(7), teams.get(5), 4),
				new Match(teams.get(4), teams.get(3), 5),
				new Match(teams.get(1), teams.get(7), 6),
				new Match(teams.get(2), teams.get(8), 7),
				new Match(teams.get(5), teams.get(0), 8),
				new Match(teams.get(6), teams.get(3), 9),
				new Match(teams.get(1), teams.get(0), 10),
				new Match(teams.get(7), teams.get(8), 11),
				new Match(teams.get(3), teams.get(2), 12),
				new Match(teams.get(4), teams.get(5), 13)

		);

		DATABASE_OBJECT.setMatches(matches);
	}

	public static void main(String[] args) {
		setDataBaseObject();
		SpringApplication.run(ScoreBoard.class, args);
	}

}
