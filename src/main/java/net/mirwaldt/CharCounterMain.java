package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CharCounterMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final CharCounter charCounter = new DefaultCharCounter();
        long codeCharsCount = 0;
        long stringCharsCount = 0;
        for (String line : lines) {
            codeCharsCount += charCounter.countCodeCharacters(line);
            stringCharsCount += charCounter.countStringCharacters(line);
        }
        final long difference = codeCharsCount - stringCharsCount;
        System.out.println(difference); // result - 1371
    }
}
