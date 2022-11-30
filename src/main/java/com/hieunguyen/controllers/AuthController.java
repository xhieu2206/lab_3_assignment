package com.hieunguyen.controllers;

import com.hieunguyen.models.User;
import com.hieunguyen.services.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/portal/login")
public class AuthController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String password = request.getParameter("password");
    String userId = request.getParameter("userId");
    ServletContext context = getServletContext();
    User user = UserService.getUserData(context);
    int loginAttemptTimes = 0;
    System.out.println(userId);
    System.out.println(password);
    RequestDispatcher dispatcher;

    if (!UserService.isPasswordValid(password) || !UserService.isUserIdValid(userId)) {
      request.setAttribute(
          "errorMessage",
          "Authentication failed: Please try again"
      );
      dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }

    if (!UserService.isValidUser(userId, password)) {
      loginAttemptTimes += 1;

      if (loginAttemptTimes == 3) {
        dispatcher = request.getRequestDispatcher("/call-centre.jsp");
        dispatcher.forward(request, response);
        return;
      }

      request.setAttribute(
          "errorMessage",
          "Invalid User ID/Password. Number of attempts left" + " " + (3 - loginAttemptTimes)
      );
      request.setAttribute(
          "loginAttemptTimesFromServer",
          loginAttemptTimes
      );

      dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
    }
    System.out.println(UserService.isValidUser(userId, password));
    if (UserService.isValidUser(userId, password) && loginAttemptTimes == 0) {
      response.sendRedirect("/portal/hint-questions");
    }
  }
}
