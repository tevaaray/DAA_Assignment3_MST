package a3mst.algo;

public class UnionFind {
    private final int[] parent;
    private final int[] rank;
    public long opCount = 0; // count of find/union operations

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        // initially each node is its own parent
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        opCount++;
        // path compression
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int a, int b) {
        opCount++;
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return false; // already connected
        // union by rank
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[rb] < rank[ra]) parent[rb] = ra;
        else { parent[rb] = ra; rank[ra]++; }
        return true;
    }
}
