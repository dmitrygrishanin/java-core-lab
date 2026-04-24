package com.dmytrohryshanin.javacorelab.collections.list;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Demonstrates the most common operations and behavior of {@link LinkedList}.
 *
 * <p>This class covers:
 * <ul>
 *     <li>different ways to create a LinkedList</li>
 *     <li>basic add/get/set/remove operations</li>
 *     <li>search operations such as contains() and indexOf()</li>
 *     <li>using LinkedList as a Queue</li>
 *     <li>using LinkedList as a Deque</li>
 *     <li>LinkedList-specific operations at both ends</li>
 *     <li>iteration with for-each, Iterator, and descendingIterator()</li>
 *     <li>how LinkedList handles null values</li>
 * </ul>
 *
 * <p>The goal is not to build a reusable utility, but to provide runnable examples
 * for learning Java collections.
 */
@Slf4j
public class LinkedListExamples {

    public static void main(String[] args) {
         createLinkedLists();
         listOperations();
         searchAndDuplicates();
         queueOperations();
         dequeOperations();
         linkedListSpecificEnds();
         iterationExamples();
         nullHandling();
    }

    /**
     * Shows common ways to create a {@link LinkedList}.
     *
     * <p>This method demonstrates:
     * <ul>
     *     <li>creating an empty LinkedList</li>
     *     <li>creating a copy from another collection</li>
     * </ul>
     */
    private static void createLinkedLists() {
        log.info("=== createLinkedLists ===");

        List<String> empty = new LinkedList<>();
        empty.add("Java");
        empty.add("Collections");

        List<String> copy = new LinkedList<>(empty);

        log.info("empty: {}", empty);
        log.info("copy: {}", copy);
    }

    /**
     * Demonstrates LinkedList used as a regular {@link List}.
     *
     * <p>This method covers:
     * <ul>
     *     <li>{@code add(element)}</li>
     *     <li>{@code add(index, element)}</li>
     *     <li>{@code get(index)}</li>
     *     <li>{@code set(index, element)}</li>
     *     <li>{@code remove(index)}</li>
     *     <li>{@code remove(object)}</li>
     * </ul>
     *
     * <p>These are the fundamental operations that are commonly used with {@link List}.
     */
    private static void listOperations() {
        log.info("=== listOperations ===");

        List<String> cities = new LinkedList<>();

        cities.add("Sofia");
        cities.add("Plovdiv");
        cities.add("Varna");
        log.info("After add(): {}", cities);

        cities.add(1, "Burgas");
        log.info("After add(index, element): {}", cities);

        String firstCity = cities.get(0);
        log.info("get(0): {}", firstCity);

        String oldValue = cities.set(2, "Ruse");
        log.info("set(2, \"Ruse\") replaced: {}", oldValue);
        log.info("After set(): {}", cities);

        String removedByIndex = cities.remove(1);
        log.info("remove(1): {}", removedByIndex);
        log.info("After remove(index): {}", cities);

        boolean removedByObject = cities.remove("Varna");
        log.info("remove(\"Varna\"): {}", removedByObject);
        log.info("After remove(object): {}", cities);
    }

    /**
     * Demonstrates common search operations on {@link LinkedList}.
     */
    private static void searchAndDuplicates() {
        log.info("=== searchAndDuplicates ===");

        LinkedList<String> languages = new LinkedList<>();
        languages.add("Java");
        languages.add("Kotlin");
        languages.add("Java");
        languages.add(null);

        log.info("languages: {}", languages);
        log.info("contains(\"Java\"): {}", languages.contains("Java"));
        log.info("contains(\"Scala\"): {}", languages.contains("Scala"));
        log.info("indexOf(\"Java\"): {}", languages.indexOf("Java"));
        log.info("lastIndexOf(\"Java\"): {}", languages.lastIndexOf("Java"));
        log.info("contains(null): {}", languages.contains(null));
    }

