package net.mirwaldt.aoc.year2015.day08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CharCounterMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final CharCounter charCounter = new ReplacingCharCounter();

        long codeCharsCount = 0;
        long stringCharsCount = 0;
        for (String line : lines) {
            codeCharsCount += charCounter.countCodeCharacters(line);
            stringCharsCount += charCounter.countStringCharacters(line);
        }
        final long partOneDifference = codeCharsCount - stringCharsCount;
        System.out.println(partOneDifference); // result - 1371

        long codeCharsCountOfEncodedString = 0;
        for (String line : lines) {
            final String escapedLine = escape(line);
            codeCharsCountOfEncodedString += charCounter.countCodeCharacters(escapedLine);
        }
        final long partTwoDifference = codeCharsCountOfEncodedString - codeCharsCount;
        System.out.println(partTwoDifference);  // result - 2117
    }

    public static String escape(String line) {
        /*
         * We first mark all escape sequences and then escape them then again to avoid confusion.
         * We can do that because numbers only occur after \x
         * in hexadecimal notation and nowhere else - and that's our luck!
         */
        return "\"" + line.replace("\\\\", "\\0")
                .replace("\\x", "\\1")
                .replace("\\\"","\\2")
                .replace("\"","\\3")
                .replace("\\0", "\\\\" + "\\\\")
                .replace("\\1", "\\" + "\\x")
                .replace("\\2", "\\\\\\\"")
                .replace("\\3", "\\\"")
                + "\"";
    }
}
