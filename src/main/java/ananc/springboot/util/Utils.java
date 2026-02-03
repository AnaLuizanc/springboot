package ananc.springboot.util;

import ananc.springboot.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Utils {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(Long id, List<Anime> animes) {
        return animes.stream()
                     .filter(anime -> anime.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }
}
