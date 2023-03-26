package island.files;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * This Class Lake is to create Lakes for the Mesh, while contributing moisture to nearby soil.
 */
public class Lake implements IslandColour {

    int lakeNum;
    int maxLakes;
    List<Double> humidity;
    List<Integer> heightPoints;

    List<Integer> lakeIdxs = new ArrayList<>();
    boolean isSeed;
    List<Structs.Polygon> polygonList;
    List<Integer> islandBlocks = new ArrayList<>();
    DecimalFormat precision  = new DecimalFormat("0.00");
    double soil;

    /**
     *
     * This method is the parent where it initiates values and calls methods
     */
    public void generateLakes(double soilPercent, List<Integer> iBlocks,boolean seed, int maximumLakes, int startIndexL, int lN, List<Double> humid,List<Integer> hPoints, List<Structs.Polygon> pList){
        lakeNum = lN;
        maxLakes = maximumLakes;
        humidity = humid;
        heightPoints = hPoints;
        polygonList = pList;
        isSeed = seed;
        islandBlocks = iBlocks;
        soil = soilPercent;

        createLakes(maximumLakes,startIndexL);
    }

    /**
     * CreateLakes will create lakes with varying sizes!
     * This method will also determine what number to use whether there is a seed or not.
     * @param maximumLakes
     * @param startIndexL
     */
    public void createLakes(int maximumLakes, int startIndexL){
        Random randNum = new Random();
        if(maximumLakes> 0){
            //int startIndexL = randNum.nextInt(heightPoints.size()); //put this in the generator in an if statement
            if (isSeed){    // IF THERE IS A SEED, USE THE NUMBER MATCHING TO THE SEED.
                lakeNum = maxLakes;
            }else {
                lakeNum = randNum.nextInt(maxLakes); // SINCE THIS A USER GIVEN "MAX" Number of Lakes, we can randomize how many based on their input, but save that random num.
            }
            for (int i = 0; i < lakeNum; i ++){
                int polyIndex = (startIndexL + i) % heightPoints.size();    // INDEX CHECKING
                int validPolyId  = heightPoints.get(polyIndex);
                lakeSizes(validPolyId);                                     // THIS WILL CREATE UNIQUE SIZES
            }
        }
    }

    /**
     * This method creates unique sizes of lake shapes by taking in a polygon identifier
     * @param polyIdx
     */
    private void lakeSizes(int polyIdx){
        lakeIdxs.add(polyIdx);
        Structs.Polygon poly = polygonList.get(polyIdx);
        colorPolygon(79, 156,255,255, polyIdx);
        new Color(79, 156, 255);       
        addLakeHumidity(polyIdx);
        List<Integer> neighbours;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(polyIdx);
        int ranInt = 6;
        while (ranInt > 0 && !stack.isEmpty()){
            int idxVal = stack.pop();
            if (idxVal != polyIdx){
                lakeIdxs.add(idxVal);
                colorPolygon(79, 156,255,255, idxVal);
                addLakeHumidity(idxVal);
            }
            Structs.Polygon polyTest = polygonList.get(idxVal);
            neighbours = polyTest.getNeighborIdxsList();

            for (Integer i : neighbours){
                if (islandBlocks.contains(i)){
                    boolean edge = false;
                    for (Integer j : polygonList.get(i).getNeighborIdxsList()){
                        if (!islandBlocks.contains(j))
                            edge = true;
                    }
                    if (!edge)
                        stack.add(i);
                }
            }
            ranInt -= 1;
        }
    }

    private void addLakeHumidity(int lakePoly){
        Structs.Polygon poly = polygonList.get(lakePoly);
        double humidityValLake = Double.parseDouble(precision.format(humidity.get(lakePoly)+150));
        humidity.set(lakePoly, humidityValLake);
        for (Integer n : poly.getNeighborIdxsList()){
            //may be used for colouring later
            Structs.Polygon neighbourPoly = polygonList.get(n);
            //colorHeight(neighbourPoly,1.2);
            double humidityValNeigbours = Double.parseDouble(precision.format(humidity.get(n)+100*soil));
            humidity.set(n,humidityValNeigbours);
            for (Integer i : neighbourPoly.getNeighborIdxsList()){
                double doubleNeighbours = Double.parseDouble(precision.format(humidity.get(i)+50*soil));
                humidity.set(i,doubleNeighbours);
            }
        }
    }
    @Override
    public void colorPolygon(int red, int green, int blue, int alpha, int index){
        Structs.Polygon poly = polygonList.get(index);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(red + "," + green + "," + blue+ "," + alpha).build();
        Structs.Polygon colored = Structs.Polygon.newBuilder(poly).addProperties(color).build();
        polygonList.set(index, colored);
    }
}
