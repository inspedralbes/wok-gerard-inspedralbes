package src.dao;

import src.model.Wok;

import java.util.List;

public interface WokDAO {
    void guardarWok(Wok wok);
    List<Wok> llegirWoks();
}
