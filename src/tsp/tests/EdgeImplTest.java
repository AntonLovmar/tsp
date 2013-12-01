package tsp.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import tsp.graph.Edge;
import tsp.graph.EdgeImpl;
import tsp.graph.Vertex;

public class EdgeImplTest {

	@Test
	public void contructorTest() {
		Edge edge = new EdgeImpl(Mockito.mock(Vertex.class), Mockito.mock(Vertex.class));
		assertEquals(2, edge.getVertices().size());
	}
	
	@Test
	public void lengthTest() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getX()).thenReturn(2.32);
		Mockito.when(vertex1.getY()).thenReturn(3.67);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getX()).thenReturn(6.77);
		Mockito.when(vertex2.getY()).thenReturn(5.56);
		
		Edge edge = new EdgeImpl(vertex1, vertex2);
		assertEquals(13, edge.length());	
	}
	
	@Test
	public void updateExistingVertexWithNew() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Mockito.when(vertex1.getX()).thenReturn(2.32);
		Mockito.when(vertex1.getY()).thenReturn(3.67);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Mockito.when(vertex2.getX()).thenReturn(6.77);
		Mockito.when(vertex2.getY()).thenReturn(5.56);
		
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(3);
		Mockito.when(vertex2.getX()).thenReturn(6.77);
		Mockito.when(vertex2.getY()).thenReturn(5.56);
		
		Edge edge = new EdgeImpl(vertex1, vertex2);
		assertTrue(edge.updateEdge(vertex1, vertex3));
	}
	
	@Test
	public void updateNonExistingVertex() {
		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Mockito.when(vertex1.getX()).thenReturn(2.32);
		Mockito.when(vertex1.getY()).thenReturn(3.67);
		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Mockito.when(vertex2.getX()).thenReturn(6.77);
		Mockito.when(vertex2.getY()).thenReturn(5.56);
		
		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(3);
		Mockito.when(vertex2.getX()).thenReturn(6.77);
		Mockito.when(vertex2.getY()).thenReturn(5.56);
		
		Edge edge = new EdgeImpl(vertex1, vertex2);
		assertFalse(edge.updateEdge(vertex3, vertex3));
	}
}
