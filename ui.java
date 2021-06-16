import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JList;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.06.2021
 * @author 
 */

public class ui extends JPanel {
  // Anfang Attribute
  /* general frame */
  private Main main;
  private KonstanteWerte kw;
  private int currentAction;
  private String[] actions = {"<html>Durchschn. Bewertung <br>eines Schuelers DEL</html>", 
                              "<html>Selbsteinsch. eines <br>Schuelers</html>", 
                              "<html>Durchschn. Selbsteinsch. <br>der Schueler</html>", 
                              "<html>Bewertung eines <br>Schuelers</html>", 
                              "Meine Bewertung DEL", 
                              "<html>Schueler sortieren <br>nach Selbsteinschaetzung</html>", 
                              "<html>Schueler sortieren <br>nach durchschn. Bewertung</html>"};
  private String[][] schueler;
  private JList optionenwahl = new JList();
    private DefaultListModel jList1Model = new DefaultListModel();
    private JScrollPane jList1ScrollPane = new JScrollPane(optionenwahl);

  /* schuelerauswahl */
  private JTextField jTextField1 = new JTextField();
  private JLabel lSuchen = new JLabel();
  private JList schuelerwahl = new JList();
    private DefaultListModel jList2Model = new DefaultListModel();
    private JScrollPane jList2ScrollPane = new JScrollPane(schuelerwahl);

  /* schuelerliste für sortieren */
  private JList schuelerAnzeige = new JList();
    private DefaultListModel schuelerAnzeigeModel = new DefaultListModel();
    private JScrollPane schuelerAnzeigePane = new JScrollPane(schuelerAnzeige);
  private JLabel schuelerAnzeigeName = new JLabel();

  /* actions 1, 2, 3 and 4 come from different group */

  private JLabel tempLabelDelete = new JLabel();
  
  // Ende Attribute
  
