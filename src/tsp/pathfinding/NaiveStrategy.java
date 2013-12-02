package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.PathImpl;
import tsp.graph.Vertex;

public class NaiveStrategy implements PathfindingStrategy {

	@Override
	public Path findPath(Graph graph) {
		Path path = new PathImpl();
		int numberOfVertices = graph.getNumberOfVertices();
		boolean[] used = new boolean[numberOfVertices];
		Vertex best = null;
		for (int i = 0; i < numberOfVertices; i++) {
			best = null;
			for (int j = 0; j < numberOfVertices; j++) {
				if (!used[j]
						&& (best == null || graph.distanceBetween(path.getVertex(i - 1), graph.getVertex(j)) < graph
								.distanceBetween(path.getVertex(i - 1), graph.getVertex(j)))) {
					best = graph.getVertex(j);
				}
				path.replaceVertex(i, best);
				used[best.getId()] = true;
			}
		}
		return path;
	}

}
