package fr.birdia.cacher.endpoint.rest.controller;

import fr.birdia.cacher.conf.FacadeIT;
import org.junit.jupiter.api.Test;

class CacherControllerIT extends FacadeIT {
  CacherController subject;

  @Test
  void miss() {
    var url =
        "https://images.unsplash.com/photo-1779896412092-4cb69cf4ccad?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";

    var cachedUrl = subject.getWithCache(url);
    // TODO: that cache was missed
  }
}
