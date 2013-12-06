package tsp.pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class NearestPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		return nearestNeighbourPath(graph);
	}

	private Path nearestNeighbourPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();
		List<Vertex> path = new ArrayList<>(numberOfVertices);
		Set<Vertex> used = new HashSet<>();

		Vertex currPathVertex = graph.getVertex(0);
		used.add(currPathVertex);
		path.add(currPathVertex);

		for (int j = 1; j < numberOfVertices; j++) {
			currPathVertex = path.get(j - 1);
			for (Vertex neighbour : graph.getNeighbourList(currPathVertex)) {
				if (!used.contains(neighbour)) {
					path.add(neighbour);
					used.add(neighbour);
					break;
				}
			}
		}
		return new Path(path);
	}
}
