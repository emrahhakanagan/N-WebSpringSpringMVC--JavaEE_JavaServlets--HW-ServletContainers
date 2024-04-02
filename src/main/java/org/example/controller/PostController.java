package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.service.PostService;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
  private static final String APPLICATION_JSON = "application/json";
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
    response.setContentType(APPLICATION_JSON);

    final Post post = service.getById(id);
      try {
          response.getWriter().print(gson.toJson(post));
      } catch (NotFoundException | IOException e) {
          throw new RuntimeException(e);
      }
  }


  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) {
    try {
      service.removeById(id);
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

  }
}
