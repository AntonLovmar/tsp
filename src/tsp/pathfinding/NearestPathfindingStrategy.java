package tsp.pathfinding;

import java.util.HashSet;
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
		Path path = new Path(numberOfVertices);

		Set<Vertex> used = new HashSet<>();
		Vertex currPathVertex = graph.getVertex((int) (Math.random() * (graph.getNumberOfVertices() - 1)));
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

		return path;
	}
}
