package ananc.springboot.client;

import ananc.springboot.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SpringClient {
    static void main() {
        ResponseEntity<Anime> animeResponseEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/17",
                                                                                    Anime.class);
        log.info("Response Entity {}", animeResponseEntity);
        log.info("Response Data {}", animeResponseEntity.getBody());

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes", Anime[].class);
        log.info("Anime Array{}", Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchangeAnimeList = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.GET,
                                                                       null, new ParameterizedTypeReference<List<Anime>>() {});
        log.info("Anime List{}", exchangeAnimeList.getBody());
    }
}
