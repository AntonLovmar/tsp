package tsp.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tsp.graph.Graph;
import tsp.graph.Path;
import tsp.graph.Vertex;

public class TwoOptStrategy implements OptimizationStrategy {

	@Override
	public Path optimize(Path path, Graph graph, long deadline) {
		return twoOpt(path, graph, deadline);
	}

	private Path twoOpt(Path path, Graph graph, long deadline) {
		Path bestPath = path;
		int bestLength = Integer.MAX_VALUE;
		while (System.currentTimeMillis() < deadline) {
			boolean gotBetter = false;
			for (Vertex root : graph.getVertices()) {
				int i = 0;
				for (Vertex neighbour : graph.getNeighbourList(root)) {
					if (System.currentTimeMillis() > deadline)
						return bestPath;
					if (i > 100)
						break;
					if (swapGivesLessDistanceWithVertices(graph, path, root, path.next(root), neighbour,
							path.next(neighbour))) {
						path.reverseVertices(root, path.next(neighbour));
						gotBetter = true;
					}
					i++;
				}
			}
			if (!gotBetter) {
				List<Vertex> randomizedVertices = new ArrayList<>(graph.getVertices());
				Collections.shuffle(randomizedVertices);
				path = new Path(randomizedVertices);
			} else {
				int currLength = graph.totalLength(path);
				if (currLength < bestLength) {
					bestPath = path;
					bestLength = currLength;
				}
			}
		}
		return bestPath;
	}

	private boolean swapGivesLessDistanceWithVertices(Graph graph, Path path, Vertex i, Vertex afterI, Vertex k,
			Vertex afterK) {
		return (graph.distanceBetween(i, k) + graph.distanceBetween(afterI, afterK)) < (graph
				.distanceBetween(afterI, i) + graph.distanceBetween(k, afterK));
	}
}
