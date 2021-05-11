package challenges.graph.utils;

import challenges.graph.interfaces.DoubleWeightedIntGraph;
import challenges.graph.interfaces.DoubleWeightedIntGraph.NeighborsAndWeights;
import challenges.graph.interfaces.WeightedGraph;
import challenges.graph.interfaces.WeightedGraph.VertexAndWeight;
import challenges.stacksQueues.IndexedDoublePriorityQueue;
import challenges.stacksQueues.IntDynamicArray;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class Paths {
    /**
     * Determines whether or not a sequence of vertices is a valid path through the graph, and if so, determines the
     * total sum of the weights for the graph.
     *
     * @param graph A weighted graph
     * @param path  An iterable of vertex labels
     * @param <T>   The value associated with each vertex
     * @return null if the path doesn't exist, otherwise returns the sum of the weights along the path.
     */
    public static <T> Double pathWeight(WeightedGraph<T, Double> graph, Iterable<T> path) {
        Iterator<T> it = path.iterator();
        T vertex = it.next();
        Double sum = null;
        while (it.hasNext()) {
            T next = it.next();
            List<VertexAndWeight<T, Double>> vws = graph.neighborsWithWeight(vertex);
            VertexAndWeight<T, Double> vertexWeight = vws.stream()
                    .filter(vw -> vw.getVertex().equals(next))
                    .findAny()
                    .orElse(null);
            if (vertexWeight == null) return null;
            double weight = vertexWeight.getWeight();
            sum = sum == null ? weight : sum + weight;
        }
        return sum;
    }

    /**
     * Finds a shortest path from the start node to the finish node
     *
     * @param graph  a double weighted graph on int nodes
     * @param start  the starting node
     * @param finish the ending node
     * @return
     */
    public static int[] shortestPath(DoubleWeightedIntGraph graph, int start, int finish) {
        IndexedDoublePriorityQueue queue = new IndexedDoublePriorityQueue(graph.size());
        BitSet visited = new BitSet(graph.size());
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        queue.add(start, 0);

        int vertex = -1;

        while (!queue.isEmpty()) {
            double weight = queue.findMinWeight();
            vertex = queue.removeMin();
            visited.set(vertex);

            if (vertex == finish) break;

            NeighborsAndWeights neighborsAndWeights = graph.neighborsWithWeight(vertex);
            int[] nbrs = neighborsAndWeights.getNeighbors();
            double[] wgts = neighborsAndWeights.getWeights();

            for (int i = 0; i < neighborsAndWeights.size(); i++) {
                if (!visited.get(nbrs[i])) {
                    parent[nbrs[i]] = i;
                    queue.put(nbrs[i], wgts[i] + weight);
                }
            }
        }

        IntDynamicArray array = new IntDynamicArray();

        while (vertex != start) {
            array.push(vertex);
            vertex = parent[vertex];
        }
        array.push(vertex);

        return array.reversed();
    }
}