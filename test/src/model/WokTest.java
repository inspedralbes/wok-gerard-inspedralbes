package src.model;

import static org.junit.jupiter.api.Assertions.*;

class WokTest {

    @org.junit.jupiter.api.Test
    void calcularPreuTest() {
        Base base = new Base("",5.0,MidaBase.PETITA);
        Ingredient ing1 = new Ingredient("",1.0);
        Ingredient ing2 = new Ingredient("",1.5);
        Ingredient[] ings ={ing1,ing2};
        Salsa salsa = new Salsa("",2.3);
        Wok wok =  new Wok(base,ings,salsa);
        assertEquals(9.8,wok.getPreu());
    }
}