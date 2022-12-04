<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
            integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
            crossorigin="anonymous"></script>
    <title>MyGSet Portal Home</title>
</head>

<body>
<h1>MyGSet Portal Home</h1>

<hr>

<div class="d-flex flex-column align-items-center">
    <div class="d-flex flex-row justify-content-center">
        <img alt="Funix Logo" src="https://www.funix.edu.vn/wp-content/uploads/2017/10/logo.png">
    </div>

    <div class="d-flex flex-row justify-content-center" style="width: 75%">
        <div class="w-50 border border-dark rounded-1 m-3 p-3">
            <p class="text-center">Hello ${userId}, </p>

            <p class="text-center">You have logged in successfully, now you can use all feature of MyGSet portal</p>
        </div>

        <div class="w-50 m-3 p-3 border border-dark rounded-1">
            <p>Placeholder ...</p>
            <a href="/portal/login">Click here to logout ...</a>
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
