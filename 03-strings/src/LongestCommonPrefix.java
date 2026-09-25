//Problem 5: Longest Common Prefix — Difficulty: Easy

//Find the longest string prefix shared by all strings in an array.

//What should I notice?

//Compare character-by-character across all strings at the same position — stop the moment any string disagrees or runs out of characters.


public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs)); // "fl"
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return ""; // no strings, no prefix

        // start by assuming the entire first string is the common prefix
        String prefix = strs[0];

        // compare 'prefix' against every other string, shrinking it as needed
        for (int i = 1; i < strs.length; i++) {
            // shrink 'prefix' until it's actually a prefix of strs[i]
            while (!strs[i].startsWith(prefix)) {
                // remove the last character and try again
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; // no common prefix exists at all
            }
        }

        return prefix;
    }
}
//strs = ["flower", "flow", "flight"]
//prefix = "flower" (start as first string)

//i=1: strs[1]="flow"
//  "flow".startsWith("flower")? NO → prefix="flowe"
//  "flow".startsWith("flowe")? NO → prefix="flow"
// "flow".startsWith("flow")? YES → stop shrinking

//i=2: strs[2]="flight"
 // "flight".startsWith("flow")? NO → prefix="flo"
 // "flight".startsWith("flo")? NO → prefix="fl"
 // "flight".startsWith("fl")? YES → stop shrinking

//Final prefix = "fl" ✓

//Pattern

//"Shared prefix/property across multiple strings" → compare against a shrinking candidate, or compare character-by-character across all strings at the same index.