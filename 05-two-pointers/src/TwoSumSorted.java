public class TwoSumSorted {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(result[0] + ", " + result[1]); // 1, 2
    }

    public static int[] twoSum(int[] nums, int target) {
        int left = 0;                  // pointer starting from smallest value
        int right = nums.length - 1;   // pointer starting from largest value

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                // found it! return 1-indexed positions (problem convention)
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // sum too small — we need a BIGGER value
                // since array is sorted, moving left forward gives a bigger nums[left]
                left++;
            } else {
                // sum too big — we need a SMALLER value
                // moving right backward gives a smaller nums[right]
                right--;
            }
        }

        return new int[]{-1, -1}; // no valid pair found
    }
}