package tsp.pathfinding;

import java.util.HashSet;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.PathImpl;
import tsp.graph.Vertex;

public class NaiveStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();
		Path path = new PathImpl(numberOfVertices);
		Set<Vertex> used = new HashSet<>();
		Vertex bestNeighbour = null, currNeighbour = null;

		Vertex currPathVertex = graph.getVertex(0);
		used.add(currPathVertex);
		path.addToPath(currPathVertex);

		for (int i = 1; i < numberOfVertices; i++) {
			currPathVertex = path.getVertex(i - 1);
			bestNeighbour = null;
			for (int j = 0; j < numberOfVertices; j++) {
				currNeighbour = graph.getVertex(j);
				if (used.contains(currNeighbour) || currNeighbour.equals(currPathVertex))
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
