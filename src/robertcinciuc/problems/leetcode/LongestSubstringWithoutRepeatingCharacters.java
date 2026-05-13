package robertcinciuc.problems.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); ++i) {
            Set<String> seenChar = new HashSet<>();
            int j = i;
            while(j < s.length()) {
                String elem = s.substring(j, j + 1);
                if (!seenChar.contains(elem)) {
                    seenChar.add(elem);
                    if (seenChar.size() > maxLength) {
                        maxLength = seenChar.size();
                    }
                } else {
                    break;
                }
                j++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
    }
}
