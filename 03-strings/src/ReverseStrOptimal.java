
//Problem 1: Reverse String — Difficulty: Easy


//Reverse a character array in-place.

//What should I notice?

//Exact same pattern as "Reverse Array" from Module 2 — strings-as-char-arrays + reverse-in-place = two pointers from opposite ends.

public class ReverseStrOptimal {
    public static void main(String[] args){
        char[] s = {'h', 'e', 'l', 'l', 'o'};

        int left = 0;
        int right = s.length-1;

        while(left<right){

            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
        System.out.println(new String(s));
    }
    
}
// simple asf no need of explanation
