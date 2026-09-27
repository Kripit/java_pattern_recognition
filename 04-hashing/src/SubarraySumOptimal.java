
//Problem 4: Subarray Sum Equals K — Difficulty: Medium (combines hashing + prefix sums from Module 2)

//Given an array and integer k, find the total number of contiguous subarrays whose sum equals k.

//What should I notice?

//"Contiguous subarray sum" → prefix sums (Module 2). But now we need to count how many pairs of prefix sums differ by exactly k — that "how many have I seen" question is a HashMap storing prefix sum → count of times seen.




import java.util.*;

public class SubarraySumOptimal {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(subarraySum(nums, k)); // 2
    }

    public static int subarraySum(int[] nums, int k) {
        // map: prefixSum value -> how many times that exact sum has occurred so far
        Map<Integer, Integer> prefixCount = new HashMap<>();

        // CRITICAL: a prefix sum of 0 has occurred once "before the array starts"
        // this handles the case where a subarray STARTING AT INDEX 0 itself sums to k
        prefixCount.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num; // extend running prefix sum by current element

            // does a prefix sum of (currentSum - k) exist from some earlier point?
            // each occurrence represents one valid subarray ending at this position
            int needed = currentSum - k;
            count += prefixCount.getOrDefault(needed, 0);

            // record that currentSum has now occurred one more time
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}

//nums = [1, 1, 1], k = 2
//prefixCount = {0: 1}   (baseline: "sum of 0" occurred once, before we start)
//currentSum = 0, count = 0

//num=1:
  //currentSum = 0 + 1 = 1
 // needed = currentSum - k = 1 - 2 = -1
//  prefixCount.get(-1)? doesn't exist → contributes 0 → count stays 0
//  record: prefixCount = {0:1, 1:1}

//num=1:
//  currentSum = 1 + 1 = 2
 // needed = currentSum - k = 2 - 2 = 0
  //prefixCount.get(0)? YES, value=1 → count += 1 → count=1
 // (this found the subarray [1,1] from index 0 to 1, which sums to 2 ✓)
 // record: prefixCount = {0:1, 1:1, 2:1}

//num=1:
 // currentSum = 2 + 1 = 3
 // needed = currentSum - k = 3 - 2 = 1
 // prefixCount.get(1)? YES, value=1 → count += 1 → count=2
 // (this found the subarray [1,1] from index 1 to 2, sums to 2 ✓)
  //record: prefixCount = {0:1, 1:1, 2:1, 3:1}

//Final: count = 2 ✓ (matches expected answer)



//Pattern

//"Count subarrays matching a sum condition" → prefix sum + HashMap storing (prefix sum → frequency), always seed the map with {0: 1} for the baseline case.



//value → seen before?           → HashSet (Contains Duplicate)
//alue → canonical group key    → HashMap<key, List> (Group Anagrams)
//value → does neighbor exist?   → HashSet, smart start-check (Longest Consecutive)
//running sum → seen before?     → HashMap<sum, count> (Subarray Sum K)