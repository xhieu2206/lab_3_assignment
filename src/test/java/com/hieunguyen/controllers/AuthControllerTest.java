package com.hieunguyen.controllers;

import com.hieunguyen.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AuthControllerTest {
  String originalPassword = "pass1111";
  String validUserId = "1234567800000001";
  String incorrectPassword = "pass2222";
  String incorrectUserId = "1234567800000000";
  AuthController controller = new AuthController();

  HttpServletRequest request = mock(HttpServletRequest.class);
  HttpServletResponse response = mock(HttpServletResponse.class);
  RequestDispatcher dispatcher = mock(RequestDispatcher.class);
  ServletContext context = mock(ServletContext.class);

  @BeforeEach
  public void mockDataAndResetDatabase() {
    UserRepository.resetLoginAttemptTimes();
    UserRepository.resetAllPassword();
  }

  @Test
  public void testDoGet_ReturnLoginPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    controller.doGet(request, response);
    verify(request, times(1)).getRequestDispatcher("/login.jsp");
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void testDoPost_LoginWithInvalidUserId_ReturnLoginPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(originalPassword);
    when(request.getParameter("userId")).thenReturn("123456780000000a");
    when(request.getAttribute("errorMessage")).thenReturn("Authentication failed: Please try again");
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    controller.doPost(request, response);
    assertEquals(request.getAttribute("errorMessage"), "Authentication failed: Please try again");
    verify(request, times(1)).getRequestDispatcher("/login.jsp");
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void testDoPost_LoginWithIncorrectUserIdAndPasswordFirstTime_ReturnLoginPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(incorrectPassword);
    when(request.getParameter("userId")).thenReturn(validUserId);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    controller.doPost(request, response);
    verify(request, times(1)).getRequestDispatcher("/login.jsp");
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void testDoPost_LoginWithValidUserIdAndPasswordFirstTime_ReturnQuestionHintPage() throws ServletException, IOException {
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(originalPassword);
    when(request.getParameter("userId")).thenReturn(validUserId);
    controller.doPost(request, response);
    verify(response, times(1)).sendRedirect("/portal/hint-questions?userId=" + validUserId);
  }

  @Test
  public void testDoPost_LoginWithValidUserIdAndPasswordSecondTime_ReturnHomePage() throws ServletException, IOException {
    UserRepository.updateLoginAttemptTimes(validUserId, 1);
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(originalPassword);
    when(request.getParameter("userId")).thenReturn(validUserId);
    controller.doPost(request, response);
    verify(response, times(1)).sendRedirect("/portal/home?userId=" + validUserId);
  }

  @Test
  public void testDoPost_LoginWithNonExistedUserId_ReturnLoginPage() throws ServletException, IOException {
    UserRepository.updateLoginAttemptTimes(validUserId, 1);
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(originalPassword);
    when(request.getParameter("userId")).thenReturn(incorrectUserId);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    controller.doPost(request, response);
    verify(request, times(1)).getRequestDispatcher("/login.jsp");
    verify(dispatcher).forward(request, response);
  }

  @Test
  public void testDoPost_LoginWithExistedUserIdInCorrectPassword3Times_ReturnCallCentrePage() throws ServletException, IOException {
    UserRepository.updateLoginAttemptTimes(validUserId, 3);
    when(request.getServletContext()).thenReturn(context);
    when(request.getParameter("password")).thenReturn(incorrectPassword);
    when(request.getParameter("userId")).thenReturn(validUserId);
    when(request.getRequestDispatcher("/call-centre.jsp")).thenReturn(dispatcher);
    controller.doPost(request, response);
    verify(request, times(1)).getRequestDispatcher("/call-centre.jsp");
    verify(dispatcher).forward(request, response);
  }
}
