package net.mirwaldt.aoc.year2015.day08;

public class SubstringsIteratingCharCounter implements CharCounter {
    @Override
    public int countStringCharacters(String input) {
        if(input.isEmpty()) {
            return 0;
        } else {
            final String noLeadingNorTrailingDoubleQuotes = input.substring(1, input.length()-1);
            return new StringCharsCounter(noLeadingNorTrailingDoubleQuotes).countChars();
        }
    }
}
