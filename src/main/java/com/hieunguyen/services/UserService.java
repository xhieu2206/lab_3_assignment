package com.hieunguyen.services;

import com.hieunguyen.models.User;

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

	public static boolean isValidUser(String userId, String password) {
		UserService.users[0] = new User("12345678900000000", "abcd1234");
		UserService.users[1] = new User("12345678900000001", "ABCD1234");
		UserService.users[2] = new User("12345678900000002", "abcdefgh1");
		boolean isValid = false;
		for (User tempUser: UserService.users) {
			if (Objects.equals(tempUser.getUserId(), userId) &&
					Objects.equals(tempUser.getPassword(), password))
			{
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static User getUserData(ServletContext context) throws IOException {
		String filename = "/WEB-INF/user.txt";
		InputStream inputStream = context.getResourceAsStream(filename);;
		try {
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(inputStreamReader);
				String text;

				while ((text = reader.readLine()) != null) {
					System.out.println("text: " + text);
				}
			}
		} catch (Exception error) {
			error.printStackTrace();
			inputStream.close();
		}

		return new User("userId", "password");
	}
}
