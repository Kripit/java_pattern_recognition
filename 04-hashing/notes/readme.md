## Module 4: Hashing

Hashing is a way to store data so I can usually find it quickly by key. In Java, the two structures I will use most are `HashMap` and `HashSet`.

The main interview question is:

> Do I keep scanning the old values, or can I remember useful information and look it up in O(1) average time?

That is the pattern. Hashing turns many problems that would be O(n squared) into O(n).

---

## 1. What hashing means in normal English

Imagine a coat-check system. I give the attendant a ticket number, and the ticket number tells them where to look for my coat. I do not search every coat one by one.

A hash table does something similar:

1. It receives a key, such as a number or a word.
2. `hashCode()` converts that key into a hash value.
3. The table uses that value to choose a bucket.
4. It stores or finds the key/value in that bucket.

Different keys can sometimes land in the same bucket. That is a collision. Java handles collisions internally, so I mainly need to remember that hash-based operations are **O(1) average**, not a mathematical guarantee for every case.

Hashing is useful when I need fast:

- membership checks: "Have I seen this?"
- counting: "How many times does this appear?"
- associations: "What information belongs to this key?"
- grouping: "Which items have the same property?"

---

## 2. `HashSet`: remember unique values

Use a `HashSet` when I only care whether a value exists. A set does not store a separate value for each key, and it automatically keeps values unique.

```java
import java.util.HashSet;
import java.util.Set;

Set<Integer> seen = new HashSet<>();

seen.add(7);
seen.contains(7); // true
seen.add(7);     // still only one 7
seen.remove(7);
```

### Duplicate detection

```java
static boolean hasDuplicate(int[] numbers) {
	Set<Integer> seen = new HashSet<>();

	for (int number : numbers) {
		if (seen.contains(number)) {
			return true;
		}
		seen.add(number);
	}

	return false;
}
```

For `[4, 2, 9, 4]`, the set changes like this:

```text
start:       []
read 4:      [4]
read 2:      [4, 2]
read 9:      [4, 2, 9]
read 4:      already present -> duplicate found
```

The slow approach compares every pair and takes O(n squared). The set remembers previous values, so the loop takes O(n) average time and O(n) extra space.

---

## 3. `HashMap`: remember a value for each key

Use a `HashMap` when a key needs associated information.

### Key first, value second

The type always follows this shape:

```java
Map<KeyType, ValueType> map = new HashMap<>();
```

The first type is the **key**: the thing I use to search. The second type is the **value**: the information stored under that key.

```java
Map<String, Integer> ages = new HashMap<>();
```

This means:

```text
key   = String  -> a person's name, such as "Maya"
value = Integer -> that person's age, such as 24
```

So this stores the pair `"Maya" -> 24`:

```java
ages.put("Maya", 24);
int age = ages.get("Maya"); // 24
```

The key comes first because I ask the map for a value **by using the key**. A key should identify one entry. A value is the answer or information attached to it.

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> counts = new HashMap<>();
counts.put("apple", 3);

