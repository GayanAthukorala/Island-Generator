package graph.files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class Edge {
    int node1;
    int node2;
    double weight;

    public void createEdge(Node nodeFirst, Node nodeSecond){
        node1 = nodeFirst.id;
        node2 = nodeSecond.id;
        weight = getWeight(nodeFirst.x, nodeFirst.y, nodeFirst.elevation, nodeSecond.x, nodeSecond.y, nodeSecond.elevation);
    }

    private double getWeight(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2) + Math.pow((z2-z1),2));
    }

    public ArrayList<Integer> getNodes(){
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        nodes.add(node1);
        nodes.add(node2);
        return nodes;
    }

    public double getWeight(){
        return weight;
    }
}