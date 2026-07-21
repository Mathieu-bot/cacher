package fr.birdia.cacher.hash;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SHA256 implements Function<String, String> {

  @SneakyThrows
  @Override
  public String apply(String value) {
    var digest = MessageDigest.getInstance("SHA-256");
    var hash = digest.digest(value.getBytes(UTF_8));
    return HexFormat.of().formatHex(hash);
  }
}
