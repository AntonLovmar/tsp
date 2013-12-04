package tsp.graph;


public class Vertex {

	private final double x, y;
	private final int id;

	public Vertex(int id, double x, double y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * Returns a unique identifier for this vertex.
	 * 
	 * @return the id of the vertex
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the x-coordinate of the vertex.
	 * 
	 * @return a double representing the vertex's x-coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate of the vertex.
	 * 
	 * @return a double representing the vertex's y-coordinate
	 */
	public double getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ") ID: " + getId();
	}
}
