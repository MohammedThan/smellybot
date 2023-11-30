import java.util.*;

public class UserInterface {

    public static void facad() {
        ArrayList<Node> iNode= NodesInput("initial" );
        ArrayList<Node> fNode= NodesInput("final" );
        ArrayList<Node> oNode= NodesInput("other" );
        ArrayList<Node> allNodes=mergeNodeLists(iNode,fNode,oNode);
        ArrayList<Edge> edges= edgesInput(allNodes);

        SimplePath sp=new SimplePath(iNode,fNode,edges,allNodes);
        List<List<Node>> SPs= getSPs(sp);
        List<List<Node>> PPs= getPPs(SPs);
        printPaths(SPs,"simple");
        printPaths(PPs,"prime");

        
        tourPrimeInput(allNodes,PPs);



     }


    
    public static ArrayList<Node> NodesInput(String type){
        ArrayList<Node> nodes = new ArrayList<Node>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of "+ type +" nodes: ");
        int n = sc.nextInt();
        while(n<1 && type=="initial"){
            System.out.println("Enter a valid number of nodes: ");
            n = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            System.out.println("Enter the name of node "+i+": ");
            String name = sc.next();
            Node node = new Node(name);
            nodes.add(node);
        }
        sc.close();
        return nodes;
    }

    public static ArrayList<Edge> edgesInput(ArrayList<Node> nodes){
        ArrayList<Edge> edges = new ArrayList<Edge>();
        ArrayList<String> nodeNames= new ArrayList<String>();
        for(Node node:nodes){
            nodeNames.add(node.toString());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of edges: ");
        int n = sc.nextInt();
        while(n<1){
            System.out.println("Enter a valid number of edges: ");
            n = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            System.out.println("Enter the name of the from node of edge "+i+": ");
            String fromName = sc.next();
            System.out.println("Enter the name of the to node of edge "+i+": ");
            String toName = sc.next();
            while(nodeNames.contains(toName) == false || nodeNames.contains(fromName) == false){
                System.out.println("Enter a valid name of the to node of edge "+i+": ");
                toName = sc.next();
                System.out.println("Enter a valid name of the from node of edge "+i+": ");
                fromName = sc.next();
            }
            Edge edge = new Edge(nodes.get(nodeNames.indexOf(fromName)), nodes.get(nodeNames.indexOf(toName)));
            edges.add(edge);
        }
        sc.close();
        return edges;
    }

    private static ArrayList<Node> mergeNodeLists(ArrayList<Node> iNode,ArrayList<Node> fNode,ArrayList<Node> oNode) {
        ArrayList<Node> allNodes = new ArrayList<Node>();
        allNodes.addAll(iNode);
        allNodes.addAll(fNode);
        allNodes.addAll(oNode);

        for(int i = 0; i < allNodes.size(); i++){
            if(allNodes.lastIndexOf(allNodes.get(i)) !=allNodes.lastIndexOf(allNodes.get(i))){
                throw new IllegalStateException("Node " + allNodes.get(i) + " is duplicated please enter a valid list of nodes");
            }
        }

        return allNodes;
    }

    private static void tourPrimeInput(ArrayList<Node> nodes,List<List<Node>> PPs){
        ArrayList<String> nodeNames= new ArrayList<String>();
        for(Node node:nodes){
            nodeNames.add(node.toString());
        }
        int n=1;

        while(n!=0){       

            ArrayList<Node> tour = new ArrayList<Node>();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of nodes in the tour or 0 to exit: ");
             n = sc.nextInt();
            if(n==0){
                System.exit(0);
            }
            while(n<1){
                System.out.println("Enter a valid number of nodes in the tour: ");
                n = sc.nextInt();
            }
            for(int i=0;i<n;i++){
                System.out.println("Enter the name of node "+i+": ");
                String name = sc.next();
                while(nodeNames.contains(name) == false){
                    System.out.println("Enter a valid name of the node "+i+": ");
                    name = sc.next();
                }
                Node node = new Node(name);
                tour.add(node);
            }
            System.out.println("The tour is: "+tour+" is it prime= "+isPrime(tour,PPs));
            sc.close();
        }
        
    }

    private static boolean isPrime(ArrayList<Node> tour,List<List<Node>> PPs){
        for(int i=0;i<PPs.size();i++){
            System.out.println(PPs.get(i)+" "+tour);
            if(PPs.get(i).toString().equals(tour.toString())){
                return true;
            }
        }

        return false;
    }

    private static List<List<Node>> getSPs(SimplePath sp){
        List<List<Node>> paths = sp.findallSimplePaths();
        return paths;
    }

    private static List<List<Node>> getPPs(List<List<Node>> sp){
        List<List<Node>> Ppaths =PrimePath.findPrimePath(sp);
        return Ppaths;
    }

    private static void printPaths(List<List<Node>> sp,String type){
        System.out.println("The number of "+type+" paths is : "+sp.size());
        for (List<Node> path : sp) {
            System.out.println(type+" " + path);
        }
    }

}
