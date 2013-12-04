package tsp.pathfinding;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tsp.graph.Edge;
import tsp.graph.Graph;
import tsp.graph.Vertex;
import tsp.pathfinding.ChristofidesPathfindingStrategy.SpanningTree;

public class ChristofidesPathfindingStrategyTest {

	@Test
	public void getMinimumSpanningTree() {
		Vertex vertex = new Vertex(0, 1.0, 1.0);
		Vertex vertex2 = new Vertex(1, 4.0, 1.0);
		Vertex vertex3 = new Vertex(2, 1.0, 5.0);
		Vertex vertex4 = new Vertex(3, 4.0, 5.0);
		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex);
		vertices.add(vertex2);
		vertices.add(vertex3);
		vertices.add(vertex4);

		Graph graph = new Graph(vertices);
		ChristofidesPathfindingStrategy strategy = new ChristofidesPathfindingStrategy();
		SpanningTree tree = strategy.findMST(graph);
		int sum = 0;
		for (Edge edge : tree.getEdges()) {
			sum += edge.getLength();
			System.out.println(edge);
		}
		assertEquals(3, tree.getEdges().size());
		assertEquals(10, sum);
	}

}
