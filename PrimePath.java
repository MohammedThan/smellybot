import java.util.*;

public class PrimePath {





    public static List<List<Node>> findPrimePath(List<List<Node>> simplePathes){
        List<List<Node>> PrimePaths = new ArrayList<>();
        List<List<Node>> notPrimePaths = new ArrayList<>();

        for (List<Node> i : simplePathes) {
            for (List<Node> j : simplePathes) {
                if(!i.equals(j)){
                    if(containsInRow2(j,i) ){
                       notPrimePaths.add(i);
                    }
                }

            }
            
        }

        for (List<Node> i : simplePathes) {
            if(!notPrimePaths.contains(i)){
                PrimePaths.add(i);
            }
        }
        return PrimePaths;
    }


    public static boolean containsInRow(List<Node> list1, List<Node> list2){
        int streek=0;
        boolean foundOne = false;
        for (int i = 0; i < list2.size(); i++) {
            for(int j = 0; j < list1.size(); j++){
                if(i+j<list2.size()&& list2.get(i+j).equals(list1.get(j))){
                    streek++;
                    foundOne=true;
                    if(streek==list1.size())
                        return true;
                    break;

                }else{
                    foundOne=false;
                }
                    
            }
            if(!foundOne){
                streek=0;}       

        }
        
        return false;
    }

    public static boolean containsInRow2(List<Node> list1, List<Node> list2){

        List<Node> big=list1;
        List<Node> sm=list2;

        if(list1.size()>list2.size()){
            big=list1;
            sm=list2;
        } else if(list1.size()==list2.size()){
            int count=0;
            for(int i=0;i<list1.size();i++){
                if(list1.get(i).equals(list2.get(i))){
                    count++;                
                }
            }
            if(count==list1.size())
                return true;
            else
                return false;

        }else{
            return false;
        }

        int streek=0;
        for(int i=0;i<big.size()-sm.size()+1;i++){
            streek=0;
            if( big.subList(i, i+sm.size()).containsAll(sm)){
                for(int j=0;j<sm.size();j++){
                    if(big.subList(i, i+sm.size()).get(j).equals(sm.get(j)))
                        streek++;
            }
            if(streek==sm.size())
                return true;
        
            }
        
        }
        return false;
  
}
}
