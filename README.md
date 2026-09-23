# Java DSA Toolkit

A module-by-module workspace for learning Java syntax, data structures, algorithms, and pattern recognition.

## Study order

1. Read the module README before coding.
2. Write the idea in plain English first.
3. Write a small example by hand.
4. Write pseudocode.
5. Implement the Java solution.
6. Test normal, edge, and invalid cases.
7. Record time and space complexity.
8. Add one lesson learned.

## Folder structure

Each module contains:

- `README.md` - what to learn and a plain-English logic worksheet
- `src/` - Java code for examples and exercises
- `notes/` - optional explanations, mistakes, and patterns

Create `src` and `notes` inside a module when you start working on it. Keep one Java file per focused problem.

## Plain-English logic template

Before writing code, answer these questions:

1. **Goal:** What must the program return or change?
2. **Input:** What do I receive? What can be empty or invalid?
3. **Output:** What exactly should I return or print?
4. **Example:** What happens for one normal example?
5. **Smallest case:** What happens for zero, one, or the minimum input?
6. **Process:** Explain each step as if teaching a beginner.
7. **Decision:** What condition tells me which step to take next?
8. **State:** What variables do I need, and what does each one mean?
9. **Stop:** When does the process finish?
10. **Complexity:** How many times can each step run? What extra memory is used?

## Suggested problem note

```text
Problem:

Goal in one sentence:

Input and output:

Example:

My idea in plain English:
1.
2.
3.

Important edge cases:

Pseudocode:

Time complexity:

Space complexity:

What I learned:
```

## Java package convention

Use a package based on the folder, for example:

```java
package module02_arrays;
```

Keep class names descriptive, such as `PrefixSum`, `TwoSumSorted`, or `ReverseLinkedList`.
