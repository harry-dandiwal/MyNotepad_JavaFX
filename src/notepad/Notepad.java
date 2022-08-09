
package notepad;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *Student Name: Harry Dandiwal
 *Student Number: 41040008
 *Course_Section #: 22S_CST8288_022
 *Declaration:
 *This is my own original work and is free from Plagiarism.
 *@author harry
 */
public class Notepad 
{
    JFrame jframe;
    JMenuBar menubar;
    //#1 menu
    JMenu file;
    //items for file menu
    JMenuItem newFile, open, save;
    
    //textarea
    JTextArea textArea;
    JScrollPane scrollpane;

    //constructor
    public Notepad()
    {
        //adding look to the GUI as per windows theme
     
        jframe = new JFrame();
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //menu bar
        menubar= new JMenuBar();
        
        //adding item to bar
        file = new JMenu("File");
        menubar.add(file);
        
        newFile = new JMenuItem("New File");
        file.add(newFile);
        open = new JMenuItem("Open");
         file.add(open);
         save = new JMenuItem("Save");
         file.add(save);
        
        //setting menubar for frame
        jframe.setJMenuBar(menubar);
        
        //text area related
        textArea = new JTextArea();
         scrollpane = new JScrollPane(textArea);
        
        jframe.add(scrollpane);
       
        jframe.setVisible(true);
    }
    
    
//    
//      public static void main(String[] args) 
//    {
//       new Notepad();
//    }
}
