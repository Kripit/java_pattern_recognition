//Problem 4: Subarray Sum Equals K — Difficulty: Medium (combines hashing + prefix sums from Module 2)
//Problem

//Given an array and integer k, find the total number of contiguous subarrays whose sum equals k.

//What should I notice?

//“Contiguous subarray sum” → prefix sums (Module 2). But now we need to count how many pairs of prefix sums differ by exactly k — that “how many have I seen” question is a HashMap storing prefix sum → count of times seen

import java.util.*;

public class SubarraySumOptimal {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};

        int k = 2;
        System.out.println("Total subarrays: " + subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {

        // Key = prefix sum
        // Value = list of indices where that prefix sum occurred
        Map<Integer, List<Integer>> prefixCount = new HashMap<>();

        // Prefix sum 0 occurred before the array started.
        // Index -1 represents the imaginary position before index 0.
        // This helps us print subarrays starting at index 0.
        prefixCount.put(0, new ArrayList<>(List.of(-1)));

        int currentSum = 0;
        int count = 0;

        // i tells us the index of the current number
        for (int i = 0; i < nums.length; i++) {

            // STEP 1: Add the current number to the running sum
            currentSum += nums[i];

            // STEP 2: Calculate the prefix sum we need to find
            int needed = currentSum - k;

            // STEP 3: Check whether the needed prefix sum exists
            if (prefixCount.containsKey(needed)) {

                // Get every earlier index where this prefix sum occurred
                for (int prefixIndex : prefixCount.get(needed)) {

                    // The subarray starts just after the earlier prefix
                    int start = prefixIndex + 1;

                    // The subarray ends at the current index
                    int end = i;

                    // We found one valid subarray
                    count++;

                    // STEP 4: Print its starting index, ending index and values
                    System.out.println(
                        "Start index: " + start
                        + ", End index: " + end
                        + ", Values: "
                        + Arrays.toString(
                            Arrays.copyOfRange(nums, start, end + 1)
                        )
                    );
                }
            }

            // STEP 5: Save the current prefix sum and its index
            // Do this AFTER checking for matches.
            prefixCount
                .computeIfAbsent(currentSum, x -> new ArrayList<>())
                .add(i);
        }

        // Return the total number of valid subarrays
        return count;
    }
}



// nums = [1, 1, 1], k = 2
// prefixCount = {0: 1}   (baseline: "sum of 0" occurred once, before we start)
// currentSum = 0, count = 0
//
// num=1:
//   currentSum = 0 + 1 = 1
//   needed = currentSum - k = 1 - 2 = -1
//   prefixCount.get(-1)? doesn't exist → contributes 0 → count stays 0
//   record: prefixCount = {0:1, 1:1}
//
// num=1:
//   currentSum = 1 + 1 = 2
//   needed = currentSum - k = 2 - 2 = 0
//   prefixCount.get(0)? YES, value=1 → count += 1 → count=1
//   (this found the subarray [1,1] from index 0 to 1, which sums to 2 ✓)
//   record: prefixCount = {0:1, 1:1, 2:1}
//
// num=1:
//   currentSum = 2 + 1 = 3
//   needed = currentSum - k = 3 - 2 = 1
//   prefixCount.get(1)? YES, value=1 → count += 1 → count=2
//   (this found the subarray [1,1] from index 1 to 2, sums to 2 ✓)
//   record: prefixCount = {0:1, 1:1, 2:1, 3:1}
//
// Final: count = 2 ✓ (matches expected answer)
//
// Edge Cases
// k = 0 with an array containing a 0 → the prefixCount.put(0, 1) baseline correctly catches subarrays that sum to zero starting from index 0.
// Negative numbers in the array → works correctly, since prefix sums (and needed) can legitimately go negative; HashMap handles negative keys just fine.
// No valid subarray exists → count stays 0 throughout.
// Complexity
// text
// Time: O(n) — single pass
// Space: O(n) — HashMap can hold up to n distinct prefix sums
// Pattern
//
// “Count subarrays matching a sum condition” → prefix sum + HashMap storing (prefix sum → frequency), always seed the map with {0: 1} for the baseline case.