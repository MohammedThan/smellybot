import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Draw {
    Pane borderPane=new Pane();
    int  montheNum;
    Project project;
    MyDate mydate;

    public Draw(Project project){
        this.project=project;
        mydate=new MyDate(project);
        montheNum=(new MyDate(project).findMonthsDifference());
    }

    private static Line hLine(){    
        Line  line = new Line(0,400,1200,400);
        return line;
    }

    private static  Line vLine(double xStart , double yStart , double xEnd , double yEnd ){    
        Line  line = new Line(xStart,yStart,xEnd,yEnd);
        return line;  
    }

    public Pane getTemplet(){
        addAllLines();
        addLable();
        addUpperLine();
        addStages();
        return borderPane;
    }

    private void addAllLines(){
        borderPane.getChildren().add(hLine());

        for(double i=0;i<=1200;i=i+1200/montheNum/30){
                borderPane.getChildren().add(vLine(i,395,i,405)); // draw day lines 
        }
        for(int i=0;i<=montheNum;i++){
            borderPane.getChildren().add(vLine(i*1200/montheNum,390,i*1200/montheNum,410));

    }

}

 private void addLable(){
    Label label;
    Label duration=new Label("Duration: "+mydate.findStageDaysDifference()+" days");
    duration.relocate((mydate.durationToMinDate()+mydate.durationToMaxDate())/2*1200/mydate.findAllDaysDruration()-35, 180);
    borderPane.getChildren().add(duration);
    for(int i=0;i<=montheNum;i++){
        label=new Label();
        label.setText(mydate.addMonthLabel(i));
        label.relocate(i*1125/montheNum,420);

        borderPane.getChildren().add(label);       
}


 }

 private void addUpperLine(){
    int cor=200;
    double startAt=(1200*mydate.durationToMinDate())/mydate.findAllDaysDruration();
    double endAt=1200*mydate.durationToMaxDate()/mydate.findAllDaysDruration();
    Line line=new Line(startAt,cor,endAt,cor);
    Line rline=new Line(startAt,cor-10,startAt,cor+10);
    Line lline=new Line(endAt,cor-10,endAt,cor+10);

    borderPane.getChildren().addAll(line,rline,lline);
 }

 private void addStages(){

    for(int i=0;i<mydate.allStagePoint().size();i++){
        if(isDuplicateed(i)){
            Line line=new Line(mydate.allStagePoint().get(i),400,mydate.allStagePoint().get(i),updatePoints().get(i) );
            Rectangle rectangle = new Rectangle(mydate.allStagePoint().get(i), updatePoints().get(i) , 10,10);
            rectangle.setStroke(color(i)); 
            rectangle.setFill(color(i));
            borderPane.getChildren().addAll(line,rectangle);
        } else{
            Line line=new Line(mydate.allStagePoint().get(i),400,mydate.allStagePoint().get(i),updatePoints().get(i)  );
            Rectangle rectangle = new Rectangle(mydate.allStagePoint().get(i), updatePoints().get(i) , 10,10);
            rectangle.setStroke(color(i)); 
            rectangle.setFill(color(i));
            borderPane.getChildren().addAll(line,rectangle);
        }
        

    }

  
 }

 private boolean isDuplicateed(int i){

           
    if(mydate.allStagePoint().subList(0, i).contains(mydate.allStagePoint().get(i)))
        return true;
    return false;
 }


 private ArrayList<Double> updatePoints(){
    ArrayList<Double> allStagePoint=(ArrayList<Double>)mydate.allStagePoint().clone();
    ArrayList<Double> hight=new ArrayList<Double>();
    for(int i=0;i<allStagePoint.size();i++){
        hight.add(360.0);
    }

    for(int i=0;i<hight.size()-1;i++){
            if( allStagePoint.get(i).equals(allStagePoint.get(i+1)) ){
                hight.set(i+1,hight.get(i)-20);
            }
            
        
    }

    return hight;
 }


 private Color color(int i){
    if(project.getProjectStages().get(i).getIsNormal()){
        return Color.GREEN;
    }
    else{
        return Color.RED;
    } 
        
 }


  
}
