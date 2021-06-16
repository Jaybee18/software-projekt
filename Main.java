//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 08.06.2021
 * @author 
 */

public class Main {

  private DatabaseConnector dbc;

  public Main() {
    dbc = new DatabaseConnector("10.120.33.187",3306,"SoftwareProjektDB","nepo2","nepo");
    getAlleSchueler();
  }
  // Anfang Methoden
  public String[][] getAlleSchueler(){
    dbc.executeStatement("SELECT name, vorname, id FROM schueler");
    QueryResult qr = dbc.getCurrentQueryResult();
    String[][] data = qr.getData();
    String[] res = new String[qr.getRowCount()];
    String[] ids = new String[qr.getRowCount()];
    for(int i = 0; i < res.length; i++)
    {
      res[i] = data[i][0] + " " + data[i][1];
      ids[i] = data[i][2];
    }
    return new String[][]{res, ids};
  }
  public String[] getSchuelerSortNachSelbsteinschaetzung(boolean asc){
    return getAlleSchueler()[0];
  }

  public String[] getSchuelerSortNachAvrLehrerBewertung(boolean asc){
    return getAlleSchueler()[0];
  }

  public String[] getAvrLehrerBewertungVonSchueler(String schueler){
    /* SQL:
     * - gegeben ist die schuelerID des betroffenen Schuelers
     * 
     * Ich will alle Antworten auf Alle Fragen, Aller Lehrer
     * zu einem Schueler
     * 
     * SELECT kuerzel, id, antwort
     * FROM beantwortetLehrer 
     * WHERE schueler = schueler
     * 
     * SELECT antwort
     * FROM lehrerFragen LEFT JOIN beantwortetLehrer
     * ON lehrerFragen.id = beantwortetLehrer.id
     * WHERE beantwortetLehrer.schueler = 'jan.bessler@neponet.de'
     * AND kuerzel = 'auf'
     * 
     */
    int anzahlLehrerfragen = getAnzahlLehrerfragen();
    String[] res = new String[anzahlLehrerfragen];

    for(int i = 0; i < anzahlLehrerfragen; i++)
    {
      dbc.executeStatement("SELECT kuerzel, id, antwort FROM beantwortetLehrer WHERE schueler = '" + schueler + "' AND id = " + i + " AND id IN (SELECT id FROM lehrerFragen WHERE kategorie = 'mc')");
      QueryResult qr = dbc.getCurrentQueryResult();
      String[][] data = qr.getData();
      String[] answers = new String[qr.getRowCount()];

      for(int j = 0; j < qr.getRowCount(); j++)
      {
        answers[j] = data[j][2];
      }
      int[] answerCounter = new int[]{0, 0, 0, 0, 0};
      for(String answer : answers){
        answerCounter[Integer.parseInt(answer)-1]++;
      }

      res[i] = answerCounter[0] + ";" + answerCounter[1] + ";" + answerCounter[2] + ";" + answerCounter[3] + ";" + answerCounter[4];
    }
    
    return res;
  }

  public String[] getSelbsteinschaetzung(String schueler){
    String[] res = new String[getAnzahlSchuelerfragen()];
    for(int i = 0; i < res.length; i++)
      res[i] = i + "";
    return res;
  }

  public String[] getAvrSelbsteinschaetzungVonKlasse(String klasse){
    String[] res = new String[getAnzahlSchuelerfragen()];
    for(int i = 0; i < res.length; i++)
      res[i] = i + "";
    return res;
  }

  public String[] getBewertung(String lehrer, String schueler){
    String[] res = new String[getAnzahlLehrerfragen()];
    for(int i = 0; i < res.length; i++)
      res[i] = i + "";
    return res;
  }

  public int getAnzahlLehrerfragen(){
    dbc.executeStatement("SELECT count(*) FROM lehrerFragen");
    return Integer.parseInt(dbc.getCurrentQueryResult().getData()[0][0]);
  }

  public int getAnzahlSchuelerfragen(){
    dbc.executeStatement("SELECT count(*) FROM schuelerFragen");
    return Integer.parseInt(dbc.getCurrentQueryResult().getData()[0][0]);
  }
  
  public static void main(String[] args) {
    new Main();
  } // end of main
  
  // Ende Methoden
}

