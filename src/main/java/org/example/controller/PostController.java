package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Post;
import org.example.service.PostService;

import java.io.IOException;
import java.io.Reader;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;
  private final Gson gson = new Gson();

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.all();
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }
}
