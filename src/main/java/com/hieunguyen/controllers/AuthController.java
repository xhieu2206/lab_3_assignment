package com.hieunguyen.controllers;

import com.hieunguyen.models.LoginData;
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
    RequestDispatcher dispatcher;

    if (!UserService.isPasswordValid(password) || !UserService.isUserIdValid(userId)) {
      request.setAttribute(
          "errorMessage",
          "Authentication failed: Please try again"
      );
      dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
    }

    LoginData loginData = UserService.login(userId, password);

    if (!loginData.isUserIdExisted()) {
      request.setAttribute(
          "errorMessage",
          "Authentication failed: Please try again"
      );
      dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
    }

    if (!loginData.isLoggedInSuccess() && loginData.isUserIdExisted()) {
      request.setAttribute(
          "errorMessage",
          "Authentication failed: Please try again"
      );
      dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
    }
  }
}
