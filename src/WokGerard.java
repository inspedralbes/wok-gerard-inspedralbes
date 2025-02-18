package src;

import src.model.*;

import java.util.ArrayList;
import java.util.Scanner;

import static src.DadesWok.*;
import static src.UtilsGerard.Utils.llegirInt;

public class WokGerard {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        Base base = escollirBase();
        Ingredient[] ingredients = escollirIngredients();
        Salsa salsa = escollirSalsa();
        Wok wok = new Wok(base,ingredients,salsa);
        System.out.println(wok);

    }

   /* private static void mostrarResumComanda(Wok wok) {
        System.out.println("Resum de la comanda");
        System.out.println("Base: " + wok.get + " - " + DadesWok.basePreus[base]);
        System.out.print("Mida: ");
        if(mida == 1){
            System.out.println("Petita");
        }else{
            System.out.println("Gran 1,00");
        }
        System.out.println("Ingredients:");
        for( int ingredient : ingredients){
            System.out.println(" - " + ingredietsDesc[ingredient] + " - " + ingredientsPreu[ingredient] );
        }
        System.out.println("Salsa: " +  salses[salsa]);
        System.out.println("Preu total: " + String.format("%.2f",preu));
    }*/


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
            System.out.println("2 - Gran(+1.00€)");
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
