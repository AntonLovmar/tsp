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
		int maxIterations = Math.min(30, graph.getNumberOfVertices() - 1);
		while (System.currentTimeMillis() < deadline) {
			boolean gotBetter = false;
			for (Vertex root : graph.getVertices()) {
				List<Vertex> neighbourList = graph.getNeighbourList(root);
				int bestSwapDifference = 0;
				Vertex bestNeighbour = null;
				for (int i = 0; i < maxIterations; i++) {
					Vertex neighbour = neighbourList.get(i);
					if (System.currentTimeMillis() > deadline)
						return bestPath;
					int distanceDifference = distanceDifference(graph, root, path.next(root), neighbour,
							path.next(neighbour));
					if (distanceDifference < bestSwapDifference) {
						bestSwapDifference = distanceDifference;
						bestNeighbour = neighbour;
						gotBetter = true;
					}
				}
				if (bestSwapDifference < 0)
					path.reverseVertices(root, path.next(bestNeighbour));
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

	/**
	 * Checks if a new edge between Vertex i and Vertex k would result in a
	 * shorter path.
	 * 
	 * @param graph
	 * @param i
	 * @param afterI
	 *            The vertex after i in the path.
	 * @param k
	 * @param afterK
	 *            The vertex after k in the path
	 * @return
	 */
	private boolean swapGivesLessDistanceWithVertices(Graph graph, Vertex i, Vertex afterI, Vertex k, Vertex afterK) {
		return (graph.distanceBetween(i, k) + graph.distanceBetween(afterI, afterK)) < (graph
				.distanceBetween(afterI, i) + graph.distanceBetween(k, afterK));
	}

	private int distanceDifference(Graph graph, Vertex i, Vertex afterI, Vertex k, Vertex afterK) {
		return (graph.distanceBetween(i, k) + graph.distanceBetween(afterI, afterK))
				- (graph.distanceBetween(afterI, i) + graph.distanceBetween(k, afterK));
	}
}
