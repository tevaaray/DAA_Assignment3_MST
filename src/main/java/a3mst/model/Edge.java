package a3mst.model;

public class Edge implements Comparable<Edge> {
    public final int u;
    public final int v;
    public final double w;

    public Edge(int u, int v, double w) {
        if (u == v) throw new IllegalArgumentException("Self-loop edge not allowed");
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge other) {
        // compare by weight
        return Double.compare(this.w, other.w);
    }

    @Override
    public String toString() {
        // simple string format for printing
        return "(" + u + " - " + v + ", w=" + w + ")";
    }
}
