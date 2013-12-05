package tsp.pathfinding;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class TwoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return twoOpt(path, graph, deadline);
	}

	private Path twoOpt(Path path, Graph graph, long deadline) {
		while (System.currentTimeMillis() < deadline) {
			for (Vertex root : graph.getVertices()) {
				int rootIndex = path.indexOf(root);
				for (Vertex neighbour : graph.getNeighbourList(root)) {
					if (System.currentTimeMillis() > deadline) {
						return path;
					}
					int neighbourIndex = path.indexOf(neighbour);
					if (swapGivesLessDistanceWithIndex(graph, path, rootIndex, neighbourIndex)) {
						path.reverseBetweenIndices(rootIndex, neighbourIndex);
					}
				}
			}
		}
		return path;
	}

	private boolean swapGivesLessDistanceWithIndex(Graph graph, Path path, int i, int k) {
		return swapGivesLessDistanceWithVertices(graph, path, path.getVertex(i - 1), path.getVertex(i),
				path.getVertex(k), path.getVertex(k + 1));
	}

	private boolean swapGivesLessDistanceWithVertices(Graph graph, Path path, Vertex beforeI, Vertex i, Vertex k,
			Vertex afterK) {
		return (graph.distanceBetween(beforeI, k) + graph.distanceBetween(afterK, i)) < (graph.distanceBetween(beforeI,
				i) + graph.distanceBetween(k, afterK));
	}
}
