package tsp.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tsp.graph.Graph;
import tsp.reader.InputReader;

public class InputReaderTest {

	@Test
	public void numberOfVerticesCorrect() {
		InputReader reader = new InputReader();
		Graph graph = reader.readInput();
		assertEquals(3, graph.getVertices().size());
	}
	
	@Test
	public void numberOfEdgesCorrect() {
		InputReader reader = new InputReader();
		Graph graph = reader.readInput();
		assertEquals(3, graph.getEdges().size());
	}
}
