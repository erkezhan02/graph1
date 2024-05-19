import java.util.*;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        if (!map.containsKey(vertex)) {
            map.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(dest)) {
            addVertex(dest);
        }
        map.get(source).add(new Edge<>(source, dest, weight));
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return map.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }
}
