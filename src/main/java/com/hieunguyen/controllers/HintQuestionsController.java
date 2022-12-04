package com.hieunguyen.controllers;

import com.hieunguyen.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/portal/hint-questions")
public class HintQuestionsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String userId = request.getParameter("userId");
    request.setAttribute(
        "userId",
        userId
    );
    RequestDispatcher dispatcher = request.getRequestDispatcher("/create-hint-question.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String userId = request.getParameter("userId");
    String password = request.getParameter("password");
    String newPassword = request.getParameter("new_password");
    String confirmPassword = request.getParameter("confirm_password");

    boolean isPasswordChanged = UserService.isPasswordChanged(userId, password, newPassword, confirmPassword);

    if (!isPasswordChanged) {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/call-centre-2.jsp");
      dispatcher.forward(request, response);
      return;
    }

    request.setAttribute(
        "userId",
        userId
    );
    response.sendRedirect("/portal/home?id=" + userId);
  }
}
