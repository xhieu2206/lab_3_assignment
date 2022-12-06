package com.hieunguyen.controllers;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HintQuestionsControllerTest {
  HttpServletRequest request = mock(HttpServletRequest.class);
  HttpServletResponse response = mock(HttpServletResponse.class);
  RequestDispatcher dispatcher = mock(RequestDispatcher.class);
  ServletContext context = mock(ServletContext.class);

  HintQuestionsController controller = new HintQuestionsController();

  @Test
  public void testDoGet_ReturnHintQuestionPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getRequestDispatcher("/create-hint-question.jsp")).thenReturn(dispatcher);
    controller.doGet(request, response);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/create-hint-question.jsp");
    dispatcher.forward(request, response);
  }
}
