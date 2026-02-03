package ananc.springboot.repository;

import ananc.springboot.domain.Anime;
import ananc.springboot.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
@RequiredArgsConstructor
public class AnimeRepository {
    private final Utils utils;

    private static final List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "Boku no Hero"), new Anime(2L, "Berserk")));
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return utils.findAnimeOrThrowNotFound(id, animes);
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 10000));
        animes.add(anime);
        return anime;
    }

    public void delete(Long id) {
        animes.remove(utils.findAnimeOrThrowNotFound(id, animes));
    }
}
