# Module 0: Java DSA Toolkit

## Purpose

This module builds the Java vocabulary needed for every later data structures and algorithms module. The goal is to become comfortable choosing the right Java data structure without getting distracted by syntax during problem solving.

The main idea is simple: most DSA problems ask you to store, find, count, order, remove, or process data. Java's Collections Framework gives you the tools for those jobs.

## Why this module matters

- Every later DSA pattern depends on basic Java syntax and collections.
- Java is statically typed, so every variable, parameter, and return value has a declared type.
- Java collections replace many familiar Python structures, but each has different performance characteristics.
- Interview fluency means reaching naturally for the appropriate structure, such as a map for counts or a deque for a stack.
- These structures also appear in backend systems, caches, queues, schedulers, and ML infrastructure.

## Prerequisites and difficulty

No special prerequisites are required beyond general programming experience. The concepts are usually straightforward, but Java's explicit types, object references, and verbosity require practice.

## Part A: Core Java concepts

### Static typing

Java variables have a declared type. Once a variable is declared as a particular type, it cannot later hold an unrelated type. This gives Java stronger compile-time checks than a dynamically typed language, but it also means you must understand the type of every value you use.

Important primitive types include:

- `int` for ordinary whole numbers
- `long` for whole numbers larger than the `int` range
- `double` for decimal values
- `boolean` for true or false values
- `char` for one character

### Arrays

An array stores values of one type in a fixed-size indexed structure. Its size is decided when it is created and cannot grow later. Array indexes start at zero, and the array exposes its size through a `length` property.

Use an array when the number of elements is known or when you need fast indexed access. Use an `ArrayList` when the collection must grow or shrink.

### Loops and conditionals

Loops repeat work over a range or collection. A traditional indexed loop is useful when the index matters. An enhanced loop is simpler when you only need each value. Conditional statements choose between paths based on a boolean condition.

When reading an algorithm, identify what the loop variable means, what changes on each iteration, and what condition ends the loop.

### Methods

A Java method declares its visibility, whether it belongs to the class or an object, its return type, its name, and the type of every parameter. DSA methods commonly use `static` while learning because they can be called without creating an object.

The return type is part of the method contract. A method that returns no value uses `void`.

### Classes and objects

A class describes the fields and behavior of an object. A constructor initializes a new object. The `this` reference means the current object, which helps distinguish an object's field from a constructor parameter with the same name.

### References and `null`

Variables for objects hold references to objects rather than containing the entire object directly. Assigning one object reference to another makes both variables point to the same object; it does not copy the object.

`null` means that an object reference points to nothing. Trying to use a field or method through a null reference causes a `NullPointerException`. Always consider whether a reference can be null before using it.

## Part B: Collections Framework

### ArrayList

`ArrayList` is Java's resizable-array structure and is the closest common equivalent to a Python list.

Use it for ordered data, frequent index access, and adding items at the end.

Typical complexity:

| Operation | Complexity |
|---|---:|
| Access by index | O(1) |
| Add at the end | O(1) amortized |
| Insert in the middle | O(n) |
| Remove by index | O(n) |
| Search with `contains` | O(n) |

Java collections store objects rather than primitive types. Wrapper classes such as `Integer` represent primitive values such as `int`. Java automatically converts between many primitives and their wrappers through autoboxing.

### LinkedList

`LinkedList` stores values as connected nodes. Adding or removing at the front or back can be efficient, but accessing an arbitrary index requires walking through the chain.

Its indexed access is O(n), so it is usually a poor replacement for `ArrayList` when random access is common. For DSA practice, you will often implement your own node-based linked list instead.

### HashMap

`HashMap` stores key-value pairs. It is the main structure for fast average-case lookup by key.

Use it when you need to:

- Count occurrences
- Remember information about a value
- Check whether a value has been seen
- Map an identifier to an object or result
- Replace repeated scanning with direct lookup

The average complexity of lookup, insertion, and membership checking is O(1). The keys must have reliable equality and hash behavior.

### HashSet

`HashSet` stores unique values without associated values. It is useful when the only question is whether an item exists.

Common uses include duplicate detection, visited-node tracking, and membership checks. Its average lookup, insertion, and removal complexity is O(1).

### TreeMap and TreeSet

`TreeMap` stores key-value pairs in sorted key order. `TreeSet` stores unique values in sorted order.

They are useful when you need sorted iteration, smallest or largest values, or range-based queries. Their main operations are generally O(log n), which is slower than average hash-based operations but provides ordering.

### Deque as a stack

A `Deque` supports operations at both ends. When used with stack operations, it follows LIFO behavior: the last item added is the first item removed.

For modern single-threaded DSA work, `ArrayDeque` is preferred over the older `Stack` class. It is efficient and expresses stack behavior clearly.

### Queue and deque behavior

A queue follows FIFO behavior: the first item added is the first item removed. A deque can act as either a queue or a stack depending on which end operations you use.

Choose a queue when work must be processed in arrival order. Choose a deque when both ends matter or when you need stack behavior.

### PriorityQueue

`PriorityQueue` is a heap-based structure. By default, the smallest element is available first. A comparator can change the priority rule so that larger or custom-priority elements come first.

