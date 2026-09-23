public class SecondLargestArrayopt {
    public static void main(String[] args){
        int[] nums = {4,2,9,9,7,1};

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int num: nums){
            if(num>largest){
                secondLargest = largest;
                largest = num;
            }else if(num> secondLargest && num!= largest){
                secondLargest= num;
            }
        }

        System.out.println("Second largest: " + secondLargest);
    }
}


//nums = [4, 2, 9, 9, 7, 1]
//largest = MIN, secondLargest = MIN

//num=4: 4>MIN → secondLargest=MIN, largest=4
//num=2: 2>4? no. 2>MIN and 2!=4? yes → secondLargest=2
//num=9: 9>4? yes → secondLargest=4, largest=9
//num=9: 9>9? no. 9>4 but 9==largest(9) → SKIPPED (duplicate guard)
//num=7: 7>9? no. 7>4 and 7!=9? yes → secondLargest=7
//num=1: 1>9? no. 1>7? no → nothing changes

//Final: largest=9, secondLargest=7

//Edge Cases
//All elements identical (e.g. [5,5,5]) → secondLargest stays MIN_VALUE forever — meaning "no second distinct value exists." Handle this by checking if (secondLargest == Integer.MIN_VALUE) before returning.
//Array with only 1 element → same issue, no valid second largest exists.

//Pattern

//When you see "find Nth largest/smallest in one pass" → think tracking N running variables simultaneously, not sorting.