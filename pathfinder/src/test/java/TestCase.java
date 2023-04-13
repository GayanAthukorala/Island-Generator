import graph.files.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCase {
    @Test

    public void emptyNodeDataList() throws Exception{
        ArrayList<ArrayList<Double>> rawData = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> a= new ArrayList<Double>();
        rawData.add(a);


        ArrayList<List<Integer>> rawDataConnect = new ArrayList<List<Integer>>();
        List<Integer> aa= new ArrayList<Integer>();
        aa.add(1);
        aa.add(3);
        rawDataConnect.add(aa);
        Graph graph = new Graph();
        graph.createGraph(rawData, rawDataConnect);
        assertNotNull(graph);
    }

    public void negativeNodeData() throws Exception{
        ArrayList<ArrayList<Double>> rawData = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> a= new ArrayList<Double>();
        rawData.add(a);
        a.add(-1.0);
        a.add(-3.0);
        a.add(0.0);
        a.add(0.0);

        ArrayList<List<Integer>> rawDataConnect = new ArrayList<List<Integer>>();
        List<Integer> aa= new ArrayList<Integer>();
        aa.add(1);
        aa.add(3);
        rawDataConnect.add(aa);
        Graph graph = new Graph();
        graph.createGraph(rawData, rawDataConnect);
        assertNotNull(graph);
    }

    public void nodeNeighboursDataOutOfRange() throws Exception{
        ArrayList<ArrayList<Double>> rawData = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> a= new ArrayList<Double>();
        rawData.add(a);
        a.add(0.0);
        a.add(0.0);
        a.add(0.0);
        a.add(0.0);

        ArrayList<List<Integer>> rawDataConnect = new ArrayList<List<Integer>>();
        List<Integer> aa= new ArrayList<Integer>();
        aa.add(1800);
        aa.add(-3);
        rawDataConnect.add(aa);
        Graph graph = new Graph();
        graph.createGraph(rawData, rawDataConnect);
        assertNotNull(graph);
    }
}
