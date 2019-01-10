import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the Driver for homework 10. You are not submitting this, so be sure that any changes you make do
 * not change the overall functionality of your homework.
 */
public class Driver {

    public static int origin;
    public static ArrayList<HashMap<Integer,Integer>> incoming_edges, outgoing_edges;

    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("Please provide the input filepath as the first argument");
            return;
        }

        //Get the input filename from command line arguments and parse it
        String inputFile = args[0];
        readFile(inputFile);

        // Get the shortest path
        HW10_Student student_class = new HW10_Student(origin, outgoing_edges, incoming_edges);
        int[] min_distances = student_class.shortest_path();

                System.out.print("[ ");
        for (int i = 0; i < min_distances.length; i++) {
            System.out.print(min_distances[i]);
            if(i != min_distances.length - 1) System.out.print(" ");
        }
        System.out.print("]\n");
    }

    /**
     * This function will read input file.
     * The format of the input file is explained on the website.
     * @param inFile file name of file to be read
     */
    private static void readFile(String inFile){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));

            // Ordered by node. The first entry corresponds to the HashMap representing the incoming edges on the
            // first node.
            incoming_edges = new ArrayList();
            outgoing_edges = new ArrayList();

            origin = Integer.parseInt(bufferedReader.readLine());

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] incoming_string = line.split(" ");
                line = bufferedReader.readLine();
                String[] outgoing_string = line.split(" ");

                // First is the other node on the edge. Second is the weight of that node.
                HashMap<Integer, Integer> incoming = new HashMap();
                HashMap<Integer, Integer> outgoing = new HashMap();

                for (int i = 0; i < incoming_string.length; i++){
                    int node = Integer.parseInt(incoming_string[i]);
                    i++;
                    int weight = Integer.parseInt(incoming_string[i]);
                    incoming.put(node, weight);
                }

                incoming_edges.add(incoming);

                for (int i = 0; i < outgoing_string.length; i++){
                    int node = Integer.parseInt(outgoing_string[i]);
                    i++;
                    int weight = Integer.parseInt(outgoing_string[i]);
                    outgoing.put(node, weight);
                }

                outgoing_edges.add(outgoing);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
