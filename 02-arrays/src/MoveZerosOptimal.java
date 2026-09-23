
//Problem 5: Move Zeroes


//Move all 0s in an array to the end, while keeping the relative order of non-zero elements, in-place.

//What should I notice?

//Same slow/fast pointer skeleton as Problem 4, but now slow tracks "next position for a non-zero value" instead of "next unique value."
//Optimized Idea

//Two pointers again: slow = "next position where a non-zero value should go." fast scans forward; whenever it finds a non-zero, swap it into slow's position and advance slow.





public class MoveZerosOptimal {
    public static void main(String[] args){
        int[] nums = {0,1,0,3,12};

        int slow = 0;

        for( int fast = 0; fast<nums.length; fast++){

            if (nums[fast]!=0){
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }System.out.println(java.util.Arrays.toString(nums));
    }
}

//nums = [0, 1, 0, 3, 12]
//slow=0

//fast=0: nums[0]=0 → zero, skip (slow stays 0)
//fast=1: nums[1]=1 → non-zero! swap nums[0],nums[1] → [1,0,0,3,12], slow=1
//fast=2: nums[2]=0 → zero, skip
//fast=3: nums[3]=3 → non-zero! swap nums[1],nums[3] → [1,3,0,0,12], slow=2
//fast=4: nums[4]=12 → non-zero! swap nums[2],nums[4] → [1,3,12,0,0], slow=3

//Final: [1, 3, 12, 0, 0]  ✓ order preserved, zeroes pushed to end

//Edge Cases
//All zeroes → slow never advances, array stays unchanged (correctly, since there's nothing to reorder).
//No zeroes → every element swaps with itself (harmless), array stays unchanged.
//Empty array → loop never runs.

//Pattern

//When you see "move/group certain elements to one side, keep relative order, in-place" → think slow/fast two pointers with swap.