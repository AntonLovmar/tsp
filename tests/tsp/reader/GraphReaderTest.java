package tsp.reader;

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
			fileReader = new FileReader(new File("lib/parsetest.txt"));
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
			fileReader = new FileReader(new File("lib/parsetest.txt"));
			GraphReader reader = new GraphReader(fileReader);
			Graph graph = reader.readGraph();
			assertEquals(3, graph.getNumberOfEdges());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
