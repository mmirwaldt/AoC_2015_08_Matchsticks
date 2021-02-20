package net.mirwaldt;

public class DefaultCharCounter implements CharCounter {
    @Override
    public int countCodeCharacters(String input) {
        return input.length();
    }

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
