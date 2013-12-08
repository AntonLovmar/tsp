package tsp.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
			search: for (Vertex root : graph.getVertices()) {
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
					}
				}
				if (bestSwapDifference < 0) {
					path.reverseVertices(root, path.next(bestNeighbour));
					gotBetter = true;
					continue search;
				}
			}
			if (System.currentTimeMillis() + 50 > deadline)
				return bestPath;
			if (!gotBetter) {
				path = randomizePartOfPath(path, 10);
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
	 * Returns a new Path with a random part of it shuffled. The length of the
	 * shuffled part is decided by the part parameter.
	 * 
	 * @param path
	 * @param part
	 *            How much of the path is shuffled.
	 * @return
	 */
	private Path randomizePartOfPath(Path path, int part) {
		List<Vertex> pathList = path.getPath();
		int size = pathList.size() / part;
		int indexToRandom = new Random().nextInt(pathList.size() - size);
		List<Vertex> shuffledPart = new ArrayList<>(pathList.subList(indexToRandom, indexToRandom + size));
		Collections.shuffle(shuffledPart);

		List<Vertex> newList = new ArrayList<>(pathList.size());
		for (int i = 0; i < indexToRandom; i++) {
			newList.add(pathList.get(i));
		}
		for (int i = 0; i < size; i++) {
			newList.add(shuffledPart.get(i));
		}
		for (int i = indexToRandom + size; i < pathList.size(); i++) {
			newList.add(pathList.get(i));
		}

		return new Path(newList);
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
