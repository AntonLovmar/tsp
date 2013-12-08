package tsp.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class VertexTest {

	@Test
	public void equalsWithEqualObject() {
		Vertex vertex1 = new Vertex(1, 1.14, 2.25);
		Vertex vertex2 = new Vertex(1, 1.14, 2.25);
		assertEquals(vertex1, vertex2);
	}

	@Test
	public void equalsWithNonEqualX() {
		Vertex vertex1 = new Vertex(1, 1.14, 2.25);
		Vertex vertex2 = new Vertex(1, 1.24, 2.25);
		assertFalse(vertex1.equals(vertex2));
	}

	@Test
	public void equalsWithNonEqualY() {
		Vertex vertex1 = new Vertex(1, 1.14, 2.25);
		Vertex vertex2 = new Vertex(1, 1.14, 2.35);
		assertFalse(vertex1.equals(vertex2));
	}

	@Test
	public void equalsWithNonEqualId() {
		Vertex vertex1 = new Vertex(1, 1.14, 2.25);
		Vertex vertex2 = new Vertex(2, 1.14, 2.25);
		assertFalse(vertex1.equals(vertex2));
	}

}
