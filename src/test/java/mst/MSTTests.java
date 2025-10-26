package mst;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTests {
    @Test
    public void testMSTCorrectness() {
        List<Graph> graphs = Graph.fromJSON("src/input.json");
        for (Graph g : graphs) {
            PrimAlgorithm.Result prim = PrimAlgorithm.run(g);
            KruskalAlgorithm.Result kr = KruskalAlgorithm.run(g);

            assertEquals(prim.totalCost, kr.totalCost,
                    "Prim and Kruskal must have the same MST cost");
            assertEquals(g.vertices.size() - 1, prim.mstEdges.size(),
                    "Prim must have V-1 edges");
            assertEquals(g.vertices.size() - 1, kr.mstEdges.size(),
                    "Kruskal must have V-1 edges");
        }
    }
}
