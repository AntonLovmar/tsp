package tsp.pathfinding;

import java.util.HashSet;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.PathImpl;
import tsp.graph.Vertex;

public class GreedyTwoOptStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph, long deadline) {
		return twoOpt(graph, greedyStartPath(graph, deadline));
	}

	private Path twoOpt(Graph graph, Path path) {
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			Vertex node1 = graph.getVertex(i);
			for (int j = 0; j < graph.getNumberOfVertices(); j++) {
				if (i == j)
					continue;
				Vertex node2 = graph.getVertex(j);
				for (int k = 0; k < graph.getNumberOfVertices(); k++) {
					if (k == j)
						continue;
					Vertex node3 = graph.getVertex(k);

				}
			}
		}
		return null;
	}

	private Path greedyStartPath(Graph graph, long deadline) {
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
