package graph.files;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    String cityName;
    double elevation;
    double x;
    double y;
    int vertexId;
    ArrayList<Node> neighbours;
    HashMap<Edge, Double> edges;


    private void assignProperties(int id, double elev, double xValue, double yValue){
        elevation = elev;
        vertexId = id;
        x = xValue;
        y = yValue;
    }

    private void assignNeighbour(Node neighbour){
        neighbours.add(neighbour);
        Edge edge = new Edge();
        edge.createEdge(this, neighbour);
        this.edges.put(edge, edge.weight);
        neighbour.edges.put(edge, edge.weight);
    }



}