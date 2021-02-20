package net.mirwaldt;

public class ReplacingCharCounter implements CharCounter {
    @Override
    public int countStringCharacters(String input) {
        if (input.isEmpty()) {
            return 0;
        } else {
            final String escapeSequencesReplaced = input.substring(1, input.length()-1)
                    .replace("\\\\", "X")
                    .replace("\\\"", "X")
                    .replaceAll("\\\\x..", "X");
            final int index = escapeSequencesReplaced.indexOf("\\");
            if(-1 < index) {
                throw new IllegalArgumentException("Corrupt escape sequence in '"
                        + escapeSequencesReplaced + "' at char index " + index);
            } else {
                return escapeSequencesReplaced.length();
            }
        }
    }
}
