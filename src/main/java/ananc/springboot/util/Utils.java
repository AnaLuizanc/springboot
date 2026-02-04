package ananc.springboot.util;

import ananc.springboot.domain.Anime;
import ananc.springboot.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class Utils {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(Long id, AnimeRepository animeRepository) {
        return animeRepository.findById(id)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }
}
