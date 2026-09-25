
//Problem 4: First Unique Character — Difficulty: Easy


//Find the index of the first character in a string that doesn't repeat.

//What should I notice?

//Need character counts first, then a second pass to find the first one with count == 1. Two-pass pattern using a frequency structure.


public class FirstUniqueChar {
    public static void main(String [] args){
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
    }

    public static int firstUniqChar(String s){
        int[] count = new int[26];

        for(int i=0; i<s.length() ; i++){
            count[s.charAt(i) - 'a']++;
        }
        for(int i=0; i<s.length() ; i++){
            if(count[s.charAt(i) -'a'] ==1 ){
                return i;
            }
        }
        return -1;
    }


}
//s = "leetcode"
//PASS 1 counts: l=1, e=3, t=1, c=1, o=1, d=1

//PASS 2:
//i=0: 'l' count=1 → return 0 ✓ (first match, stop immediately)
//Space: O(1) — fixed 26-slot array
//Pattern

//"Need to know full data before deciding" → two-pass approach: first pass gathers info (counts), second pass uses it.