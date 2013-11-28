package tsp.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tsp.graph.Edge;
import tsp.graph.Graph;
import tsp.graph.TSPGraph;
import tsp.graph.Vertex;
import tsp.graph.VertexImpl;

public class InputReader {
	private BufferedReader reader;
	
	public InputReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Graph readInput() {
		List<Vertex> vertices = new ArrayList<Vertex>();
		try {
			String line = reader.readLine();
			int numberOfVertices = Integer.parseInt(line);
			for(int i = 0; i < numberOfVertices; i++) {
				line = reader.readLine();
				String[] xAndY = line.split(" ");
				int x = Integer.parseInt(xAndY[0]);
				int y = Integer.parseInt(xAndY[1]);
				vertices.add(new VertexImpl(i, x, y));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Edge> edges = new ArrayList<Edge>();
		Graph graph = new TSPGraph(vertices, edges);
		return graph;
	}

}
