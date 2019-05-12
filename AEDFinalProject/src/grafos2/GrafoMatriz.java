package grafos2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import grafos.VerticeMatriz;

public class GrafoMatriz<K> implements IGrafoMatriz<K> {
	public static final int MAX_VERT = 1000;
	private int limite;
	private int[][] matriz;
	private LinkedList<VerticeMatriz<K>> vertices;
	private int[]dfsVertices;
	private int[]dfsDeadEnd;
	private boolean[]visitados;
	public GrafoMatriz() {
		limite = 0;
		matriz = new int[MAX_VERT][MAX_VERT];
		vertices = new LinkedList<VerticeMatriz<K>>();
	}

	public GrafoMatriz(int ver) {
		matriz = new int[ver][ver];
		vertices = new LinkedList<VerticeMatriz<K>>();
		limite = 0;
	}

	public int getLimite() {
		return limite;
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public LinkedList<VerticeMatriz<K>> getVertices() {
		return vertices;
	}

	public boolean estaVacio() {
		boolean vacio = false;
		if (limite == 0) {
			vacio = true;
		}
		return vacio;
	}

	public void agregarVertice(K clave) {
		VerticeMatriz<K> vert = buscarVertice(clave);
		if (vert == null) {
			vert = new VerticeMatriz<K>(clave);
			vertices.add(vert);
			vert.setPos(vertices.indexOf(vert));
//			System.out.println("Agrego:"+ vert.getPos());
			limite++;
		}
	}

	public VerticeMatriz<K> buscarVertice(K clave) {
		VerticeMatriz<K> vert = null;
		boolean encontro = false;
		for (int i = 0; i < vertices.size() && !encontro; i++) {
			if (vertices.get(i).getClave().equals(clave)) {
				vert = vertices.get(i);
				encontro = true;
			}
		}
		return vert;
	}

	public void agregarArista(K clave, K clave2, int dis) {
		VerticeMatriz<K> v1 = buscarVertice(clave);
		VerticeMatriz<K> v2 = buscarVertice(clave2);
		if (v1 == null) {
			agregarVertice(clave);
			v1 = buscarVertice(clave);
		}
		if (v2 == null) {
			agregarVertice(clave2);
			v2 = buscarVertice(clave2);
		}
		int pos1 = vertices.indexOf(v1);
		int pos2 = vertices.indexOf(v2);
//		System.out.println("posicion1: "+pos1 +" "+"posicion2: "+pos2);
		matriz[pos1][pos2] = dis; // cambiar por la formula de distancia entre los 2 puntos
		matriz[pos2][pos1] = dis;
		//revisar esto
		v1.getAdyacentes().add(v2);
		v2.getAdyacentes().add(v1);
	}

	public void eliminarVertice(K clave) {
		VerticeMatriz<K> v = buscarVertice(clave);
		if (v != null) {
			int pos = vertices.indexOf(v);
			for (int i = 0; i < limite; i++) {
				matriz[pos][i] = 0;
				matriz[i][pos] = 0;
			}
			for (int i = pos; i < limite; i++) {
				for (int j = pos; j < limite; j++) {
					matriz[i][j] = matriz[i + 1][j + 1];
				}
			}
			limite--;
		}
		vertices.remove(v);
	}

	public void eliminarArista(K clave, K clave2) {
		VerticeMatriz<K> v1 = buscarVertice(clave);
		VerticeMatriz<K> v2 = buscarVertice(clave2);
		if (v1 != null && v2 != null) {
			int pos1 = vertices.indexOf(v1);
			int pos2 = vertices.indexOf(v2);
			matriz[pos1][pos2] = 0;
			matriz[pos2][pos1] = 0;
		}
	}

	public boolean adyacenciaVertices(K clave, K clave2) {
		boolean si = false;
		VerticeMatriz<K> v1 = buscarVertice(clave);
		VerticeMatriz<K> v2 = buscarVertice(clave2);
		if (v1 != null && v2 != null) {
			int pos1 = vertices.indexOf(v1);
			int pos2 = vertices.indexOf(v2);
			if (matriz[pos1][pos2] != 0) {
				si = true;
			}
		}
		return si;
	}

	public int darDistanciaEntreVertices(K clave1, K clave2) {

		int dis = 0;

		VerticeMatriz<K> v1 = buscarVertice(clave1);
		VerticeMatriz<K> v2 = buscarVertice(clave2);

		if (v1 != null && v2 != null) {
			int pos1 = vertices.indexOf(v1);
			int pos2 = vertices.indexOf(v2);
			dis = matriz[pos1][pos2];
		}

		return dis;
	}

	// camino mas corto entre un vertice y todos los demas, L y C son el tamaño de
	// los vertices
	public void dijkstra(VerticeMatriz<K> inicial, int[] L, LinkedList<VerticeMatriz<K>>[] C) {
		String mensaje = "";
		for (int i = 0; i < L.length; i++) {
			L[i] = Integer.MAX_VALUE;
		}
		L[vertices.indexOf(inicial)] = 0;
		C[vertices.indexOf(inicial)].add(inicial);
		LinkedList<VerticeMatriz<K>> S = new LinkedList<VerticeMatriz<K>>();
		while (!S.containsAll(vertices)) {
			VerticeMatriz<K> u = null;
			int dis = Integer.MAX_VALUE;
			for (int i = 0; i < vertices.size(); i++) {
				if (L[i] <= dis && !S.contains(vertices.get(i))) {
					u = vertices.get(i);
					dis = L[i];
				}
			}
			S.add(u);
			mensaje += u.getClave();
			for (VerticeMatriz<K> vertice : vertices) {
				if (!S.contains(vertice)) {
					long w = matriz[u.getPos()][vertice.getPos()];
					if (w == 0) {
						w = Integer.MAX_VALUE;
					}
					if (L[vertices.indexOf(u)] + w < L[vertices.indexOf(vertice)]) {
						L[vertices.indexOf(vertice)] = (int) (L[vertices.indexOf(u)] + w);
						C[vertices.indexOf(vertice)].clear();
						C[vertices.indexOf(vertice)].addAll(C[vertices.indexOf(u)]);
						C[vertices.indexOf(vertice)].add(vertice);
					}
				}

			}
		}
		System.out.println(mensaje);
	}

	// warshall camino mas corto entre dos vertices si sirve
	public int[][] warshall() {
		int[][] floyd = matriz.clone();
		convertirInfinito(floyd);
		for (int fijo = 0; fijo < limite; fijo++) {
			for (int i = 0; i < limite; i++) {
				for (int j = 0; j < limite; j++) {
					if (fijo != i && fijo != j) {
						long nuevo = (long) matriz[i][fijo] + (long) matriz[fijo][j];
						if (matriz[i][j] > nuevo) {
							matriz[i][j] = (int) nuevo;
							matriz[j][i] = (int) nuevo;
						}

					}
				}
			}
		}
		return floyd;
	}

	public void convertirInfinito(int[][] floyd) {
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j < limite; j++) {
				if (floyd[i][j] == 0 && i != j) {
					floyd[i][j] = Integer.MAX_VALUE;
					floyd[j][i] = Integer.MAX_VALUE;
				}
			}
		}

	}

	public ArrayList<K> darClavesVertices() {
		ArrayList<K> claves = new ArrayList<K>(vertices.size());
		for (int i = 0; i < vertices.size(); i++) {
			claves.add(vertices.get(i).getClave());
		}
		return claves;
	}

	public void pintarMatriz() {
		String claves = "";
		String retornar = "";
		for (int i = 0; i < vertices.size(); i++) {
			claves += vertices.get(i).getClave();
		}
//		System.out.println(claves + "  <<<Vertices");
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j <= limite; j++) {
				if (j == limite) {
					retornar += "\n";
				} else {
					retornar += matriz[i][j] + ",";
				}
			}
