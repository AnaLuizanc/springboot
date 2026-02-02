package ananc.springboot.repository;

import ananc.springboot.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeRepository {
    public List<Anime> listAll() {
        return List.of(
                new Anime(1L, "Boku no Hero"),
                new Anime(2L, "Berserk"));
    }
}
