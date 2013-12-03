package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;

/**
 * The responsibility of a pathfinding strategy is to find a path through a
 * graph in which all vertices are visited.
 * 
 * @author anton
 * 
 */
public interface OptimizationStrategy {

	/**
	 * Improves the path by decreasing the length.
	 * 
	 * @param path
	 *            The path to improve.
	 * @param graph
	 *            A graph in which the path will be found
	 * @param deadline
	 *            the time which we will fail
	 * @return A path through the graph
	 */
	public Path optimize(Path path, Graph graph, long deadline);

}
