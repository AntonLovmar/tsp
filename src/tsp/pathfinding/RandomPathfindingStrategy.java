package tsp.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class RandomPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		List<Vertex> path = new ArrayList<>(graph.getVertices());
		Collections.shuffle(path);
		return new Path(path);
	}

}
