package island.files;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


abstract class IslandSeed{
    int shape;
    int altType;
    int altStartIdx;
    int lakeNum;
    int lakeStartIdx;
    int riverNum;
    int riverStartIdx;
    int aquaNum;
    int aquaStartIdx;
    int soil;
    int biome;

}

public class IslandGen extends IslandSeed {

    List<Polygon> polygonList;
    List<Segment> segmentList;
    List<Vertex> vertexList;
    List<Double> elevations;
    List<Double> humidity;

    String islandColor = "253,255,208,255";
    List<Integer> islandBlocks = new ArrayList<>();
    List<Integer> heightPoints = new ArrayList<>();
    DecimalFormat precision  = new DecimalFormat("0.00");

    private void circleIsland(Mesh aMesh){
        for (int i =0; i< aMesh.getPolygonsCount(); i++){
            Polygon poly = polygonList.get(i);
            Vertex centroid = vertexList.get(poly.getCentroidIdx());
            double x = centroid.getX();
            double y = centroid.getY();
            double distance = Math.sqrt(Math.pow(x-255,2)+Math.pow(y-255,2));
            //lake
//            if (distance < 100){
//                colorPolygon(poly, 102, 178,255,255);
//            }
            //island
            if (distance < 200){
                colorPolygon(253, 255,208,255, i);
            }
            else{
                colorPolygon( 35, 85,138,255, i);
            }
        }
    }
    private void crossIsland(Mesh aMesh){
        Random bag = new Random();
        for (int i =0; i< aMesh.getPolygonsCount(); i++){
            Polygon poly = polygonList.get(i);
            Vertex centroid = vertexList.get(poly.getCentroidIdx());
            double x = centroid.getX();
            double y = centroid.getY();
            double distance = Math.sqrt(Math.pow(x-250,2)+Math.pow(y-250,2));

            if (distance < 200){
                colorPolygon(253, 255,208,255, i);
            }
            else{
                colorPolygon( 35, 85,138,255, i);
            }

            for (int j = 100; j<= 400; j+=150) {
                if (inOval(50, 100, j, 50, x, y) < 0) {
                    colorPolygon( 35, 85, 138, 255, i);
                }
                if (inOval(50, 100, j, 450, x, y) < 0) {
                    colorPolygon(35, 85, 138, 255, i);
                }
                if (inOval(100, 50, 50, j, x, y) < 0) {
                    colorPolygon( 35, 85, 138, 255, i);
                }

                if (inOval(100, 50, 450, j, x, y) < 0) {
                    colorPolygon(35, 85, 138, 255, i);
                }

            }

        }
    }

    private double inOval(int a,int b,int offsetX, int offsetY, double x, double y){
        double result = Math.pow(((x-offsetX)/a),2) + Math.pow(((y-offsetY)/b),2) -1;
        return result;
    }

    private void moonIsland(Mesh aMesh){
        Random bag = new Random();
        for (int i =0; i< aMesh.getPolygonsCount(); i++){
            Polygon poly = polygonList.get(i);
            Vertex centroid = vertexList.get(poly.getCentroidIdx());
            double x = centroid.getX();
            double y = centroid.getY();
            double distance = Math.sqrt(Math.pow(x-250,2)+Math.pow(y-250,2));

            double distance1 = Math.sqrt(Math.pow(x-450,2)+Math.pow(y-250,2));

            if (distance < 200 && distance1 > 100){
                colorPolygon(253, 255,208,255, i);
            }
            else{
                colorPolygon(35, 85,138,255, i);
            }
        }
    }
    private void ovalIsland(Mesh aMesh){
        Random bag = new Random();
        int a = bag.nextInt(100, 200);
        int b = bag.nextInt(50, 150);
        for (int i =0; i< aMesh.getPolygonsCount(); i++){
            Polygon poly = polygonList.get(i);
            Vertex centroid = vertexList.get(poly.getCentroidIdx());
            double x = centroid.getX();
            double y = centroid.getY();
            double result = Math.pow(((x-250)/a),2) + Math.pow(((y-250)/b),2) -1;

            if (result < 0) {
                colorPolygon(253, 255, 208, 255, i);
            }

            else{
                colorPolygon(35, 85,138,255, i);
            }

        }

    }

    private void createLakes(Mesh aMesh, int maxLakes){
        Random startLake = new Random();
        int startIndexL = startLake.nextInt(heightPoints.size());
        Random randLakeNum = new Random();
        int numLakes = randLakeNum.nextInt(1,maxLakes);
        for (int i = 0; i < numLakes; i ++){
            int polyIndex = (startIndexL + i) % heightPoints.size();
            int validPolyId  = heightPoints.get(polyIndex);
            Polygon poly = polygonList.get(validPolyId);
            colorPolygon(102, 178,255,255, validPolyId);

       }
        lakeStartIdx = startIndexL;
        lakeNum = numLakes;
    }
    private void addLakeHumidity(){

    }


