package ananc.springboot.repository;

import ananc.springboot.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class AnimeRepository {
    private static final List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(
                new Anime(1L, "Boku no Hero"),
                new Anime(2L, "Berserk"))
        );
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 10000));
        animes.add(anime);
        return anime;
    }
}
