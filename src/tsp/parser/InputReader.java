package tsp.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		//reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			FileReader fileReader = new FileReader(new File("/home/anton/Github/tsp/tests/parsetest.txt"));
			reader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Graph readInput() {
		List<Vertex> vertices = new ArrayList<Vertex>();
		try {
			String line = reader.readLine();
			int numberOfVertices = Integer.parseInt(line);
			for(int i = 0; i < numberOfVertices; i++) {
				line = reader.readLine();
				String[] xAndY = line.split(" ");
				double x = Double.parseDouble(xAndY[0]);
				double y = Double.parseDouble(xAndY[1]);
				vertices.add(new VertexImpl(i, x, y));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Edge> edges = new ArrayList<Edge>();
		for(Vertex vertex : vertices) {
			
		}
		Graph graph = new TSPGraph(vertices, edges);
		return graph;
	}

}
