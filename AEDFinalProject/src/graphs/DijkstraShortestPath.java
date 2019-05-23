package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
 
public class DijkstraShortestPath<V> {
 
	public void computeShortestPaths(Nodo<V> sourceVertex){
 
		sourceVertex.setDistance(0);
		PriorityQueue<Nodo<V>> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
 
		while( !priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
			Nodo<V> actualVertex = priorityQueue.poll();
 
			for(Edge<V> edge : actualVertex.getAdjacents()){
 
				Nodo<V> v = edge.getDestination();
				if(!v.isVisited())
				{
					double newDistance = actualVertex.getDistance() + edge.getDistance();
 
					if( newDistance < v.getDistance() ){
						priorityQueue.remove(v);
						v.setDistance(newDistance);
						v.setPredecessor(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
		}
	}
 
	public List<Nodo<V>> getShortestPathTo(Nodo<V> targetVertex){
		List<Nodo<V>> path = new ArrayList<>();
 
		for(Nodo<V> vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
			path.add(vertex);
		}
 
		Collections.reverse(path);
		return path;
	}
 
}
