
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class ReWorkUI {
    
    public static GridPane getReWorkUI(int BA, int AA){
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label reWork =new Label("ReWork");
        Label BAReWork=new Label(String.valueOf(BA));
        Label AAReWork=new Label(String.valueOf(AA));
        Label Bef=new Label("Before Award");
        Label afr=new Label("After Award");

        GridPane.setConstraints(reWork, 0, 0,2,1);
        GridPane.setHalignment(reWork, HPos.CENTER);
        GridPane.setConstraints(BAReWork,0,1);
        GridPane.setHalignment(BAReWork, HPos.CENTER);
        GridPane.setConstraints(AAReWork,1,1);
        GridPane.setHalignment(AAReWork, HPos.CENTER);
        GridPane.setConstraints(Bef,0,2);
        GridPane.setConstraints(afr,1,2);



        grid.getChildren().addAll(reWork,BAReWork,AAReWork,Bef,afr);
        grid. setAlignment(Pos.BASELINE_CENTER);
        
        return grid;
       
    }
}
