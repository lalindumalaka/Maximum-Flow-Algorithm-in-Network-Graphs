package network;

public class Edge {
    public int from, to, capacity, flow;
    public Edge reverse;
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }
    public int residualCapacity() {
        return capacity - flow;
    }
    public void addFlow(int addedFlow) {
        this.flow += addedFlow;
        this.reverse.flow -= addedFlow;
    }
    @Override
    public String toString() {
        return String.format("(%d -> %d | cap: %d, flow: %d)", from, to,
                capacity, flow);
    }
}