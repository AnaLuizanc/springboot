package ananc.springboot.controller;

import ananc.springboot.domain.Anime;
import ananc.springboot.repository.AnimeRepository;
import ananc.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;

    private final AnimeRepository animeRepository;

    @GetMapping
    public ResponseEntity<List<Anime>> listAll() {
        log.info("Formatted Date {}", dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeRepository.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> finfById(@PathVariable Long id) {
        Anime animeFound = animeRepository.listAll()
                                          .stream()
                                          .filter(anime -> anime.getId().equals(id))
                                          .findFirst()
                                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                         "Anime not found"));

        return ResponseEntity.ok(animeFound);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        return ResponseEntity.ok(animeRepository.save(anime));
    }
}
