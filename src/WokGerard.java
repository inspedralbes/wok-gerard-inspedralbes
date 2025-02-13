package src;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static src.UtilsGerard.Utils.llegirInt;

public class WokGerard {
    static Scanner scan = new Scanner(System.in);
    static String[] basesDesc = {"Gyoza","Fideus Cruixents", "Tallarines d'arròs",
            "Fideus d'arròs","Fins d'arròs","Arròs Gesami","Arròs Integral",
            "Verdures Variades","Udon"};
    static double[] basePreus = {8,4.10,4.10,4.10,5.10,5.10,5.10,4.10,5.10};
    static boolean[] baseMida = {false,true,true,true,true,true,true,true,false};
    static String[] ingredietsDesc = {"Pollastre","Vedella","Gambes","Tofu","Ou","Xampinyons","Ceba","Pebrot"};
    static double[] ingredientsPreu = {1.50,2.00,1.80,2.50,3.00,2.20,1.70,2.80};
    static String[] salses = {"Soja","Ostres","Agredolça","Teriyaki","Picant"};

    public static void main(String[] args) {

        Base base = escollirBase();
        Ingredient[] ingredients = escollirIngredients();
        Salsa salsa = escollirSalsa();
        Wok wok = new Wok(base,ingredients,salsa);

        //TODO: Transformar mètode mostrarResumComanda per que utilitzi el wok
        //mostrarResumComanda(wok);
    }

    private static void mostrarResumComanda(int base, int mida, ArrayList<Integer> ingredients, int salsa, double preu) {
        System.out.println("Resum de la comanda");
        System.out.println("Base: " + basesDesc[base] + " - " + basePreus[base]);
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
    }

    private static double calcularPreu(int base, int mida, ArrayList<Integer> ingredients) {
        double preu = basePreus[base];
        if(mida == 2 && baseMida[base]){
            preu += 1.00;
        }
        for( int ingredient : ingredients){
            preu += ingredientsPreu[ingredient];
        }
        return preu;
    }

    private static Salsa escollirSalsa() {
        System.out.println("Salses disponibles:");
        for (int i = 0; i < salses.length; i++) {
            System.out.println( (i+1) +" - "+salses[i] );
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
        do{
            opcio = llegirInt(scan,"Escolleix un ingredient (0 per acabar)"
                    ,0,ingredietsDesc.length);
            ingredients.add(new Ingredient(ingredietsDesc[opcio],
                    ingredientsPreu[opcio] ));
        }while(opcio!= 0);

        return ingredients.toArray(new Ingredient[0]);
    }

    private static String escollirMida(int base) {

        if (baseMida[base]){
            System.out.println("Tria la mida del Wok:");
            System.out.println("1 - Petita");
            System.out.println("2 - Gran(+1.00€)");
            if(llegirInt(scan,"Escolleix una mida:",1,2) == 1){
                return "Petit";
            }else{
                return "Gran";
            }
        }else{
            System.out.println("Aquesta base només pot anar amb la mida gran del Wok.");
            return "Gran";
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
        String mida = escollirMida(base);

        return new Base(basesDesc[base],basePreus[base],mida );
    }
}