counts.get("apple");              // 3
counts.containsKey("apple");      // true
counts.getOrDefault("banana", 0); // 0
```

The key is the thing I want to search by. The value is the information I want to remember.

| Problem | Key | Value |
|---|---|---|
| Count numbers | number | count |
| Two Sum | number | index |
| First unique character | character | frequency |
| Group anagrams | sorted character pattern | list of words |
| Prefix-sum subarrays | prefix sum | number of times seen |

### Frequency counting

```java
static Map<Integer, Integer> frequency(int[] numbers) {
	Map<Integer, Integer> counts = new HashMap<>();

	for (int number : numbers) {
		counts.put(number, counts.getOrDefault(number, 0) + 1);
	}

	return counts;
}
```

Read the method declaration from left to right:

```text
static                  -> this method belongs to the class
Map<Integer, Integer>   -> this method returns a map
frequency               -> method name
int[] numbers           -> input: an array of ints
```

The returned map is `Map<Integer, Integer>` because both the key and the value are integers:

```text
key   = the number from the array
value = how many times that number appeared
```

For `[2, 2, 5]`, the answer is `{2=2, 5=1}`. The key `2` identifies the number, and the value `2` records its count.

### Why `getOrDefault(number, 0) + 1`?

This line counts one number at a time:

```java
counts.put(number, counts.getOrDefault(number, 0) + 1);
```

Break it into three steps:

```java
int oldCount = counts.getOrDefault(number, 0);
int newCount = oldCount + 1;
counts.put(number, newCount);
```

`getOrDefault(number, 0)` means:

- Look for `number` as a key.
- If it is already there, return its current count.
- If it is not there yet, return `0` instead of returning `null`.

The `+ 1` means I just read one more occurrence of that number. The first time I see `2`, its old count is `0`, so I store `1`. The next time, its old count is `1`, so I store `2`.

```text
read 2: old count 0 -> 0 + 1 -> store 2=1
read 2: old count 1 -> 1 + 1 -> store 2=2
read 5: old count 0 -> 0 + 1 -> store 5=1
```

I choose the map types from the question. Ask: "What am I looking up?" That is the key type. Then ask: "What do I need to remember about it?" That is the value type.

```text
count numbers:       Map<Integer, Integer>  number -> count
remember indexes:    Map<Integer, Integer>  number -> index
count letters:       Map<Character, Integer> letter -> count
name to phone:       Map<String, String>    name -> phone number
word to its length:  Map<String, Integer>   word -> length
```

The variable name can be anything, but the declared types must match the data I plan to store. I cannot put a `String` key into a `Map<Integer, Integer>` because that map was defined to use integer keys.

For `[2, 2, 5, 2]`, the map becomes:

```text
read 2: {2=1}
read 2: {2=2}
read 5: {2=2, 5=1}
read 2: {2=3, 5=1}
```

The map is not magic. Each iteration reads the old count, adds one, and stores the new count under the same key.

---

## 4. The most important recognition patterns

### "Have I seen this before?"

Reach for a `HashSet`.

```java
if (seen.contains(value)) {
	// repeated value
}
seen.add(value);
```

### "How many times has this appeared?"

Reach for a `HashMap<Value, Integer>`.

```java
counts.put(value, counts.getOrDefault(value, 0) + 1);
```

### "What belongs to this value?"

Reach for a `HashMap<Key, Value>`. For Two Sum, the number is the key and its earlier index is the value.

```java
static int[] twoSum(int[] numbers, int target) {
	Map<Integer, Integer> indexByNumber = new HashMap<>();

	for (int index = 0; index < numbers.length; index++) {
		int needed = target - numbers[index];

		if (indexByNumber.containsKey(needed)) {
			return new int[] {indexByNumber.get(needed), index};
		}

		indexByNumber.put(numbers[index], index);
	}

	return new int[0];
}
```

For `[2, 7, 11, 15]` and target `9`:

```text
read 2: need 7, map is {}
        store 2 -> 0
read 7: need 2, map is {2=0}
        found 2 -> return [0, 1]
```

The order matters: check for the complement first, then store the current number. This prevents using the same array element twice.

### "How many subarrays have a target sum?"

Use a prefix-sum map. If the current running sum is `sum`, I need an earlier prefix sum of `sum - target`.

```java
static int subarraySum(int[] numbers, int target) {
	Map<Integer, Integer> prefixCounts = new HashMap<>();
	prefixCounts.put(0, 1);

	int sum = 0;
	int answer = 0;

	for (int number : numbers) {
		sum += number;
		answer += prefixCounts.getOrDefault(sum - target, 0);
		prefixCounts.put(sum, prefixCounts.getOrDefault(sum, 0) + 1);
	}

	return answer;
}
```

The initial `{0=1}` represents a prefix sum of zero before the array starts. Without it, a valid subarray beginning at index `0` could be missed.

---

## 5. Hashing checklist

When I see a problem, ask:

1. Am I repeatedly searching values I have already passed?
2. Do I need only existence? Use `HashSet`.
3. Do I need counts, indexes, or another piece of data? Use `HashMap`.
4. What exactly is my key?
5. Do I check before inserting, or insert before checking?
6. What should the map contain after each loop iteration?
7. Is the expected answer O(n) time with O(n) extra space?

Hashing is used in real systems for caches, deduplication pipelines, database indexes, lookup tables, and ML preprocessing features such as frequency counts and embedding lookups.

## Complexity

For `HashMap` and `HashSet`, `get`, `put`, `add`, `contains`, and `remove` are usually O(1) average. A loop that performs a constant amount of hash-table work per item is therefore usually O(n). The tradeoff is extra memory, usually O(n).

Hash-based collections do not promise sorted order. If I need sorted keys or range queries, I should consider `TreeMap` or `TreeSet` instead.
