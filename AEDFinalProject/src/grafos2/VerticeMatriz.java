package grafos2;

import java.util.LinkedList;
public class VerticeMatriz<K> {
	private K clave;
	private int pos;
	private int posx;
	private int posy;
	private boolean visitado;
	private LinkedList<VerticeMatriz<K>> adyacentes;

	public VerticeMatriz(K cla) {
		clave = cla;
		adyacentes = new LinkedList<VerticeMatriz<K>>();
		visitado = false;
	}

	public K getClave() {
		return clave;
	}

	public void setClave(K clave) {
		this.clave = clave;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public LinkedList<VerticeMatriz<K>> getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(LinkedList<VerticeMatriz<K>> adyacentes) {
		this.adyacentes = adyacentes;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}
