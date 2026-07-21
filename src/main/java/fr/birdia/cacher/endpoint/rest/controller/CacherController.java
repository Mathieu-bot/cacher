package fr.birdia.cacher.endpoint.rest.controller;

import static java.nio.charset.StandardCharsets.UTF_8;

import fr.birdia.cacher.service.CacheService;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CacherController {

  private final CacheService cacheService;

  @GetMapping("/cached-url")
  public String getWithCache(@RequestParam String encodedUrl) {
    try {
      var decodedUrl = URLDecoder.decode(encodedUrl, UTF_8);
      URL cachedUrl = cacheService.getWithCache(new URL(decodedUrl));
      return cachedUrl.toString();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
