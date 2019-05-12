package grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo<K, V> implements IGrafo<K, V> {
	private LinkedList<Vertice<K, V>> listaVertices;
	private int numeroDeAristas;
	private int numeroDeVertices;

	public Grafo() {
		numeroDeAristas = 0;
		numeroDeVertices = 0;
		listaVertices = new LinkedList<Vertice<K, V>>();
	}

	public void BFS(Vertice<K, V> s) {
		for (int i = 0; i < listaVertices.size(); i++) {
			Vertice<K, V> u = listaVertices.get(i);
			if (u != s) {
				u.setColor(Vertice.BLANCO);
				// u.getDatos();
			}
		}
		s.setColor(Vertice.GRIS);
		// s.getDatos();
		Queue<Vertice<K, V>> Q = new LinkedList<Vertice<K, V>>();
		Q.offer(s);
		while (!Q.isEmpty()) {
			Vertice<K, V> u = Q.poll();
			for (int i = 0; i < u.getListaAdyacencia().size(); i++) {
				Adyacencia<K, V> v = u.getListaAdyacencia().get(i);
				if (v.getVerticeA().getColor() == Vertice.BLANCO) {
					v.getVerticeA().setColor(Vertice.GRIS);
					// v.getDistancia();
					Q.add(v.getVerticeA());
				}
			}
			u.setColor(Vertice.NEGRO);
		}
	}

	@Override
	public Vertice<K, V> buscarVertice(K key) {
		Vertice<K, V> ver = null;
		boolean encontrado = false;
		for (int i = 0; i < listaVertices.size() && !encontrado; i++) {
			if (listaVertices.get(i).getDatos().getKey().equals(key)) {
				ver = listaVertices.get(i);
				encontrado = true;
			}

		}
		return ver;
	}

	@Override
	public int buscarDistanciaArista(K keyA, K keyB) {
		int distancia = -1;
		Vertice<K, V> a = buscarVertice(keyA);
		Vertice<K, V> b = buscarVertice(keyB);

		if (a == null || b == null) {
			return distancia;
		} else {
			if (listaVertices.contains(a) && listaVertices.contains(b)) {
				for (int i = 0; i < listaVertices.size(); i++) {
					Vertice<K, V> per = listaVertices.get(i);
					if (per.getDatos().getKey().equals(a.getDatos().getKey())) {
						for (int j = 0; j < listaVertices.get(i).getListaAdyacencia().size(); j++) {
							if (per.getListaAdyacencia().get(j).getVerticeA().getDatos().getKey()
									.equals(b.getDatos().getKey())) {
								distancia = per.getListaAdyacencia().get(j).getDistancia();
							}
						}
					}
				}
			}
			return distancia;
		}
	}

	public boolean agregarVertice(Vertice<K, V> verti) {
		boolean agrego;
		if (!listaVertices.contains(verti)) {
			listaVertices.add(verti);
			agrego = true;
			numeroDeVertices++;
		} else {
			agrego = false;
		}
		return agrego;
	}

	@Override
	public boolean existeAristaEntre(K keyA, K keyB) {
		Vertice<K, V> a = buscarVertice(keyA);
		Vertice<K, V> b = buscarVertice(keyB);
		if (a == null || b == null) {
			return false;
		} else {
			boolean sera = false;
			if (listaVertices.contains(a) && listaVertices.contains(b)) {
				for (int i = 0; i < a.getListaAdyacencia().size() && !sera; i++) {
					if (a.getListaAdyacencia().get(i).getVerticeA().getDatos().getKey().equals(b.getDatos().getKey())) {
						for (int j = 0; j < b.getListaAdyacencia().size() && !sera; j++) {
							if (b.getListaAdyacencia().get(j).getVerticeA().getDatos().getKey()
									.equals(a.getDatos().getKey())) {
								sera = true;
							}
						}
					}
				}
			}
			return sera;
		}
	}

	@Override
	public boolean eliminarVertice(K key) {
		boolean seElimino=false;
		for (int i = 0; i < listaVertices.size()&&!seElimino; i++) {
			K verTem=listaVertices.get(i).getDatos().getKey();
			if(!verTem.equals(key)) {
				LinkedList<Adyacencia<K,V>>list=listaVertices.get(i).getListaAdyacencia();
				for (int j = 0; j < list.size()&&!seElimino; j++) {
					if(list.get(j).getVerticeA().getDatos().getKey().equals(key)) {
					list.remove(j);
					seElimino=true;
					}
				}
			}else {
				listaVertices.remove(i);
				seElimino=true;
				numeroDeVertices--;
			}
		}
		return seElimino;
	}

	@Override
	public boolean eliminarArista(K keyA, K keyB) {
		Vertice<K,V>a=null;
		Vertice<K,V>b=null;
		boolean seHalloA=false;
		boolean seHalloB=false;
		
		for (int i = 0; i <listaVertices.size()&&(!seHalloA||!seHalloB); i++) {
			if(listaVertices.get(i).getDatos().getKey().equals(keyA)) {
				a=listaVertices.get(i);
				seHalloA=true;
			}if(listaVertices.get(i).getDatos().getKey().equals(keyB)) {
				b=listaVertices.get(i);
				seHalloB=true;
			}
			
		}
		if(a==null||b==null) {
			return false;
		}else {
			if(existeAristaEntre(a.getDatos().getKey(),b.getDatos().getKey())) {
				boolean aun=false;
				for (int i = 0; i <a.getListaAdyacencia().size()&& !aun; i++) {
					if(a.getListaAdyacencia().get(i).getVerticeA().getDatos().getKey().equals(b.getDatos().getKey())) {
						a.getListaAdyacencia().remove(i);
						aun=true;
					}
				}
				aun=false;
				for (int i = 0; i <b.getListaAdyacencia().size()&&!aun; i++) {
					if(b.getListaAdyacencia().get(i).getVerticeA().getDatos().getKey().equals(b.getDatos().getKey())) {
						b.getListaAdyacencia().remove(i);
						aun=true;
					}
				}
				numeroDeAristas--;
				return true;
			}else {
				return false;
			}
		}
	}

	@Override
	public boolean estaVacio() {
		return listaVertices.isEmpty();
	}

	@Override
	public ArrayList<Vertice<K, V>> caminoMasCortoEntreLosVertices(Vertice<K, V> a, Vertice<K, V> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aregarArista(K keyA, K keyB, int distan) {
		Vertice<K,V>a=buscarVertice(keyA);
		Vertice<K,V>b=buscarVertice(keyB);
		if(a==null||b==null) {
			return false;
		}else {
			boolean agrego=false;
			if(listaVertices.contains(a)&&listaVertices.contains(b)) {
				for (int i = 0; i < listaVertices.size(); i++) {
					if(listaVertices.get(i).getDatos().getKey().equals(a.getDatos().getKey())) {
						Adyacencia<K,V>deA=new Adyacencia<>(b,distan);
						listaVertices.get(i).getListaAdyacencia().add(deA);
					}if(listaVertices.get(i).getDatos().getKey().equals(b.getDatos().getKey())) {
						Adyacencia<K,V>deB=new Adyacencia<>(a,distan);
						listaVertices.get(i).getListaAdyacencia().add(deB);
					}
				}
				agrego=true;
				numeroDeAristas++;
			}
			return agrego;
		}
	}

	@Override
	public boolean agregarVertice(K clave, V valor) {
		Vertice<K,V>nuevo=new Vertice<K,V>(clave,valor);
		return agregarVertice(nuevo);
	}
	public LinkedList<Vertice<K,V>>getListaVertices(){
		return listaVertices;
	}
	public int getNumeroDeAristas() {
		return numeroDeAristas;
	}
	public int getNumeroDeVertices() {
		return numeroDeVertices;
	}
	
}
