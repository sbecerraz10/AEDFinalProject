package grafos;

public class Adyacencia<K, V> {
	private Vertice<K, V> verticeAdyacente;
	private int distancia;

	public Adyacencia(Vertice<K, V> vertice, int dista) {
		verticeAdyacente = vertice;
		distancia = dista;
	}

	public Vertice<K, V> getVerticeA() {
		return verticeAdyacente;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

}
