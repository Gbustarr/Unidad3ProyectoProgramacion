/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guillermo
 */
public class Interfaz_panelController implements Initializable {
    
    @FXML
    TextArea textArea;
    
    @FXML
    Button Btn_Close;
    
    InterfazController context;
    
    
    protected void setController(InterfazController ic) {
        this.context = ic;
        
    }

    protected void setTextArea(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<context.lista_simbolos.size();i++){
            if(context.lista_simbolos.get(i).valor == 13){
            s.append(context.lista_simbolos.get(i));
            s.append("\n");
            }
        }
        
        textArea.setText(s.toString());
    }
    @FXML
    protected void Cerrar() {
        Stage stage = (Stage) Btn_Close.getScene().getWindow();
        // do what you have to do
        stage.close();
        context.l.panelAgregado = 0;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    
    
}
