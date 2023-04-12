package island.files;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import graph.files.Graph;
import graph.files.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cities{
    int cityNum;
    int cityHubIdx;
    List<Structs.Vertex> vertexList;
    List<Structs.Segment> segmentList;
    List<Structs.Polygon> polygonList;
    List<Node> nodeList;
    Graph graph = new Graph();
    ArrayList<Integer> cityVertexList = new ArrayList<>();


    public void cityGen(int numCities, List<Integer> islandBlocks, List<Integer> lakeIdxs, List<Structs.Polygon> polyList, List<Double> elevations, List<Structs.Vertex> vertList, List<Structs.Segment> segList){

        polygonList = polyList;
        vertexList = vertList;
        segmentList = segList;
        createCities(islandBlocks, lakeIdxs, elevations);

//        for(int j =0; j<adjacentCities.size(); j++){
//            List<Integer> neighbours = adjacentCities.get(j);
//            for (int i=0; i<neighbours.size();i++){
//
//                neighbours.set(i,polygonList.get(neighbours.get(i)).getCentroidIdx());
//            }
//        }

    }

    private void createCities(List<Integer> islandBlocks, List<Integer> lakeIdxs, List<Double> elevations){
        ArrayList<ArrayList<Double>> cities = new ArrayList<>();
        ArrayList<Double> city ;
        List<Integer> adjacents;
        ArrayList<List<Integer>> adjacentCities = new ArrayList<>();
        ArrayList<Integer> landBlocks = new ArrayList<>(islandBlocks);
        for(int lakeIdx : lakeIdxs){
            landBlocks.remove((Integer) lakeIdx);
        }

        for(int i : landBlocks){
            city = new ArrayList<>();
            adjacents = new ArrayList<>();

            Structs.Polygon poly =  polygonList.get(i);

            for (int neighbour: poly.getNeighborIdxsList()){
                adjacents.add(polygonList.get(neighbour).getCentroidIdx());
            }
            adjacentCities.add(adjacents);

            double elev =  elevations.get(i);
            cityVertexList.add(poly.getCentroidIdx());
            int centroidIdx = poly.getCentroidIdx();
            Structs.Vertex centroid = vertexList.get(centroidIdx);
            double x = centroid.getX();
            double y = centroid.getY();

            city.add(x);
            city.add(y);
            city.add(elev);
            city.add((double) centroidIdx);
            cities.add(city);

        }

//        System.out.println("adj cities");
//        System.out.println(adjacentCities);

//        System.out.println(cities);
//        System.out.println(adjacentCities);
        nodeList = graph.createGraph(cities,adjacentCities);
        cityHubIdx = (nodeList.size())/2;
        colorVertex(vertexList.get(cityVertexList.get(cityHubIdx)),0,255,0,255);
        addThickness(vertexList.get(cityVertexList.get(cityHubIdx)),12.0);


    }

    public void createRoads(int cityIdx){
        colorVertex(vertexList.get(cityVertexList.get((cityIdx))),0,255,0,255);
        addThickness(vertexList.get(cityVertexList.get(cityIdx)),0);
        System.out.println("path");
        LinkedList<Integer> path = (graph.dijkstrasAlgorithm(nodeList.get(cityHubIdx), nodeList.get(cityIdx)));
        System.out.println(path);
        for (int i = 0; i<path.size()-1;i++){
            Structs.Segment road = Structs.Segment.newBuilder().setV1Idx(cityVertexList.get(path.get(i))).setV2Idx(cityVertexList.get(path.get(i+1))).build();
            segmentList.add(road);
            System.out.println(road);
            colorSegment(road, 0,0,0,255);
        }


    }

    private void colorVertex(Structs.Vertex vertex, int red, int green, int blue, int alpha){
        String colorCode = red + "," + green + "," + blue + "," + alpha;
        // Create new Property with "rgb_color" key and the rgb value as the value
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Structs.Vertex colored = Structs.Vertex.newBuilder(vertex).addProperties(color).build();
        // Set the old vertex in the list as the new one with color property
        vertexList.set(vertexList.indexOf(vertex), colored);
    }

    private void colorSegment(Structs.Segment seg, int red, int green, int blue, int alpha){
        // Create new Property with "rgb_color" key and the rgb value as the value
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(red + "," + green + "," + blue+ "," + alpha).build();
        Structs.Segment colored = Structs.Segment.newBuilder(seg).addProperties(color).build();
        // Set the old segment in the list as the new one with color property
        segmentList.set(segmentList.indexOf(seg), colored);
    }

    private void addThickness(Structs.Vertex v, double thickness){
        if (thickness == 0){
            Random rand = new Random();
            thickness = rand.nextDouble(3.0,8.0);
        }

        Structs.Property thick = Structs.Property.newBuilder().setKey("thickness").setValue(String.valueOf(thickness)).build();
        Structs.Vertex thickened = Structs.Vertex.newBuilder(v).addProperties(thick).build();
        vertexList.set(vertexList.indexOf(v),thickened);

    }

}
