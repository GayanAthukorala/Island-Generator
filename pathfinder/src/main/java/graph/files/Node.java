package graph.files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Node {
    String cityName;
    double elevation;
    double x;
    double y;
    int id;
    Double distance = Double.MAX_VALUE;
    ArrayList<Node> neighbours;
    public ArrayList<Edge> edges;


    public void assignProperties(double elev, double xValue, double yValue, int nodeId){
        elevation = elev;
        x = xValue;
        y = yValue;
        id = nodeId;
    }

    public void assignNeighbour(Node neighbour){
        neighbours.add(neighbour);
        Edge edge = new Edge();
        edge.createEdge(this, neighbour);
        this.edges.add(edge);
        neighbour.edges.add(edge);
    }

    public void setDist(double dist){
        distance = dist;
    }

    public double getDist(){
        return distance;
    }



}