//			System.out.println(i+" Longitud de filas");
		}
//		System.out.println(retornar);
	}
	public int[][] convertirAdyacencia(){
		int[][] matri=matriz.clone();
		String claves="";
		String retornar="";
		for (int i = 0; i < vertices.size(); i++) {
			claves+=vertices.get(i).getClave();
		}
		for (int i = 0; i < limite; i++) {
			for (int j = 0; j <=limite; j++) {
				if(j==limite) {
					retornar+="\n";
				}else if(matri[i][j]!=0) {
					matri[i][j]=1;
					retornar+=matri[i][j]+",";
				}else {
					retornar+=matriz[i][j]+",";
				}
			}
		}
//		System.out.println(retornar);
		return matri;
	}
	// aplicar la conversion de adyacencia
	//punto de partida
	public String BFS(int[][] matriz,int partida) {
		String mensaje="";
		Queue<Integer>queue=new LinkedList<Integer>();
		int[][]retornar=convertirAdyacencia();
		int numeroNodos=matriz[partida].length-1;
		int visitados[]=new int[numeroNodos+1];
		int i,elemento;
		visitados[partida]=1;
		queue.add(partida);
		while(!queue.isEmpty()) {
			elemento=queue.remove();
			i=elemento;
			mensaje+=i+",";
			while(i<=numeroNodos) {
				if(matriz[elemento][i]==1 && visitados[i]==0) {
					queue.add(i);
					visitados[i]=1;
				}
				i++;
			}
		}
		return mensaje;
	}
	public void DFS(int i, int[][]graph,boolean[]visited) {
		if(!visited[i]) {
			visited[i]=true;
			System.out.println(i+1+" ");
			for (int j = 0; j < graph[i].length; j++) {
				if(graph[i][j]==1 && !visited[j]) {
					DFS(j,graph,visited);
				}
			}
		}
	}
	public void terminarDFS() {
		boolean[] visited=new boolean[matriz.length];
		int count=0;
		String mensaje="";
		for (int i = 0; i < matriz.length; i++) {
			if(!visited[i]) {
				System.out.println("Component:");
				DFS(i,  matriz, visited);
				++count;
			}
		}
		System.out.println("total de componentes"+count);
	}
	
	//con linked list
	public LinkedList<VerticeMatriz<K>> dijkstra2(VerticeMatriz<K> inicial, VerticeMatriz<K> llegada) {
		LinkedList<VerticeMatriz<K>> camino = new LinkedList<VerticeMatriz<K>>();
		camino.add(inicial);
		VerticeMatriz<K> actual = inicial;
		VerticeMatriz<K> menor = null;
		VerticeMatriz<K> anterior = null;
		int sumando = 0;
		String mensaje = "";
		while (!(camino.getLast() == llegada)) {
			actual.setVisitado(true);
			mensaje += actual.getClave() + ",";
			int max = Integer.MAX_VALUE;
			for (int i = 0; i < actual.getAdyacentes().size(); i++) {
				if (anterior == null && actual.getAdyacentes().get(i).isVisitado()==false) {
					int distancia = darDistanciaEntreVertices(actual.getClave(),
							actual.getAdyacentes().get(i).getClave());
					if (distancia < max) {
						max = distancia;
						menor = actual.getAdyacentes().get(i);
					}
				} else if (anterior != actual.getAdyacentes().get(i)
						&& (actual.getAdyacentes().get(i).isVisitado() == false)) {
					int distancia = darDistanciaEntreVertices(actual.getClave(),
							actual.getAdyacentes().get(i).getClave());
					if (distancia < max) {
						max = distancia;
						menor = actual.getAdyacentes().get(i);
					}
				}
			}
			if(menor.isVisitado()==true && anterior.isVisitado()==true) {
				camino.removeAll(camino);
				camino.add(inicial);
				actual=inicial;
				actual.setVisitado(false);
				anterior=null;
				menor=null;
				sumando=0;
				mensaje="";
			}else {
				sumando += max; // bucle infinito entre J Y D
				anterior = actual;
				camino.add(menor);
				actual = menor;
			}
//			}
		}
		mensaje += llegada.getClave();
		mensaje += " con distancia " + sumando;
		System.out.println(mensaje);
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setVisitado(false);
		}
		return camino;
	}

}

