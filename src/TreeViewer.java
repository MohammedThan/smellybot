import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeView;



public class TreeViewer {

    String path;
    String fileName;


    TreeViewer(String path){
        this.path=path;
        fileName=path;
    }

    public String getLisPath(){
        return path;
    }

    public String getName(){
        return fileName;
    }

    public TreeView getTreeView() throws IOException{
        Label rootLabel=new Label("All Folders");
        rootLabel.setGraphic(getFolderIcome());
        TreeItem<Label> root=new TreeItem<>(rootLabel);
        root.setExpanded(true);

        
        TreeView tree=new TreeView(makeTreeItem( root, new File(path)));

        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
            fileName= tree.getSelectionModel().getSelectedItem().toString().substring(tree.getSelectionModel().getSelectedItem().toString().indexOf("'")+1,tree.getSelectionModel().getSelectedItem().toString().length()-3);
            System.out.println(fileName);
            });

        return tree;
    }

    private TreeItem<Label> makeTreeItem(TreeItem<Label> root,File file) throws IOException{

        
        File[] folders=file.listFiles(File::isDirectory);

        for(int i=0;i<folders.length;i++){
            Label label=new Label(folders[i].getName());
            label.setGraphic(getFolderIcome());
            TreeItem<Label> tI=new TreeItem<>(label);
            root.getChildren().add(tI);
            makeTreeItem( tI, folders[i]);
        }
        return root;

        }

    

    public static ImageView getFolderIcome(){
        Image image = new Image(("folder.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        return imageView;
    }

}
