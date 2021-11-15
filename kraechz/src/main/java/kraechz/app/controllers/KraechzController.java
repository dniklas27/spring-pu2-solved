package kraechz.app.controllers;

import java.util.List;

import kraechz.app.models.Kraechz;
import kraechz.app.services.KraechzService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KraechzController {

  private final KraechzService service;

  public KraechzController(KraechzService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String index(Model model) {
    List<Kraechz> kraechze = service.kraechzeAnzeigen("all");
    model.addAttribute("kraechze", kraechze);
    return "index";
  }

  @GetMapping("/filter/{name}")
  public String filter(Model model, @PathVariable String name) {
    List<Kraechz> kraechze = service.kraechzeAnzeigen(name);
    model.addAttribute("kraechze", kraechze);
    model.addAttribute("filter", true);
    return "index";
  }

  @PostMapping("/")
  public String add(Kraechz kraechz) {
    service.kraechze(kraechz);
    return "redirect:/";
  }


}
