
//Problem 7: Maximum Subarray (Kadane's Algorithm) — Difficulty: Medium ⭐ (very high frequency)

//Find the contiguous subarray (at least one element) with the largest sum.

//What should I notice?

//"Contiguous subarray" + "maximum sum" is the signature of Kadane's Algorithm. The key insight: at each position, you decide — does extending the previous subarray help, or is it better to start fresh from here?

//Optimized Idea (Kadane's)

//At each index, decide: is it better to extend the existing running sum, or abandon it and restart fresh from the current element? Restart when the running sum turns negative — a negative running sum can only drag down future sums, so it's never worth keepin


public class MaxSubarrayOptimal {

    public static void main(String [] args){
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
// currentSum = best sum of a subarray ENDING at the current index
        int currentSum = nums[0];
// maxSum = best sum seen across ALL subarrays so far
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            maxSum = Math.max(maxSum, currentSum);

        }
        System.out.println("Max subarray sum: " +  maxSum);

    }
    
}


//nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
//currentSum = -2, maxSum = -2

//i=1: nums[1]=1  → max(1, -2+1=-1) = 1   → currentSum=1,  maxSum=max(-2,1)=1
//i=2: nums[2]=-3 → max(-3, 1-3=-2) = -2  → currentSum=-2, maxSum=max(1,-2)=1
//i=3: nums[3]=4  → max(4, -2+4=2) = 4    → currentSum=4,  maxSum=max(1,4)=4
//i=4: nums[4]=-1 → max(-1, 4-1=3) = 3    → currentSum=3,  maxSum=max(4,3)=4
//i=5: nums[5]=2  → max(2, 3+2=5) = 5     → currentSum=5,  maxSum=max(4,5)=5
//i=6: nums[6]=1  → max(1, 5+1=6) = 6     → currentSum=6,  maxSum=max(5,6)=6
//i=7: nums[7]=-5 → max(-5, 6-5=1) = 1    → currentSum=1,  maxSum=max(6,1)=6
//i=8: nums[8]=4  → max(4, 1+4=5) = 5     → currentSum=5,  maxSum=max(6,5)=6

//Final answer: maxSum = 6  (from subarray [4, -1, 2, 1])

//Edge Cases
//All negative numbers (e.g. [-3,-1,-2]) → answer is the single least-negative element (-1), since the problem requires "at least one element" — Kadane's handles this correctly because nums[i] alone can still win against a negative currentSum.
//Single element array → maxSum = nums[0] directly, loop doesn't execute.
//Complexity (final)
//text
//Time: O(n) — single pass
//Space: O(1) — just two tracking variables
//Pattern

//When you see "maximum/best contiguous subarray sum" → think Kadane's: extend or restart at each step.