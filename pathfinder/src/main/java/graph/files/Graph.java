package graph.files;

import java.text.ParseException;
import java.util.*;

public class Graph implements PathContract{
    ArrayList<Node> nodeList = new ArrayList<Node>();
    ArrayList<Integer> nodeIndex = new ArrayList<>();

    public ArrayList<Node> createGraph(ArrayList<ArrayList<Double>> rawNodeDataList, ArrayList<List<Integer>> connections){
        for (List<Double> li : rawNodeDataList){
            double x = 0;
            double y = 0;
            double elevation = 0;
            int idx = 0;
            try{
                x = li.get(0);
                y = li.get(1);
                elevation = li.get(2);
                idx = (int)(double)li.get(3);
            }
            catch (Exception e){
                System.out.println("Incorrect Node Data");
            }

            Node node = new Node();
            node.assignProperties(elevation,x,y,idx);
            nodeList.add(node);
            nodeIndex.add(idx);
        }

        for (int i = 0; i<nodeList.size(); i++){
            List<Integer> nodeConnections = connections.get(i);
            for (Integer connectedNodeIdx : nodeConnections){
                Node node = null;
                try{
                     node = nodeList.get(i);
                }
                catch (Exception e){
                    System.out.println("Node Neighbour is out of bounds");
                }

                if(nodeIndex.contains(connectedNodeIdx)){
                    Node connectedNode = nodeList.get(nodeIndex.indexOf(connectedNodeIdx));
                    node.assignNeighbour(connectedNode);
                }
            }
        }

        return nodeList;

    }


    @Override
    public LinkedList<Integer> dijkstrasAlgorithm(Node startNode, Node endNode) {
        HashMap<Integer, Integer> paths = new HashMap<>();
        LinkedList<Integer> unCheckedNodes = new LinkedList<Integer>();

        for (Node n: nodeList){
            unCheckedNodes.add(nodeList.indexOf(n));
        }

        unCheckedNodes.remove((Integer) nodeList.indexOf(startNode));
        startNode.setDist(0);
        unCheckedNodes.addFirst(nodeList.indexOf(startNode));
        paths.put(nodeList.indexOf(startNode), nodeList.indexOf(startNode));

        while (!unCheckedNodes.isEmpty()){
            Node nodeFirst = nodeList.get(unCheckedNodes.getFirst());
            unCheckedNodes.remove((Integer) nodeList.indexOf(nodeFirst));
            for(Edge e: nodeFirst.edges){
                Node nodeSecond;
                if(nodeFirst == nodeList.get(nodeIndex.indexOf(e.getNodes().get(0)))){
                    nodeSecond = nodeList.get(nodeIndex.indexOf(e.getNodes().get(1)));
                }
                else if(nodeFirst == nodeList.get(nodeIndex.indexOf(e.getNodes().get(1)))){
                    nodeSecond = nodeList.get(nodeIndex.indexOf(e.getNodes().get(0)));
                }
                else{
                    nodeSecond = nodeList.get(nodeIndex.indexOf(e.getNodes().get(0)));
                    System.out.println("error");
                }

                int index1 = nodeList.indexOf(nodeFirst);
                int index2 = nodeList.indexOf(nodeSecond);

                if ((nodeFirst.getDist() + e.getWeight()) < nodeSecond.getDist()){
                    nodeSecond.setDist(nodeFirst.getDist() + e.getWeight());
                    nodeList.set(index1, nodeFirst);
                    nodeList.set(index2, nodeSecond);

                    if(nodeList.indexOf(nodeSecond) == nodeList.indexOf(endNode)){
                        System.out.println(nodeList.indexOf(nodeSecond));
                        System.out.println(nodeList.indexOf(nodeFirst));
                    }
                    paths.put(nodeList.indexOf(nodeSecond), nodeList.indexOf(nodeFirst));


                    for (Integer n: unCheckedNodes){
                        if (nodeList.get(n).getDist() > nodeSecond.getDist()){
                            unCheckedNodes.remove((Integer) nodeList.indexOf(nodeSecond));
                            unCheckedNodes.add(unCheckedNodes.indexOf(n),nodeList.indexOf(nodeSecond));
                            break;
                        }
                    }
                }
            }


        }

        return determineShortestPath(startNode, endNode,paths);
    }


    private LinkedList<Integer> determineShortestPath(Node startNode, Node endNode, HashMap<Integer, Integer> paths){
        System.out.println("HASHMAP");
        paths.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        LinkedList<Integer> shortestPath = new LinkedList<>();
        int endIdx = nodeList.indexOf(endNode);
        int startIdx = nodeList.indexOf(startNode);
        int nodeIdx = endIdx;
        shortestPath.addFirst(nodeIdx);
        while(nodeIdx!=startIdx){
            System.out.println("nodes");
            System.out.println(nodeIdx);
            nodeIdx = paths.get(nodeIdx);
            shortestPath.addFirst(nodeIdx);
        }

        return shortestPath;
    }

}

