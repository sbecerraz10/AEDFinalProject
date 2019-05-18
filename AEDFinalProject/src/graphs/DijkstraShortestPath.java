package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
 
public class DijkstraShortestPath<V> {
 
	public void computeShortestPaths(Node<V> sourceVertex){
 
		sourceVertex.setDistance(0);
		PriorityQueue<Node<V>> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
 
		while( !priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
			Node<V> actualVertex = priorityQueue.poll();
 
			for(Edge<V> edge : actualVertex.getAdjacents()){
 
				Node<V> v = edge.getDestination();
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
 
	public List<Node<V>> getShortestPathTo(Node<V> targetVertex){
		List<Node<V>> path = new ArrayList<>();
 
		for(Node<V> vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
			path.add(vertex);
		}
 
		Collections.reverse(path);
		return path;
	}
 
}
