What it contains: # Arrays — The Foundation of Data Structures

## Overview
**What it covers:** How arrays sit in memory, traversal/insertion/deletion, prefix sums, subarray thinking, and Kadane's algorithm.

**Why you need it:** Arrays are the "base layer" — almost every other data structure (ArrayList, HashMap buckets, heaps) is literally built on top of an array internally.

**Difficulty:** Easy to medium — the hard part isn't syntax, it's recognizing which pattern (two pointers? prefix sum? Kadane's?) a given problem wants.

**Interview importance:** ⭐ Extremely high — nearly every interview starts with an array problem as a warm-up.

**Real-world use:** Time-series data (stock prices, sensor readings), image pixel data (2D arrays), feature vectors in ML.

**Prerequisites:** Module 0 (arrays, loops), Module 1 (complexity analysis).

---

## Array Fundamentals — How It Actually Works

### Simple Explanation
An array is a block of memory slots sitting right next to each other, all the same size, all holding the same data type. Because they're **contiguous** (touching, in order), the computer can jump to any index instantly using pure math — no searching needed.

### Memory Layout
```
Array indices:    [0]  [1]  [2]  [3]
Array values:     [10] [20] [30] [40]
Memory address:  1000 1004 1008 1012
                  ↑ (base address)
```

### Time Complexity
| Operation | Time | Reason |
|-----------|------|--------|
| **Access** | O(1) | Direct address calculation: `base + index × size` |
| **Search** | O(n) | Must check each element |
| **Insert (middle)** | O(n) | Must shift all elements after insertion point |
| **Delete (middle)** | O(n) | Must shift all remaining elements left |
| **Append** | O(1) | Just add to the end (if space available) |

### Why Access is O(1)
If your array starts at memory address 1000 and each int takes 4 bytes:
- `arr[0]` → address = 1000 + 0×4 = 1000
- `arr[3]` → address = 1000 + 3×4 = 1012

The computer computes the address directly and jumps there instantly. It doesn't walk through indices 0, 1, 2 — that's the magic of contiguous memory!

### Why Insertion/Deletion in the Middle is O(n)
Arrays have no "gaps". When you remove index 2 from `[10, 20, 30, 40]`, everything after must shift left:
```
Before:  [10, 20, 30, 40]
Delete index 2:
After:   [10, 20, 40]  ← 30 and 40 had to move
```

This shifting is what costs O(n) time.

---



### 1.  Example

```java

public class ArrayBasics{
    public static void main(String[] args){
        int[] arr = {10,20,30,40,50};

        System.out.println(arr[2]);
        // 30 ACCESS - O(1), direct memory jump using the index formula

        // Traversal - O(n), must visit every element once
        for(int i = 0; i <arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();

// UPDATE — O(1), overwrite the value at a known index directly
        arr[0] = 99;
        int target = 40;
        int FoundIndex = -1;
        for(int i = 0; i<arr.length; i++){
            if(arr[i]==target){
                FoundIndex = i;
                break;// stop early once found — saves time on average
            }
        }System.out.println("Found at index: " + foundIndex);
    }
}

```

i=0: arr[0]=99  -> not target, continue
i=1: arr[1]=20  -> not target, continue
i=2: arr[2]=30  -> not target, continue
i=3: arr[3]=40  -> MATCH! foundIndex = 3, break


Frequency Arrays — a bridge before HashMaps

Sometimes you don't need a full HashMap — if your values are bounded (like lowercase letters, or numbers 0–100), a plain array indexed by value is faster and simpler than hashing.

### 1.  Example

```java

public class FrequencyArrayDemo{
    public static void main(String[] args){
        int[] nums = {1,3,1,2,3,3};

        int[] freq = new int[4];

        for (int num: nums){
            freq[num]++;
        }
        for (int i = 0; i <freq.length; i ++){
            System.out.println(i+ "occured" + freq[i] + " times");
        // 1 occurred 2 times
        // 2 occurred 1 times
        // 3 occurred 3 times
        }
    }
}

```