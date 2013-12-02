package tsp;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.pathfinding.GreedyStrategy;
import tsp.pathfinding.PathfindingStrategy;
import tsp.reader.GraphReader;

public class Solver {

	public static void main(String[] args) {
		long deadline = System.currentTimeMillis() + 1990;
		Solver solver = new Solver();
		GraphReader reader = new GraphReader();
		Graph graph = reader.readGraph();
		PathfindingStrategy strategy = new GreedyStrategy();
		Path path = solver.solveWithStrategy(strategy, graph, deadline);
		System.out.println(path);
	}

	public Path solveWithStrategy(PathfindingStrategy strategy, Graph graph, long deadline) {
		return strategy.findPath(graph, deadline);
	}

}
