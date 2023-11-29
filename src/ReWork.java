import java.util.ArrayList;


public class ReWork {


    
    public static int getBARework(ArrayList<ProjectStage> allStages){
        int bAReWork = 0;
        for (int i=0;i<allStages.size();i++){
            if(!allStages.get(i).getIsNormal() && ((((int)allStages.get(i).GetNewValue())<4))){

                bAReWork+=1;
            }

        }
        return bAReWork;
    }
    public static int  getAARework(ArrayList<ProjectStage> allStages){
        int aAReWork = 0;
        
        for (int i=0;i<allStages.size();i++){
            if(!allStages.get(i).getIsNormal() && (((int)allStages.get(i).GetNewValue())>=4)){
                aAReWork+=1;

            }
        }
        return aAReWork;
    }

    
}
