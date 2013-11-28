package tsp.solver;

import tsp.graph.Graph;

/**
 * The solver is to read input from stdin for the TSP problem, and solve the problem for the given input.
 * 
 * @author anton
 *
 */
public interface Solver {
	
	/**
	 * Reads input from stdin. The input must be of the following format:
	 * The first line of standard input contains an integer 1 ≤ N ≤ 1000, the number of points. 
	 * The following N lines each contain a pair of real numbers x y giving the coordinates of the N points. 
	 * All numbers in the input have absolute value bounded by 106.
	 * 
	 */
	public Graph readInput();
	
	
}
