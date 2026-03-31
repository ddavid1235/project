package ro.example.catalogcarti.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ro.example.catalogcarti.model.Carte;
import ro.example.catalogcarti.repository.CarteRepository;

@Repository
public class CarteRepositoryImpl implements CarteRepository {
    private List<Carte> carti = new ArrayList<>();
    private int nextId = 1;

    // date demo la pornire
    public CarteRepositoryImpl() {
        salveaza(new Carte("Harry Potter", "J.K. Rowling", 1997, "Fantasy"));
        salveaza(new Carte("Dune", "Frank Herbert", 1965, "SF"));
        salveaza(new Carte("1984", "George Orwell", 1949, "Distopie"));
    }

    @Override
    public List<Carte> gasesteToate() {
        return new ArrayList<>(carti); // copie pentru siguranta
    }

    @Override
    public Carte gasesteDupaId(int id) {
        return carti.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void salveaza(Carte carte) {
        if (carte.getId() == 0) {
            carte.setId(nextId++);
            carti.add(carte);
        } else {
            // update
            for (int i = 0; i < carti.size(); i++) {
                if (carti.get(i).getId() == carte.getId()) {
                    carti.set(i, carte);
                    return;
                }
            }
        }
    }

    @Override
    public void sterge(int id) {
        carti = carti.stream().filter(c -> c.getId() != id).collect(Collectors.toList());
    }
}