package ananc.springboot.controller;

import ananc.springboot.domain.Anime;
import ananc.springboot.repository.AnimeRepository;
import ananc.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
