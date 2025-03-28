package src.dao;

import com.mysql.cj.protocol.a.TextResultsetReader;
import src.BBDDConnection.ConexioBD;
import src.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Wok> woks = new ArrayList<>();
        try {
            Connection con = ConexioBD.getInstance();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Wok");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Base base = new Base( rs.getString("basedesc"),
                                      rs.getDouble("preubase"),
                                      MidaBase.valueOf(rs.getString("midabase")));
                Ingredient[] ings = stringToIngredients(rs.getString("ingredients"));
                Salsa salsa = new Salsa(rs.getString("salsadesc"),
                        rs.getDouble("preusalsa") );
                woks.add(new Wok(base,ings,salsa));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return woks;
    }

    @Override
    public Wok servirWok() {
        Wok wok = null;
        try {
            Connection con = ConexioBD.getInstance();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Wok ORDER BY id ASC LIMIT 1");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Base base = new Base( rs.getString("basedesc"),
                        rs.getDouble("preubase"),
                        MidaBase.valueOf(rs.getString("midabase")));
                Ingredient[] ings = stringToIngredients(rs.getString("ingredients"));
                Salsa salsa = new Salsa(rs.getString("salsadesc"),
                        rs.getDouble("preusalsa") );
                wok = new Wok(base,ings,salsa);
                //Eliminem el wok
                PreparedStatement delStmt = con.prepareStatement("DELETE FROM Wok WHERE id = ?");
                delStmt.setInt(1,rs.getInt("ID"));
                delStmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wok;
    }

    private  Ingredient[] stringToIngredients(String stringIngs) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] ingredientsData =  stringIngs.split(";");
        for (String ing: ingredientsData){
            if(!ing.isEmpty()) {
                String parts[] = ing.split(":");
                ingredients.add(new Ingredient(parts[0], Double.parseDouble(parts[1])));
            }
        }
        return ingredients.toArray(new Ingredient[0]);
    }
}
