//Problem 2: Reverse Array


//Reverse an array in-place (without using extra array).

//What should I notice?

//"In-place" is the keyword — it's telling you to use two pointers moving toward each other, swapping as they go, instead of building a new array.
import java.util.*;
public class ReverseArrayOptimal{
    public static void main(String[] args){
        int[] nums = {1, 2, 3, 4, 5};
        int left = 0;
        int right = nums.length-1;

        while(left<right){

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;

        }
        System.out.println(Arrays.toString(nums));
    }
}
//nums = [1, 2, 3, 4, 5]
//left=0, right=4

//Step 1: swap nums[0] and nums[4] → [5, 2, 3, 4, 1]
//        left=1, right=3

//Step 2: swap nums[1] and nums[3] → [5, 4, 3, 2, 1]
//        left=2, right=2

//Step 3: left < right is FALSE (2 < 2 is false) → loop stops

//Final: [5, 4, 3, 2, 1]
//Edge Cases
//Empty array → loop never runs, harmless.
//Single element → left=0, right=0, left<right false immediately, no swap needed (correct, since a 1-element array reversed is itself).
//Even length → pointers meet with left == right + 1, still handled correctly.

//Pattern

//When you see "reverse in-place" → think two pointers from opposite ends, moving inward.