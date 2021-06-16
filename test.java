public class test {
    public test()
    {
        DatabaseConnector dbc = new DatabaseConnector("10.120.33.187",3306,"SoftwareProjektDB","nepo2","nepo");
        //dbc.executeStatement("SELECT kuerzel, id, antwort FROM beantwortetLehrer WHERE schueler = 'jan.bessler@neponet.de' AND id = 0");
        //dbc.executeStatement("INSERT INTO beantwortetLehrer (id, kuerzel, antwort, schueler) VALUES (5, 'wie', 'Ist n spast', 'jan.bessler@neponet.de')");
        //dbc.executeStatement("SELECT * FROM beantwortetLehrer");
        dbc.executeStatement("SELECT antwort FROM lehrerFragen JOIN beantwortetLehrer ON lehrerFragen.id = beantwortetLehrer.id WHERE beantwortetLehrer.schueler = 'jan.bessler@neponet.de' AND beantwortetLehrer.kuerzel = 'auf'");

        System.out.println(dbc.getErrorMessage());

        QueryResult qr = dbc.getCurrentQueryResult();
        for(int i = 0; i < qr.getRowCount(); i++){
            for(int j = 0; j < qr.getColumnCount(); j++){
                System.out.println(qr.getData()[i][j]);
            }
            System.out.println(" ");
        }
    }
    
    public static void main(String args[]){new test();}
}