import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class HW7_Student {

	private int _startNode;
	private int _endNode;
	private HashMap<Integer, ArrayList<Integer>> graph;

	public HW7_Student(int startNode, int endNode, HashMap<Integer, ArrayList<Integer>> g) {
		_startNode = startNode;
		_endNode = endNode;
		graph = g;
	}

	public ArrayList<Integer> outputPath() {
		/*
		 * Find the smallest weighted path between _startNode and _endNode The
		 * first number of graph's adjacency list is the weight of it's node
		 */
		HashMap<Integer, Integer> weightsMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> edgeFrom = new HashMap<Integer, Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();

		for (int i = 0; i < graph.size(); i++) {
			weightsMap.put(i, Integer.MAX_VALUE);
		}

		weightsMap.put(_startNode, graph.get(_startNode).get(0));

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(_startNode, graph.get(_startNode).get(0)));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int from = node.index;

			if (from == _endNode) {
				break;
			}

			visited.add(from);
			
			int weightFrom = weightsMap.get(from);
			for (int i = 1; i < graph.get(from).size(); i++) {
				int to = graph.get(from).get(i);
				
				if(visited.contains(to))
					continue;
				
				int weight = graph.get(to).get(0);
				if (weightsMap.get(to) > weightFrom + weight) {
					weightsMap.put(to, weightFrom + weight);
					edgeFrom.put(to, from);
					queue.add(new Node(to, weightFrom + weight));
				}
			}
		}

		ArrayList<Integer> path = new ArrayList<Integer>();

		path.add(0, _endNode);
		while (path.get(0) != _startNode) {
			if (edgeFrom.get(path.get(0)) == null) {
				path.clear();
				return path;
			}
			path.add(0, edgeFrom.get(path.get(0)));
		}

		return path;
	}

	class Node implements Comparable<Node> {
		int index;
		int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node n) {
			return weight - n.weight;
		}
	}
}
