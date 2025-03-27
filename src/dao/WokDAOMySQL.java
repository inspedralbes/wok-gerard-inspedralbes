package src.dao;

import src.BBDDConnection.ConexioBD;
import src.model.Ingredient;
import src.model.Wok;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WokDAOMySQL implements WokDAO{
    @Override
    public void guardarWok(Wok wok) {
        try {
            Connection con = ConexioBD.getInstance();
            String query = "INSERT INTO Wok (basedesc, preubase, midabase, " +
                    "ingredients, salsadesc, preusalsa) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,wok.getBase().getDescripcio());
            stmt.setDouble(2,wok.getBase().getPreu());
            stmt.setString(3,wok.getBase().getMida().toString());
            stmt.setString(4,ingredientsToString(wok.getIngredients()));
            stmt.setString(5,wok.getSalsa().getDescripcio());
            stmt.setDouble(6,wok.getSalsa().getPreu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String ingredientsToString(Ingredient[] ingredients) {
        StringBuilder sb = new StringBuilder();
        for(Ingredient i : ingredients ){
            sb.append(i.getDescripcio()).append(":");
            sb.append(i.getPreu()).append(";");
        }
        return sb.toString();
    }

    @Override
    public List<Wok> llegirWoks() {
        //TODO: connectar-me a la BBDD
        //TODO:Recuperar tots els woks
        return List.of();
    }
}
