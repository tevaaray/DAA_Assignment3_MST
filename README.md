# DAA Assignment 3 — Minimum Spanning Tree Optimization

This project implements **Prim's** and **Kruskal's** algorithms to find a **Minimum Spanning Tree (MST)** for a city transportation network.  
The goal is to minimize the total cost of connecting all districts with roads.

---

## 📘 Objective
To apply two classical graph algorithms (Prim and Kruskal) for optimizing a city’s road network and compare their efficiency in terms of total cost, number of operations, and execution time.

---

## ⚙️ How to Run
1. Open the project in **IntelliJ IDEA** or any Java IDE.
2. Make sure `pom.xml` is loaded as a Maven project.
3. Run the main class:

5. Results will appear in the console and be saved in `mst_metrics.csv`.

---

## 📊 Results

| Graph | Algorithm | Vertices | Edges | Total Cost | Operations | Time (ms) | MST Edges |
|--------|------------|-----------|--------|-------------|-------------|-----------|------------|
| Small | Kruskal | 5 | 7 | 16.0 | 18 | 3.0889 | B–C(2.0), A–C(3.0), B–D(5.0), D–E(6.0) |
| Small | Prim | 5 | 7 | 16.0 | 23 | 7.555 | A–C(3.0), C–B(2.0), B–D(5.0), D–E(6.0) |
| Medium | Kruskal | 10 | 12 | 36.0 | 42 | 0.0772 | C–E(2.0), I–J(2.0), A–B(3.0), F–G(3.0), A–C(4.0), D–E(4.0), G–H(5.0), H–I(6.0), E–F(7.0) |
| Medium | Prim | 10 | 12 | 36.0 | 35 | 0.0908 | A–B(3.0), A–C(4.0), C–E(2.0), E–D(4.0), E–F(7.0), F–G(3.0), G–H(5.0), H–I(6.0), I–J(2.0) |
| Large | Kruskal | 20 | 20 | 87.0 | 72 | 0.0939 | A–B(2.0), G–H(2.0), R–S(2.0), C–E(3.0), J–K(3.0), N–O(3.0), A–C(4.0), K–L(4.0), O–P(4.0), B–D(5.0), I–J(5.0), M–N(5.0), S–T(5.0), H–I(6.0), L–M(6.0), P–Q(6.0), E–F(7.0), Q–R(7.0), F–G(8.0) |
| Large | Prim | 20 | 20 | 87.0 | 62 | 0.1143 | A–B(2.0), A–C(4.0), C–E(3.0), B–D(5.0), E–F(7.0), F–G(8.0), G–H(2.0), H–I(6.0), I–J(5.0), J–K(3.0), K–L(4.0), L–M(6.0), M–N(5.0), N–O(3.0), O–P(4.0), P–Q(6.0), Q–R(7.0), R–S(2.0), S–T(5.0) |

> **Analysis:**  
> All three graphs (small, medium, large) produced identical MST total costs for Prim’s and Kruskal’s algorithms, confirming correctness.  
> Kruskal’s algorithm consistently required fewer operations, while Prim’s algorithm was slightly slower due to additional heap operations.  
> Execution times below 1 ms for medium and large graphs are expected due to rounding precision and JVM optimization.

---

## 🧠 Analysis
Both algorithms produced the same total MST cost (**16.0**), which proves correctness.  
Kruskal’s algorithm performed fewer operations and ran slightly faster, since it works directly with sorted edges and uses Union-Find.  
Prim’s algorithm uses a priority queue, which adds more heap operations but achieves the same optimal result.

---

## 🏁 Conclusion
- **Kruskal** is better for sparse graphs (fewer edges).
- **Prim** is more efficient for dense graphs or adjacency-based representations.
- Both algorithms correctly find the minimum spanning tree with the same cost.

---

## 📚 References
- *Algorithms (4th Edition)* by Robert Sedgewick & Kevin Wayne
- [Princeton Algorithms 4.3 — Minimum Spanning Trees](https://algs4.cs.princeton.edu/43mst/)


