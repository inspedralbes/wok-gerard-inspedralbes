package src.dao;

import src.model.Wok;

import java.util.List;

public class WokDAOMySQL implements WokDAO{
    @Override
    public void guardarWok(Wok wok) {
        //TODO: connectar-me a la BBDD
        //TODO:Emmagatzemar un wok
    }

    @Override
    public List<Wok> llegirWoks() {
        //TODO: connectar-me a la BBDD
        //TODO:Recuperar tots els woks
        return List.of();
    }
}
