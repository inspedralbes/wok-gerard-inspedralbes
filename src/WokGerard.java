package src;

import src.dao.WokDAO;
import src.dao.WokDAOCSV;
import src.dao.WokDAOMySQL;
import src.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.DadesWok.*;
import static src.UtilsGerard.Utils.llegirInt;

public class WokGerard {
    static Scanner scan = new Scanner(System.in);
    static WokDAO wokDAO = new WokDAOMySQL();

    /**
     * Mètode principal de l'aplicació
     * @param args
     */
    public static void main(String[] args) {
        Wok.setPreuMidaGran(1.00);
        int option;
        do{
            System.out.println("Menu");
            System.out.println("1 - Crear un wok");
            System.out.println("2 - Llistar tots els woks");
            System.out.println("3 - Servir wok");
            System.out.println("0 - Sortir");
            option = llegirInt(scan,"Escull una opció",0,3);
            switch (option){
                case 1: crearWok();break;
                case 2: llistarWoks();break;
                case 3: servirWok();break;
                default: System.out.println("Adeu!!");
            }
        }while (option != 0);

    }

    private static void servirWok() {
        Wok wok = wokDAO.servirWok();
        if (wok != null){
            System.out.println("Aqui te el seu wok:");
            System.out.println(wok);
            System.out.println();
        }
    }

    private static void llistarWoks() {
        List<Wok> woks = wokDAO.llegirWoks();
        for (Wok wok : woks){
            System.out.println(wok);
            System.out.println();
        }
    }

    private static void crearWok() {
        Base base = escollirBase();
        Ingredient[] ingredients = escollirIngredients();
        Salsa salsa = escollirSalsa();
        Wok wok1 = new Wok(base,ingredients,salsa);
        wokDAO.guardarWok(wok1);
    }

    private static Salsa escollirSalsa() {
        System.out.println("Salses disponibles:");
        for (int i = 0; i < salses.length; i++) {
            System.out.println( (i+1) +" - "+ salses[i] );
        }
        int salsa = llegirInt(scan,"Escolleix una salsa:",1,salses.length)-1;
        return  new Salsa(salses[salsa],0 );
    }

    private static Ingredient[] escollirIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        System.out.println("Ingredients disponibles:");
        for (int i = 0; i < ingredietsDesc.length; i++) {
            System.out.println( (i+1) +" - "+ingredietsDesc[i] +
                    " - " + ingredientsPreu[i] );
        }
        int opcio;
        while(true){
            opcio = llegirInt(scan,"Escolleix un ingredient (0 per acabar)"
                    ,0,ingredietsDesc.length)-1;
            if(opcio == -1) break;
            ingredients.add(new Ingredient(ingredietsDesc[opcio],
                    ingredientsPreu[opcio] ));
        }

        return ingredients.toArray(new Ingredient[0]);
    }

    private static MidaBase escollirMida(int base) {

        if (baseMida[base]){
            System.out.println("Tria la mida del Wok:");
            System.out.println("1 - Petita");
            System.out.println("2 - Gran(+"+Wok.getPreuMidaGran()+")");
            if(llegirInt(scan,"Escolleix una mida:",1,2) == 1){
                return MidaBase.PETITA;
            }else{
                return MidaBase.GRAN;
            }
        }else{
            System.out.println("Aquesta base només pot anar amb la mida gran del Wok.");
            return MidaBase.GRAN;
        }
    }

    /**
     * Funció per demanar el tipus de base de la comanda
     * @return el codi de la base escollida
     */
    private static Base escollirBase() {
        System.out.println("Bases disponibles:");
        for (int i = 0; i < basesDesc.length; i++) {
            System.out.println( (i+1) +" - "+basesDesc[i] + " - " + basePreus[i] );
        }
        int base =  llegirInt(scan,"Escolleix una base:",1,basesDesc.length)-1;
        MidaBase mida = escollirMida(base);

        return new Base(basesDesc[base],basePreus[base],mida );
    }
}
