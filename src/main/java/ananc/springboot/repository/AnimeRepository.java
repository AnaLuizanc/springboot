package ananc.springboot.repository;

import ananc.springboot.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class AnimeRepository {
    private static final List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "Boku no Hero"), new Anime(2L, "Berserk")));
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 10000));
        animes.add(anime);
        return anime;
    }

    public void delete(Long id) {
        animes.remove(animes.stream()
                            .filter(anime -> anime.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found")));
    }
}
