package grafos2;

import java.util.Stack;

public class DFSGraph {
	int size;
	AdjList[]array;
	public DFSGraph(int six) {
		size=six;
		array=new AdjList[size];
		for (int i = 0; i < size; i++) {
			array[i]=new AdjList();
			array[i].head=null; 
		}
	}
	public void agregarNode(int src, int dest) {
		Node n=new Node(dest, null);
		n.next=array[src].head;
		array[src].head=n;
	}
	public void DFSExplore(int startVertex) {
		boolean[] visited=new boolean[size];
		for (int i = 0; i < size; i++) {
			visited[i]=false;
		}
		Stack<Integer>s=new Stack<Integer>();
		s.push(startVertex);
		while(!s.isEmpty()) {
			int n=s.pop();
			s.push(n);
			visited[n]=true;
			Node head=array[n].head;
			boolean isDone=true;
			while(head!=null) {
				if(visited[head.dest]==false) {
					s.push(head.dest);
					visited[head.dest]=true;
					isDone=false;
					break;
				}else {
					head=head.next;
				}
			}
			if(isDone==true) {
				int out=s.pop();
				System.out.println("Visited Node:"+ out);
			}
		}
	}
	public void DFSSearch(int startVertex,int search) {
		boolean[] visited=new boolean[size];
		boolean isfound=false;
		for (int i = 0; i < size; i++) {
			visited[i]=false;
		}
		Stack<Integer>s=new Stack<Integer>();
		s.push(startVertex);
		while(!s.isEmpty()) {
			int n=s.pop();
			s.push(n);
			visited[n]=true;
			Node head=array[n].head;
			boolean isDone=true;
			while(head!=null) {
				if(search==head.dest) {
					System.out.println("node is found");
				isfound=true;
				break;
				}
				if(visited[head.dest]==false) {
					s.push(head.dest);
					visited[head.dest]=true;
					isDone=false;
					break;
				}else {
					head=head.next;
				}
			}
			if(isfound==true) {
				break; 
			}
			if(isDone==true) {
				int out=s.pop();
				System.out.println("Visited Node:"+ out);
			}
		}
		if(isfound==false) {
			System.out.println("No node found");
		}
	}
}
