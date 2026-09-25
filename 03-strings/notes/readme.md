## Module 3 - Strings

Strings are one of the most common things in coding interviews. They look simple at first, but a lot of problems are really array problems where the array contains characters.

The main things to get comfortable with here are:

- String immutability in Java
- `StringBuilder`
- Reading and changing characters
- Two pointers
- Palindromes
- Anagrams and frequency counting

Most of the array patterns carry over to strings. The Java-specific thing to remember is that a `String` cannot be changed after it is created.

### Why this module matters

Strings show up everywhere: text processing, tokenizing input, parsing logs, checking user input, and searching for pieces of text.

This is also useful groundwork for chunking text for RAG later on. Before splitting a document into chunks, you usually need to clean the text, find separators, and check or manipulate substrings.

**Difficulty:** Easy to medium  
**Interview importance:** Very high  
**Prerequisites:** Module 0 (`StringBuilder`) and Module 2 (two pointers)

---

## 1. String immutability -

Once a Java `String` is created, its contents cannot be changed. Methods that seem to modify a string actually return a new string.

```java
String s = "hello";
s = s + " world";
```

The original `"hello"` was not changed. Java created a new string containing `"hello world"`, and then `s` was made to point to it.

This is easy to forget when writing loops. For example:

```java
String result = "";

for (int i = 0; i < 5; i++) {
	result += i;
}
```

This keeps creating new string objects. For a large string, repeatedly using `+=` can lead to O(n^2) work.

### Use StringBuilder when building a string

`StringBuilder` has a mutable internal buffer. It changes that buffer instead of creating a new `String` every time.

```java
StringBuilder builder = new StringBuilder();

for (int i = 0; i < 5; i++) {
	builder.append(i);
}

String result = builder.toString();
```

The usual pattern is:

1. Create a `StringBuilder`.
2. Append characters or strings.
3. Call `toString()` once at the end.

For building an n-character result, this is normally O(n), instead of repeatedly copying the whole result.

Useful methods:

```java
StringBuilder builder = new StringBuilder("abc");

builder.append('d');       // "abcd"
builder.insert(0, 'x');    // "xabcd"
builder.deleteCharAt(1);   // removes the character at index 1
builder.reverse();          // reverses the builder
builder.setCharAt(0, 'z');  // replaces one character

String text = builder.toString();
```

---

## 2. Accessing characters

Use `charAt(index)` to read one character. String indexes start at 0, just like array indexes.

```java
String word = "abc";

char first = word.charAt(0); // 'a'
char second = word.charAt(1); // 'b'
int length = word.length(); // 3
```

Do not try to assign directly to a position in a `String`:

```java
// This does not work:
// word.charAt(0) = 'z';
```

If I need to change characters, I can use a `char[]` or a `StringBuilder`.

```java
char[] chars = word.toCharArray();
chars[0] = 'z';

String changed = new String(chars); // "zbc"
```

---

## 3. Characters and lowercase letters

Characters have numeric values underneath. For lowercase English letters, subtracting `'a'` gives a useful index from 0 to 25.

```java
char c = 'c';
int index = c - 'a'; // 2
```

So this is useful for counting lowercase letters:

```java
int[] frequency = new int[26];
String word = "banana";

for (int i = 0; i < word.length(); i++) {
	char c = word.charAt(i);
	frequency[c - 'a']++;
}
```

The mapping is:

```text
'a' - 'a' = 0
'b' - 'a' = 1
'c' - 'a' = 2
...
'z' - 'a' = 25
```

This only works directly when the input is known to contain lowercase English letters. If the input can contain uppercase letters, spaces, punctuation, or other characters, I need to handle those cases first.

For a single character, Java can convert between `char` and `int`:

```java
char c = 'b';
int value = (int) c;
char next = (char) 99; // 'c'
```

For interview problems, the important part is usually not memorizing ASCII values. It is recognizing when a character can be converted into an array index.

---

## 4. Common string patterns

### Palindrome

A palindrome reads the same from both directions, such as `"level"` or `"racecar"`.

The two-pointer approach compares the first and last characters, then moves inward.

```java
static boolean isPalindrome(String text) {
	int left = 0;
	int right = text.length() - 1;

	while (left < right) {
		if (text.charAt(left) != text.charAt(right)) {
			return false;
		}

		left++;
		right--;
	}

	return true;
}
```

Time complexity is O(n), and the extra space is O(1).

If the problem says to ignore spaces, punctuation, or capitalization, that cleanup rule needs to be included in the pointer logic or done before the comparison.

### Anagram

Two strings are anagrams when they contain the same characters with the same counts. For example, `"listen"` and `"silent"` are anagrams.

For lowercase English letters, a frequency array is enough:

```java
static boolean areAnagrams(String first, String second) {
	if (first.length() != second.length()) {
		return false;
	}

	int[] frequency = new int[26];

	for (int i = 0; i < first.length(); i++) {
		frequency[first.charAt(i) - 'a']++;
		frequency[second.charAt(i) - 'a']--;
	}

	for (int count : frequency) {
		if (count != 0) {
			return false;
		}
	}

	return true;
}
```

The idea is to add counts for one string and remove counts for the other. If every count ends at zero, the strings contain the same characters.

---

## Things to remember

- `String` is immutable.
- Use `StringBuilder` when constructing a string in a loop.
- Use `charAt()` to read a character.
- Use `toCharArray()` or `StringBuilder` when characters need to be changed.
- `c - 'a'` is useful for lowercase frequency arrays.
- Palindrome problems usually suggest two pointers.
- Anagram problems usually suggest frequency counting.
- Always check the input rules before using a 26-element array.
