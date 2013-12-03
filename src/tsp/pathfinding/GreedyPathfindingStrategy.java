package tsp.pathfinding;

import java.util.HashSet;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class GreedyPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		return greedyStartPath(graph);
	}

	private Path greedyStartPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();
		Path path = new Path(numberOfVertices);
		Set<Vertex> used = new HashSet<>();
		Vertex bestNeighbour = null, currNeighbour = null;

		Vertex currPathVertex = graph.getVertex(0);
		used.add(currPathVertex);
		path.addToPath(currPathVertex);

		for (int i = 1; i < numberOfVertices; i++) {
			currPathVertex = path.getVertex(i - 1);
			bestNeighbour = null;
			for (int j = 1; j < numberOfVertices; j++) {
				currNeighbour = graph.getVertex(j);
				if (used.contains(currNeighbour))
					continue;
				if (bestNeighbour == null
						|| graph.distanceBetween(currPathVertex, currNeighbour) < graph.distanceBetween(currPathVertex,
								bestNeighbour)) {
					bestNeighbour = currNeighbour;
				}
			}
			path.addToPath(bestNeighbour);
			used.add(bestNeighbour);
		}
		return path;
	}
}
