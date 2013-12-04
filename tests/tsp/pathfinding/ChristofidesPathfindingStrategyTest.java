package tsp.pathfinding;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import tsp.graph.Edge;
import tsp.graph.Graph;
import tsp.graph.Vertex;

public class ChristofidesPathfindingStrategyTest {

	@Test
	public void testGetMinimumSpanningTree() {
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
		Map<Vertex, Set<Edge>> tree = strategy.findMST(graph);
		int sum = 0;
		Set<Edge> edges = new HashSet<Edge>();
		for (Vertex v : tree.keySet()) {
			edges.addAll(tree.get(v));
		}
		for (Edge edge : edges) {
			sum += edge.getLength();
			// System.out.println(edge);
		}
		assertEquals(3, edges.size());
		assertEquals(10, sum);
	}

	@Test
	public void testMatchUneven() {
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
		Map<Vertex, Set<Edge>> tree = strategy.findMST(graph);

		tree = strategy.matchUneven(tree, graph);

		int sum = 0;
		Set<Edge> edges = new HashSet<Edge>();
		for (Vertex v : tree.keySet()) {
			edges.addAll(tree.get(v));
		}
		for (Edge edge : edges) {
			sum += edge.getLength();
			// System.out.println(edge);
		}
		assertEquals(4, edges.size());
		assertEquals(14, sum);
	}

}
