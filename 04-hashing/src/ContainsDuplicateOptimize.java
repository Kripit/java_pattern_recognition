//Problem 1: Contains Duplicate — Difficulty: Easy


//Given an array, return true if any value appears at least twice.

//What should I notice?

//"Has this been seen before?" is the single clearest signal for a HashSet — you don't need counts, just existence.

//Optimized Idea
import java.util.*;
//Walk through once. Before adding a number to the set, check if it's already there.
public class ContainsDuplicateOptimize {
    public static void main(String[] args){
        int[] nums = {1,2,3,1};
        System.out.println(hasDuplicate(nums));
    }
    public static boolean hasDuplicate(int[] nums){

        Set<Integer> seen = new HashSet<>();

        for (int num: nums){
 // add() returns FALSE if the value was ALREADY present in the set
            // (this is a lesser-known but very useful behavior of Set.add)
            if(!seen.add(num)){
                return true;
            }
        }return false;
    }
    
}
