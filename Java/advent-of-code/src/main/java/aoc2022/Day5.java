package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        partOne(reader);
        partTwo(reader);
    }

    private static void partOne(BufferedReader reader) throws IOException {
        List<List<Character>> stacks = parseStacks(reader);
        String input;
        while (!(input = reader.readLine()).isEmpty()) {
            Command.parseCommand(input).executeWithCrateMover9000(stacks);
        }
        printTopCratesOnEachStack(stacks);
    }

    private static void partTwo(BufferedReader reader) throws IOException {
        List<List<Character>> stacks = parseStacks(reader);
        String input;
        while (!(input = reader.readLine()).isEmpty()) {
            Command.parseCommand(input).executeWithCrateMover9001(stacks);
        }
        printTopCratesOnEachStack(stacks);
    }

    private static void printTopCratesOnEachStack(List<List<Character>> stacks) {
        System.out.println(stacks.stream()
                .filter(e -> !e.isEmpty())
                .map(e -> String.valueOf(e.get(0)))
                .collect(Collectors.joining()));
    }

    private static List<List<Character>> parseStacks(BufferedReader reader) throws IOException {
        String input;
        List<List<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(new LinkedList<>());
        }
        while (!(input = reader.readLine()).isEmpty()) {
            if (Character.isDigit(input.charAt(1))) {
                break;
            }
            int idx = 0;
            for (int i = 1; i < 38 && i < input.length(); i += 4) {
                char c = input.charAt(i);
                if (c != ' ') {
                    stacks.get(idx).add(c);
                }
                idx++;
            }
        }
        reader.readLine();
        return stacks;
    }

    private record Command(int from, int to, int count) {

        void executeWithCrateMover9000(List<List<Character>> stacks) {
            for (int i = 0; i < count && !stacks.get(from).isEmpty(); i++) {
                stacks.get(to).add(0, stacks.get(from).remove(0));
            }
        }

        void executeWithCrateMover9001(List<List<Character>> stacks) {
            List<Character> cratesToBeMoved = new ArrayList<>();
            for (int i = 0; i < count && !stacks.get(from).isEmpty(); i++) {
                cratesToBeMoved.add(stacks.get(from).remove(0));
            }
            stacks.get(to).addAll(0, cratesToBeMoved);
        }

        static Command parseCommand(String input) {
            String[] splits = input.split("\\s+");
            return new Command(Integer.parseInt(splits[3]) - 1,
                    Integer.parseInt(splits[5]) - 1,
                    Integer.parseInt(splits[1]));
        }
    }
}
