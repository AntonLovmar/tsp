package tsp.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class PathTest {

	@Test
	public void testReverseBetweenVertices() {
		Vertex vertex = Mockito.mock(Vertex.class);
		Mockito.when(vertex.getId()).thenReturn(1);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Vertex vertex4 = Mockito.mock(Vertex.class);
		Mockito.when(vertex4.getId()).thenReturn(4);

		List<Vertex> pathList = new ArrayList<>();

		pathList.add(vertex);
		pathList.add(vertex2);
		pathList.add(vertex3);
		pathList.add(vertex4);

		Path path = new Path(pathList);

		path.reverseVertices(vertex, vertex4);

		pathList = path.getPath();

		assertEquals(1, pathList.get(0).getId());
		assertEquals(3, pathList.get(1).getId());
		assertEquals(2, pathList.get(2).getId());
		assertEquals(4, pathList.get(3).getId());
	}

}
