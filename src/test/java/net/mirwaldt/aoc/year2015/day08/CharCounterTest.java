package net.mirwaldt.aoc.year2015.day08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharCounterTest {
    public static final String ESCAPED_BACKSLASH = "\\\\";
    public static final String ESCAPED_DOUBLE_QUOTE = "\\\"";

    private static Stream<Arguments> charCounter() {
        return Stream.of(Arguments.of(new SubstringsIteratingCharCounter()), Arguments.of(new ReplacingCharCounter()));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_emptyCode(CharCounter charCounter) {
        final String input = "";
        assertEquals(0, charCounter.countCodeCharacters(input));
        assertEquals(0, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_invalid_onlySlash(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("\\");
        assertEquals(3, charCounter.countCodeCharacters(input));
        assertThrows(IllegalArgumentException.class, () -> charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_invalid_onlySlashAndLowerCaseX(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("\\x");
        assertEquals(4, charCounter.countCodeCharacters(input));
        assertThrows(IllegalArgumentException.class, () -> charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_emptyString(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("");
        assertEquals(2, charCounter.countCodeCharacters(input));
        assertEquals(0, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_oneLetter(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("a");
        assertEquals(3, charCounter.countCodeCharacters(input));
        assertEquals(1, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_threeLetters(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("abc");
        assertEquals(5, charCounter.countCodeCharacters(input));
        assertEquals(3, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_oneEscapedBackSlash(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes(ESCAPED_BACKSLASH);
        assertEquals(4, charCounter.countCodeCharacters(input));
        assertEquals(1, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_twoEscapedBackSlashes(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes(ESCAPED_BACKSLASH + ESCAPED_BACKSLASH);
        assertEquals(6, charCounter.countCodeCharacters(input));
        assertEquals(2, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_oneEscapedDoubleQuote(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes(ESCAPED_DOUBLE_QUOTE);
        assertEquals(4, charCounter.countCodeCharacters(input));
        assertEquals(1, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_twoEscapedDoubleQuotes(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes(ESCAPED_DOUBLE_QUOTE + ESCAPED_DOUBLE_QUOTE);
        assertEquals(6, charCounter.countCodeCharacters(input));
        assertEquals(2, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_oneEscapedChar(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("\\x10");
        assertEquals(6, charCounter.countCodeCharacters(input));
        assertEquals(1, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_twoEscapedChars(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("\\x10\\x13");
        assertEquals(10, charCounter.countCodeCharacters(input));
        assertEquals(2, charCounter.countStringCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("charCounter")
    void test_oneLetter_oneEscapedBackSlash_oneEscapedChar_oneEscapedDoubleQuote(CharCounter charCounter) {
        final String input = surroundWithDoubleQuotes("a" + ESCAPED_BACKSLASH + "\\x10" + ESCAPED_DOUBLE_QUOTE);
        assertEquals(11, charCounter.countCodeCharacters(input));
        assertEquals(4, charCounter.countStringCharacters(input));
    }

    private String surroundWithDoubleQuotes(String input) {
        return "\"" + input + "\"";
    }
}
