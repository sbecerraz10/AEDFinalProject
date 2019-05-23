package graphs;

public class Edge<V> {
	private Nodo<V> origin;
    private Nodo<V> destination;
    private double distance;
 
    public Edge(Nodo<V> origin, Nodo<V> destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

	public Nodo<V> getOrigin() {
		return origin;
	}

	public void setOrigin(Nodo<V> origin) {
		this.origin = origin;
	}

	public Nodo<V> getDestination() {
		return destination;
	}

	public void setDestination(Nodo<V> destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
 
    
}
