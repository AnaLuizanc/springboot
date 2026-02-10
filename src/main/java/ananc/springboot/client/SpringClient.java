package ananc.springboot.client;

import ananc.springboot.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SpringClient {
    static void main() {
        ResponseEntity<Anime> animeResponseEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}",
                                                                                    Anime.class, 17);
        log.info("Response Entity {}", animeResponseEntity);
        log.info("Response Data {}", animeResponseEntity.getBody());

        Anime anime = new RestTemplate().getForObject("http://localhost:8080/animes/17", Anime.class);
        log.info("Anime {}", anime);
    }
}
