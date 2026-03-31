package ro.example.catalogcarti.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ro.example.catalogcarti.model.Carte;
import ro.example.catalogcarti.repository.CarteRepository;

@Service
public class CarteService {
    private final CarteRepository repository;

    public CarteService(CarteRepository repository) {
        this.repository = repository;
    }

    public List<Carte> gasesteToate() { return repository.gasesteToate(); }

    public void adauga(Carte carte) { repository.salveaza(carte); }

    public void actualizeaza(Carte carte) { repository.salveaza(carte); }

    public void sterge(int id) { repository.sterge(id); }

    public List<Carte> cautaDupaTitlu(String termen) {
        return repository.gasesteToate().stream()
                .filter(c -> c.getTitlu().toLowerCase().contains(termen.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Carte> sorteazaDupaAn(boolean crescator) {
        return repository.gasesteToate().stream()
                .sorted((a, b) -> crescator ? Integer.compare(a.getAn(), b.getAn())
                                           : Integer.compare(b.getAn(), a.getAn()))
                .collect(Collectors.toList());
    }
}