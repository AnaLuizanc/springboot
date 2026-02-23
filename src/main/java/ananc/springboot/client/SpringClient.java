package ananc.springboot.client;

import ananc.springboot.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SpringClient {
    public static void main(String[] args) {
//        testGetWithRestTemplate();

        Anime drStone = Anime.builder().name("Dra. Stone").build();

        Anime drStoneSaved = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST,
                                                         new HttpEntity<>(drStone, createJsonHeader()), Anime.class)
                                               .getBody();

//        ResponseEntity<PagebleResponse<Anime>> exchangeAnimeList = new RestTemplate().exchange(
//                "http://localhost:8080/animes?sort=name,desc", HttpMethod.GET, null,
//                new ParameterizedTypeReference<PagebleResponse<Anime>>() {});
//        log.info("Anime List{}", exchangeAnimeList.getBody());

        assert drStoneSaved != null;
        drStoneSaved.setName("Dr. Stone ");
        ResponseEntity<Void> updatedDrStone = new RestTemplate().exchange("http://localhost:8080/animes",
                                                                          HttpMethod.PUT, new HttpEntity<>(drStoneSaved,
                                                                                                           createJsonHeader()),
                                                                          Void.class);
        log.info("Updated Dr. Stone {}", updatedDrStone.getStatusCode());


        ResponseEntity<Void> updatedDrStoneDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                                                                                 HttpMethod.DELETE, null, Void.class, drStoneSaved.getId());
        log.info("Updated Dr. Stone {}", updatedDrStone.getStatusCode());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
