import java.util.*;

public interface Search<V> {
    List<Vertex<V>> search(Vertex<V> source, Vertex<V> target);
}
