public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");

        graph.addEdge(a, b, 1.0);
        graph.addEdge(a, c, 2.0);
        graph.addEdge(b, c, 3.0);


        System.out.println("Vertices:");
        for (Vertex<String> vertex : graph.getVertices()) {
            System.out.println(vertex.getData());
        }

        System.out.println("Edges from A:");
        for (Edge<String> edge : graph.getEdges(a)) {
            System.out.println(edge.getSource().getData() + " -> " + edge.getDest().getData() + ", Weight: " + edge.getWeight());
        }
    }
}
