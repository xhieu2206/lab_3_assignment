<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

            <p class="text-center">If you have forgotten your password, <a href="/portal/index-users">Click here</a></p>
        </div>

        <div class="w-50 m-3 p-3 border border-dark rounded-1">
            <form
                    class="login-form"
                    action="${pageContext.request.contextPath}/portal/login"
                    method="POST"
            >
                <h3>MEMBER LOGIN</h3>
                <div class="mb-3">
                    <label class="form-label">User ID:</label>
                    <input id="field_1" class="form-control" type="text" name="userId" maxlength="16" minlength="16" autocomplete="do-not-autofill"/>
                    <div class="form-text text-danger">(Max 16 characters)</div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password:</label>
                    <input id="field_2" class="form-control" type="password" name="password" maxlength="8" minlength="8" autocomplete="do-not-autofill"/>
                    <div class="form-text text-danger">(Max 8 characters)</div>
                </div>

                <div class="d-flex justify-content-end">
                    <button id="submit-button" type="submit" class="btn btn-primary mb-3 me-3" disabled>
                        Submit
                    </button>
                    <button type="reset" id="clear-button" type="reset" class="btn btn-secondary mb-3" disabled>
                        Clear
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    let inputs = document.querySelectorAll('input');
    let buttonSend = document.getElementById('submit-button');
    let buttonClear = document.getElementById('clear-button');

    let inputValidator = {
        "userId": false,
        "password": false
    }

    inputs.forEach((input) => {
        input.addEventListener('input', () => {
            let name = event.target.getAttribute('name');
            if (event.target.value.length > 0) {
                inputValidator[name] = true;
            } else {
                inputValidator[name] = false;
            }

            let allTrue = Object.keys(inputValidator).every((item) => {
                return inputValidator[item] === true
            });

            buttonSend.disabled = !allTrue;
            buttonClear.disabled = !allTrue;
        })
    })
</script>
</html>
