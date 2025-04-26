package network;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<Edge>[] graph;
    @SuppressWarnings("unchecked")
    public Network(int numNodes) {
        graph = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            graph[i] = new ArrayList<>();
        }
    }
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);
        forward.reverse = backward;
        backward.reverse = forward;
        graph[from].add(forward);
        graph[to].add(backward);
    }
    public List<Edge>[] getGraph() {
        return graph;
    }
    public int size() {
        return graph.length;
    }
}