import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class App extends Application {

    Scene scene ;
    public static void main(String[] args) throws IOException {
    launch();
        
    
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Excel excel=new Excel("src/Projects.xls","src/Stages.xls","src/Stages_Detailed.xls");

    // Getting the data from the excel files and storing it in an array list.
    CreateProjectObj projectsCreator = new CreateProjectObj(excel);
    
    ArrayList<Project> projectsArrayFromCreator = projectsCreator.getAllProjects(excel);


    ArrayList StagesArray = excel.getStagesMerged();

         primaryStage.setTitle("XYZsoft");
         
         TableView projectsTable = new TableView<Project>();

         TableColumn projectIdColumn = new TableColumn<Project,String>("costumerProjectID");
         TableColumn projectStageColumn = new TableColumn<Project,String>("currentStage");

         projectIdColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("costumerProjectID"));
         projectStageColumn.setCellValueFactory(new PropertyValueFactory<Project, String>("currentStage"));

         projectsTable.getColumns().add(projectIdColumn);
         projectsTable.getColumns().add(projectStageColumn);

         projectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);         

         for(int i=0; i<projectsArrayFromCreator.size(); i++){
            projectsTable.getItems().add(projectsArrayFromCreator.get(i));
         }

         TableViewSelectionModel selectionModel = projectsTable.getSelectionModel();
         ObservableList<Integer> selectedIndecies = selectionModel.getSelectedIndices();
         int selectedIndex = selectedIndecies.isEmpty() ? 0 : selectedIndecies.get(0);
         Pane borderPane = new Draw(projectsArrayFromCreator.get(0)).getTemplet();
         HBox root = new HBox();
        scene = new Scene(root);

         VBox vbox = new VBox();
         vbox.setAlignment(Pos.BASELINE_CENTER);

         Button folderbtn = new Button("Go to Folder Traverse");       
         GridPane rWUI= ReWorkUI.getReWorkUI(projectsArrayFromCreator.get(0).getBARework(), projectsArrayFromCreator.get(0).getBARework());

         vbox.getChildren().addAll( rWUI,borderPane,folderbtn);
         vbox.setMargin(rWUI, new Insets(10));
         vbox.setMargin(borderPane, new Insets(10));
         vbox.setMargin(folderbtn, new Insets(50));





         ArrayList borderPanesList = new ArrayList<>();
         borderPanesList.add(borderPane);

         selectedIndecies.addListener(
            new ListChangeListener<Integer>() {
                @Override
                public void onChanged(
                    Change<? extends Integer> change) {
                    int selectedIndex = selectedIndecies.get(0);
                    borderPanesList.set(0, new Draw(projectsArrayFromCreator.get(selectedIndex)).getTemplet());



                    //rerender
                    HBox root = new HBox();
                    Scene scene = new Scene(root);
                    root.setSpacing(50);
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll( ReWorkUI.getReWorkUI(projectsArrayFromCreator.get(selectedIndex).getBARework(), projectsArrayFromCreator.get(selectedIndex).getAARework()),((Pane)borderPanesList.get(0)),folderbtn);
                    root.getChildren().addAll(projectsTable,vbox);
                    primaryStage.setScene(scene);
                    primaryStage.setFullScreen(true);

                }
            });

        folderbtn.setOnAction(e ->{
            try {
                
             Button exit=new Button("Go back to Timeline");

             exit.setOnAction(e2->{
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                primaryStage.show();
             });
    
                primaryStage.setScene(new FolderTraverse().getScene(exit));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            primaryStage.setFullScreen(true);
            primaryStage.show();        
            } );
        
        root.setSpacing(50);
        root.getChildren().addAll(projectsTable,vbox);
        
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

}