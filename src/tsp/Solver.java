package tsp;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.pathfinding.GreedyTwoOptStrategy;
import tsp.pathfinding.PathfindingStrategy;
import tsp.reader.GraphReader;

public class Solver {

	public static void main(String[] args) {
		long deadline = System.currentTimeMillis() + 1600;
		Solver solver = new Solver();
		GraphReader reader = new GraphReader();
		Graph graph = reader.readGraph();
		PathfindingStrategy strategy = new GreedyTwoOptStrategy();
		Path path = solver.solveWithStrategy(strategy, graph, deadline);
		System.out.println(path);
	}

	public Path solveWithStrategy(PathfindingStrategy strategy, Graph graph, long deadline) {
		return strategy.findPath(graph, deadline);
	}

}
