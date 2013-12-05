package tsp.pathfinding;

import java.util.HashSet;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class NearestPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		long deadline = System.currentTimeMillis() + 700;
		return nearestNeighbourPath(graph, deadline);
	}

	private Path nearestNeighbourPath(Graph graph, long deadline) {
		int numberOfVertices = graph.getNumberOfVertices();
		Path path = null;
		Path bestPath = new Path(graph.getVertices());
		int bestPathLength = graph.totalLength(bestPath.getPath());

		for (int i = 0; i < graph.getNumberOfVertices() && System.currentTimeMillis() < deadline; i++) {
			path = new Path(numberOfVertices);
			Set<Vertex> used = new HashSet<>();
			Vertex currPathVertex = graph.getVertex(i);
			used.add(currPathVertex);
			path.addToPath(currPathVertex);

			for (int j = 1; j < numberOfVertices; j++) {
				currPathVertex = path.getVertex(j - 1);
				for (Vertex neighbour : graph.getNeighbourList(currPathVertex)) {
					if (!used.contains(neighbour)) {
						path.addToPath(neighbour);
						used.add(neighbour);
						break;
					}
				}
			}
			int pathLength = graph.totalLength(path.getPath());
			if (pathLength < bestPathLength) {
				bestPath = path;
				bestPathLength = pathLength;
			}
		}
		return bestPath;
	}
}
