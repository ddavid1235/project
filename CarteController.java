package ro.example.catalogcarti.controller;
import java.util.List;
import ro.example.catalogcarti.model.Carte;
import ro.example.catalogcarti.service.CarteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarteController {
    private final CarteService service;

    public CarteController(CarteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(defaultValue = "0") int pagina,
                          @RequestParam(required = false) String cautare,
                          @RequestParam(required = false) Boolean sortare,
                          Model model, HttpSession session) {
        
        List<Carte> lista = service.gasesteToate();

        if (cautare != null && !cautare.isEmpty()) {
            lista = service.cautaDupaTitlu(cautare);
        }
        if (sortare != null) {
            lista = service.sorteazaDupaAn(sortare);
        }

        int start = pagina * 5;
        int end = Math.min(start + 5, lista.size());
        model.addAttribute("carti", lista.subList(start, end));
        model.addAttribute("paginaCurenta", pagina);
        model.addAttribute("totalPagini", (int) Math.ceil(lista.size() / 5.0));
        model.addAttribute("role", session.getAttribute("role"));
        return "catalog";
    }

    @GetMapping("/adauga")
    public String formAdauga(Model model, HttpSession session) {
        if (session.getAttribute("role") == null) return "redirect:/login";
        model.addAttribute("carte", new Carte());
        return "adauga";
    }

    @PostMapping("/adauga")
    public String adauga(@ModelAttribute Carte carte, HttpSession session) {
        if (session.getAttribute("role") == null) return "redirect:/login";
        service.adauga(carte);
        return "redirect:/catalog";
    }

    @GetMapping("/editeaza/{id}")
    public String formEditeaza(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("role") == null) return "redirect:/login";
        model.addAttribute("carte", service.gasesteToate().stream()
                .filter(c -> c.getId() == id).findFirst().orElse(null));
        return "editeaza";
    }

    @PostMapping("/editeaza")
    public String editeaza(@ModelAttribute Carte carte, HttpSession session) {
        if (session.getAttribute("role") == null) return "redirect:/login";
        service.actualizeaza(carte);
        return "redirect:/catalog";
    }

    @GetMapping("/sterge/{id}")
    public String sterge(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("role") == null) return "redirect:/login";
        service.sterge(id);
        return "redirect:/catalog";
    }
}