//Problem 6: Two Sum — Difficulty: Easy ⭐ (extremely high interview frequency)

//Given an array and a target, return indices of the two numbers that add up to target.

//What should I notice?

//For each number, you need to know: "does target - currentNumber exist somewhere else in the array?" That "does X exist" question is a strong signal for HashMap — O(1) lookup instead of scanning.

//Optimized Idea

//Walk through the array once. For each number, check if its "partner" (target - num) is already in a HashMap. If not, store the current number for future elements to find.

import java.util.*;

public class TwoSumOptimal {
    public static void main(String [] args){

        int[] nums = {2,7,11,15};
        int target = 9;
// map stores: value -> index where it was seen
        Map<Integer, Integer> seen = new HashMap<>();

        for(int i =0; i< nums.length; i++){
            int needed = target - nums[i];

            if(seen.containsKey(needed)){

                System.out.println("[" + seen.get(needed) + ", "+ i + "]");
                break;
            }

            seen.put(nums[i], i); }
    }
}

//nums = [2, 7, 11, 15], target = 9
//seen = {}

//i=0: nums[0]=2, needed=9-2=7 → seen has 7? NO → seen={2:0}
//i=1: nums[1]=7, needed=9-7=2 → seen has 2? YES (at index 0) → answer=[0,1] ✓
//Edge Cases
//No valid pair exists → loop ends without printing anything (in real interview code, you'd return an empty array or throw an exception).
//Duplicate values (e.g. nums=[3,3], target=6) → works correctly because we check seen before adding current number, so the second 3 correctly finds the first 3.
//Pattern

//When you see "find a pair/complement that sums/matches to something" → think HashMap storing value → index.