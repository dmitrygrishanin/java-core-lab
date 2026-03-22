package com.dmytrohryshanin.javacorelab.collections.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates the most common operations and behavior of {@link ArrayList}.
 *
 * <p>This class covers:
 * <ul>
 *     <li>different ways to create an ArrayList</li>
 *     <li>basic add/get/set/remove operations</li>
 *     <li>search operations such as contains() and indexOf()</li>
 *     <li>replaceAll() and sort()</li>
 *     <li>difference between remove(index) and remove(object)</li>
 *     <li>safe removal with removeIf()</li>
 *     <li>how subList() works as a view</li>
 *     <li>difference between capacity and size</li>
 *     <li>a common pitfall when using {@code var} with {@code new ArrayList<>()}</li>
 * </ul>
 *
 * <p>The goal is not to build a reusable utility, but to provide runnable examples
 * for learning Java collections.
 */
@Slf4j
public class ArrayListExamples {

    public static void main(String[] args) {
        createArrayLists();
        basicOperations();
        searchOperations();
        replaceAllAndSort();
        removeIntegerPitfall();
        iterationAndRemoveIf();
        subListView();
        varPitfall();
    }

    /**
     * Shows different ways to create an {@link ArrayList}.
     *
     * <p>This method demonstrates:
     * <ul>
     *     <li>an empty list</li>
     *     <li>a copy created from another collection</li>
     *     <li>a list with predefined initial capacity</li>
     * </ul>
     *
     * <p>Important: initial capacity affects internal storage allocation,
     * but it does not change the list size.
     */

    private static void createArrayLists() {
        log.info("=== createArrayLists ===");

        List<String> empty = new ArrayList<>();
        empty.add("Add first item to empty list");
        empty.add("Add second item");

        List<String> copy = new ArrayList<>(empty);
        copy.add("Adding item to 'copy' collection does not affect 'empty' collection");

        ArrayList<String> withCapacity = new ArrayList<>(10);
        int withCapacitySize = withCapacity.size();
        log.info("withCapacity size: {}", withCapacitySize);
        withCapacity.add("Reserved capacity does not mean size = 10");

        log.info("empty: {}", empty);
        log.info("copy: {}", copy);
        log.info("withCapacity: {}", withCapacity);
    }

    /**
     * Demonstrates the core list operations.
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
    private static void basicOperations() {
        log.info("=== basicOperations ===");

        List<String> cities = new ArrayList<>();

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

        boolean removedByNonExistingObject = cities.remove("Non-existing object");
        log.info("Result of remove(nonExistingObject): {}", removedByNonExistingObject);
    }

    /**
     * Demonstrates common search-related operations on {@link ArrayList}.
     */
    private static void searchOperations() {
        log.info("=== searchOperations ===");

        List<String> languages = new ArrayList<>();
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
     * Demonstrates bulk element transformation and sorting.
     *
     * <p>{@code replaceAll()} applies the given operation to every element in the list.
     * {@code sort()} reorders the list according to the provided comparator.
     */
    private static void replaceAllAndSort() {
        log.info("=== replaceAllAndSort ===");

        List<String> names = new ArrayList<>();
        names.add("dmitry");
        names.add("anna");
        names.add("boris");

        log.info("Before replaceAll(): {}", names);
        names.replaceAll(String::toUpperCase);
        log.info("After replaceAll(): {}", names);

        names.sort(Comparator.naturalOrder());
        log.info("After sort(): {}", names);

        names.sort(Comparator.reverseOrder());
        log.info("After reverse sort(): {}", names);
    }

    /**
     * Demonstrates the overloaded remove() methods with {@link Integer} values.
     */
    private static void removeIntegerPitfall() {
        log.info("=== removeIntegerPitfall ===");

        List<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 20));
        log.info("Initial numbers: {}", numbers);

        Integer removedByIndex = numbers.remove(1);
        log.info("remove(1) removed element at index 1: {}", removedByIndex);
        log.info("After remove(1): {}", numbers);

        boolean removedByObject = numbers.remove(Integer.valueOf(20));
        log.info("remove(Integer.valueOf(20)) removed value 20: {}", removedByObject);
        log.info("After remove(Integer.valueOf(20)): {}", numbers);
    }

    /**
     * Demonstrates iteration and safe removal using {@code removeIf()}.
     */
    private static void iterationAndRemoveIf() {
        log.info("=== iterationAndRemoveIf ===");

        List<String> fruits = new ArrayList<>(List.of("apple", "banana", "apricot", "pear"));

        log.info("for-each iteration:");
        for (String fruit : fruits) {
            log.info(" - {}", fruit);
        }

        fruits.removeIf(fruit -> fruit.startsWith("ap"));
        log.info("After removeIf(fruit -> fruit.startsWith(\"ap\")): {}", fruits);
    }

    /**
     * Demonstrates that {@code subList()} returns a view backed by the original list.
     */
    private static void subListView() {
        log.info("=== subListView ===");

        List<String> letters = new ArrayList<>(List.of("A", "B", "C", "D"));
        List<String> middle = letters.subList(1, 3);

        log.info("Original list: {}", letters);
        log.info("subList(1, 3): {}", middle);

        middle.set(0, "Changed in subList");
        log.info("After middle.set(0, ...):");
        log.info("Original list: {}", letters);
        log.info("subList: {}", middle);
    }

    /**
     * Demonstrates a common pitfall when using var together with new ArrayList<>()
     *
     * <p>When writing:
     * <pre>{@code
     * var objects = new ArrayList<>();
     * }</pre>
     *
     * <p>the inferred type becomes {@code ArrayList<Object>}, not a more specific generic type.
     * This allows values of different types to be added to the same list.
     */
    private static void varPitfall() {
        log.info("=== varPitfall ===");

        var strings = new ArrayList<String>();
        strings.add("hello");
        log.info("var strings type is ArrayList<String>: {}", strings);

        var objects = new ArrayList<>();
        objects.add("text");
        objects.add(42);
        objects.add(true);

        log.info("var objects inferred as ArrayList<Object>: {}", objects);
        log.info("Avoid 'var list = new ArrayList<>()' in real code.");
    }
}
