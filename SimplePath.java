
import java.util.*;

public class SimplePath {
    private List<Node> inodes;
    private List<Node> fnodes;
    private List<Edge> edges;
    private List<Node> allNodes;

    public SimplePath(List<Node> inodes, List<Node> fnodes, List<Edge> edges, List<Node> allNodes) {
        this.inodes = inodes;
        this.fnodes = fnodes;
        this.edges = edges;
        this.allNodes = allNodes;
        if (inodes.size() == 0) {
            throw new IllegalArgumentException("No initial nodes specified.");
        }
    }

    public List<List<Node>> findallSimplePaths() {
        List<List<Node>> result = new ArrayList<>();
        for (Node inode : allNodes) {
            List<Node> singels = new ArrayList<>();
            singels.add(inode);
            result.add(singels);
            List<Node> visited = new ArrayList<>();
            visited.add(inode);
            findAllSimplePaths(inode, visited, result);
        }
        return result;
    }

    private void findAllSimplePaths(Node node, List<Node> visited, List<List<Node>> result) {
        for (Edge edge : edges) {
            Node nextNode = null;
            if (edge.getFromNode().equals(node) && (!visited.contains(edge.getToNode()) || visited.get(0) == edge.getToNode())) {
                nextNode = edge.getToNode();
            }
            if (nextNode != null) {
                List<Node> temp = new ArrayList<>(visited);
                temp.add(nextNode);
                if (!hasDuplicate(temp))
                    result.add(temp);
                findAllSimplePaths(nextNode, temp, result);
            }
        }
    }


    public static boolean hasDuplicate(List<Node> list) {
        HashSet<String> uniqueSet = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (uniqueSet.contains(node.getId())) {
                if (i == 0 || i == list.size() - 1) {
                    continue;
                } else {
                    return true; 
                }
            } else {
                uniqueSet.add(node.getId());
            }
        }
        return false; 
    }
}