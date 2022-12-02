package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Day2 {

    private static final int WIN_SCORE = 6;
    private static final int DRAW_SCORE = 3;

    private static enum MATCH_RESULT {
        WON, DRAW, LOST;
    }

    private static final Map<String, Integer> MOVE_SCORE_MAPPING = Map.of(
            "X", 1,
            "Y", 2,
            "Z", 3
    );

    private static final Map<String, String> WINNING_COMBINATION = Map.of(
            "A", "Y",
            "B", "Z",
            "C", "X");

    private static final Map<String, String> DRAW_COMBINATION = Map.of(
            "A", "X",
            "B", "Y",
            "C", "Z"
    );

    private static final Map<String, String> LOSING_COMBINATION = Map.of(
            "A", "Z",
            "B", "X",
            "C", "Y"
    );

    private static final Map<String, MATCH_RESULT> EXPECTED_MATCH_RESULT = Map.of(
            "X", MATCH_RESULT.LOST,
            "Y", MATCH_RESULT.DRAW,
            "Z", MATCH_RESULT.WON
    );

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        getTotalScore(reader);
        getTotalScoreForExpectedResult(reader);
    }

    private static void getTotalScore(BufferedReader reader) throws IOException {
        String input;
        int totalScore = 0;
        while (!(input = reader.readLine()).isEmpty()) {
            String opponentMove = input.split("\\s+")[0];
            String yourMove = input.split("\\s+")[1];
            MATCH_RESULT matchResult = getMatchResult(opponentMove, yourMove);
            totalScore += getMatchScore(matchResult, yourMove);
        }
        System.out.println(totalScore);
    }

    private static void getTotalScoreForExpectedResult(BufferedReader reader) throws IOException {
        String input;
        int totalScore = 0;
        while (!(input = reader.readLine()).isEmpty()) {
            String opponentMove = input.split("\\s+")[0];
            String roundExpectation = input.split("\\s+")[1];
            MATCH_RESULT expectedMatchResult = EXPECTED_MATCH_RESULT.get(roundExpectation);
            totalScore += getMatchScore(expectedMatchResult, getExpectedMove(expectedMatchResult, opponentMove));
        }
        System.out.println(totalScore);
    }

    private static String getExpectedMove(MATCH_RESULT expectedMatchResult, String opponentMove) {
        return switch (expectedMatchResult) {
            case WON -> WINNING_COMBINATION.get(opponentMove);
            case DRAW -> DRAW_COMBINATION.get(opponentMove);
            case LOST -> LOSING_COMBINATION.get(opponentMove);
        };
    }

    private static int getMatchScore(MATCH_RESULT matchResult, String yourMove) {
        return switch (matchResult) {
            case WON -> WIN_SCORE + MOVE_SCORE_MAPPING.get(yourMove);
            case DRAW -> DRAW_SCORE + MOVE_SCORE_MAPPING.get(yourMove);
            case LOST -> MOVE_SCORE_MAPPING.get(yourMove);
        };
    }

    private static MATCH_RESULT getMatchResult(String opponentMove, String yourMove) {
        if (WINNING_COMBINATION.get(opponentMove).equals(yourMove)) {
            return MATCH_RESULT.WON;
        }
        if (DRAW_COMBINATION.get(opponentMove).equals(yourMove)) {
            return MATCH_RESULT.DRAW;
        }
        return MATCH_RESULT.LOST;
    }
}
