package com.hieunguyen.controllers;

import com.hieunguyen.models.User;
import com.hieunguyen.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/portal/index-users")
public class UsersController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<User> users = UserService.index();
    PrintWriter out = response.getWriter();

    response.setContentType("text/html");
    out.println("<p>User ID | Password | Login attempt times</p>");

    for (User user: users) {
      out.println("<p>" + user.getUserId() + "|" + user.getPassword() + "|" + user.getLoginAttemptTimes() + "</p>");
    }
  }
}
