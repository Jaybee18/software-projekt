import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.JPanel;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 08.06.2021
 * @author 
 */

public class loader extends JFrame {
  // Anfang Attribute
  private JPanel JPanel1;
  // Ende Attribute
  
  public loader() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1000; 
    int frameHeight = 600;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Schülersprechtag");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Kompui
    JPanel1 = new ui();
    JPanel1.setBounds(0, 0, 1000, 600);
    JPanel1.setLayout(null);
    cp.add(JPanel1);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public loader
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new loader();
  } // end of main
  
  // Ende Methoden
} // end of class loader
