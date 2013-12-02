package tsp;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.pathfinding.NaiveStrategy;
import tsp.pathfinding.PathfindingStrategy;
import tsp.reader.GraphReader;

public class Solver {

	public static void main(String[] args) {
		Solver solver = new Solver();
		GraphReader reader = new GraphReader();
		Graph graph = reader.readGraph();
		PathfindingStrategy strategy = new NaiveStrategy();
		Path path = solver.solveWithStrategy(strategy, graph);
		System.out.println(path);
	}

	public Path solveWithStrategy(PathfindingStrategy strategy, Graph graph) {
		return strategy.findPath(graph);
	}

}
