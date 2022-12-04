package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        findTotalPriorityForRucksackErrors(reader);
        findTotalPriorityForCommonItemInRucksackGroup(reader);
    }

    private static void findTotalPriorityForCommonItemInRucksackGroup(BufferedReader reader) throws IOException {
        String ruckSack;
        int totalPriority = 0;
        while (!(ruckSack = reader.readLine()).isEmpty()) {
            char commonItem = findCommonItemInRucksacks(List.of(ruckSack, reader.readLine(), reader.readLine()));
            totalPriority += getPriorityForItem(commonItem);
        }
        System.out.println(totalPriority);
    }

    private static char findCommonItemInRucksacks(List<String> ruckSacks) {
        Map<Character, Set<Integer>> characterToRuckSackIndexMapping = new HashMap<>();
        for (int i = 0; i < ruckSacks.size(); i++) {
            for (char c : ruckSacks.get(i).toCharArray()) {
                characterToRuckSackIndexMapping.computeIfAbsent(c, k -> new HashSet<>()).add(i);
            }
        }
        return characterToRuckSackIndexMapping.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == ruckSacks.size())
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(' ');
    }

    private static void findTotalPriorityForRucksackErrors(BufferedReader reader) throws IOException {
        String rucksack;
        int totalPriority = 0;
        while (!(rucksack = reader.readLine()).isEmpty()) {
            char item = getCommonItem(rucksack);
            totalPriority += getPriorityForItem(item);
        }
        System.out.println(totalPriority);
    }

    private static int getPriorityForItem(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        }
        return item - 'A' + 27;
    }

    private static char getCommonItem(String rucksack) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < rucksack.length() / 2; i++) {
            set.add(rucksack.charAt(i));
        }
        for (int i = rucksack.length() / 2; i < rucksack.length(); i++) {
            if (set.contains(rucksack.charAt(i))) {
                return rucksack.charAt(i);
            }
        }
        return ' ';
    }
}
