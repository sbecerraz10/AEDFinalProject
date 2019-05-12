package graphs;

public class Edge<V> {
	private Node<V> origin;
    private Node<V> destination;
    private double distance;
 
    public Edge(Node<V> origin, Node<V> destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

	public Node<V> getOrigin() {
		return origin;
	}

	public void setOrigin(Node<V> origin) {
		this.origin = origin;
	}

	public Node<V> getDestination() {
		return destination;
	}

	public void setDestination(Node<V> destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
 
    
}
