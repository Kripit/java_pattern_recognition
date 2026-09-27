//Problem 3: Longest Consecutive Sequence — Difficulty: Medium (very popular, deceptively tricky)


//Given an unsorted array, find the length of the longest run of consecutive integers (e.g., [100, 4, 200, 1, 3, 2] → the sequence 1,2,3,4 has length 4).

//What should I notice?

//Naive approach is "sort then scan" (O(n log n)) — but this problem specifically wants O(n), which is only possible with hashing: put everything in a HashSet for O(1) "does this number exist?" checks, and cleverly avoid rechecking the same sequence multiple times.





import java.util.*;



public class LongestConsecutiveOptimal {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums)); // 4
    }

    public static int longestConsecutive(int[] nums) {
        // dump every number into a HashSet for O(1) existence checks
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (int num : numSet) {
            // only START counting if 'num' is the BEGINNING of a sequence
            // (i.e., num-1 is NOT in the set — so num can't be extended backward)
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // keep extending forward as long as the next number exists in the set
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longest = Math.max(longest, currentStreak);
            }
            // if num-1 DOES exist, we skip — some earlier iteration (or a future one,
            // doesn't matter, order is irrelevant) starting from the TRUE beginning
            // will already count this whole sequence
        }

        return longest;
    }
}


//nums = [100, 4, 200, 1, 3, 2]
//numSet = {100, 4, 200, 1, 3, 2}   (HashSet, order doesn't matter for us)

//Iterating over numSet (order may vary, let's say): 100, 4, 200, 1, 3, 2

//num=100:
//  contains(99)? NO → 100 IS a sequence start
 // currentNum=100, streak=1
 // contains(101)? NO → stop extending
//  longest = max(0, 1) = 1

//num=4:
 /// contains(3)? YES → 4 is NOT a sequence start (it's in the middle of 1,2,3,4)
 // → SKIP entirely, no counting work done here at all

///num=200:
 // contains(199)? NO → 200 IS a sequence start
  //currentNum=200, streak=1
 // contains(201)? NO → stop extending
 // longest = max(1, 1) = 1

//num=1:
 // contains(0)? NO → 1 IS a sequence start
 // currentNum=1, streak=1
  //contains(2)? YES → currentNum=2, streak=2
 // contains(3)? YES → currentNum=3, streak=3
 // contains(4)? YES → currentNum=4, streak=4
 // contains(5)? NO → stop extending
 // longest = max(1, 4) = 4

//num=3:
  //contains(2)? YES → 3 is NOT a sequence start → SKIP

//num=2:
 // contains(1)? YES → 2 is NOT a sequence start → SKIP

//Final: longest = 4




//Edge Cases
//Empty array → numSet empty, for-loop never runs, returns 0 (the longest = 0 initial value).
//All duplicate values (e.g. [5,5,5]) → HashSet collapses them to {5}, streak length correctly comes out as 1.
//Already sorted, no gaps → whole array is one sequence, longest = nums.length.
//Complexity

//Time: O(n) — every number is visited by the inner while loop at most once total
//Space: O(n) — the HashSet
//Pattern

//"Longest run/streak in unsorted data" → HashSet + only start counting from true sequence starts (check num - 1 doesn't exist before expanding forward).