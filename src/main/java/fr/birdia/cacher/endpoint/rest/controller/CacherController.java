package fr.birdia.cacher.endpoint.rest.controller;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import fr.birdia.cacher.service.CacheService;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CacherController {

  private final CacheService cacheService;
  private final String expectedApiKey;

  public CacherController(CacheService cacheService, @Value("${API_KEY}") String expectedApiKey) {
    this.cacheService = cacheService;
    this.expectedApiKey = expectedApiKey;
  }

  @GetMapping("/cached-url")
  public String getWithCache(@RequestParam String encodedUrl, @RequestParam String apiKey) {
    authorize(apiKey);

    try {
      var decodedUrl = URLDecoder.decode(encodedUrl, UTF_8);
      URL cachedUrl = cacheService.getWithCache(new URL(decodedUrl));
      return cachedUrl.toString();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private void authorize(String apiKey) {
    if (!Objects.equals(expectedApiKey, apiKey)) {
      throw new ResponseStatusException(UNAUTHORIZED, "Invalid API key");
    }
  }
}
