package net.mirwaldt;

public interface CharCounter {
    default int countCodeCharacters(String input) {
        return input.length();
    }
    int countStringCharacters(String input);
}
