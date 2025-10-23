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

| Algorithm | Vertices | Edges | Total Cost | Operations | Time (ms) | MST Edges |
|------------|-----------|--------|-------------|-------------|-----------|------------|
| Kruskal | 5 | 7 | 16.0 | 18 | 7         | B–C(2.0), A–C(3.0), B–D(5.0), D–E(6.0) |
| Prim | 5 | 7 | 16.0 | 23 | 43         | A–C(3.0), C–B(2.0), B–D(5.0), D–E(6.0) |

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


