import java.util.Scanner;

// Problem 1: Find Maximum and Minimum
// Given an array, find the largest and smallest elements.

//Optimized Idea

//Track the running min and max as you scan once — no ordering needed, just comparison

public class MaxMinOptimal{
    public static void main(String[] args){
        int[] nums;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter array length: ");
            nums = new int[scanner.nextInt()];

            for (int i = 0; i < nums.length; i++) {
                System.out.print("Enter number " + (i + 1) + ": ");
                nums[i] = scanner.nextInt();
            }
        }

        if (nums.length == 0){ 

            return;

         }
        int min = nums[0];
        int max = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i]<min){
                min = nums[i];
            }
            if(nums[i]> max){
                max = nums[i];
            }
        } System.out.println("Min: " + min + ", Max: " + max);
    }
}