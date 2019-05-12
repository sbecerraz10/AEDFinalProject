package grafos;

import java.util.LinkedList;

public class Vertice<K, V> {
	public static int BLANCO = 1;
	public static int NEGRO = 0;
	public static int GRIS = 2;
	private V value;
	private K key;
	private int color;
	private Datos<K, V> datos;
	private LinkedList<Adyacencia<K, V>> listaAdyacencia;

	public Vertice(K clave, V valor) {
		datos = new Datos<K, V>(clave, valor);
		listaAdyacencia = new LinkedList<Adyacencia<K, V>>();
		color = 1;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Datos<K, V> getDatos() {
		return datos;
	}

	public void setDatos(Datos<K, V> datos) {
		this.datos = datos;
	}

	public LinkedList<Adyacencia<K, V>> getListaAdyacencia() {
		return listaAdyacencia;
	}

	public void setListaAdyacencia(LinkedList<Adyacencia<K, V>> listaAdyacencia) {
		this.listaAdyacencia = listaAdyacencia;
	}
	
}
