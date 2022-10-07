package SisII.Tarea_sis2.Recetas;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecetaGUI extends JFrame{
        
    //Labels
    private JLabel recetaLabel;
    private JLabel ingredientesLabel;
    private JLabel difLabel;
    //Textfields
    public JTextField recetaField;
    public JTextField difField;
    //TextArea
    public JTextArea ingredientesArea;
    //JButtons
    public JButton guardarButton;
    
    
    public RecetaGUI(){
        FlatLightLaf.setup();
        iniComponentes();
    }

    private void iniComponentes(){
        //Frame
        setTitle("RECETAS");
        setSize(new Dimension(495, 330));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);       
        setResizable(false);
        
        //Labels
        recetaLabel = new JLabel();
        recetaLabel.setBounds(25, 25, 100, 25);
        recetaLabel.setText("Receta");
        add(recetaLabel);
        
        ingredientesLabel = new JLabel();
        ingredientesLabel.setBounds(25, 85, 100, 25);
        ingredientesLabel.setText("Ingredientes");
        add(ingredientesLabel);
        
        difLabel = new JLabel();
        difLabel.setBounds(25, 220, 100, 25);
        difLabel.setText("Dificultad");
        add(difLabel);
        
        //TextFields
        recetaField = new JTextField();
        recetaField.setBounds(25, 50, 425, 25);
        add(recetaField);
        
        difField = new JTextField();
        difField.setBounds(25, 245, 50, 25);
        add(difField);
        
        //TextArea
        ingredientesArea = new JTextArea();
        ingredientesArea.setBounds(25, 115, 425, 100);
        add(ingredientesArea);
        
        //JButton
        guardarButton = new JButton();
        guardarButton.setText("Guardar Receta");
        guardarButton.setFont(new Font("sansserif", Font.PLAIN, 15));
        guardarButton.setBounds(300, 232, 150, 40);
        add(guardarButton);
        
        setVisible(true);
    }
}