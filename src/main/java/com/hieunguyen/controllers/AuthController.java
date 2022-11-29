package com.hieunguyen.controllers;

import com.hieunguyen.services.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/portal/login")
public class AuthController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String password = request.getParameter("password");
    String userId = request.getParameter("userId");
    int loginAttemptTimes = Integer.parseInt(request.getParameter("loginAttemptTimes"));
    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");

    if (!UserService.isPasswordValid(password) || !UserService.isUserIdValid(userId)) {
      request.setAttribute(
          "errorMessage",
          "Authentication failed: Please try again"
      );

      request.setAttribute(
          "loginAttemptTimes",
          0
      );

      dispatcher.forward(request, response);
      return;
    }

    if (!UserService.isValidUser(userId, password)) {
      loginAttemptTimes += 1;

      request.setAttribute(
          "errorMessage",
          "Invalid User ID/Password. Number of attempts left" + " " + (3 - loginAttemptTimes)
      );
      request.setAttribute(
          "loginAttemptTimesFromServer",
          loginAttemptTimes
      );
      dispatcher.forward(request, response);
      return;
    }
  }
}
