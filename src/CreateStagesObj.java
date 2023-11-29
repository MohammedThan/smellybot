import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreateStagesObj {

    ArrayList<ArrayList> excelStg;

    public CreateStagesObj( Excel excel) throws IOException{
        excelStg=excel.getStagesMerged();
    }

    public ArrayList<ProjectStage> getAllStages(String objValue){
        ArrayList<ProjectStage> stages=new  ArrayList<ProjectStage>();
        for(int i=0; i<excelStg.size(); i++){
            String currentStageObjectValue = (String)excelStg.get(i).get(0);
            if( currentStageObjectValue.equals(objValue)){
                stages.add(
                    new ProjectStage(
                        (String)excelStg.get(i).get(0),
                        (double)excelStg.get(i).get(1),
                        (Date) excelStg.get(i).get(2),
                        (double) excelStg.get(i).get(3),
                        (boolean) excelStg.get(i).get(4))
                    );
            }
    }
    return stages;
}

}