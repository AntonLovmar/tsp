package tsp.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tsp.graph.Graph;
import tsp.graph.TSPGraph;
import tsp.graph.Vertex;
import tsp.graph.VertexImpl;

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
				vertices.add(new VertexImpl(i, x, y));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graph graph = new TSPGraph(vertices);
		return graph;
	}

}
