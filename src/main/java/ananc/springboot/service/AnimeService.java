package ananc.springboot.service;

import ananc.springboot.domain.Anime;
import ananc.springboot.repository.AnimeRepository;
import ananc.springboot.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimeService {
    private final Utils utils;

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(Long id) {
        return utils.findAnimeOrThrowNotFound(id, animeRepository);
    }

    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(utils.findAnimeOrThrowNotFound(id, animeRepository));
    }

    public void update(Anime anime) {
        animeRepository.save(anime);
    }
}
