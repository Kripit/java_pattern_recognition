//Problem 2: Container With Most Water — Difficulty: Medium ⭐ (very popular, non-obvious greedy proof)


//Given heights of vertical lines, find two lines that together with the x-axis form a container holding the most water. Water held = min(height[i], height[j]) × (j - i).

//What should I notice?

//This looks like it needs checking every pair (O(n²)), but two pointers can solve it in O(n) using a clever greedy insight: always move the pointer at the shorter line.

public class ContainerOptimzed {

    public static void main(String[] args){
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height){
        int left=0;
        int right = height.length - 1;
        int maxWater =0;

        while(left<right){
            int width = right-left;

            int currentHeight = Math.min(height[left], height[right]);
            int water = width* currentHeight;

            maxWater = Math.max(maxWater, water);

            if(height[left]< height[right]){
                left++;
            }
            else{
                right--;
            }

        }return maxWater;
    }
    
}

// ez asf there is no need of tracing and explanation yall lwk goated can understand this at a glance 