import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HW5_Student {

	private HashMap<Integer, ArrayList<Integer>> graph;

	public HW5_Student(HashMap<Integer, ArrayList<Integer>> g) {
		graph = g;
	}

	public ArrayList<Integer> findCycle() {
		for (int node : graph.keySet()) {
			Set<Integer> visited = new HashSet<Integer>();

			ArrayList<Integer> cycle = findCycle(visited, node, -1);
			if (cycle != null) {
				return cycle;
			}
		}

		return null;
	}

	private ArrayList<Integer> findCycle(Set<Integer> visited, int node, int prev) {
		if(visited.contains(node))
			return new ArrayList<Integer>();
		
		visited.add(node);
		
		for(int nextNode : graph.get(node)) {
			if(nextNode == prev) 
				continue;
			
			ArrayList<Integer> cycle = findCycle(visited, nextNode, node);
			
			if(cycle != null) {
				cycle.add(node);
				return cycle;
			}
		}
		
		return null;
	}
}
