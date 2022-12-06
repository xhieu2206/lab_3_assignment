package com.hieunguyen.services;

import com.hieunguyen.models.LoginData;
import com.hieunguyen.models.User;
import com.hieunguyen.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
  String originalPassword = "pass1111";
  String validUserId = "1234567800000001";

  @BeforeEach
  public void resetPasswordAndLoginAttemptTimes() {
    UserRepository.resetAllPassword();
    UserRepository.resetLoginAttemptTimes();
  }

  @Test
  public void testIsPassWordValid_PasswordLengthLessThan8_ReturnFalse() {
    String password = "passwor";
    assertFalse(UserService.isPasswordValid(password));
  }

  @Test
  public void testIsPassWordValid_PasswordLengthLongerThan8_ReturnFalse() {
    String password = "password1";
    assertFalse(UserService.isPasswordValid(password));
  }

  @Test
  public void testIsPassWordValid_NotIncludeDigit_ReturnFalse() {
    String password = "password";
    assertFalse(UserService.isPasswordValid(password));
  }

  @Test
  public void testIsPassWordValid_NotIncludeCharacter_ReturnFalse() {
    String password = "11112222";
    assertFalse(UserService.isPasswordValid(password));
  }

  @Test
  public void testIsPassWordValid_IncludeUpperCaseCharacter_ReturnTrue() {
    String password = "1234abcd";
    assertTrue(UserService.isPasswordValid(password));
  }

  @Test
  public void testIsUserIdValid_UserIdLengthLessThan16_ReturnFalse() {
    String userId = "123456789abcdef";
    assertFalse(UserService.isUserIdValid(userId));
  }

  @Test
  public void testIsUserIdValid_UserIdLengthLongerThan16_ReturnFalse() {
    String userId = "123456789abcdef";
    assertFalse(UserService.isUserIdValid(userId));
  }

  @Test
  public void testIsUserIdValid_IncludeNonDigitCharacter_ReturnFalse() {
    String userId = "123456789abcdef";
    assertFalse(UserService.isUserIdValid(userId));
  }

  @Test
  public void testIsUserIdValid_ValidUserId_ReturnTrue() {
    String userId = "1234567891234567";
    assertTrue(UserService.isUserIdValid(userId));
  }

  @Test
  public void testFindUserById_ValidUserId_ReturnUser() {
    User user = UserService.findUserById(validUserId);
    assertEquals(user.getUserId(), validUserId);
    assertEquals(user.getPassword(), originalPassword);
    assertEquals(user.getLoginAttemptTimes(), 0);
  }

  @Test
  public void testFindUserById_InvalidUserId_ReturnNull() {
    User user = UserService.findUserById("1234567800000000");
    assertNull(user);
  }

  @Test
  public void testUpdateLoginAttemptTimes_UpdateByOne_UpdateSucceed() {
    UserService.updateLoginAttemptTimes(validUserId, 1);
    User user = UserService.findUserById(validUserId);
    assertEquals(user.getLoginAttemptTimes(), 1);
  }

  @Test
  public void testResetLoginTimes_ResetAll_ResetSucceed() {
    UserService.updateLoginAttemptTimes(validUserId, 1);
    UserService.updateLoginAttemptTimes("1234567800000002", 1);
    UserService.updateLoginAttemptTimes("1234567800000003", 1);
    UserService.resetLoginTimes();
    List<User> users = UserRepository.index();
    for (User user: users) {
      assertEquals(user.getLoginAttemptTimes(), 0);
    }
  }

  @Test
  public void testIndex_GetAll_ReturnAllUsers() {
    List<User> users = UserRepository.index();
    int index = 0;
    for (User user: users) {
      index++;
      assertEquals(user.getUserId(), "123456780000000" + index);
    }
  }

  @Test
  public void testLogin_LoginWithIncorrectUserId_ReturnFalse() {
    String invalidUserId = "123456780000000";
    String password = "password";

    LoginData loginData = UserService.login(invalidUserId, password);
    assertEquals(loginData, new LoginData(false, 0, false));
  }

  @Test
  public void testLogin_LoginWithValidUserIdLoginAttemptTimeEquals3_ReturnFalse() {
    UserRepository.updateLoginAttemptTimes(validUserId, 3);

    LoginData loginData = UserService.login(validUserId, originalPassword);
    assertEquals(loginData, new LoginData(false, 3, true));
  }

  @Test
  public void testLogin_LoginWithValidUserIdFirstTimeWithIncorrectPassword_ReturnFalseAndIncreaseLoginAttemptTimesByOne() {
    String invalidPassword = "invalid1";

    LoginData loginData = UserService.login(validUserId, invalidPassword);
    assertEquals(
        loginData,
        new LoginData(false, 1, true)
    );
  }

  @Test
  public void testLogin_LoginWithValidUserIdFirstTime_ReturnTrue() {
    LoginData loginData = UserService.login(validUserId, originalPassword);
    assertEquals(
        loginData,
        new LoginData(true, 1, true)
    );
  }

  @Test
  public void testIsPasswordChanged_InvalidPassword_ReturnFalse() {
    String invalidPassword = "password";
    String newPassword = "pass2222";
    String confirmPassword = "pass2222";

    assertFalse(UserService.isPasswordChanged(
        validUserId,
        invalidPassword,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_InvalidNewPassword_ReturnFalse() {
    String password = "pass1111";
    String newPassword = "password";
    String confirmPassword = "password";

    assertFalse(UserService.isPasswordChanged(
        validUserId,
        password,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_InvalidConfirmPassword_ReturnFalse() {
    String password = "pass1111";
    String newPassword = "password";
    String confirmPassword = "password";

    assertFalse(UserService.isPasswordChanged(
        validUserId,
        password,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_PasswordAndConfirmPasswordNotMatch_ReturnFalse() {
    String password = "pass1111";
    String newPassword = "pass2222";
    String confirmPassword = "pass2221";

    assertFalse(UserService.isPasswordChanged(
        validUserId,
        password,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_UserIdNotFound_ReturnFalse() {
    String password = "pass1111";
    String newPassword = "pass2222";
    String confirmPassword = "pass2222";

    assertFalse(UserService.isPasswordChanged(
        "1234567800000000",
        password,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_PasswordIncorrect_ReturnFalse() {
    String incorrectPassword = "pass1112";
    String newPassword = "pass2222";
    String confirmPassword = "pass2222";

    assertFalse(UserService.isPasswordChanged(
        "1234567800000000",
        incorrectPassword,
        newPassword,
        confirmPassword
    ));
  }

  @Test
  public void testIsPasswordChanged_ValidAllPassword_ReturnTrue() {
    String newPassword = "pass2222";
    String confirmPassword = "pass2222";

    assertTrue(UserService.isPasswordChanged(
        validUserId,
        originalPassword,
        newPassword,
        confirmPassword
    ));

    User user = UserRepository.findByUserId(validUserId);
    assert user != null;
    assertEquals(user.getPassword(), newPassword);
  }
}
