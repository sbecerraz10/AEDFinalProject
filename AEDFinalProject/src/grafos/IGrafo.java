package grafos;

import java.util.ArrayList;

public interface IGrafo<K,V> {

	public Vertice<K,V> buscarVertice(K key);
	
	public int buscarDistanciaArista(K keyA, K keyB);
	
	public boolean existeAristaEntre(K keyA, K keyB);
	
	public boolean eliminarVertice(K key);
	
	public boolean eliminarArista(K key, K keyB);
	
	public boolean estaVacio();
	
	public ArrayList<Vertice<K,V>> caminoMasCortoEntreLosVertices(Vertice<K,V> a, Vertice<K,V>b);
	
	public boolean aregarArista(K keyA, K keyB, int distan);
	
	public boolean agregarVertice(K clave, V valor);
}
