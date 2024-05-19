import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distanceMap;

    @Override
    public List<Vertex<V>> search(Vertex<V> source, Vertex<V> target) {
        // Comparator to compare vertices based on their tentative distances
        Comparator<Vertex<V>> distanceComparator = Comparator.comparingDouble(v -> getTentativeDistance(v));
        // Priority queue to store vertices based on their tentative distances
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(distanceComparator);
        // Set to keep track of visited vertices
        Set<Vertex<V>> visited = new HashSet<>();
        // Map to store parent vertices for path reconstruction
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();

        // Initialize distances
        distanceMap = new HashMap<>();
        for (Vertex<V> vertex : source.getAdjacentVertices().keySet()) {
            double weight = source.getAdjacentVertices().get(vertex);
            distanceMap.put(vertex, weight);
            queue.offer(vertex);
            parentMap.put(vertex, source);
        }
        visited.add(source);

        // Perform Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            visited.add(current);
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    double distanceThroughCurrent = getTentativeDistance(current) + current.getAdjacentVertices().get(neighbor);
                    if (distanceThroughCurrent < getTentativeDistance(neighbor)) {
                        distanceMap.put(neighbor, distanceThroughCurrent);
                        parentMap.put(neighbor, current);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // Reconstruct the shortest path from target to source
        return reconstructPath(parentMap, source, target);
    }

    // Helper method to get the tentative distance of a vertex
    private double getTentativeDistance(Vertex<V> vertex) {
        return distanceMap.getOrDefault(vertex, Double.POSITIVE_INFINITY);
    }

    // Helper method to reconstruct the shortest path from target to source
    private List<Vertex<V>> reconstructPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> source, Vertex<V> target) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> current = target;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
