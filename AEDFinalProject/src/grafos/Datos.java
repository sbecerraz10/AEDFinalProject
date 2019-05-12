package grafos;

public class Datos<K, V> {
	private K key;
	private V valor;

	public Datos(K ke, V valo) {
		key = ke;
		valor = valo;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValor() {
		return valor;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

}
