package com.dmytrohryshanin.javacorelab.collections.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Compares {@link ArrayList} and {@link LinkedList}.
 *
 * <p>This class focuses on practical differences between the two implementations:
 * <ul>
 *     <li>both implement {@link List}</li>
 *     <li>both support the same core List API</li>
 *     <li>{@link LinkedList} also implements {@link Deque}</li>
 *     <li>{@link ArrayList} is usually the default choice for general-purpose lists</li>
 *     <li>{@link LinkedList} is mainly useful when you explicitly need list + deque behavior in one type</li>
 * </ul>
 *
 * <p>The goal is not to provide precise performance benchmarks, but to demonstrate
 * behavior and usage patterns.
 */
@Slf4j
public class ArrayListVsLinkedList {

    public static void main(String[] args) {
        compareAsLists();
        compareInsertAtBeginning();
        compareIterationStyle();
        showDequeCapability();
        practicalRecommendation();
    }

    /**
     * Demonstrates that both ArrayList and LinkedList support the same core List operations.
     *
     * <p>This includes:
     * <ul>
     *     <li>adding elements</li>
     *     <li>reading by index</li>
     *     <li>updating by index</li>
     *     <li>removing by index</li>
     * </ul>
     */
    private static void compareAsLists() {
        log.info("=== compareAsLists ===");

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        log.info("Initial ArrayList: {}", arrayList);
        log.info("Initial LinkedList: {}", linkedList);

        log.info("ArrayList get(1): {}", arrayList.get(1));
        log.info("LinkedList get(1): {}", linkedList.get(1));

        arrayList.set(1, "X");
        linkedList.set(1, "X");

        log.info("After set(1, \"X\") ArrayList: {}", arrayList);
        log.info("After set(1, \"X\") LinkedList: {}", linkedList);

        arrayList.remove(0);
        linkedList.remove(0);

        log.info("After remove(0) ArrayList: {}", arrayList);
        log.info("After remove(0) LinkedList: {}", linkedList);
        log.info("Both classes support the same List API, but that does not mean they are equally suitable in all scenarios.");
    }

    /**
     * Demonstrates a scenario where inserting at the beginning looks the same in code,
     * but LinkedList offers a more natural API for working at both ends.
     */
    private static void compareInsertAtBeginning() {
        log.info("=== compareInsertAtBeginning ===");

        List<String> arrayList = new ArrayList<>(List.of("B", "C"));
        LinkedList<String> linkedList = new LinkedList<>(List.of("B", "C"));

        arrayList.add(0, "A");
        linkedList.addFirst("A");

        log.info("ArrayList after add(0, \"A\"): {}", arrayList);
        log.info("LinkedList after addFirst(\"A\"): {}", linkedList);
        log.info("LinkedList exposes first/last operations directly, while ArrayList stays focused on indexed access.");
    }

    /**
     * Demonstrates that both implementations are iterated in the same way through the List interface.
     */
    private static void compareIterationStyle() {
        log.info("=== compareIterationStyle ===");

        List<String> arrayList = new ArrayList<>(List.of("red", "green", "blue"));
        List<String> linkedList = new LinkedList<>(List.of("red", "green", "blue"));

        log.info("for-each over ArrayList:");
        for (String color : arrayList) {
            log.info(" - {}", color);
        }

        log.info("for-each over LinkedList:");
        for (String color : linkedList) {
            log.info(" - {}", color);
        }

        log.info("Iteration syntax is identical when you program against the List interface.");
    }

    /**
     * Demonstrates an important capability of LinkedList that ArrayList does not have directly:
     * using it as a deque.
     *
     * <p>LinkedList supports operations on both ends such as:
     * <ul>
     *     <li>{@code addFirst()}</li>
     *     <li>{@code addLast()}</li>
     *     <li>{@code removeFirst()}</li>
     *     <li>{@code removeLast()}</li>
     * </ul>
     */
    private static void showDequeCapability() {
        log.info("=== showDequeCapability ===");

        Deque<String> deque = new LinkedList<>();

        deque.addFirst("middle");
        deque.addFirst("start");
        deque.addLast("end");

        log.info("LinkedList as Deque: {}", deque);
        log.info("removeFirst(): {}", deque.removeFirst());
        log.info("removeLast(): {}", deque.removeLast());
        log.info("After deque removals: {}", deque);
        log.info("ArrayList does not implement Deque directly.");
    }

    /**
     * Prints a concise practical recommendation about when to choose each implementation.
     *
     * <p>This is not a strict rule, but a useful default guideline for everyday coding.
     */
    private static void practicalRecommendation() {
        log.info("=== practicalRecommendation ===");

        log.info("Use ArrayList by default for general-purpose List usage.");
        log.info("Use LinkedList when you specifically need one type that works as both List and Deque.");
        log.info("If you only need deque or queue operations, prefer ArrayDeque in most real applications.");
        log.info("Do not choose LinkedList only because it sounds more 'dynamic'.");
    }
}
