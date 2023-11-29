import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreateProjectObj {
    ArrayList<ArrayList> excelPro;
    ArrayList<Project> projects=new  ArrayList<Project>();
    
    CreateProjectObj(Excel excel) throws IOException{
        excelPro=excel.getProjects();


    }


    public ArrayList<Project>   getAllProjects(Excel excel) throws IOException{

        CreateStagesObj stagesObjectsCreator = new CreateStagesObj(excel);


        for(int i=0;i< excelPro.size();i++){
            
            String nodeID = (String)excelPro.get(i).get(0); //NodeID. example: 005056AB1EC01...
            String customerProjectID = (String)excelPro.get(i).get(1);  //Costomer project ID.  example: S-0001
            Integer currentStage = ((Double) (excelPro.get(i).get(2))).intValue();  //Stage.  example: 7
            Date startDate = (Date) excelPro.get(i).get(3);  //Start Date. example: Fri May 28 00:00:00 AST 2021            
            Date endDate = (Date) excelPro.get(i).get(4);  //End Date. example: Thu Jun 02 00:00:00 AST 2022            
            Date createdOn = (Date) excelPro.get(i).get(5); //Created On Date. example: Wed Sep 15 00:00:00 AST 2021
            Date changedOn = (Date) excelPro.get(i).get(6);  //Thu Mar 17 00:00:00 AST 2022

            ArrayList<ProjectStage> AllStages=stagesObjectsCreator.getAllStages(nodeID);

                        
                Project newProject = new Project(
                    nodeID, 
                    customerProjectID,
                    currentStage,
                    startDate,
                    endDate,
                    createdOn,  
                    changedOn,
                    ReWork.getBARework(AllStages),
                    ReWork.getAARework(AllStages),
                    AllStages
            );
            
            projects.add(newProject);
        }

        return projects;
    }

    
}
