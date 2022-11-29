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

    if (theCookies != null) {
        for (Cookie temp : theCookies) {
            if ("myApp.loginAttemptTimes".equals(temp.getName())) {
                temp.setValue("0");
            }
        }
    }
%>

<body>
<h1>Set hint questions</h1>

<hr>

<div class="d-flex flex-column align-items-center">
    <div class="d-flex flex-row justify-content-center">
        <img alt="Funix Logo" src="https://www.funix.edu.vn/wp-content/uploads/2017/10/logo.png">
    </div>

    <div class="d-flex flex-row justify-content-center" style="width: 75%">
        <div class="w-50 border border-dark rounded-1 m-3 p-3">
            <img src="https://www.91-cdn.com/hub/wp-content/uploads/2022/03/Best-keyboards-for-typing-feature-image.jpg" alt="keyboard" />
        </div>

        <div class="w-50 m-3 p-3 border border-dark rounded-1">
            <form
                    class="login-form"
                    method="POST"
            >

            </form>
        </div>
    </div>
</div>
</body>
</html>
