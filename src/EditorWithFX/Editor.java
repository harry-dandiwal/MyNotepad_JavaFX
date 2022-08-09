
package EditorWithFX;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *Student Name: Harry Dandiwal
 *Student Number: 41040008
 *Course_Section #: 22S_CST8288_022
 *Declaration:
 *This is my own original work and is free from Plagiarism.
 *@author harry
 */
public class Editor extends Application {
    
    private static Stage stg;
    

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        //to load FXML and bridge ui and controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
        loader.setControllerFactory(t -> new EditorController(stage));
        stage.setTitle("Untitled");
        stage.getIcons().add(new Image("C:\\Users\\harry\\OneDrive - Algonquin College\\Desktop\\S3\\OOP - CST8288\\NetBeans Projects\\Notepad\\src\\EditorWithFX\\notepad_icon.png"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("EditorStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        
        
        //////////////////Alternate///////////////////
//        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
//        stage.setTitle("Untitled");
//        stage.setScene(new Scene(root, 800, 500));
//        stage.show();
    }
    
    public void changeScene(String fxml){
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            stg.getScene().setRoot(pane);
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
