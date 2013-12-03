package tsp.pathfinding;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.reader.GraphReader;

/**
 * System test for opt.
 * 
 * @author peter
 * 
 */
public class TwoOptStrategyTest {

	@Test
	public void testOptimizeGivesShorterPath() {
		GraphReader reader = null;
		try {
			reader = new GraphReader(new FileReader(new File("lib/big-test.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Graph graph = reader.readGraph();
		Path path = new GreedyPathfindingStrategy().findPath(graph);

		int initialPathLength = graph.totalLength(path.getPath());

		path = new TwoOptStrategy().optimize(path, graph, System.currentTimeMillis() + 1000);

		assertTrue(graph.totalLength(path.getPath()) < initialPathLength);
	}
}
