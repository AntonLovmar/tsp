package tsp.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class PathTest {

	@Test
	public void swapVertices() {
		Vertex vertex = Mockito.mock(Vertex.class);
		Mockito.when(vertex.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Vertex vertex4 = Mockito.mock(Vertex.class);
		Mockito.when(vertex4.getId()).thenReturn(4);

		Path path = new Path(4);
		path.addToPath(vertex);
		path.addToPath(vertex2);
		path.addToPath(vertex3);
		path.addToPath(vertex4);

		path.swapVerticesAt(1, 3);
		assertEquals(4, path.getVertex(1).getId());
		assertEquals(2, path.getVertex(3).getId());
		assertEquals(1, path.getVertex(0).getId());
		assertEquals(3, path.getVertex(2).getId());
	}

	@Test
	public void testReverseBetweenIndices() {
		Vertex vertex = Mockito.mock(Vertex.class);
		Mockito.when(vertex.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Vertex vertex4 = Mockito.mock(Vertex.class);
		Mockito.when(vertex4.getId()).thenReturn(4);

		Path path = new Path(4);
		path.addToPath(vertex);
		path.addToPath(vertex2);
		path.addToPath(vertex3);
		path.addToPath(vertex4);

		path.reverseBetweenIndices(1, 2);

		assertEquals(1, path.getPath().get(0).getId());
		assertEquals(3, path.getPath().get(1).getId());
		assertEquals(2, path.getPath().get(2).getId());
		assertEquals(4, path.getPath().get(3).getId());
	}

}
