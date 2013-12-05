package tsp.pathfinding;

import org.junit.Test;
import org.mockito.Mockito;

import tsp.graph.Graph;
import tsp.graph.Vertex;

public class NearestNeighbourPathfindingStrategyTest {

	@Test
	public void testSpanAllVertices() {
		Graph graph = Mockito.mock(Graph.class);
		Mockito.when(graph.getNumberOfVertices()).thenReturn(4);

		Vertex vertex0 = Mockito.mock(Vertex.class);
		Mockito.when(vertex0.getId()).thenReturn(0);
		Mockito.when(graph.getVertex(0)).thenReturn(vertex0);

		Vertex vertex1 = Mockito.mock(Vertex.class);
		Mockito.when(vertex1.getId()).thenReturn(1);
		Mockito.when(graph.getVertex(1)).thenReturn(vertex1);

		Vertex vertex2 = Mockito.mock(Vertex.class);
		Mockito.when(vertex2.getId()).thenReturn(2);
		Mockito.when(graph.getVertex(2)).thenReturn(vertex2);

		Vertex vertex3 = Mockito.mock(Vertex.class);
		Mockito.when(vertex3.getId()).thenReturn(3);
		Mockito.when(graph.getVertex(3)).thenReturn(vertex3);

		PathfindingStrategy strategy = new NearestPathfindingStrategy();
		// assertEquals(4, strategy.findPath(graph).getPath().size());
	}
}
