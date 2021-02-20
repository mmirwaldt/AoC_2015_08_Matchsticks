package net.mirwaldt;


public class StringCharsCounter {
    private final String noLeadingNorTrailingDoubleQuotes;
    private final int length;
    private int index = 0;
    private int stringCharCounter = 0;

    public StringCharsCounter(String noLeadingNorTrailingDoubleQuotes) {
        this.noLeadingNorTrailingDoubleQuotes = noLeadingNorTrailingDoubleQuotes;
        this.length = noLeadingNorTrailingDoubleQuotes.length();
    }

    public int countChars() {
        while(index < length) {
            countChar();
        }
        return stringCharCounter;
    }

    private void countChar() {
        final String charString = noLeadingNorTrailingDoubleQuotes.substring(index, index+1);
        if(isStartOfEscapeChar(charString)) {
            countEscaped();
        } else {
            countNonEscapeChar();
        }
    }

    private void countEscaped() {
        if(index + 1 < length) {
            final String escapeSequence = noLeadingNorTrailingDoubleQuotes.substring(index, index+2);
            if(isEscapedBackSlash(escapeSequence) || isEscapedDoubleQuote(escapeSequence)) {
                countEscapedBackSlashOrDoubleQuote();
            } else if(isStartOfEscapedChar(escapeSequence) && index + 3 < length) {
                countEscapedChar();
            } else {
                rejectArgument();
            }
        } else {
            rejectArgument();
        }
    }

    private void rejectArgument() {
        throw new IllegalArgumentException("Corrupt escape sequence in '"
                + noLeadingNorTrailingDoubleQuotes + "' at char index " + index);
    }

    private void countEscapedChar() {
        stringCharCounter++;
        index+=4;
    }

    private void countEscapedBackSlashOrDoubleQuote() {
        stringCharCounter++;
        index+=2;
    }

    private void countNonEscapeChar() {
        stringCharCounter++;
        index++;
    }

    private boolean isStartOfEscapedChar(String escapeSequence) {
        return escapeSequence.equals("\\x");
    }

    private boolean isEscapedDoubleQuote(String escapeSequence) {
        return escapeSequence.equals("\\\"");
    }

    private boolean isEscapedBackSlash(String escapeSequence) {
        return escapeSequence.equals("\\\\");
    }

    private boolean isStartOfEscapeChar(String charString) {
        return charString.equals("\\");
    }
}