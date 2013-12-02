package tsp.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class TSPGraphTest {

	@Test
	public void getEdgeFromExistingVertex() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);

		Edge edge1 = mockedEdge(vertex1, vertex2);
		Edge edge2 = mockedEdge(vertex1, vertex3);
		Edge edge3 = mockedEdge(vertex2, vertex3);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		Graph graph = new TSPGraph(vertices, edges);
		assertEquals(2, graph.getEdgesForVertex(vertex1).size());
	}

	@Test
	public void getEdgeFromNonExistingVertex() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Vertex vertex4 = Mockito.mock(Vertex.class);
		Mockito.when(vertex4.getId()).thenReturn(4);

		Edge edge1 = mockedEdge(vertex1, vertex2);
		Edge edge2 = mockedEdge(vertex1, vertex3);
		Edge edge3 = mockedEdge(vertex2, vertex3);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		Graph graph = new TSPGraph(vertices, edges);
		assertNull(graph.getEdgesForVertex(vertex4));
	}

	private Edge mockedEdge(Vertex vertex1, Vertex vertex2) {
		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		Edge edge = Mockito.mock(Edge.class);

		Mockito.when(edge.getVertices()).thenReturn(vertices);
		return edge;
	}

	@Test
	public void getDistanceBetweenVertices() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);

		Edge edge1 = mockedEdge(vertex1, vertex2);
		Mockito.when(edge1.length()).thenReturn(3);
		Edge edge2 = mockedEdge(vertex1, vertex3);
		Mockito.when(edge2.length()).thenReturn(6);
		Edge edge3 = mockedEdge(vertex2, vertex3);
		Mockito.when(edge3.length()).thenReturn(8);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		Graph graph = new TSPGraph(vertices, edges);
		assertEquals(3, graph.distanceBetween(vertex1, vertex2));
		assertEquals(6, graph.distanceBetween(vertex1, vertex3));
		assertEquals(8, graph.distanceBetween(vertex2, vertex3));

	}
}
