package fr.birdia.cacher.endpoint.rest.controller;

import fr.birdia.cacher.service.CacheService;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CacherController {

  private final CacheService cacheService;

  @GetMapping("/cached-url")
  public String getWithCache(@RequestParam String url) {
    try {
      URL cachedUrl = cacheService.getWithCache(new URL(url));
      return cachedUrl.toString(); // TODO: test
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
