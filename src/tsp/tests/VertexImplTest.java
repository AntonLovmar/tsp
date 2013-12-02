package tsp.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tsp.graph.Vertex;
import tsp.graph.VertexImpl;

public class VertexImplTest {

	@Test
	public void equalsWithEqualObject() {
		Vertex vertex1 = new VertexImpl(1, 1.14, 2.25);
		Vertex vertex2 = new VertexImpl(1, 1.14, 2.25);
		assertEquals(vertex1, vertex2);
	}
	
	@Test
	public void equalsWithNonEqualX() {
		Vertex vertex1 = new VertexImpl(1, 1.14, 2.25);
		Vertex vertex2 = new VertexImpl(1, 1.24, 2.25);
		assertFalse(vertex1.equals(vertex2));
	}
	
	@Test
	public void equalsWithNonEqualY() {
		Vertex vertex1 = new VertexImpl(1, 1.14, 2.25);
		Vertex vertex2 = new VertexImpl(1, 1.14, 2.35);
		assertFalse(vertex1.equals(vertex2));
	}
	
	@Test
	public void equalsWithNonEqualId() {
		Vertex vertex1 = new VertexImpl(1, 1.14, 2.25);
		Vertex vertex2 = new VertexImpl(2, 1.14, 2.25);
		assertFalse(vertex1.equals(vertex2));
	}

}
