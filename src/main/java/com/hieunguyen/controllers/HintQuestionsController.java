package com.hieunguyen.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/portal/hint-questions")
public class HintQuestionsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
//    String userId = request.getParameter("userId");
    String userId = (String) request.getAttribute("userId");
    System.out.println("CLOG in 'doGet', value 'userId': " + userId);
    request.setAttribute(
        "userId",
        userId
    );
    RequestDispatcher dispatcher = request.getRequestDispatcher("/create-hint-question.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
