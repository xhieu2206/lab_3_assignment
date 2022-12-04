package com.hieunguyen.controllers;

import com.hieunguyen.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/portal/reset-login-times")
public class ResetLoginAttemptCountController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    UserService.resetLoginTimes();

    response.sendRedirect("/portal/login");
  }
}
