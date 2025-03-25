package src.dao;

import src.model.*;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WokDAOCSV implements WokDAO{
    private static Path path = Path.of("files","woks.csv");
    private static File file = new File(path.toString());
    @Override
    public void guardarWok(Wok wok) {
        try {
            FileWriter myWriter = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            bw.write(wokToCSV(wok));
            bw.newLine();
            bw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String wokToCSV(Wok wok) {
        StringBuilder csv = new StringBuilder();
        csv.append(wok.getBase().getDescripcio()).append(",");
        csv.append(wok.getBase().getPreu()).append(",");
        csv.append(wok.getBase().getMida()).append(",");
        for(Ingredient i : wok.getIngredients() ){
            csv.append(i.getDescripcio()).append(":");
            csv.append(i.getPreu()).append(";");
        }
        csv.append(",");
        csv.append(wok.getSalsa().getDescripcio()).append(",");
        csv.append(wok.getSalsa().getPreu()).append(",");
        return csv.toString();

    }

    @Override
    public List<Wok> llegirWoks() {
        List<Wok> woks = new ArrayList<>();
        try{
            File f = new File(file.toString());
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()){
                String data = scan.nextLine();
                woks.add(wokFromCSV(data));
            }

        }catch (FileNotFoundException e){
            System.out.println("An error ocurred");
            e.printStackTrace();
        }
        return woks;
    }

    private Wok wokFromCSV(String data) {
        String[] camps = data.split(",");
        Base base = new Base(camps[0],Double.parseDouble(camps[1]), MidaBase.valueOf(camps[2]));
        String[] ingredientsData =  camps[3].split(";");
        List<Ingredient> ingredients = new ArrayList<>();
        for (String ing: ingredientsData){
            if(!ing.isEmpty()) {
                String parts[] = ing.split(":");
                ingredients.add(new Ingredient(parts[0], Double.parseDouble(parts[1])));
            }
        }
        Salsa salsa = new Salsa(camps[4],Double.parseDouble(camps[5]));
        return new Wok(base,ingredients.toArray(new Ingredient[0]),salsa);
    }
}
