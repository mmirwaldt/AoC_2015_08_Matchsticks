package net.mirwaldt.aoc.year2015.day08;

public interface CharCounter {
    default int countCodeCharacters(String input) {
        return input.length();
    }
    int countStringCharacters(String input);
}
