package robertcinciuc.problems.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int start = 0;
        int end = 0;
        Set<String> seenElems = new HashSet<>();
        while(end < s.length()) {
            String elem = s.substring(end, end + 1);
            if (seenElems.contains(elem)) {
                seenElems.remove(s.substring(start, start + 1));
                start++;
            } else {
                seenElems.add(elem);
                end++;
                if (seenElems.size() > maxLength) {
                    maxLength = seenElems.size();
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
    }
}