    /**
     * Demonstrates LinkedList used as a {@link Queue}.
     *
     * <p>This method shows FIFO-style operations:
     * <ul>
     *     <li>{@code offer()} adds an element to the tail</li>
     *     <li>{@code peek()} reads the head without removing it</li>
     *     <li>{@code poll()} removes and returns the head</li>
     * </ul>
     */
    private static void queueOperations() {
        log.info("=== queueOperations ===");

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        log.info("Initial queue: {}", queue);
        log.info("peek(): {}", queue.peek());
        log.info("poll(): {}", queue.poll());
        log.info("After poll(): {}", queue);
    }

    /**
     * Demonstrates LinkedList used as a {@link Deque}.
     *
     * <p>This method focuses on operations at both ends:
     * <ul>
     *     <li>{@code addFirst()}</li>
     *     <li>{@code addLast()}</li>
     *     <li>{@code getFirst()}</li>
     *     <li>{@code getLast()}</li>
     *     <li>{@code removeFirst()}</li>
     *     <li>{@code removeLast()}</li>
     * </ul>
     */
    private static void dequeOperations() {
        log.info("=== dequeOperations ===");

        Deque<String> deque = new LinkedList<>();

        deque.addFirst("middle");
        deque.addFirst("start");
        deque.addLast("end");

        log.info("Deque after addFirst/addLast: {}", deque);
        log.info("getFirst(): {}", deque.getFirst());
        log.info("getLast(): {}", deque.getLast());

        String removedFirst = deque.removeFirst();
        String removedLast = deque.removeLast();

        log.info("removeFirst(): {}", removedFirst);
        log.info("removeLast(): {}", removedLast);
        log.info("Deque after removals: {}", deque);
    }

    /**
     * Demonstrates LinkedList-specific operations at both ends and occurrence-based removal.
     */
    private static void linkedListSpecificEnds() {
        log.info("=== linkedListSpecificEnds ===");

        LinkedList<String> tasks = new LinkedList<>();

        tasks.addFirst("deploy");
        tasks.addLast("test");
        tasks.addLast("deploy");
        tasks.addFirst("build");

        log.info("After addFirst/addLast: {}", tasks);
        log.info("peekFirst(): {}", tasks.peekFirst());
        log.info("peekLast(): {}", tasks.peekLast());

        boolean removedFirstOccurrence = tasks.removeFirstOccurrence("deploy");
        boolean removedLastOccurrence = tasks.removeLastOccurrence("deploy");

        log.info("removeFirstOccurrence(\"deploy\"): {}", removedFirstOccurrence);
        log.info("removeLastOccurrence(\"deploy\"): {}", removedLastOccurrence);
        log.info("After occurrence-based removal: {}", tasks);
    }

    /**
     * Demonstrates several ways to iterate through a LinkedList.
     */
    private static void iterationExamples() {
        log.info("=== iterationExamples ===");

        LinkedList<String> colors = new LinkedList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");

        log.info("for-each iteration:");
        for (String color : colors) {
            log.info(" - {}", color);
        }

        log.info("Iterator iteration:");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            log.info(" - {}", iterator.next());
        }

        log.info("descendingIterator iteration:");
        Iterator<String> descendingIterator = colors.descendingIterator();
        while (descendingIterator.hasNext()) {
            log.info(" - {}", descendingIterator.next());
        }
    }

    /**
     * Demonstrates that LinkedList can store null values.
     *
     * <p>This is useful to know because queue methods like {@code peek()} and {@code poll()}
     * also return {@code null} when the queue is empty.
     */
    private static void nullHandling() {
        log.info("=== nullHandling ===");

        LinkedList<String> list = new LinkedList<>();
        list.add(null);
        list.add("Java");

        log.info("LinkedList with null: {}", list);

        Queue<String> queue = new LinkedList<>();
        log.info("peek() on empty queue: {}", queue.peek());
        log.info("poll() on empty queue: {}", queue.poll());
    }

}
