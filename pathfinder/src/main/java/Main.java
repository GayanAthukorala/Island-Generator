import graph.files.Edge;
import graph.files.Graph;
import graph.files.Node;
import graph.files.PathContract;

import java.util.*;

public class Main {

    public static void main(String[] args){
        ArrayList<ArrayList<Double>> rawData = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> a= new ArrayList<Double>();
        a.add(0.0);
        a.add(0.0);
        a.add(0.0);
        ArrayList<Double> b= new ArrayList<Double>();
        b.add(0.0);
        b.add(1.0);
        b.add(0.0);
        ArrayList<Double> c= new ArrayList<Double>();
        c.add(1.0);
        c.add(1.0);
        c.add(0.0);
        ArrayList<Double> d= new ArrayList<Double>();
        d.add(1.0);
        d.add(0.0);
        d.add(0.0);

        rawData.add(a);
        rawData.add(b);
        rawData.add(c);
        rawData.add(d);


        ArrayList<ArrayList<Integer>> rawDataConnect = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> aa= new ArrayList<Integer>();
        aa.add(1);
        aa.add(3);
        rawDataConnect.add(aa);

        ArrayList<Integer> bb= new ArrayList<Integer>();
        bb.add(0);
        bb.add(2);
        rawDataConnect.add(bb);

        ArrayList<Integer> cc= new ArrayList<Integer>();
        cc.add(1);
        cc.add(3);
        rawDataConnect.add(cc);

        ArrayList<Integer> dd= new ArrayList<Integer>();
        dd.add(0);
        dd.add(2);
        rawDataConnect.add(dd);

//        Graph graphGen = new Graph();
//        ArrayList<Node> nodeList = graphGen.createGraph(rawData,rawDataConnect);
//        System.out.println("node list");
//        System.out.println(nodeList);
//        System.out.println(graphGen.dijkstrasAlgorithm(nodeList.get(0),nodeList.get(1)));
//
    }

}
