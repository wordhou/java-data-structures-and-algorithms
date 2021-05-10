package challenges.graph;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface WeightedGraph<T, W> {
    T addVertex(T vertex);

    void addEdge(T vertex1, T vertex2, W weight);

    @Nullable
    W getWeight(T vertex1, T vertex2);

    List<VertexAndWeight<T, W>> neighborsWithWeight(T vertex);

    List<T> neighbors(T vertex);

    Set<T> getVertices();

    int size();
}
