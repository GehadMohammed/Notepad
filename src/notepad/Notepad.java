


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package notepad;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gehad
 */
public class Notepad extends Application {
    
        String file_menuitem_names[] ={"New","Open","Save","Exit"}; 
        String edit_menuitem_names[] ={ "Undo","Redo", "Cut", "Copy", "Paste" ,"Delete" ,"Select All"}; 
        String help_menuitem_names[] ={"About"}; 
        
        Menu file;
        Menu edit;
        Menu help;
        
        MenuBar bar = new MenuBar();
        TextArea textarea = new TextArea(); 
        FileChooser fil_chooser = new FileChooser();
        Dialog<String>dialog = new Dialog<String>();
   
   
    @Override
    public void init() {
       
        
        file=create_menu(file_menuitem_names);
        file.setText("File");
        
        edit=create_menu(edit_menuitem_names);
        edit.setText("Edit");

        help=create_menu(help_menuitem_names);
        help.setText("Help");
        
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle("About");
        dialog.setContentText("© 2022, Gehad Mohammed. All rights reserved.");
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE));

        
    }
      
    public void start(Stage primaryStage) {


        file.getItems().get(0).setOnAction((ActionEvent event) -> {
           textarea.clear();
        });

        file.getItems().get(1).setOnAction((ActionEvent event) -> {
               File file = fil_chooser.showOpenDialog(primaryStage);

            if (file != null) {
                   System.out.println("file==="+file.getAbsolutePath());                       
                   FileReader open_file = null;
                   textarea.clear();
                   try {

                       open_file = new FileReader(file.getAbsolutePath());
                       char [] file_content= new char [(int)file.length()];                           
                       open_file.read(file_content);
                       open_file.close();
                            System.out.println(file_content);
                       for(char c:file_content){
                           textarea.appendText(Character.toString(c));
                       }
                   }
                   catch (FileNotFoundException ex) {
                       Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                   }





            }
        });
        file.getItems().get(2).setOnAction((ActionEvent event) -> {

             File file = fil_chooser.showSaveDialog(primaryStage);
                         System.out.println(file.getAbsolutePath());
            if (file != null) {
                   System.out.println("file==="+file.getAbsolutePath());                       
                   FileWriter save_file = null;

                     try {

                       save_file = new FileWriter(file.getAbsolutePath());
                       save_file.write(textarea.getText());
                       save_file.close();

                   } catch (IOException ex) {
                       Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                   }

            }
            });
    
        file.getItems().get(3).setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
       
        edit.getItems().get(0).setOnAction((ActionEvent event) -> {
            textarea.undo();
         
        });
        edit.getItems().get(1).setOnAction((ActionEvent event) -> {
            textarea.redo();
        });
        edit.getItems().get(2).setOnAction((ActionEvent event) -> {
            textarea.cut();      
        });
        
        edit.getItems().get(3).setOnAction((ActionEvent event) -> {
            textarea.copy();  
        });
        
        edit.getItems().get(4).setOnAction((ActionEvent event) -> {
            textarea.paste();
        });
        edit.getItems().get(5).setOnAction((ActionEvent event) -> {
            textarea.deleteText(textarea.getSelection());
            
        });
        
        edit.getItems().get(6).setOnAction((ActionEvent event) -> {
            textarea.selectAll();
        });
        
        help.getItems().get(0).setOnAction((ActionEvent event) -> {
            dialog.showAndWait();
        });
           
        
           
        bar.getMenus().addAll(file,edit,help);
        
        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(textarea);
        
        
        Scene scene = new Scene(pane,700,500);
        primaryStage.setScene(scene); 
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
        
        
        
    }
    
    /**
     *
     * @param m
     * @param EventHandler
     */
    
    public Menu create_menu (String [] names)
    {
        Menu menu=new Menu() ;
        int size = names.length;
        
        for(int i =0 ; i<size ; i++)
        {
            menu.getItems().add(new MenuItem(names[i]) );  
        }
    
        return menu;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
