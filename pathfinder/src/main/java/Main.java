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
            node.assignProperties(elevation,x,y,rawNodeDataList.indexOf(li));
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
    public HashMap<Integer,Integer> dijkstrasAlgorithm(Node startNode) {
        HashMap<Integer, Integer> paths = new HashMap<>();
        LinkedList<Integer> unCheckedNodes = new LinkedList<Integer>();

        for (Node n: nodeList){
            unCheckedNodes.add(nodeList.indexOf(n));
        }

        unCheckedNodes.remove(nodeList.indexOf(startNode));
        startNode.setDist(0);
        unCheckedNodes.addFirst(nodeList.indexOf(startNode));
        paths.put(nodeList.indexOf(startNode), nodeList.indexOf(startNode));
        while (!unCheckedNodes.isEmpty()){
            Node nodeFirst = nodeList.get(unCheckedNodes.getFirst());
            unCheckedNodes.remove(nodeList.indexOf(nodeFirst));
            for(Edge e: nodeFirst.edges){
                Node nodeSecond;
                if(nodeFirst == nodeList.get(e.getNodes().get(0))){
                    nodeSecond = nodeList.get(e.getNodes().get(1));
                }
                else if(nodeFirst == nodeList.get(e.getNodes().get(1))){
                    nodeSecond = nodeList.get(e.getNodes().get(0));
                }
                else{
                    nodeSecond = nodeList.get(e.getNodes().get(0));
                    System.out.println("error");
                }

                int index1 = nodeList.indexOf(nodeFirst);
                int index2 = nodeList.indexOf(nodeSecond);

                if ((nodeFirst.getDist() + e.getWeight()) < nodeSecond.getDist()){
                    nodeSecond.setDist(nodeFirst.getDist() + e.getWeight());
                    nodeList.set(index1, nodeFirst);
                    nodeList.set(index2, nodeSecond);

                    paths.put(nodeList.indexOf(nodeSecond), nodeList.indexOf(nodeFirst));

                    for (Integer n: unCheckedNodes){
                        if (nodeList.get(n).getDist() > nodeSecond.getDist()){
                            unCheckedNodes.remove(nodeList.indexOf(nodeSecond));
                            unCheckedNodes.add(unCheckedNodes.indexOf(n),nodeList.indexOf(nodeSecond));
                        }
                    }
                }
            }


        }

        return paths;
    }
}
