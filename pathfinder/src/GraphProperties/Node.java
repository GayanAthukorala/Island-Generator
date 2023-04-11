package GraphProperties;

import java.util.ArrayList;

public class Node {
    String cityName;
    double elevation;
    int vertexId;
    ArrayList<Integer> neighbours;

    private void assignProperties(int id, double elev){
        elevation = elev;
        vertexId = id;
    }

    private void assignNeighbour(int neighbourId){
        neighbours.add(neighbourId);
    }
}
