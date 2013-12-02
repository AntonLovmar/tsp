package tsp.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import tsp.graph.Graph;
import tsp.reader.GraphReader;

public class GraphReaderTest {

	@Test
	public void numberOfVerticesCorrect() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File("/home/anton/Github/tsp/tests/parsetest.txt"));
			GraphReader reader = new GraphReader(fileReader);
			Graph graph = reader.readGraph();
			assertEquals(3, graph.getVertices().size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void numberOfEdgesCorrect() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File("/home/anton/Github/tsp/tests/parsetest.txt"));
			GraphReader reader = new GraphReader(fileReader);
			Graph graph = reader.readGraph();
			assertEquals(3, graph.getEdges().size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
