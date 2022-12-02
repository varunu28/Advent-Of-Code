package aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        highestCalorieCarryingElf(reader);
        top3HighestCalorieCarryingElves(reader);
    }

    private static void top3HighestCalorieCarryingElves(BufferedReader reader) throws IOException {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int limit = 3;
        String input;
        while (!(input = reader.readLine()).isEmpty()) {
            int totalCaloriesWithCurrentElf = Integer.parseInt(input);
            while (!(input = reader.readLine()).isEmpty()) {
                totalCaloriesWithCurrentElf += Integer.parseInt(input);
            }
            priorityQueue.add(totalCaloriesWithCurrentElf);
            if (priorityQueue.size() > limit) {
                priorityQueue.poll();
            }
        }
        int totalCalories = 0;
        while (!priorityQueue.isEmpty()) {
            totalCalories += priorityQueue.poll();
        }
        System.out.println(totalCalories);
    }

    private static void highestCalorieCarryingElf(BufferedReader reader) throws IOException {
        String input;
        int maxCalories = 0;
        while (!(input = reader.readLine()).isEmpty()) {
            int totalCaloriesWithCurrentElf = Integer.parseInt(input);
            while (!(input = reader.readLine()).isEmpty()) {
                totalCaloriesWithCurrentElf += Integer.parseInt(input);
            }
            maxCalories = Math.max(maxCalories, totalCaloriesWithCurrentElf);
        }
        System.out.println(maxCalories);
    }
}
