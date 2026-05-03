package robertcinciuc.problems.leetcode.string;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); ++i){
            if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') ||
                    (s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                sb.append(String.valueOf(s.charAt(i)).toLowerCase());
            }
        }
        int mid = sb.length() / 2 - 1;
        for(int i = 0; i <= mid; ++i){
            if(sb.charAt(i) != sb.charAt(sb.length() - i - 1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
