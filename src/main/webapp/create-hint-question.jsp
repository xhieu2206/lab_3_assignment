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
    <title>MyGSet Set hint question</title>
</head>

<body>
<h1>Set hint questions</h1>
<hr>

<div class="d-flex flex-column align-items-center">
    <div class="d-flex flex-row justify-content-center">
        <img alt="Funix Logo" src="https://www.funix.edu.vn/wp-content/uploads/2017/10/logo.png">
    </div>

    <div class="d-flex flex-row justify-content-center" style="width: 75%">
        <div class="w-25 border border-dark rounded-1 m-3 p-3">
            <img
                    class="img-thumbnail"
                    src="https://www.91-cdn.com/hub/wp-content/uploads/2022/03/Best-keyboards-for-typing-feature-image.jpg"
                    alt="keyboard"
            />
        </div>

        <div class="w-50 m-3 p-3 border border-dark rounded-1">
            <form
                    class="login-form"
                    method="POST"
            >
                <input type="hidden" name="userId" value="${userId}"/>
                <p>Please answer at least one (1) of the hint question below, maximum selection is 3 questions</p>

                <div class="row mb-2">
                    <div class="col-6 pe-2">
                        <select class="form-select question" name="first_question">
                            <option selected>Open this select menu</option>
                            <option>What is your mom name?</option>
                            <option>What is your dad name?</option>
                            <option>What is your sister name?</option>
                        </select>
                    </div>
                    <div class="col-6">
                        <input class="form-control answer" type="text" name="first_answer"/>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6 pe-2">
                        <select class="form-select question" name="second_question">
                            <option selected>Open this select menu</option>
                            <option>What is your first school name?</option>
                            <option>What is your first teacher's name?</option>
                            <option>What is your pet name?</option>
                        </select>
                    </div>
                    <div class="col-6">
                        <input class="form-control answer" type="text" name="second_answer"/>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6 pe-2">
                        <select class="form-select question" name="third_question">
                            <option selected>Open this select menu</option>
                            <option>What is your favorite program language?</option>
                            <option>What is your favorite program library?</option>
                            <option>What is your favorite program framework?</option>
                        </select>
                    </div>
                    <div class="col-6">
                        <input class="form-control answer" type="text" name="third_answer"/>
                    </div>
                </div>

                <p>Please key in your old password and new password. The new password must be difference from the old
                    password/p>

                <div class="row mb-2">
                    <div class="col-6 pe-2 text-end">
                        <label class="form-label">Old password</label>
                    </div>
                    <div class="col-6">
                        <input class="form-control password_input" type="password" name="password"/>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6 pe-2 text-end">
                        <label class="form-label">New password</label>
                    </div>
                    <div class="col-6">
                        <input class="form-control password_input" type="password" name="new_password"/>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6 pe-2 text-end">
                        <label class="form-label">Confirm Password</label>
                    </div>
                    <div class="col-6">
                        <input class="form-control password_input" type="password" name="confirm_password"/>
                    </div>
                </div>

                <div class="d-flex justify-content-end">
                    <button id="submit-button" type="submit" class="btn btn-primary mb-3 me-3" disabled>
                        Submit
                    </button>
                    <button type="reset" id="clear-button" type="reset" class="btn btn-secondary mb-3 me-3" disabled>
                        Clear
                    </button>
                    <a
                        href="/portal/login"
                        class="btn btn-danger mb-3" disabled
                    >
                        Cancel
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    let inputs = document.querySelectorAll('.password_input');
    let buttonSend = document.getElementById('submit-button');
    let buttonClear = document.getElementById('clear-button');

    let inputValidator = {
        "password": false,
        "new_password": false,
        "confirm_password": false,
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
