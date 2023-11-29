import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String nodeID;
    private String costumerProjectID;
    private int currentStage;
    private Date startDate;
    private Date endDate;
    private Date createdOn;
    private Date changedOn;
    private int bAReWork;
    private int aAReWork;
    private ArrayList<ProjectStage> projectStages;

   

    public ArrayList<ProjectStage> getProjectStages() {
        return this.projectStages;
    }

    public void addToProjectStages(ProjectStage stageObject){
        this.projectStages.add(stageObject);
    }

    public void setProjectStages(ArrayList<ProjectStage> projectStages) {
        this.projectStages = projectStages;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getCostumerProjectID() {
        return costumerProjectID;
    }

    public void setCostumerProjectID(String costumerProjectID) {
        this.costumerProjectID = costumerProjectID;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    public int getAARework(){
        return aAReWork;
    }

    public void setAAReWork(int aAReWork){
        this.aAReWork= aAReWork;
    }

    public int getBARework(){
        return bAReWork;
    }
    public void setBAReWork(int bAReWork){
        this.bAReWork= bAReWork;
    }

    public Project(String nodeID, String costumerProjectID, int currentStage, Date startDate, Date endDate, Date createdOn, Date changedOn,int bAReWork,int aAReWork, ArrayList<ProjectStage> projectStages){
        this.nodeID = nodeID;
        this.costumerProjectID = costumerProjectID;
        this.currentStage = currentStage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdOn = createdOn;
        this.changedOn = changedOn;
        this.bAReWork=bAReWork;
        this.aAReWork=aAReWork;
        this.projectStages = projectStages;
    }

    

    
}
