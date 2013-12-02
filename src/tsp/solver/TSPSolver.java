package tsp.solver;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.pathfinding.PathfindingStrategy;

/**
 * The solver is to read input from stdin for the TSP problem, and solve the problem for the given input.
 * 
 * @author anton
 *
 */
public interface TSPSolver {
	
	/**
	 * 
	 * @param strategy
	 * @return
	 */
	public Path solveWithStrategy(PathfindingStrategy strategy, Graph graph);
	
	
}
