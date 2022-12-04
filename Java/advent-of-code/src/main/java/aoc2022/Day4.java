package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        getFullyOverlappedCount(reader);
        getOverlappedCount(reader);
    }

    private static void getFullyOverlappedCount(BufferedReader reader) throws IOException {
        String input;
        int fullyContainedCount = 0;
        while (!(input = reader.readLine()).isEmpty()) {
            Interval intervalOne = buildInterval(input.split(",")[0]);
            Interval intervalTwo = buildInterval(input.split(",")[1]);
            if (eitherIntervalFullyOverlaps(intervalOne, intervalTwo)) {
                fullyContainedCount++;
            }
        }
        System.out.println(fullyContainedCount);
    }

    private static void getOverlappedCount(BufferedReader reader) throws IOException {
        String input;
        int overlapCount = 0;
        while (!(input = reader.readLine()).isEmpty()) {
            Interval intervalOne = buildInterval(input.split(",")[0]);
            Interval intervalTwo = buildInterval(input.split(",")[1]);
            if (eitherIntervalPartiallyOverlaps(intervalOne, intervalTwo)) {
                overlapCount++;
            }
        }
        System.out.println(overlapCount);
    }

    private record Interval(int start, int end) {
    }

    private static Interval buildInterval(String input) {
        return new Interval(Integer.parseInt(input.split("-")[0]), Integer.parseInt(input.split("-")[1]));
    }

    private static boolean eitherIntervalFullyOverlaps(Interval intervalOne, Interval intervalTwo) {
        return (intervalOne.start >= intervalTwo.start && intervalOne.end <= intervalTwo.end) ||
                (intervalTwo.start >= intervalOne.start && intervalTwo.end <= intervalOne.end);
    }

    private static boolean eitherIntervalPartiallyOverlaps(Interval intervalOne, Interval intervalTwo) {
        return !((intervalOne.start > intervalTwo.end) || (intervalTwo.start > intervalOne.end));
    }
}
