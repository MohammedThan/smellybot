public class Main {
    public static void main(String[] args) {

        UserInterface.facad();


        //test case 1-4a
        // Node n0 = new Node("0");
        // Node n1 = new Node("1");
        // Node n2 = new Node("2");
        // Node n3 = new Node("3");
        // Node[] inodes = {n0};
        // Node[] fnodes = {};
        // Edge[] edges = {new Edge(n0, n2), new Edge( n2,n3), new Edge(n1,n3), new Edge(n0, n1), new Edge(n3, n0)};
        // SimplePath sp = new SimplePath(inodes, fnodes, edges);



        //test case 2-4a
        // Node n0 = new Node("0");
        // Node n1 = new Node("1");
        // Node n2 = new Node("2");
        // Node n3 = new Node("3");
        // Node n4 = new Node("4");
        // Node n5 = new Node("5");
        // Node n6 = new Node("6");
        // Node[] inodes = {};
        // Node[] fnodes = {n6};
        // Edge[] edges = {new Edge(n0, n1),new Edge(n0, n2),new Edge(n1, n2),new Edge(n2, n3),new Edge(n2, n4),new Edge(n3, n6),new Edge(n4, n5),new Edge(n5, n4),new Edge(n4, n6) };
        // SimplePath sp = new SimplePath(inodes, fnodes, edges);




        //test case 3-4b
        // Node n1 = new Node("1");
        // Node n2 = new Node("2");
        // Node n3 = new Node("3");
        // Node n4 = new Node("4");
        // Node n5 = new Node("5");
        // Node n6 = new Node("6");
        // Node n7 = new Node("7");
        // Node n8 = new Node("8");
        // Node[] inodes = {n1};
        // Node[] fnodes = {n8};
        // Edge[] edges = {new Edge(n1, n2), new Edge( n2,n3), new Edge(n3,n4), new Edge(n4, n3), new Edge(n3, n5),new Edge(n5, n6),new Edge(n6, n7),new Edge(n6, n8),new Edge(n7, n6)};
        // SimplePath sp = new SimplePath(inodes, fnodes, edges);

        // List<List<Node>> paths = sp.findallSimplePaths();
        //     System.out.println(paths.size());
        // // for (List<Node> path : paths) {
        // //     System.out.println("simple: "+ path);
        // // }

        // List<List<Node>> Ppaths =PrimePath.findPrimePath(paths);
        // System.out.println(Ppaths.size());
        // for (List<Node> path : Ppaths) {
        //     System.out.println("prime: "+path);
        // }

}

}