package tsp.pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class BestNNPathfindingStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		return nearestNeighbourPath(graph);
	}

	private Path nearestNeighbourPath(Graph graph) {
		int numberOfVertices = graph.getNumberOfVertices();

		List<Vertex> path = new ArrayList<>(graph.getVertices());
		List<Vertex> bestPath = path;
		int bestLength = Integer.MAX_VALUE;

		for (int i = 0; i < numberOfVertices; i++) {
			Set<Vertex> used = new HashSet<>();

			Vertex currPathVertex = graph.getVertex(i);
			used.add(currPathVertex);
			path.set(0, currPathVertex);
			int currLength = 0;

			for (int j = 1; j < numberOfVertices; j++) {
				currPathVertex = path.get(j - 1);
				for (Vertex neighbour : graph.getNeighbourList(currPathVertex)) {
					if (!used.contains(neighbour)) {
						path.set(j, neighbour);
						used.add(neighbour);
						currLength += graph.distanceBetween(currPathVertex, neighbour);
						break;
					}
				}
			}
			currLength += graph.distanceBetween(path.get(0), path.get(path.size() - 1));

			if (currLength < bestLength) {
				bestPath = new ArrayList<Vertex>(path);
				bestLength = currLength;
			}

		}
		return new Path(bestPath);
	}
}
