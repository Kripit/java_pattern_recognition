//Problem 2: Valid Palindrome — Difficulty: Easy

//Check if a string reads the same forwards and backwards, ignoring case and non-alphanumeric characters.

//What should I notice?

//Palindrome check = comparing from both ends toward the middle → two pointers. The extra twist: skip characters that aren't letters/digits.


public class ValidPalindromeOptimal {

    public static void main(String [] args){
        String s = " A man, A plan, A canal: panama";
        System.out.println(isPalindrome(s));

    }
    public static boolean isPalindrome(String s){
        int left = 0;
        int right = s.length()-1;

        while(left<right){
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left))!= Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

//s = "A man, a plan, a canal: Panama"
//left=0('A'), right=30('a')
//'A' vs 'a' → toLowerCase both → 'a'=='a' ✓ → left=1, right=29

//left=1(' ')→ not alnum, skip → left=2('m')
//right=29('m') → alnum
//'m' vs 'm' ✓ → left=3, right=28
//... continues matching all the way through ...
//Eventually left meets/crosses right → return true

//Pattern

//"Palindrome check" → two pointers, opposite ends, skip/normalize as needed.