
package EditorWithFX;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *Student Name: Harry Dandiwal
 *Student Number: 41040008
 *Course_Section #: 22S_CST8288_022
 *Declaration:
 *This is my own original work and is free from Plagiarism.
 *@author harry
 */
public class ReplaceBoxController {
    
    @FXML
    private Pane replacePane;
    @FXML
    private Button replaceBtn;
    @FXML
    private TextField findTF;
    @FXML
    private TextField replaceTF;
    private TextArea textArea;
    
    @FXML
    private void onReplaceClick()
    {
            String replaceWhat = findTF.getText();
            String replaceWith = replaceTF.getText();

            System.out.println(replaceWhat+" --- "+replaceWith);
            
            String text = textArea.getText();
            String newContent = text.replace(replaceWhat, replaceWith);
            textArea.setText(newContent);
            replacePane.setVisible(false);
    }

}
