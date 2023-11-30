class Edge {
    private Node fromNode;
    private Node toNode;
    public Edge(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }
    public Node getFromNode() { return fromNode; }
    public Node getToNode() { return toNode; }
}