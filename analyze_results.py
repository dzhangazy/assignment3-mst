import json
import matplotlib.pyplot as plt

# --- 1️⃣ Загружаем реальные данные ---
with open(r"src\main\resources\output.json", "r") as f:
    data = json.load(f)

results = data["results"]
results.sort(key=lambda x: x["input_stats"]["vertices"])  # по возрастанию вершин

# --- 2️⃣ Извлекаем реальные значения ---
graph_ids = [r["graph_id"] for r in results]
vertices = [r["input_stats"]["vertices"] for r in results]
edges = [r["input_stats"]["edges"] for r in results]

prim_time = [r["prim"]["execution_time_ms"] for r in results]
kruskal_time = [r["kruskal"]["execution_time_ms"] for r in results]

prim_ops = [r["prim"]["operations_count"] for r in results]
kruskal_ops = [r["kruskal"]["operations_count"] for r in results]

prim_cost = [r["prim"]["total_cost"] for r in results]
kruskal_cost = [r["kruskal"]["total_cost"] for r in results]

# --- 3️⃣ Настройка графиков ---
plt.style.use("seaborn-v0_8-darkgrid")
fig, axs = plt.subplots(3, 1, figsize=(10, 12))

# --- ⏱ Время выполнения ---
axs[0].plot(vertices, prim_time, 'o-', label="Prim", linewidth=2)
axs[0].plot(vertices, kruskal_time, 's--', label="Kruskal", linewidth=2)
axs[0].set_title("Execution Time vs Number of Vertices")
axs[0].set_xlabel("Number of Vertices")
axs[0].set_ylabel("Execution Time (ms)")
axs[0].legend()
axs[0].grid(True)

# --- ⚙️ Количество операций ---
axs[1].plot(vertices, prim_ops, 'o-', label="Prim", linewidth=2)
axs[1].plot(vertices, kruskal_ops, 's--', label="Kruskal", linewidth=2)
axs[1].set_title("Operations Count vs Number of Vertices")
axs[1].set_xlabel("Number of Vertices")
axs[1].set_ylabel("Operations Count")
axs[1].legend()
axs[1].grid(True)

# --- 💰 Общая стоимость MST ---
axs[2].plot(vertices, prim_cost, 'o-', label="Prim", linewidth=2)
axs[2].plot(vertices, kruskal_cost, 's--', label="Kruskal", linewidth=2)
axs[2].set_title("Total MST Cost (Correctness Check)")
axs[2].set_xlabel("Number of Vertices")
axs[2].set_ylabel("Total MST Cost")
axs[2].legend()
axs[2].grid(True)

# --- 4️⃣ Сохранение ---
plt.tight_layout()
plt.savefig("empirical_validation.png", dpi=300)
plt.show()

print("✅ Empirical validation plot saved as 'empirical_validation.png'")
