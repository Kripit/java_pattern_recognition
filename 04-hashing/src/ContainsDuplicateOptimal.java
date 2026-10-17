//Problem 1: Contains Duplicate — Difficulty: Easy


//Given an array, return true if any value appears at least twice.

//What should I notice?

//"Has this been seen before?" is the single clearest signal for a HashSet — you don't need counts, just existence.

//Optimized Idea

import java.util.*;

public class ContainsDuplicateOptimal{
    public static void main(String[] args){
        int[] nums = {1,2,3,1};
        System.out.println(hasDuplicate(nums));
    }

    public static boolean hasDuplicate(int[] nums){

        Set<Integer> seen = new HashSet<>();
        // HashSet - stores value we've already seen, no duplicates allowed inside
        for(int num: nums){
            if(!seen.add(num)){
                return true;
            }
        }
        return false;
    }
}

//nums = [1, 2, 3, 1]
//seen={} → add(1)→true → seen={1}
//seen={1} → add(2)→true → seen={1,2}
//seen={1,2} → add(3)→true → seen={1,2,3}
//seen={1,2,3} → add(1)→FALSE (1 already there) → return true

//Time: O(n) — one pass, O(1) average per add()
//Space: O(n) — worst case, set holds every element



//Pattern

//“Has this been seen before?” → HashSet, and remember add()’s boolean return value can replace a separate contains() check.