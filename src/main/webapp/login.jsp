<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>

<html>
<head>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
            integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
            crossorigin="anonymous"></script>
    <title>MyGSet Login</title>
</head>

<%
    // Get data from cookies
    Cookie[] theCookies = request.getCookies();
    int loginAttemptTimesFromServer;
    try {
        loginAttemptTimesFromServer = (int)request.getAttribute("loginAttemptTimesFromServer");
    } catch(Exception e) {
      e.printStackTrace();
        loginAttemptTimesFromServer = 0;
    }

    int loginAttemptTimes = 0;

    if (theCookies != null) {
        boolean foundCookie = false;
        for (Cookie temp : theCookies) {
            if ("myApp.loginAttemptTimes".equals(temp.getName())) {
                foundCookie = true;
                loginAttemptTimes = Integer.parseInt(URLDecoder.decode(temp.getValue(), "UTF-8"));
            }
        }

        if (!foundCookie) {
            Cookie theCookie = new Cookie("myApp.loginAttemptTimes", "0");
            theCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(theCookie);
        }

        if (loginAttemptTimesFromServer > 0) {
            Cookie theCookie = new Cookie("myApp.loginAttemptTimes", String.valueOf(loginAttemptTimesFromServer));
            theCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(theCookie);
            loginAttemptTimes = loginAttemptTimesFromServer;
        }
    }
%>

<body>
<h1>Login to MyGSet Portal</h1>

<hr>

<div class="d-flex flex-column align-items-center">
    <div class="d-flex flex-row justify-content-center">
        <img alt="Funix Logo" src="https://www.funix.edu.vn/wp-content/uploads/2017/10/logo.png">
    </div>

    <div class="d-flex flex-row justify-content-center" style="width: 75%">
        <div class="w-50 border border-dark rounded-1 m-3 p-3">
            <p class="text-center">Enter your Single Sign-On User ID and password to login</p>

            <c:if test="${errorMessage != null}">
                <p class="text-center text-danger">${errorMessage}</p>
            </c:if>

            <p class="text-center">If you have forgotten your password, <a href="#">Click here</a></p>
        </div>

        <div class="w-50 m-3 p-3 border border-dark rounded-1">
            <form
                    class="login-form"
                    action="${pageContext.request.contextPath}/portal/login"
                    method="POST"
            >
                <input name="loginAttemptTimes" type="hidden" value="<%= loginAttemptTimes %>"/>

                <h3>MEMBER LOGIN</h3>
                <div class="mb-3">
                    <label class="form-label">User ID:</label>
                    <input class="form-control" type="text" name="userId" maxlength="16"/>
                    <div class="form-text text-danger">(Max 16 characters)</div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password:</label>
                    <input class="form-control" type="password" name="password" maxlength="16"/>
                    <div class="form-text text-danger">(Max 8 characters)</div>
                </div>

                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary mb-3 me-3">Submit</button>
                    <button type="reset" class="btn btn-secondary mb-3">Clear</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
