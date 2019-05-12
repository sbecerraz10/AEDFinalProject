package grafos2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSGraph {
	int size;
	AdjList[] array;
	Arbol raiz;

	public BFSGraph(int six) {
		size = six;
		array = new AdjList[size];
		for (int i = 0; i < size; i++) {
			array[i] = new AdjList();
			array[i].head = null;
		}
		raiz=new Arbol();
	}

	public void agregarNode(int src, int dest) {
		Node n = new Node(dest, null);
		n.next = array[src].head;
		array[src].head = n;
	}

	public void BFSExplore(int startVertex) {
		String mensaje="A";
		String combinacion="A";
		int cambio=0;
		boolean[] visited = new boolean[size];
		for (int i = 0; i < size; i++) {
			visited[i] = false;
		}
		Queue<Integer> s = new LinkedList<Integer>();
		s.add(startVertex);
		while (!s.isEmpty()) {
			int n = s.poll();
			System.out.println("Visit:" + n);
			visited[n] = true;
			Node head = array[n].head;
			while (head != null) {
				if (visited[head.dest] == false) {
					s.add(head.dest);
					raiz.insertarBFS(mensaje, head.dest);
					if(mensaje.charAt(0)=='Z') {
						mensaje="A";
						mensaje+=combinacion;
					}
					visited[head.dest] = true;
				} else {
					head = head.next;
				}
			}
		}
	}

}
