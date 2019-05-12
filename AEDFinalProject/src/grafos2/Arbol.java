package grafos2;

public class Arbol {
	NodoArbol raiz;
	NodoArbol raizDFS;

	public void Arbol() {
		NodoArbol raiz = new NodoArbol();
	}
	public void ArbolDFS() {
		NodoArbol raiz=new NodoArbol();
	}

	public boolean esVacio() {
		return (raiz == null);
	}

	public void insertarBFS(String mensaje, int dato) {
		if (esVacio()) {
			NodoArbol nuevo = new NodoArbol();
			nuevo.valor = dato;
			nuevo.mensaje = mensaje;
			nuevo.derecha = new Arbol();
			nuevo.izquierda = new Arbol();
		} else {
			if (dato > raiz.valor) {
				raiz.derecha.insertarBFS(mensaje, dato);
			}
			if (dato < raiz.valor) {
				raiz.izquierda.insertarBFS(mensaje, dato);
			}
		}
	}

	public boolean esVacioDFS() {
		return (raizDFS == null);
	}
	public void insertarDFS(String mensaje,int dato) {
		if(esVacioDFS()) {
			NodoArbol dfs=new NodoArbol();
			dfs.valor=dato;
			dfs.mensaje=mensaje;
			dfs.derecha=new Arbol();
			dfs.izquierda=new Arbol();
		}else {
			if(dato>raizDFS.valor) {
				raizDFS.derecha.insertarDFS(mensaje, dato);
			}if(dato<raizDFS.valor) {
				raiz.izquierda.insertarDFS(mensaje, dato);
			}
		}
	}
}
