package pl.wicherski.keycloak.demo;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final List<Hero> someHeroes = List.of(
            new Hero(1, "Ken"),
            new Hero(2, "Yannick"),
            new Hero(3, "Pieter"));

    @GetMapping
    @RolesAllowed({ "user", "admin" })
    public List<Hero> heroes() {
        return someHeroes;
    }

    @GetMapping("/{id}")
    @RolesAllowed("admin")
    public Hero hero(@PathVariable("id") int id) {
        return someHeroes.stream()
                .filter(hero -> hero.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
