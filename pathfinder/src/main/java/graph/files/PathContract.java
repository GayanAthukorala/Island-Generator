package graph.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface PathContract {

    LinkedList<Integer> dijkstrasAlgorithm(Node startNode, Node endNode);
}
