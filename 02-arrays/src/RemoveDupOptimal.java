//Problem 4: Remove Duplicates from Sorted Array
//Given a sorted array, remove duplicates in-place so each element appears once, and return the new length.

//What should I notice?

//"Sorted" means all duplicates are adjacent — you never need to search far. This is a classic same-direction two pointers setup (slow/fast pointer).
import java.util.*;

public class RemoveDupOptimal{
    public static void main(String[] args){
        int[] nums = {1,1,2,2,3,3,4,4,5};

        if(nums.length == 0) return;

        int slow = 0;

        for (int fast = 1; fast<nums.length; fast++){
            if(nums[fast]!= nums[slow]){
                slow++;// move slow forward to the next free slot
                nums[slow] = nums[fast];// place the new unique value there
            }
        } // if nums[fast] == nums[slow], it's a duplicate — just skip it, do nothing
        int newlength = slow + 1;
        System.out.println("new length: "+ newlength);
        System.out.println(Arrays.toString(Arrays.copyOf(nums, newlength)));
    }

}

//nums = [1, 1, 2, 2, 3, 4, 4, 5]
//slow=0

//fast=1: nums[1]=1, nums[0]=1 → equal, skip
//fast=2: nums[2]=2, nums[0]=1 → different! slow=1, nums[1]=2 → [1,2,2,2,3,4,4,5]
//fast=3: nums[3]=2, nums[1]=2 → equal, skip
//fast=4: nums[4]=3, nums[1]=2 → different! slow=2, nums[2]=3 → [1,2,3,2,3,4,4,5]
//fast=5: nums[5]=4, nums[2]=3 → different! slow=3, nums[3]=4 → [1,2,3,4,3,4,4,5]
//fast=6: nums[6]=4, nums[3]=4 → equal, skip
//fast=7: nums[7]=5, nums[3]=4 → different! slow=4, nums[4]=5 → [1,2,3,4,5,4,4,5]

//newLength = slow+1 = 5
//First 5 elements: [1,2,3,4,5]  ✓ correct

//Edge Cases
//Empty array → guarded explicitly, would otherwise crash on nums[slow] check.
//All duplicates (e.g. [2,2,2]) → slow never moves, newLength = 1. Correct.
//No duplicates at all → slow advances every iteration, newLength = nums.length. Correct.
//Complexity (Time: O(n) — single pass with two pointers
//Space: O(1) — true in-place)
//Time: O(n) — single pass with two pointers
//Space: O(1) — true in-place
//Pattern

//When you see "sorted array + remove duplicates/elements in-place" → think slow/fast two pointers, same direction.