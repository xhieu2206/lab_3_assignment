package com.hieunguyen.controllers;

import com.hieunguyen.models.User;
import com.hieunguyen.repository.UserRepository;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTest {
  HttpServletRequest request = mock(HttpServletRequest.class);
  HttpServletResponse response = mock(HttpServletResponse.class);
  ServletContext context = mock(ServletContext.class);

  UsersController controller = new UsersController();

  @Test
  public void testDoGet_ReturnListOfUser() throws IOException {
    List<User> users = UserRepository.index();
    when(request.getServletContext()).thenReturn(context);

    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    when(response.getWriter()).thenReturn(writer);

    controller.doGet(request, response);

    for (User user: users) {
      assert(stringWriter.toString().contains("<p>" + user.getUserId() + "|" + user.getPassword() + "|" + user.getLoginAttemptTimes() + "</p>"));
    }
  }
}
