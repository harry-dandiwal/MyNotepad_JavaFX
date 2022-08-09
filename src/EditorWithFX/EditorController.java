
package EditorWithFX;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.controlsfx.dialog.FontSelectorDialog;
;

/**
 *Student Name: Harry Dandiwal
 *Student Number: 41040008
 *Course_Section #: 22S_CST8288_022
 *Declaration:
 *This is my own original work and is free from Plagiarism.
 *@author harry
 */

//This controller class is going to take care of the actions performed on click of the buttons
public class EditorController {
    
    private Editor editor;
    @FXML
    private TextArea textArea;
    private Stage stage;
    File file;
    String fileTitle;
    String content;
    public static final String UNTITLED_FILE = "Untitled";
    FileChooser fileChooser;
    @FXML
    BorderPane rootPane;
    @FXML
    private Button replaceBtn;
    @FXML
    private TextField findTF;
    @FXML
    private TextField replaceTF;
    @FXML
    private Pane replacePane;
    @FXML
    private MenuItem fontColor;
    @FXML
    private MenuItem save;
     
    
    EditorController(Stage stage) {
        this.stage=stage;
  
    }

    public EditorController() {
    }

    public void onFile(){
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN));
    }
    
   @FXML
    private void onNew(){
        
        content = textArea.getText();
        System.out.println("new"+content);
        if(!content.equals("") || content!=null){
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            
             Alert alert = new Alert(AlertType.CONFIRMATION,"", yes, no, cancel);
             
             Optional<ButtonType> o=null;
             
             //if the title is "Untitled" then it implies that file was never saved
             if(stage.getTitle()==UNTITLED_FILE || stage.getTitle()==fileTitle)
             {
                 alert.setTitle("File not saved");
                 alert.setHeaderText("Do you want to save changes to "+stage.getTitle()+"?");
                 o = alert.showAndWait();
                 
                 //actions based on user selection of yes or no
                if(o.get() == yes){
                    onSaveAs();  //calls save method
                    stage.setTitle(UNTITLED_FILE);
                    textArea.setText("");
                }else if(o.get() == no){
                    stage.setTitle(UNTITLED_FILE);
                    textArea.setText("");
                }
                else if(o.get() == cancel){
                    //do nothing
                }
             }
             else
             {
                 stage.setTitle(UNTITLED_FILE);
                 textArea.setText("");
             }
              
        }      
    }
    
    @FXML
    private void onSaveAs(){
        
        //this will be called only 
       
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        file = fileChooser.showSaveDialog(stage);
       
        if(file!=null) //is null when user clicks close or cross btn
        {
           String content = textArea.getText(); //to fetch data on textarea
           byte[] b = content.getBytes(); //to convert string to bytes
           
           fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*")); //mention types of files that can be saved
            try {
                FileOutputStream fos = new FileOutputStream(file);
                
                fileTitle = file.getName();
                stage.setTitle(fileTitle); //to set title of the doxument in top title bar of editor
                fos.write(b);
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
            else
            {
//             Alert alert = new Alert(AlertType.WARNING);
//             alert.setTitle("File not saved");
//             alert.setHeaderText("Save");
//             alert.setContentText("If closed, you may loose your data");
//
//             alert.showAndWait();
             }
        
       }
    
     @FXML
    private EventHandler onSave(){
        if(stage.getTitle()==UNTITLED_FILE)
        {
            onSaveAs();
        }
        else
        {
            content = textArea.getText(); //to fetch data on textarea
            System.out.println(" "+content);
            byte[] b = content.getBytes(); //to convert string to bytes
             System.out.println("path: "+file.getName());
             try {
                 FileOutputStream fos = new FileOutputStream(file);
                 System.out.println("path: "+file.getName());
                fos.write(b);
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return null; 
    }
    
    @FXML
    private void onLoad(){
        
    }
    
    @FXML
    private void onOpen(){
         fileChooser = new FileChooser();
         fileChooser.setTitle("Open");
         file = fileChooser.showOpenDialog(stage);
         
         if(file!=null){
             try {
                 FileInputStream fis = new FileInputStream(file);
                 int data = fis.read();
                 
                 content="";
                 while(data != -1){
                     content += (char)data;
                     data = fis.read();
                 }
                 
                 fis.close();
                 textArea.setText(content);
                 fileTitle=file.getName();
                 stage.setTitle(fileTitle);
                 
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }    
    }
    
    @FXML
    private void onClose(){
       System.exit(0);
    }
     
    @FXML
    private void onPageSetup(){
  //TODO
    }
    
   
    @FXML
    private void onPrint(){
    //TODO
    }
    
    
    
  /**Tasks related to Edit menu group**/
     @FXML
     private void onCut(){
     textArea.cut();
    }
      @FXML
      private void onCopy(){
     textArea.copy();
    }
       @FXML
       private void onPaste(){
    textArea.paste();
    }
        @FXML
    private void onReplace(){    
       
        String text = textArea.getText();
        System.out.println(""+text);
        
        try {
           replacePane = FXMLLoader.load(getClass().getResource("practice.fxml"));
           Stage stage = new Stage();
           stage.setTitle("Replace");
           stage.setResizable(false);
           stage.setScene(new Scene(replacePane));
           stage.show();
       
         
        } catch (Exception ex) {
            Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void onReplaceClick()
    {
          
            String text = textArea.getText();
            String replaceWhat = findTF.getText();
            String replaceWith = replaceTF.getText();
            //Working until here
            //TODO: not able to fetch data from root textArea. Throws null pointer exceptio
            //will come back to it later sometime.Try skipping the fxml for replace dialogue and use Java to make it at runtime

//            
//            String newContent = text.replace(replaceWhat, replaceWith);
//            textArea.setText(newContent);
//            replacePane.setVisible(false);
            
    }

    
    @FXML
    private void onDateTime(){
        LocalDateTime ldt = LocalDateTime.now();
        String dateTime = ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        textArea.appendText(dateTime);
    }
    
      @FXML
    private void onFindClick(){
  
    }
    
    /**Related to Format Menu**/
    @FXML
    private void onFont(){
        //with the help of controlsFX .jar
        FontSelectorDialog fsd = new FontSelectorDialog(null);
        fsd.setTitle("Fonts");
        Optional<Font> resp = fsd.showAndWait();
        textArea.setFont(resp.get());
    }

    @FXML
    private void onFontColor(){
        ColorPicker cp = new ColorPicker(Color.ALICEBLUE);
         HBox hbox = new HBox();
         hbox.setSpacing(20);
         hbox.setPadding(new Insets(25, 50, 50, 60));
         hbox.getChildren().addAll(cp);
        //UNFINISHED
    }
    
    @FXML
    private void onEdBackground(){
        
    }
    
   
}
