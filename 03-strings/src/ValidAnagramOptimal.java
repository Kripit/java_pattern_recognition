//Problem 3: Valid Anagram — Difficulty: Easy Check if two strings are anagrams (same characters, same frequency, different order allowed).
//What should I notice?

//"Same characters, same counts" → frequency counting. Since we know the input is lowercase letters, a frequency array (26 slots) is even faster than a HashMap here.

//Optimized Idea

//Use a frequency array: increment for characters in s, decrement for characters in t. If they're true anagrams, every count returns to exactly 0.
public class ValidAnagramOptimal {
    public static void main(String[] args){
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram( String s, String t){
        if(s.length() != t.length()) return false;

        int[] count = new int[26];

        for(int i = 0; i<s.length(); i++){
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) -'a']--;

        }
        for(int c: count){
            if(c!=0) return false;
        }
        return true;
    }
    
}

//s="ana", t="naa" (small example for clarity)
//count[26] all 0

//i=0: s[0]='a' → count['a'-'a']=count[0]++ → count[0]=1
  //   t[0]='n' → count['n'-'a']=count[13]-- → count[13]=-1
//i=1: s[1]='n' → count[13]++ → count[13]=0
  //   t[1]='a' → count[0]-- → count[0]=0
//i=2: s[2]='a' → count[0]++ → count[0]=1
  //   t[2]='a' → count[0]-- → count[0]=0

//Final check: all counts are 0 → return true ✓
//Edge Cases
//Different lengths → caught immediately by the early-exit check.
//Empty strings → both length 0, loop doesn't run, all counts stay 0 → returns true correctly.
//Complexity
//text
//Time: O(n), Space: O(1) — 26 slots is a constant, doesn't grow with input
//Pattern

//"Same characters/counts, different order" → think frequency array/HashMap, increment for one, decrement for the other.