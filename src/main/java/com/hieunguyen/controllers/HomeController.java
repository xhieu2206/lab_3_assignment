package com.hieunguyen.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/portal/home")
public class HomeController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userId = request.getParameter("userId");
      request.setAttribute(
          "userId",
          userId
      );
      RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
      dispatcher.forward(request, response);
  }
}
