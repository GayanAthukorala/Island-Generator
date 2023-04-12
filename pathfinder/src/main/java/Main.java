import graph.files.Edge;
import graph.files.Node;
import graph.files.PathContract;

import java.util.*;

public class Main implements PathContract {
    ArrayList<Node> nodeList;

    public void createGraph(ArrayList<List<Double>> rawNodeDataList, ArrayList<ArrayList<Integer>> connections){
        for (List<Double> li : rawNodeDataList){
            double x = li.get(0);
            double y = li.get(1);
            double elevation = li.get(2);
            Node node = new Node();
            node.assignProperties(elevation,x,y);
            nodeList.add(node);
        }

        for (int i = 0; i<nodeList.size(); i++){
            ArrayList<Integer> nodeConnections = connections.get(i);
            for (Integer connectedNodeIdx : nodeConnections){
                Node node = nodeList.get(i);
                Node connectedNode = nodeList.get(connectedNodeIdx);
                node.assignNeighbour(connectedNode);
            }
        }

    }


    @Override
    public ArrayList<Node> dijkstrasAlgorithm(Node startNode) {
        HashMap<Node, Node> paths = new HashMap<>();
        LinkedList<Node> unCheckedNodes = new LinkedList<Node>();
        unCheckedNodes.addAll(nodeList);
        unCheckedNodes.remove(startNode);
        startNode.setDist(0);
        unCheckedNodes.addFirst(startNode);
        while (unCheckedNodes!=null){
            Node nodeFirst = unCheckedNodes.getFirst();
            unCheckedNodes.remove(nodeFirst);
            for(Edge e: nodeFirst.edges){
                Node nodeSecond;
                if(nodeFirst == e.getNodes().get(0)){
                    nodeSecond = e.getNodes().get(1);
                }
                else if(nodeFirst == e.getNodes().get(1)){
                    nodeSecond = e.getNodes().get(0);
                }
                else{
                    nodeSecond = e.getNodes().get(1);
                    System.out.println("error");
                }
                if ((nodeFirst.getDist() + e.getWeight()) < nodeSecond.getDist()){
                    paths.put(nodeFirst, nodeSecond);
                    nodeSecond.setDist(nodeFirst.getDist() + e.getWeight());

                }
            }


        }

        return null;
    }
}
