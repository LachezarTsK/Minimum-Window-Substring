
public class Solution {

    private static final String SUBSTRING_NOT_FOUND = "";

    public String minWindow(String source, String target) {
        int[] targetFrequency = new int['z' - 'A' + 1];
        for (int i = 0; i < target.length(); ++i) {
            ++targetFrequency[target.charAt(i) - 'A'];
        }

        int minWindowSize = Integer.MAX_VALUE;
        int startIndexMinWindow = 0;
        int countMatches = 0;
        int left = 0;
        int right = 0;

        while (right < source.length()) {
            if (--targetFrequency[source.charAt(right) - 'A'] >= 0) {
                ++countMatches;
            }

            if (countMatches == target.length()) {
                if (minWindowSize > right - left + 1) {
                    minWindowSize = right - left + 1;
                    startIndexMinWindow = left;
                }
                ++targetFrequency[source.charAt(left) - 'A'];
                ++left;
                --countMatches;
            }

            while (left <= right && targetFrequency[source.charAt(left) - 'A'] < 0) {
                ++targetFrequency[source.charAt(left) - 'A'];
                ++left;
            }
            ++right;
        }

        return minWindowSize < Integer.MAX_VALUE
                ? source.substring(startIndexMinWindow, startIndexMinWindow + minWindowSize)
                : SUBSTRING_NOT_FOUND;
    }
}
