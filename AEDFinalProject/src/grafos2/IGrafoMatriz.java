package grafos2;

import grafos.VerticeMatriz;

public interface IGrafoMatriz<K> {
	public void agregarVertice(K clave);
	public void agregarArista(K clave, K clave2, int dis);
	public VerticeMatriz<K> buscarVertice(K clave);
	public void eliminarVertice(K clave);
	public void eliminarArista(K clave, K clave2);
	public boolean adyacenciaVertices(K clave,K clave2);
	public int darDistanciaEntreVertices(K clave1, K clave2);
}
