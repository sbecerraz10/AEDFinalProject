package grafos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import grafos2.GrafoMatriz;

public class Main {
private static GrafoMatriz<String> grafo;
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(System.out));
		
		grafo = new GrafoMatriz<String>(5);
		
		grafo.agregarArista("a", "b", 3);
		grafo.agregarArista("a", "c", 1);
		grafo.agregarArista("a", "d", 4);
		grafo.agregarArista("b", "c", 5);
		grafo.agregarArista("b", "e", 8);
		grafo.agregarArista("c", "d", 2);
		grafo.agregarArista("d", "e", 1);

		//matriz del grafo
		LinkedList <VerticeMatriz<String>> vertices = grafo.getVertices();
		for (int i = 0; i < vertices.size(); i++) {
			escritor.write(vertices.get(i).getClave()+"");
		}
		escritor.write("  <<<Vertices\n");
		int[][] matriz = grafo.getMatriz();
		int limit = grafo.getLimite();
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j <= limit; j++) {
				if(j==limit){
					escritor.write("\n");
				} else{
					escritor.write(matriz[i][j]+"");

				}
				
			}
		}
		if(grafo.adyacenciaVertices("a", "b"))
			escritor.write("a y b son adyecentes");
		escritor.write(" con distancia: "+grafo.darDistanciaEntreVertices("a", "b"));
		
		//dijkstra
		LinkedList<VerticeMatriz<String>>[] C = new LinkedList[limit];
		for (int i = 0; i < C.length; i++) {
			C[i]= new LinkedList<VerticeMatriz<String>>();
		}
		int[] L = new int[limit];
		VerticeMatriz<String> inicial = grafo.buscarVertice("b");
		grafo.dijkstra(inicial, L, C);
		
		//floyd
		int[][] floyd = grafo.warshall();
		escritor.close();
	}
}
