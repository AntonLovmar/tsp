package tsp.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class TSPGraphTest {

	@Test
	public void getDistanceBetweenVertices() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);

		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);

		List<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(vertex1);
		vertices.add(vertex2);
		vertices.add(vertex3);

		Graph graph = new TSPGraph(vertices);
		assertEquals(3, graph.distanceBetween(vertex1, vertex2));
		assertEquals(6, graph.distanceBetween(vertex1, vertex3));
		assertEquals(8, graph.distanceBetween(vertex2, vertex3));

	}
}
