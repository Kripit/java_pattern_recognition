import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class module0 {
    public static void main(String[] args) {
        // Java starts running at main(). Each method below is one lesson section.
        coreJavaExamples();
        collectionExamples();
        utilityExamples();
        equalityAndOrderingExamples();
    }

    private static void coreJavaExamples() {
        System.out.println("=== Core Java ===");

        // An array has a fixed size. This array has five int values, initially all 0.
        int[] nums = new int[5];

        // This shorter syntax creates an array and fills it with these four values.
        int[] arr = {2, 7, 11, 15};
        System.out.println("First array value: " + arr[0]);

        // The normal for loop is useful when you need the index.
        for (int i = 0; i < nums.length; i++) {
            System.out.println("Index " + i + ": " + nums[i]);
        }

        // The enhanced for loop gives you each value directly when the index is not needed.
        for (int num : arr) {
            System.out.println("Array value: " + num);
        }

        // if/else chooses one path based on a boolean condition.
        if (nums[0] > 5) {
            System.out.println("First value is big");
        } else {
            System.out.println("First value is small");
        }

        // A method is reusable code. static means this method belongs to the class,
        // so main() can call it without creating a module0 object.
        System.out.println("Square of 4: " + square(4));

        // A class is a template. new Point(...) creates an object from that template.
        Point point = new Point(3, 4);
        System.out.println("Point: " + point);
    }

    private static int square(int value) {
        return value * value;
    }

    private static void collectionExamples() {
        System.out.println("\n=== Collections ===");

        // ArrayList is Java's resizable-array equivalent to a Python list.
        // get(index) is O(1), add at the end is O(1) amortized,
        // but inserting/removing in the middle is O(n) because values shift.
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        System.out.println("ArrayList item: " + list.get(1));
        list.remove(0);
        System.out.println("ArrayList size: " + list.size());

        // LinkedList stores values in connected nodes. Front/back operations are cheap,
        // but get(index) is O(n) because Java must walk through the nodes.
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addLast(2);
        linkedList.removeFirst();
        System.out.println("LinkedList: " + linkedList);

        // HashMap stores key/value pairs. It is useful for counts and fast average O(1) lookup.
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 3);
        map.put("banana", 5);
        map.put("apple", map.get("apple") + 1);
        System.out.println("Apples: " + map.get("apple"));
        System.out.println("Has mango: " + map.containsKey("mango"));

        // HashSet stores unique values. Adding 5 twice still leaves one 5.
        // contains/add/remove are average O(1), and there are no key/value pairs.
        HashSet<Integer> seen = new HashSet<>();
        seen.add(5);
        seen.add(5);
        System.out.println("Contains 5: " + seen.contains(5));
        System.out.println("Unique values: " + seen.size());

        // TreeMap keeps its keys sorted. Operations are usually O(log n),
        // unlike HashMap, which is faster on average but does not sort keys.
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(5, "five");
        sortedMap.put(1, "one");
        sortedMap.put(3, "three");
        System.out.println("Smallest key: " + sortedMap.firstKey());

        // Deque means double-ended queue. push/pop make it behave as a stack: LIFO
        // (last in, first out). ArrayDeque is preferred over the old Stack class.
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        System.out.println("Stack pop: " + stack.pop());

        // offer adds to the back and poll removes from the front: FIFO
        // (first in, first out), like people waiting in a line.
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println("Queue poll: " + queue.poll());

        // The same Deque can add/remove at either end when both ends matter.
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println("Deque: " + deque);

        // PriorityQueue is a min-heap by default, so the smallest value comes out first.
        // offer and poll are O(log n); peek is O(1). A Comparator can change the priority.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(5);
        priorityQueue.offer(1);
        priorityQueue.offer(3);
        System.out.println("PriorityQueue poll: " + priorityQueue.poll());
    }

    private static void utilityExamples() {
        System.out.println("\n=== Utilities ===");

        // Arrays.sort changes the array into ascending order.
        int[] values = {5, 2, 8, 1};
        Arrays.sort(values);
        System.out.println("Sorted array: " + Arrays.toString(values));

        // Collections.sort works on collection objects such as ArrayList.
        ArrayList<Integer> sortableList = new ArrayList<>(List.of(5, 2, 8));
        sortableList.sort(null);
        System.out.println("Sorted list: " + sortableList);

        // Math utilities avoid rewriting common comparisons by hand.
        System.out.println("Max: " + Math.max(3, 7));
        System.out.println("Min: " + Math.min(3, 7));

        // String is immutable: repeated += creates new String objects.
        // StringBuilder changes one buffer in place, which is better in loops.
        StringBuilder builder = new StringBuilder();
        builder.append("hello").append(" world");
        System.out.println(builder);

        // These constants are useful when tracking a largest or smallest value in a loop.
        int largestPossible = Integer.MAX_VALUE;
        int smallestPossible = Integer.MIN_VALUE;
        System.out.println("Integer limits: " + largestPossible + ", " + smallestPossible);
    }

    private static void equalityAndOrderingExamples() {
        System.out.println("\n=== Equality, Comparable, and Comparator ===");

        String first = new String("hi");
        String second = new String("hi");

        // For objects, == asks whether both variables point to the same object.
        // These are two separate String objects, so the answer is false.
        System.out.println("first == second: " + (first == second));

        // equals() asks whether the contents are equal. For Strings, "hi" equals "hi".
        System.out.println("first.equals(second): " + first.equals(second));

        Point pointA = new Point(3, 4);
        Point pointB = new Point(3, 4);
        System.out.println("Equal points: " + pointA.equals(pointB));

        List<Person> people = new ArrayList<>(List.of(
                new Person("Ana", 30),
                new Person("Ben", 20),
                new Person("Cam", 25)));

        // Comparable is an interface that says: "this class knows its default order."
        // Person implements Comparable<Person>, so compareTo() defines natural order by age.
        // Passing null means List.sort uses Person.compareTo().
        people.sort(null);
        System.out.println("Natural order (age): " + people);

        // Comparator is an outside rule. It lets us sort the same Person objects another way
        // without changing Person's natural age order. This comparator sorts by name.
        Comparator<Person> byName = Comparator.comparing(person -> person.name);
        people.sort(byName);
        System.out.println("Custom order (name): " + people);
    }

    static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            // equals receives Object because Java must allow comparison with any object type.
            // instanceof asks whether o is actually a Point (or a Point subclass).
            if (!(o instanceof Point)) {
                return false;
            }

            // At this moment Java still sees o as Object, so o.x would not compile.
            // The cast changes the compiler's view from Object to Point.
            // It is safe because the instanceof check succeeded immediately above.
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            // Equal objects must return the same hash code for HashSet/HashMap to work correctly.
            return 31 * x + y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class Person implements Comparable<Person> {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            // Return negative if this Person should come first, 0 if tied,
            // or positive if other should come first.
            // Integer.compare avoids overflow that could happen with age - other.age.
            return Integer.compare(age, other.age);
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
