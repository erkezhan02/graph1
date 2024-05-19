import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    @Override
    public List<Vertex<V>> search(Vertex<V> source, Vertex<V> target) {
        // Create a queue for BFS
        Queue<Vertex<V>> queue = new LinkedList<>();
        // Create a set to keep track of visited vertices
        Set<Vertex<V>> visited = new HashSet<>();
        // Create a map to keep track of parent vertices for path reconstruction
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        // Initialize with source vertex
        queue.offer(source);
        visited.add(source);

        // Perform BFS
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            // Check if we have reached the target vertex
            if (current.equals(target)) {
                // Reconstruct the path from target to source
                return reconstructPath(parentMap, source, target);
            }
            // Explore adjacent vertices
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        // If target is unreachable
        return null;
    }

    // Helper method to reconstruct the path from target to source
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
