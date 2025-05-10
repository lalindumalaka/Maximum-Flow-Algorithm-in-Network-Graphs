// src/algorithm/Algorithm.java
package algorithm;

import network.Edge;
import network.Network;
import java.util.*;


public class Algorithm implements MaxFlow {
    @Override
    public int findMaxFlow(Network network, int source, int sink) {
        int totalFlow = 0;
        List<Edge>[] graph = network.getGraph();
        while (true) {
            Edge[] parent = new Edge[graph.length];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty() && parent[sink] == null) {
                int u = queue.poll();
                for (Edge e : graph[u]) {
                    if (parent[e.to] == null && e.residualCapacity() > 0 &&
                            e.to != source) {
                        parent[e.to] = e;
                        queue.add(e.to);
                    }
                }
            }

            if (parent[sink] == null) break;

            // Find minimum residual capacity
            int pathFlow = Integer.MAX_VALUE;
            for (Edge e = parent[sink]; e != null; e = parent[e.from]) {
                pathFlow = Math.min(pathFlow, e.residualCapacity());
            }

            // Add flow
            for (Edge e = parent[sink]; e != null; e = parent[e.from]) {
                e.addFlow(pathFlow);
            }

            totalFlow += pathFlow;
            System.out.println("Augmented path flow: " + pathFlow);
        }

        return totalFlow;
    }
}