    /**
     * Generate the new Islands
     * @param aMesh
     * @return
     */
    public Mesh generate(Mesh aMesh,String shape){
        // Get old mesh details
        polygonList = new ArrayList<>(aMesh.getPolygonsList());
        segmentList = new ArrayList<>(aMesh.getSegmentsList());
        vertexList = new ArrayList<>(aMesh.getVerticesList());

        // Set new Stats
        int nPolygons = polygonList.size();
        elevations = new ArrayList<Double>(Collections.nCopies(nPolygons, 1.0));
        humidity = new ArrayList<Double>(Collections.nCopies(nPolygons, 100.0));

        // New Island Meshes -- Will need to change to option
        crossIsland(aMesh);

        // Get Island Blocks
        getIslandBlocks();

        // Generate Elevation
        generateElevation(5);

        //
        createLakes(aMesh, 100);

        // Assigning Biomes and Types
        return Mesh.newBuilder().addAllVertices(vertexList).addAllSegments(segmentList).addAllPolygons(polygonList).build();
    }

    private void getIslandBlocks(){
        for (int i = 0; i < polygonList.size();i++){
            Polygon poly = polygonList.get(i);
            if (extractColorString(poly.getPropertiesList()).equals(islandColor)){
                islandBlocks.add(i);
                elevations.set(i,1.0);
            }
        }
        Collections.shuffle(islandBlocks,new Random(2));
        generateInnerIsland();
    }
    private void generateInnerIsland(){
        // heightPoint Island Blocks
        for (Integer polyIdx : islandBlocks){
            boolean allNeighbourIslands = true;
            Polygon poly = polygonList.get(polyIdx);
            List<Integer> neighbourList = poly.getNeighborIdxsList();
            for (Integer j : neighbourList){
                if (!islandBlocks.contains(j)){
                    allNeighbourIslands = false;
                }
            }
            if (allNeighbourIslands){
                heightPoints.add(polyIdx);
            }
        }
    }

    private void generateElevation(int startIdx){
        // Have it incrementally do it with the seed
        altStartIdx = startIdx;
        for (int i = 0; i < heightPoints.size()/2; i++){
            int Idx = (startIdx + i) % heightPoints.size();
            int polyIdx = heightPoints.get(Idx);
            Polygon poly = polygonList.get(polyIdx);
            List<Integer> neighbourList = poly.getNeighborIdxsList();
            colorHeight(poly,1.5);
            double elevationVal = Double.parseDouble(precision.format(elevations.get(polyIdx)+1.5));
            elevations.set(polyIdx,elevationVal);
            for (Integer j : neighbourList){
                Polygon neighbourPoly = polygonList.get(j);
                colorHeight(neighbourPoly,1.2);
                double elevationVal2 = Double.parseDouble(precision.format(elevations.get(polyIdx)+1.2));
                elevations.set(j,elevationVal2);
            }
        }
    }
    private void colorHeight(Polygon poly, double value){
        // Island is "253,255,208,255"
        double red = 253/value;
        double green = 255/value;
        double blue = 208/value;
        int index = polygonList.indexOf(poly);
        colorPolygon((int)red,(int)green,(int)blue,255, index);
    }
    private void colorPolygon(int red, int green, int blue, int alpha, int index){
        Polygon poly = polygonList.get(index);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(red + "," + green + "," + blue+ "," + alpha).build();
        Polygon colored = Polygon.newBuilder(poly).addProperties(color).build();
        polygonList.set(index, colored);
    }
    private void assignType(Polygon poly, String type){
        Structs.Property typeProperty = Structs.Property.newBuilder().setKey("Type").setValue(type).build();
        Polygon typed = Polygon.newBuilder(poly).addProperties(typeProperty).build();
        polygonList.set(polygonList.indexOf(poly), typed);
    }
    private String extractType(List<Structs.Property> properties){
        String val = null;
        for(Structs.Property p: properties) {
            // TRY TO FIND THE RGB COLOR
            if (p.getKey().equals("Type")) {
                val = p.getValue();
            }
        }
        if (val == null){       // IF THE RGB COLOR PROPERTY DOESNT EXIST, COVER THAT CASE BY MAKING IT BLACK
            return "None"; // COVERING CASE IF KEY RGB_COLOR DOESN'T EXIST
        }
        return val;
    }
    private String extractColorString(List<Structs.Property> properties){
        String val = null;

        for(Structs.Property p: properties) {
            // TRY TO FIND THE RGB COLOR
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null){       // IF THE RGB COLOR PROPERTY DOESNT EXIST, COVER THAT CASE BY MAKING IT BLACK
            return "0,0,0,0"; // COVERING CASE IF KEY RGB_COLOR DOESN'T EXIST
        }
        return val;
    }
}
