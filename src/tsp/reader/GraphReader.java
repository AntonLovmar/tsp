package tsp.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tsp.graph.Graph;
import tsp.graph.Vertex;

public class GraphReader {
	private final BufferedReader reader;

	public GraphReader(FileReader fileReader) {
		reader = new BufferedReader(fileReader);
	}

	public GraphReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public Graph readGraph() {
		List<Vertex> vertices = new ArrayList<Vertex>();
		try {
			String line = reader.readLine();
			int numberOfVertices = Integer.parseInt(line);
			for (int i = 0; i < numberOfVertices; i++) {
				line = reader.readLine();
				String[] xAndY = line.split(" ");
				double x = Double.parseDouble(xAndY[0]);
				double y = Double.parseDouble(xAndY[1]);
				vertices.add(new Vertex(i, x, y));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graph graph = new Graph(vertices);
		return graph;
	}

	public Graph generateRandomGraph(int size) {
		Random random = new Random();
		if (size < 0) {
			size = random.nextInt(1000);
		}
		List<Vertex> vertices = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			vertices.add(new Vertex(i, random.nextInt(1000000), random.nextInt(1000000)));
		}
		return new Graph(vertices);
	}

}
