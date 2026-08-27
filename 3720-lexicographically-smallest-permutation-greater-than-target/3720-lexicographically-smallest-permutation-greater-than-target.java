class Solution {
    public String lexGreaterPermutation(String s, String target) {
        
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {
            int curr = target.charAt(i) - 'a';

            // Try to match target
            if (freq[curr] > 0) {
                freq[curr]--;
                ans.append(target.charAt(i));
            } else {
                // Cannot match, try a greater character
                int greater = findGreater(freq, curr);

                if (greater != -1) {
                    ans.append((char)('a' + greater));
                    freq[greater]--;

                    appendSmallest(ans, freq);
                    return ans.toString();
                }

                // Backtrack
                return backtrack(ans, freq);
            }
        }

        // We matched the entire target exactly.
        // Need to backtrack because answer must be STRICTLY greater.
        return backtrack(ans, freq);
    }

    private String backtrack(StringBuilder ans, int[] freq) {

        while (ans.length() > 0) {

            int last = ans.length() - 1;
            int previous = ans.charAt(last) - 'a';

            ans.deleteCharAt(last);
            freq[previous]++;

            int greater = findGreater(freq, previous);

            if (greater != -1) {
                ans.append((char)('a' + greater));
                freq[greater]--;

                appendSmallest(ans, freq);

                return ans.toString();
            }
        }

        return "";
    }

    private int findGreater(int[] freq, int curr) {
        for (int i = curr + 1; i < 26; i++) {
            if (freq[i] > 0) {
                return i;
            }
        }

        return -1;
    }

    private void appendSmallest(StringBuilder ans, int[] freq) {
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                ans.append((char)('a' + i));
                freq[i]--;
            }
        }
    }
}