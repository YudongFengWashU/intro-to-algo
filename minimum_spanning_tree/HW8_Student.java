import java.util.ArrayList;
import java.util.Arrays;

/**
 * The student template. For use in HW8
 */
public class HW8_Student {

	/**
	 * This is the adjacency matrix representation for the graph. Each ArrayList
	 * represents a row, with the values contained representing the value in
	 * that row and column.
	 */
	private ArrayList<ArrayList<Integer>> adj_matrix;

	/**
	 * This does not need to be changed. It is merely the constructor.
	 * 
	 * @param adj_matrix
	 *            The adjacency matrix representation of the graph.
	 */
	public HW8_Student(ArrayList<ArrayList<Integer>> adj_matrix) {
		this.adj_matrix = adj_matrix;
	}

	/**
	 * You will fill this out. We expect you to find out the minimum spanning
	 * tree. You may choose your root arbitrarily. The int[] contained will
	 * represent this tree. Each value will be the parent of the node with the
	 * index value. For example, if output[7] = 12, then node 7 has 12 as its
	 * parent. For the root node, it should have a value of -1.
	 * 
	 * @return a representation of the MST.
	 */
	public int[] output_edges() {
		int n = adj_matrix.size();
		int[] lowcost = new int[n];
		int[] edgeFrom = new int[n];

		for (int i = 1; i < n; i++) {
			int weight = adj_matrix.get(0).get(i);
			if (weight > 0) {
				lowcost[i] = weight;
				edgeFrom[i] = 0;
			} else {
				lowcost[i] = Integer.MAX_VALUE;
				edgeFrom[i] = -1;
			}
			
		}
		edgeFrom[0] = -1;
		lowcost[0] = 0;

		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			int minid = 0;
			for (int j = 0; j < n; j++) {
				if (lowcost[j] < min && lowcost[j] != -1) {
					min = lowcost[j];
					minid = j;
				}
			}
			lowcost[minid] = -1;

			for (int j = 0; j < n; j++) {
				int weight = adj_matrix.get(minid).get(j);
				if (weight >= 0 && weight < lowcost[j]) {
					lowcost[j] = weight;
					edgeFrom[j] = minid;
				}
			}
			
		}

		return edgeFrom;
	}
}
