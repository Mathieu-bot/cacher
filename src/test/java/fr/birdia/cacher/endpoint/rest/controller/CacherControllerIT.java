package fr.birdia.cacher.endpoint.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.birdia.cacher.conf.FacadeIT;
import fr.birdia.cacher.file.bucket.BucketComponent;
import java.io.File;
import java.net.URI;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class CacherControllerIT extends FacadeIT {

  @Autowired CacherController subject;

  @MockBean BucketComponent bucketComponent;

  @Test
  void miss() throws Exception {
    var url =
        "https://images.unsplash.com/photo-1779896412092-4cb69cf4ccad?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    when(bucketComponent.download(url)).thenThrow(new RuntimeException("cache miss"));
    when(bucketComponent.presign(eq(url), any(Duration.class)))
        .thenReturn(
            URI.create("https://example.com/presigned?param1=3&param2=4%20param3=5").toURL());

    var response = subject.getWithCache(url);

    verify(bucketComponent, times(1)).upload(any(File.class), eq(url));
    assertEquals("https://example.com/presigned?param1=3&param2=4%20param3=5", response);
  }
}
