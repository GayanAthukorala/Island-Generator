package graph.files;

import java.util.ArrayList;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class Edge {
    Node node1;
    Node node2;
    double weight;

    public void createEdge(Node start, Node end){
        node1 = start;
        node2 = end;
        weight = getWeight(node1.x, node1.y, node1.elevation, node2.x, node2.y, node2.elevation);
    }

    private double getWeight(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2) + Math.pow((z2-z1),2));
    }
}