Typical complexity:

| Operation | Complexity |
|---|---:|
| Inspect highest-priority item | O(1) |
| Add an item | O(log n) |
| Remove highest-priority item | O(log n) |

Use it when you repeatedly need the smallest, largest, or highest-priority item without fully sorting all data. Top-K problems frequently use this structure.

## Part C: Useful utilities

Java provides standard utilities for common DSA tasks:

- `Arrays` for operations on arrays, including sorting
- `Collections` for operations on collection objects
- `Math` for minimum, maximum, absolute value, and related calculations
- `StringBuilder` for efficiently building mutable text
- `Integer.MAX_VALUE` and `Integer.MIN_VALUE` for initial boundary values

### String immutability

Java `String` objects are immutable. An operation that appears to modify a string actually creates another string. Repeated concatenation inside a loop can therefore create unnecessary work and may become O(n squared).

`StringBuilder` changes one mutable buffer in place, making repeated construction much more efficient.

## Part D: Equality and ordering

### `==` versus `.equals()`

For primitives, `==` compares values. For objects, `==` compares references, meaning it asks whether two variables point to the same object.

`.equals()` is used to compare object contents when the class defines meaningful content equality. Use `.equals()` for values such as strings and custom objects when you care about their contents rather than their identity.

### `equals()` and `hashCode()`

Hash-based collections use both equality and hash codes. If a custom class is used as a `HashMap` key or stored in a `HashSet`, equal objects must produce equal hash codes.

The general contract is:

- If two objects are equal, their hash codes must be equal.
- Unequal objects may share a hash code, although fewer collisions are better.
- Equality and hash-code behavior should be based on stable fields.

Built-in types such as `String` and `Integer` already implement these rules.

### Comparable and Comparator

`Comparable` defines a class's natural ordering. The ordering becomes part of the class itself.

`Comparator` defines an external ordering rule. It is useful when the same class may be sorted in different ways, such as by name, score, age, or priority.

Comparators are used by sorting methods and priority queues. The comparison result communicates which item should come first, which item should come later, or whether the two items are considered equal for ordering purposes.

Avoid subtracting values blindly in comparison logic because integer overflow can produce an incorrect ordering. Prefer explicit comparison utilities when values may be large.

## Common mistakes

- Forgetting the required collection import
- Using the old `Stack` class instead of an `ArrayDeque`
- Assuming `HashMap` preserves insertion or sorted order
- Calling `get` and immediately unboxing a missing map value into a primitive
- Comparing boxed values with `==` instead of content equality
- Forgetting that arrays have fixed size
- Using `ArrayList` for frequent front insertions
- Mutating a collection while iterating over it without understanding the rules
- Building long strings with repeated concatenation
- Forgetting that object assignment copies a reference, not an object
- Using a comparator that can overflow when subtracting large numbers

## Complexity cheat sheet

| Structure | Strength | Typical lookup | Typical insertion |
|---|---|---:|---:|
| Array | Fast indexed access | O(1) | Fixed size |
| ArrayList | Ordered resizable data | O(1) by index | O(1) amortized at end |
| LinkedList | Front and back links | O(n) by index | O(1) at known ends |
| HashMap | Key-based lookup | O(1) average | O(1) average |
| HashSet | Membership and uniqueness | O(1) average | O(1) average |
| TreeMap / TreeSet | Sorted data | O(log n) | O(log n) |
| ArrayDeque | Queue, stack, or both ends | O(1) at ends | O(1) at ends |
| PriorityQueue | Repeated priority access | O(1) for highest priority | O(log n) |

These are typical costs, not guarantees for every operation or implementation detail. Always check whether the operation is indexed, hash-based, tree-based, or performed at an end.

## Real-world connections

- A cache commonly maps an identifier to stored data with a hash map.
- A job scheduler can use a priority queue to process the most urgent task first.
- A background worker pipeline can use a queue for first-in-first-out processing.
- A visited set prevents repeated work while traversing records or relationships.
- A tree-based map supports sorted keys and range queries.
- Collections are the same vocabulary used in backend services, ERP systems, search systems, and ML infrastructure.

## Pattern recognition from Module 0

When reading a problem, look for these clues:

| Problem wording or need | First structure to consider |
|---|---|
| Count occurrences | HashMap |
| Have I seen this value? | HashSet |
| Preserve insertion order and access by index | ArrayList |
| Process in arrival order | Queue / ArrayDeque |
| Reverse processing order | Stack behavior with Deque |
| Always take the smallest or largest next | PriorityQueue |
| Need sorted keys or range queries | TreeMap / TreeSet |
| Build text repeatedly | StringBuilder |

The structure should follow the operation you need. Do not choose a collection only because it is familiar.

## Module checkpoint

Before moving to Module 1, you should be able to explain in plain English:

- Why arrays and `ArrayList` are different
- When to use a map versus a set
- Why `ArrayDeque` is preferred for stack and queue work
- Why a priority queue is not the same as a sorted list
- Why tree-based collections cost O(log n)
- Why object equality differs from primitive equality
- Why `equals()` and `hashCode()` must agree
- When to use `Comparable` versus `Comparator`
- Which structure you would choose for counting, membership, ordering, and priority problems
