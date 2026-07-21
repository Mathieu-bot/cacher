package fr.birdia.cacher.service;

import fr.birdia.cacher.file.bucket.BucketComponent;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CacheService {
  private final BucketComponent bucketComponent;
  private final Duration DOWNLOAD_DURATION = Duration.ofMinutes(5);

  public URL getWithCache(URL url) {
    var bucketKey = url.toString(); // TODO: might not correctly handle special char
    try {
      bucketComponent.download(bucketKey);
    } catch (Exception e) {
      var downloadedFromSource = downloadWithGet(url);
      bucketComponent.upload(downloadedFromSource, bucketKey);
    }
    return bucketComponent.presign(bucketKey, DOWNLOAD_DURATION);
  }

  private File downloadWithGet(URL url) {
    throw new RuntimeException("TODO");
  }
}
