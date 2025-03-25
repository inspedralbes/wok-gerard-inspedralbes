package src.BBDDConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioBD {

    private Connection connection;
    private static ConexioBD instance;

    private ConexioBD(){
        this.connection = this.openConnection();
    }

    private Connection openConnection() {
        Connection con = null;
        try{
            con = DriverManager.getConnection("TODO: connectionString");
        }catch (SQLException e) {
            System.out.println("Problema al establir la connexió: " + e.getMessage() );
        }
        return con;
    }

    public static ConexioBD getInstance(){
        if(ConexioBD.instance == null){
            ConexioBD.instance = new ConexioBD();
        }
        return ConexioBD.instance;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("No s'ha pogut tantcar la connexió: "+ e.getMessage());
        }
    }
}
