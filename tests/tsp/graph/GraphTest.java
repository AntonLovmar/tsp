package tsp.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class GraphTest {

	@Test
	public void getDistanceBetweenVertices() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Mockito.when(vertex1.getX()).thenReturn(0.0);
		Mockito.when(vertex1.getY()).thenReturn(0.0);

		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Mockito.when(vertex2.getX()).thenReturn(1.0);
		Mockito.when(vertex2.getY()).thenReturn(1.0);

		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Mockito.when(vertex3.getX()).thenReturn(3.0);
		Mockito.when(vertex3.getY()).thenReturn(4.0);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);

		Graph graph = new Graph(vertices);
		assertEquals(1, graph.distanceBetween(vertex1, vertex2));
		assertEquals(5, graph.distanceBetween(vertex1, vertex3));
		assertEquals(4, graph.distanceBetween(vertex2, vertex3));

	}

	@Test
	public void getNeighbourList() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Mockito.when(vertex1.getX()).thenReturn(0.0);
		Mockito.when(vertex1.getY()).thenReturn(0.0);

		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Mockito.when(vertex2.getX()).thenReturn(1.0);
		Mockito.when(vertex2.getY()).thenReturn(1.0);

		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Mockito.when(vertex3.getX()).thenReturn(3.0);
		Mockito.when(vertex3.getY()).thenReturn(4.0);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);

		Graph graph = new Graph(vertices);
		assertEquals(vertex2, graph.getNeighbourList(vertex1).get(0));
		assertEquals(vertex3, graph.getNeighbourList(vertex1).get(1));
		assertEquals(vertex1, graph.getNeighbourList(vertex2).get(0));
		assertEquals(vertex3, graph.getNeighbourList(vertex2).get(1));

	}
}
