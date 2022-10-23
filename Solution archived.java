public class Solution {

    public final int ascii_z = 122;
    int[] requiredFrequency;

    public String minWindow(String s, String t) {

        initialize_requiredFrequency(t);
        int[] actualFrequency = new int[ascii_z + 1];

        int minSubstring = s.length() + 1;
        int left_minSubstring = 0;
        int right_minSubstring = 0;

        int left = 0;
        int right = 0;
        int countMatches = 0;

        while (right < s.length()) {

            char ch = s.charAt(right);
            if (actualFrequency[ch] < requiredFrequency[ch]) {// expanding the window.
                countMatches++;
            }
            actualFrequency[ch]++;

            while (countMatches == t.length()) {//contracting the window.

                ch = s.charAt(left);
                if (minSubstring > right - left + 1) {
                    minSubstring = right - left + 1;
                    left_minSubstring = left;
                    right_minSubstring = right;
                }

                if (--actualFrequency[ch] < requiredFrequency[ch]) {
                    countMatches--;
                }
                left++;
            }
            right++;
        }
        return minSubstring == s.length() + 1 ? "" : s.substring(left_minSubstring, right_minSubstring + 1);
    }

    public void initialize_requiredFrequency(String t) {
        requiredFrequency = new int[ascii_z + 1];
        for (int i = 0; i < t.length(); i++) {
            requiredFrequency[t.charAt(i)]++;
        }
    }
}
