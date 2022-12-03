package com.hieunguyen.services;

import com.hieunguyen.models.LoginData;
import com.hieunguyen.models.User;
import com.hieunguyen.repository.UserRepository;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Objects;

public class UserService {
	private static final User[] users = new User[3];

	public static boolean isPasswordValid(String password) {
		if (password.length() != 8) {
			return false;
		}
		boolean isDigitOccurrence = false;
		boolean isWordCharOccurrence = false;
		boolean isValid = true;
		for (int i = 0; i < password.length(); i ++) {
			if ((
				(int) password.charAt(i) >= 65
				&& (int) password.charAt(i) <= 90
			) || (
				(int) password.charAt(i) >= 97
				&& (int) password.charAt(i) <= 122
			)) {
				isWordCharOccurrence = true;
			} else if (
				(int) password.charAt(i) >= 48
				&& (int) password.charAt(i) <= 57
			) {
				isDigitOccurrence = true;
			} else {
				isValid = false;
			}
		}

		return isDigitOccurrence && isValid && isWordCharOccurrence;
	}

	public static boolean isUserIdValid(String userId) {
		if (userId.length() != 16) {
			return false;
		}
		boolean isValid = true;
		for (int i = 0; i < userId.length(); i++) {
			if (
				(int) userId.charAt(i) < 47 ||
				(int) userId.charAt(i) > 57
			) {
				isValid = false;
			}
		}
		return isValid;
	}

	public static User findUserById(String userId) throws IOException {
		return UserRepository.findByUserId(userId);
	}

	public static boolean isValidUser(
			User user,
			String userId,
			String password
	) {
		return (
			Objects.equals(user.getUserId(), userId) &&
			Objects.equals(user.getPassword(), password)
		);
	}

	public static LoginData login(
		String userId,
		String password
	) throws IOException {
		User user = UserService.findUserById(userId);
		if (user == null) {
			return new LoginData(
				false,
				0,
				false
			);
		}
		if (UserService.isValidUser(user, userId, password)) {
			return new LoginData(
				true,
				user.getLoginAttempTimes() + 1,
				true
			);
		}
		return new LoginData(
			false,
			user.getLoginAttempTimes() + 1,
			true
		);
	}
}
