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
		return twoOpt(graph, greedyStartPath(graph), deadline);
	}

	private Path twoOpt(Graph graph, Path path, long deadline) {
		Path currentPath = path;
		while (System.currentTimeMillis() < deadline) {
			int bestDistance = graph.totalLength(currentPath.getPath());
			for (int i = 0; i < graph.getNumberOfVertices() - 1; i++) {
				for (int k = i + 1; k < graph.getNumberOfVertices(); k++) {
					if (System.currentTimeMillis() > deadline) {
						return currentPath;
					}
					Path newPath = twoOptSwap(currentPath, i, k, graph.getNumberOfVertices());
					int newDistance = graph.totalLength(newPath.getPath());
					if (newDistance < bestDistance) {
						currentPath = newPath;
					}
				}
			}
		}
		return currentPath;
	}

	private Path twoOptSwap(Path path, int reverseFrom, int reverseTo, int numberOfVertices) {
		Path newPath = new PathImpl(numberOfVertices);
		for (int v = 0; v < reverseFrom; v++) {
			newPath.addToPath(path.getVertex(v));
		}
		for (int v = reverseTo; v >= reverseFrom; v--) {
			newPath.addToPath(path.getVertex(v));
		}
		for (int v = reverseTo + 1; v < numberOfVertices; v++) {
			newPath.addToPath(path.getVertex(v));
		}
		return newPath;
	}

	private Path greedyStartPath(Graph graph) {
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
