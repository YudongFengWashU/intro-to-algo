import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * For use in CSE 331 The brute-force algorithm for finding the shortest path to a node. HW10
 */
public class HW10_Student {

	private ArrayList<HashMap<Integer, Integer>> outgoing_edges;
	private ArrayList<HashMap<Integer, Integer>> incoming_edges;
	private int origin;

	/**
	 * This is the class constructor. You should not edit this.
	 * 
	 * @param origin
	 *            the chosen origin point
	 * @param outgoing_edges
	 *            This contains the list of edges starting from this node. For a specific node, the edges will be a hashmap. The key is the target node, the value is the weight of the edge.
	 * @param incoming_edges
	 *            Same as above, but for edges ending in this node.
	 */
	public HW10_Student(int origin, ArrayList<HashMap<Integer, Integer>> outgoing_edges, ArrayList<HashMap<Integer, Integer>> incoming_edges) {
		this.origin = origin;
		this.outgoing_edges = outgoing_edges;
		this.incoming_edges = incoming_edges;
	}

	/**
	 * 
	 * @return an array representing the shortest path from the chosen origin to each other node. The origin value should be zero.
	 */
	public int[] shortest_path() {
		int n = outgoing_edges.size();
		int[] distance = new int[n];

		for (int i = 0; i < n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[origin] = 0;

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(origin);

		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (int nextNode : outgoing_edges.get(node).keySet()) {
				int d = distance[node] + outgoing_edges.get(node).get(nextNode);
				if (d < distance[nextNode]) {
					distance[nextNode] = d;
					queue.offer(nextNode);
				}
			}
		}

		return distance;
	}
}
