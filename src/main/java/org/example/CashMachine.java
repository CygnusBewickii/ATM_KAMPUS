package org.example;

import java.util.*;

public class CashMachine {

    private Map<String, Map<Integer, Integer>> cash;

    public CashMachine() {
        this.cash = new HashMap<>();
    }

    public String execute(String command) {
        String[] commandParts = command.split(" ");

        switch (commandParts[0]) {
            case "+":
                return addCash(commandParts[1], Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[3]));
            case "-":
                return getCash(commandParts[1], Integer.parseInt(commandParts[2]));
            case "?":
                return printCash();
            default:
                return "ERROR";
        }
    }

    private String addCash(String currency, int value, int number) {
        if (isCurrencyValid(currency) && isValueValid(value) && isNumberValid(number)) {
            cash.putIfAbsent(currency, new HashMap<>());
            Map<Integer, Integer> currentCurrencyMap = cash.get(currency);
            currentCurrencyMap.put(value, currentCurrencyMap.getOrDefault(value, 0) + number);
            return "OK";
        }
        return "ERROR";
    }

    private String getCash(String currency, int amount) {
        if (!cash.containsKey(currency)) {
            return "ERROR";
        }

        Map<Integer, Integer> currentCurrencyMap = cash.get(currency);
        List<Integer> values = new ArrayList<>(currentCurrencyMap.keySet());
        Collections.sort(values, Collections.reverseOrder());

        Map<Integer, Integer> result = new LinkedHashMap<>();

        int remainingAmount = amount;
        for (int val : values) {
            int numNotes = Math.min(remainingAmount / val, currentCurrencyMap.get(val));
            if (numNotes > 0) {
                result.put(val, numNotes);
                remainingAmount -= val * numNotes;
            }
        }

        if (remainingAmount != 0) {
            return "ERROR";
        }

        result.forEach((key, value) -> currentCurrencyMap.put(key, currentCurrencyMap.get(key) - value));
        return result.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("");
    }

    private String printCash() {
        StringBuilder stringBuilder = new StringBuilder();
        cash.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    entry.getValue().entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .forEach(notes ->
                                    stringBuilder.append(entry.getKey()).append(" ")
                                            .append(notes.getKey()).append(" ")
                                            .append(notes.getValue()).append("\n")
                            );
                });
        return stringBuilder.append("OK").toString();
    }

    private boolean isCurrencyValid(String currency) {
        if (currency.length() == 3 && currency.toUpperCase().equals(currency)) {
            return true;
        }
        return false;
    }

    private boolean isValueValid(int value) {
        for (int n = 0; n <= 3; n++) {
            if (Math.pow(10, n) == value || Math.pow(10, n) * 5 == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberValid(int number) {
        return number > 0;
    }
}