  public ui() { 
    // Frame-Initialisierung
    super();
    int frameWidth = 1000; 
    int frameHeight = 600;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setBackground(kw.BASIS_FARBEN[3]);
    main = new Main();
    schueler = main.getAlleSchueler();


    
    // optionen
    optionenwahl.setModel(jList1Model);
    optionenwahl.setBackground(kw.BASIS_FARBEN[1]);
    optionenwahl.setForeground(new Color(255, 255, 255));
    jList1ScrollPane.setBounds(0, 0, 200, 600);
    jList1ScrollPane.setBorder(BorderFactory.createEmptyBorder());
    optionenwahl.setListData(actions);
    optionenwahl.setSelectionBackground(kw.BASIS_FARBEN[1]);
    optionenwahl.setSelectionForeground(kw.FOKUS_FARBE);
    optionenwahl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                   currentAction = optionenwahl.getSelectedIndex();
                   switch (optionenwahl.getSelectedIndex()) {
                     case 0: 
                       showSchuelerAnzeige(false);
                       showSchuelerwahl(true);
                       break;
                     case 1: 
                       showSchuelerAnzeige(false);
                       showSchuelerwahl(true);
                       break;
                     case 2: 
                       showSchuelerAnzeige(false);
                       showSchuelerwahl(false);
                       tempLabelDelete.setText("Avr. Selbsteinsch. der Schueler");
                       break;
                     case 3: 
                       showSchuelerAnzeige(false);
                       showSchuelerwahl(true);
                       break;
                     case 4: 
                       showSchuelerAnzeige(false);
                       showSchuelerwahl(true);
                       break;
                     case 5: 
                       showSchuelerwahl(false);
                       showSchuelerAnzeige(true);
                       break;
                     case 6: 
                       showSchuelerwahl(false);
                       showSchuelerAnzeige(true);
                       break;
                     default: 
                       break;
                   } // end of switch 
                }
            }
        });
    this.add(jList1ScrollPane);
    
    
    
    // schuelerauswahl
    ListSelectionListener schuelerwahllsl = new ListSelectionListener(){
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          switch (currentAction) {
            case 0: 
            // show durchschn. bewertung eines schuelers
            tempLabelDelete.setText("avr. bewertung von " + String.valueOf(schuelerwahl.getSelectedValue()));
            break;
            case 1: 
            // show selbsteinsch. eines schuelers
            tempLabelDelete.setText("selbsteinschaetzung von " + String.valueOf(schuelerwahl.getSelectedValue()));
            break;
            case 2: 
            // durchschn. selbsteinsch. eines schuelers
            tempLabelDelete.setText("avr. selbsteinschaetzung von " + String.valueOf(schuelerwahl.getSelectedValue()));
            break;
            case 3: 
            // bewertung eines schuelers
            // einen Fragebogen mit antworten ALLER Fachlehrer gleichzeitig
            // - farblich markiert oder so
            // durchschnittliche bewertung dieses schuelers in der jeweiligen frage anzeigen
            // keine lehrer-auswahl mehr nötig
            // maybe so n kleines feld mit insgesamter avr. selbst und insgesamter avr. lehrer bewertung unten dran
            String[] data = main.getAvrLehrerBewertungVonSchueler(schueler[1][schuelerwahl.getSelectedIndex()]);
            for(String d : data)
              System.out.println(d);
            tempLabelDelete.setText("<html>bewertung von " + String.valueOf(schuelerwahl.getSelectedValue()) + "<br>" + data[0] + "</html>");
            break;
            case 4: 
            // meine bewertung
            tempLabelDelete.setText("meine bewertung von " + String.valueOf(schuelerwahl.getSelectedValue()));
            break;
            case 5: 
            // show schueler sort. nach selbsteinsch. -> liste mit namen und durchschn. selbsteinsch. [click]->einsch�tzung sehen
            tempLabelDelete.setText("schueler sort nach selbsteinschaetzung");
            break;
            case 6: 
            // show schueler sort. nach durchschn. bewertung -> liste mit namen und durchschn. bewertung [click]->nichts
            tempLabelDelete.setText("schueler sort nach avr. bewertun");
            break;
            default: 
            break;
          } // end of switch
        }
      }
    };

    schuelerwahl.setModel(jList2Model);
    schuelerwahl.setBackground(kw.BASIS_FARBEN[1]);
    schuelerwahl.setSelectionBackground(kw.BASIS_FARBEN[1]);
    schuelerwahl.setSelectionForeground(kw.FOKUS_FARBE);
    schuelerwahl.setForeground(new Color(255, 255, 255));
    jList2ScrollPane.setBounds(210, 40, 200, 515);
    jList2ScrollPane.setBorder(BorderFactory.createEmptyBorder());
    schuelerwahl.addListSelectionListener(schuelerwahllsl);
    this.add(jList2ScrollPane);
    
    jTextField1.setBounds(270, 10, 140, 20);
    jTextField1.setBackground(kw.BASIS_FARBEN[2]);
    jTextField1.setForeground(new Color(255, 255, 255));
    jTextField1.setBorder(BorderFactory.createEmptyBorder());
    jTextField1.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateSchuelerListe();
        }
    
        @Override
        public void removeUpdate(DocumentEvent e) {
            updateSchuelerListe();
        }
    
        @Override
        public void changedUpdate(DocumentEvent e) {
            return;
        }
      });
    this.add(jTextField1);

    lSuchen.setBounds(210, 10, 60, 20);
    lSuchen.setForeground(new Color(255, 255, 255));
    lSuchen.setText("Suchen:");
    this.add(lSuchen);



    // bewertung
    // todo : holen von gruppe 2



    // sortierte schuelerliste
    schuelerAnzeige.setModel(schuelerAnzeigeModel);
    schuelerAnzeigePane.setBounds(210, 30, 780, 525);
    schuelerAnzeige.setListData(schueler[0]);
    schuelerAnzeige.setSelectedIndex(1);
    this.add(schuelerAnzeigePane);

    schuelerAnzeigeName.setBounds(210, 10, 50, 10);
    schuelerAnzeigeName.setText("Name");
    this.add(schuelerAnzeigeName);



    // content placeholder
    tempLabelDelete.setBounds(650, 200, 350, 40);
    tempLabelDelete.setText("temp");
    this.add(tempLabelDelete);

    

    setVisible(true);
    showSchuelerwahl(false);
    showSchuelerAnzeige(false);
  } // end of public ui
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new ui();
  } // end of main
  
  public void showSchuelerwahl(boolean visible){
    lSuchen.setVisible(visible);
    schuelerwahl.setVisible(visible);
    jTextField1.setVisible(visible);
    jList2ScrollPane.setVisible(visible);
    schuelerwahl.setSelectedIndex(1);
    if(!visible)
       return;
    
    schuelerwahl.setListData(schueler[0]);
    schuelerwahl.setSelectedIndex(1);
  }

  public void showSchuelerAnzeige(boolean visible){
    schuelerAnzeige.setVisible(visible);
    schuelerAnzeigePane.setVisible(visible);
    schuelerAnzeigeName.setVisible(visible);
  }
  
  private String[] getElementsFromList(JList list){
    int size = list.getModel().getSize();
    String[] res = new String[size];
    for(int i = 0; i < size; i++){
      res[i] = String.valueOf(list.getModel().getElementAt(i));
    }
    return res;
  }
  
  private void updateSchuelerListe(){
    // Nur die Schuelernamen anzeigen, die mit dem
    // Suchbegriff in 'search' anfangen
    List<String> newElements = new List<String>();
    String search = jTextField1.getText().toLowerCase();
    int elementCount = 0;
    for(String a : schueler[0]){
      if(a.toLowerCase().contains(search)){
        newElements.append(a);
        elementCount++;
      }
    }
    String[] res = new String[elementCount];
    newElements.toFirst();
    for(int i = 0; i < elementCount; i++){
      res[i] = newElements.getContent() + "";
      newElements.next();
    }
    schuelerwahl.setListData(res);
    schuelerwahl.setSelectedIndex(1);
  }
  // Ende Methoden
} // end of class ui

