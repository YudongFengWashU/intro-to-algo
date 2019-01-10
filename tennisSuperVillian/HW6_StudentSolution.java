import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * The student solution For use in CSE 331
 */
public class HW6_StudentSolution {

	private ArrayList<int[]> rallies;

	public HW6_StudentSolution(ArrayList<int[]> rallies) {
		this.rallies = rallies;
	}

	/**
	 * You should fill this in on your own. Rallies is list of tuples, in the
	 * form (rally duration, rally deadline). Your output should also be a list
	 * of tuples, of the form (rally id, start time of rally). If no possible
	 * schedule exists, you should return an empty list.
	 * 
	 * @return
	 */
	public ArrayList<int[]> getSchedule() {
		ArrayList<int[]> schedule = new ArrayList<>();
		ArrayList<int[]> sortedRallies = new ArrayList<int[]>(rallies);
		Collections.sort(sortedRallies, new Comparator<int[]>() {

			@Override
			public int compare(int[] r1, int[] r2) {
				return r1[1] - r2[1];
			}
		});

		int t = 0;
		for (int[] rally : sortedRallies) {
			int index = rallies.indexOf(rally);
			int[] tuple = {index, t};
			t += rally[0];
			if (t > rally[1] + rally[0]) {
				schedule.clear();
				return schedule;
			}
			schedule.add(tuple);
		}

		return schedule;
	}
}
