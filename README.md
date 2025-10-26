🧮 Assignment 3 — Minimum Spanning Tree (MST)
Course: Algorithms & Object-Oriented Programming

Student: Jangazy Bakytzhan

Topic: Empirical Comparison of Prim’s and Kruskal’s Algorithms

Language: Java (Maven Project)

IDE: Visual Code

Submission: GitHub Repository

📘 Abstract

This project implements and empirically compares two fundamental algorithms for constructing a Minimum Spanning Tree (MST) — Prim’s and Kruskal’s.
Both algorithms were implemented in Java with modular object-oriented design, using JSON for input/output and a Maven-based structure for easy build and execution.

The comparison was based on:

Execution time (in milliseconds)

Operation count (number of algorithmic steps)

Total cost of the resulting MST

Empirical validation was performed using randomly generated graphs of different sizes, and results were analyzed using Python and Matplotlib.

📁 Project Structure
assignment3-mst/
│
├── src/
│   ├── main/java/mst/
│   │   ├── Edge.java
│   │   ├── Graph.java
│   │   ├── PrimAlgorithm.java
│   │   ├── KruskalAlgorithm.java
│   │   ├── MSTMain.java
│   │   └── Utils.java
│   ├── main/resources/
│   │   ├── input.json
│   │   └── output.json
│
├── analyze_results.py       ← Empirical analysis (Python)
├── empirical_validation.png ← Generated comparison plot
├── pom.xml                  ← Maven configuration
└── README.md                ← Project report

⚙️ Implementation Overview

🧩 Prim’s Algorithm

Builds the MST incrementally, always adding the lowest-weight edge that connects a new vertex to the tree.

Suitable for dense graphs.

Time complexity:

𝑂
(
𝑉
2
)
O(V
2
) using adjacency matrix

𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV) using min-heap

🧩 Kruskal’s Algorithm

Sorts all edges by weight and adds them one by one, skipping those that would form a cycle.

Uses Disjoint Set Union (DSU) structure.

Better suited for sparse graphs.

Time complexity: 
𝑂
(
𝐸
log
⁡
𝐸
)
O(ElogE) ≈ 
𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV)

🧠 Algorithm Design (OOP)
Class	Responsibility
Edge.java	Represents a weighted connection between two vertices.

Graph.java	Stores all vertices and edges, supports JSON import/export.

PrimAlgorithm.java	Implementation of Prim’s MST algorithm with operation tracking.

KruskalAlgorithm.java	Implementation of Kruskal’s MST algorithm using DSU.

MSTMain.java	Loads graphs from input.json, runs both algorithms, saves results to output.json.

Utils.java	Contains helper methods for timing, operations counting, and data formatting.

🧾 Input and Output

📥 Input (input.json)

Contains randomly generated graphs:

{
  "graphs": [
    {
      "id": 1,
      "vertices": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 3},
        {"from": "A", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 4}
      ]
    }
  ]
}

📤 Output (output.json)

Stores computed MST results:

{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {"vertices": 5, "edges": 7},
      "prim": {
        "total_cost": 16,
        "operations_count": 42,
        "execution_time_ms": 1.52
      },
      "kruskal": {
        "total_cost": 16,
        "operations_count": 37,
        "execution_time_ms": 1.28
      }
    }
  ]
}

🧪 Empirical Validation

The performance of Prim’s and Kruskal’s algorithms was empirically tested on 6 randomly generated graphs with varying sizes.

For each graph, the following metrics were recorded:

Execution time (ms)

Number of operations

Total MST cost

Visualization

The data from output.json was analyzed using analyze_results.py, producing the comparative plot below:

📊 Results & Discussion

Metric	Prim	Kruskal	Interpretation

Execution Time (ms)	Slightly higher on small graphs	Faster on small dense graphs	Kruskal benefits from edge sorting for small graphs

Operations Count	Increases roughly linearly with vertices	Slightly lower overall	Both consistent with 
𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV)
MST Total Cost	Identical	Identical	Confirms correctness of both algorithms

📈 Observations

Prim’s Algorithm shows more stable performance as graph size increases.

Kruskal’s Algorithm executes faster on smaller graphs due to efficient sorting and DSU operations.

Both algorithms produce identical MST total costs, confirming correctness.

The empirical data aligns well with theoretical complexity predictions.

🧮 Theoretical vs Empirical Behavior
Algorithm	Theoretical Complexity	Empirical Trend	Validation
Prim	
𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV)	Nearly linear with growth in vertices	✅ Matches
Kruskal	
𝑂
(
𝐸
log
⁡
𝐸
)
O(ElogE)	Slightly lower slope than Prim	✅ Matches
Both	Produce identical MST total cost	Observed	✅ Correct
<img width="3000" height="3600" alt="empirical_validation" src="https://github.com/user-attachments/assets/6281e0fe-80d5-4390-a24b-540f31d9418a" />

💾 How to Run the Project

1️⃣ Build (Maven)

mvn compile

2️⃣ Run Java Program

mvn compile exec:java '-Dexec.mainClass=mst.MSTMain'


This reads input.json, executes both algorithms, and writes results to output.json.

3️⃣ Generate Plots (Python)

python analyze_results.py


Output:

✅ Empirical validation plot saved as 'empirical_validation.png'

🧰 Tools & Technologies
Tool	Purpose
Java (JDK 17)	Algorithm implementation
Maven	Build automation
JSON.simple	JSON parsing
Python + Matplotlib	Visualization
GitHub	Version control and submission

🧩 Conclusion

Both Prim’s and Kruskal’s algorithms were successfully implemented and tested.
The results show:

Identical MST costs (correctness ✅)

Similar growth in operation count and runtime (efficiency ✅)

Behavior consistent with theoretical complexity predictions (validation ✅)

Hence, both algorithms are correct, efficient, and empirically verified to behave according to their theoretical asymptotic bounds.
