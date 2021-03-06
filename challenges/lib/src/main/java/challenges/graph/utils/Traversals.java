package challenges.graph.utils;

import challenges.graph.interfaces.MutableIntGraph;
import challenges.graph.interfaces.Graph;
import challenges.stacksQueues.IntDynamicArray;
import challenges.stacksQueues.IntStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Traversals {
    public static <T> void breadthFirstTraversal(Graph<T> graph, T init, Consumer<? super T> consumer) {
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        if (!graph.contains(init)) throw new NoSuchElementException();
        visited.add(init);
        queue.add(init);

        while (!queue.isEmpty()) {
            T vertex = queue.remove();
            consumer.accept(vertex);
            Collection<T> neighbors = graph.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        queue.add(v);
                        visited.add(v);
                    });
        }
    }

    public static void breadthFirstTraversal(MutableIntGraph graph, int init, IntConsumer consumer) {
        Queue<Integer> queue = new LinkedList<>();
        BitSet visited = new BitSet();
        if (init >= graph.size()) throw new IndexOutOfBoundsException();
        queue.add(init);
        visited.set(init);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            consumer.accept(vertex);
            int[] neighbors = graph.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    queue.add(v);
                    visited.set(v);
                }
            }
        }
    }

    public static <T> void depthFirstTraversal(Graph<T> graph, T init, Consumer<? super T> consumer) {
        Deque<T> stack = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        if (!graph.contains(init)) throw new NoSuchElementException();
        visited.add(init);
        stack.push(init);

        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            consumer.accept(vertex);
            Collection<T> neighbors = graph.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        stack.push(v);
                        visited.add(v);
                    });
        }
    }

    public static void depthFirstTraversal(MutableIntGraph graph, int init, IntConsumer consumer) {
        IntStack stack = new IntDynamicArray();
        BitSet visited = new BitSet();
        if (init >= graph.size()) throw new IndexOutOfBoundsException();
        stack.push(init);
        visited.set(init);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            consumer.accept(vertex);
            int[] neighbors = graph.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    stack.push(v);
                    visited.set(v);
                }
            }
        }
    }

    public static <T> boolean connected(Graph<T> graph, T from, T to) {
        if (!graph.contains(from) || !graph.contains(to))
            throw new NoSuchElementException("Vertex is not in graph");

        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        if (!graph.contains(from)) throw new NoSuchElementException();
        visited.add(from);
        queue.add(from);

        while (!queue.isEmpty()) {
            T vertex = queue.remove();
            if (vertex.equals(from)) return true;
            Collection<T> neighbors = graph.neighbors(vertex);
            neighbors.stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        queue.add(v);
                        visited.add(v);
                    });
        }
        return false;
    }

    public static boolean connected(MutableIntGraph graph, int from, int to) {
        if (from >= graph.size() || to >= graph.size())
            throw new NoSuchElementException("Vertex is not in graph");

        IntStack stack = new IntDynamicArray();
        BitSet visited = new BitSet();
        stack.push(from);
        visited.set(from);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (vertex == to) return true;
            int[] neighbors = graph.neighbors(vertex);
            for (int v : neighbors) {
                if (!visited.get(v)) {
                    stack.push(v);
                    visited.set(v);
                }
            }
        }
        return false;
    }
}
