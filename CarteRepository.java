package ro.example.catalogcarti.repository;

import ro.example.catalogcarti.model.Carte;
import java.util.List;


public interface CarteRepository {
    List<Carte> gasesteToate();
    Carte gasesteDupaId(int id);
    void salveaza(Carte carte);
    void sterge(int id);
}