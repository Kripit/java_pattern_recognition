//Problem 2: Group Anagrams — Difficulty: Medium

//Group an array of strings so all anagrams of each other end up in the same group.

//What should I notice?

//Anagrams share the same sorted form (e.g., "eat" and "tea" both sort to "aet"). That sorted string can act as a shared key — perfect for a HashMap where the key groups multiple values into a list.

//Optimized Idea

//Sort each string's characters to get a canonical key ("eat" → "aet", "tea" → "aet" — same key!). Use that key in a HashMap where the value is the list of all strings matching that key
import java.util.*;

public class GroupAnagramsOptimal {
    public static void main(String[] args){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
    
    public static List<List<String>> groupAnagrams(String[] strs){

        // key = sorted version of a string, value = list of all original strings

        Map<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            //convert string to char array so we can sort it
            char[] chars = s.toCharArray();
            Arrays.sort(chars);//sorts char alphabetically "eat" -> ['a','e','t']
            String key = new String(chars);// convert back to a string"aet"

            map.computeIfAbsent(key, k-> new ArrayList<>()).add(s);


        }
        return new ArrayList<>(map.values());

    }
}
//How this works internally — step by step

//map.computeIfAbsent(key, k -> new ArrayList<>()) is doing two jobs in one line:

//Check: does key already exist in the map?
//If NO: run the lambda k -> new ArrayList<>() to create a fresh empty list, insert it under key, and return that new list.
//If YES: skip creation entirely, just return the existing list already stored under key
//Then .add(s) appends the current string onto whatever list got returned — either brand new or already-existing.

//Edge Cases
//Empty string in the array → sorts to "", becomes its own key, groups correctly with other empty strings.
//All strings are anagrams of each other → everything collapses into one single group.
//No two strings are anagrams → every string becomes its own group of size 1.

// how ts works ->

//map = {} (empty)

//s="eat" → sorted chars="aet" → key="aet"
  //map has "aet"? NO → create new ArrayList → map={"aet":[]}
 // add "eat" → map={"aet":["eat"]}

//s="tea" → sorted chars="aet" → key="aet"
 // map has "aet"? YES → reuse existing list, no new list created
 // add "tea" → map={"aet":["eat","tea"]}

//s="tan" → sorted chars="ant" → key="ant"
 // map has "ant"? NO → create new ArrayList → map={"aet":[...], "ant":[]}
 // add "tan" → map={"aet":["eat","tea"], "ant":["tan"]}

//s="ate" → sorted="aet" → key="aet" → EXISTS → add "ate"
//  map={"aet":["eat","tea","ate"], "ant":["tan"]}

//s="nat" → sorted="ant" → key="ant" → EXISTS → add "nat"
 // map={"aet":["eat","tea","ate"], "ant":["tan","nat"]}

//s="bat" → sorted="abt" → key="abt" → NEW → add "bat"
 // map={"aet":["eat","tea","ate"], "ant":["tan","nat"], "abt":["bat"]}

//Final: map.values() = [["eat","tea","ate"], ["tan","nat"], ["bat"]]

//Complexity
//text
//Time: O(n × k log k) — n strings, each needs O(k log k) to sort its characters
//Space: O(n × k) — storing all strings across all groups
//Pattern

//"Group items that share some transformed property" → HashMap where the key is a computed canonical form (sorted string, normalized value, etc.), value is a list.
