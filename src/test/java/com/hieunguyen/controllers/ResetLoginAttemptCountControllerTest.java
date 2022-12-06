package com.hieunguyen.controllers;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ResetLoginAttemptCountControllerTest {
  HttpServletRequest request = mock(HttpServletRequest.class);
  HttpServletResponse response = mock(HttpServletResponse.class);
  RequestDispatcher dispatcher = mock(RequestDispatcher.class);
  ServletContext context = mock(ServletContext.class);

  ResetLoginAttemptCountController controller = new ResetLoginAttemptCountController();

  @Test
  public void testDoGet_ReturnLoginPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    controller.doGet(request, response);
    verify(response, times(1)).sendRedirect("/portal/login");
  }
}
