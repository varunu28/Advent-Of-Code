package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        findStartOfMarker(reader, /* packetMarkerLimit= */ 4);
        findStartOfMarker(reader, /* messageMarkerLimit= */ 14);
    }

    private static void findStartOfMarker(BufferedReader reader, int limit) throws IOException {
        String charStream = reader.readLine();
        System.out.println(IntStream.range(0, charStream.length())
                .filter(i -> allUnique(charStream, i, limit))
                .map(i -> i + limit)
                .findFirst()
                .orElse(0));
    }

    private static boolean allUnique(String charStream, int idx, int limit) {
        return charStream.substring(idx, idx + limit)
                .chars()
                .boxed()
                .collect(Collectors.toSet())
                .size() == limit;
    }
}
