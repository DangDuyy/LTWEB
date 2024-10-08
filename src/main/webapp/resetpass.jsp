<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <!-- Font Awesome Icons  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
          integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
          crossorigin="anonymous" />
    <!-- Google Fonts  -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background-color: #ff99f5;
            background-image:
                    radial-gradient(at 61% 4%, hsla(303, 91%, 61%, 1) 0px, transparent 50%),
                    radial-gradient(at 75% 66%, hsla(196, 91%, 79%, 1) 0px, transparent 50%),
                    radial-gradient(at 98% 88%, hsla(76, 87%, 78%, 1) 0px, transparent 50%),
                    radial-gradient(at 23% 16%, hsla(238, 96%, 77%, 1) 0px, transparent 50%),
                    radial-gradient(at 95% 65%, hsla(13, 91%, 75%, 1) 0px, transparent 50%),
                    radial-gradient(at 10% 79%, hsla(228, 96%, 69%, 1) 0px, transparent 50%),
                    radial-gradient(at 85% 58%, hsla(328, 81%, 68%, 1) 0px, transparent 50%);
            background-repeat: no-repeat;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .card {
            backdrop-filter: blur(16px) saturate(180%);
            -webkit-backdrop-filter: blur(16px) saturate(180%);
            background-color: rgba(0, 0, 0, 0.75);
            border-radius: 12px;
            border: 1px solid rgba(255, 255, 255, 0.125);
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 30px 40px;
            width: 300px;
        }

        .lock-icon {
            font-size: 3rem;
        }

        h2 {
            font-size: 1.5rem;
            margin-top: 10px;
            text-transform: uppercase;
        }

        p {
            font-size: 12px;
        }

        .passInput {
            margin-top: 15px;
            width: 100%;
            background: transparent;
            border: none;
            border-bottom: 2px solid deepskyblue;
            font-size: 15px;
            color: white;
            outline: none;
        }

        button {
            margin-top: 15px;
            width: 100%;
            background-color: deepskyblue;
            color: white;
            padding: 10px;
            text-transform: uppercase;
            border: none;
            cursor: pointer;
        }

        button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="card">
    <p class="lock-icon"><i class="fas fa-lock"></i></p>
    <h2>Reset Password</h2>
    <form action="resetpass" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" class="passInput" name="email" required><br>
        <label for="psw">New Password:</label>
        <input type="password" id="psw" class="passInput" name="psw" required><br>
        <label for="repeat-psw">Confirm Password:</label>
        <input type="password" id="repeat-psw" class="passInput" name="repeat-psw" required><br>
        <button type="submit">Reset Password</button>
    </form>
    <c:if test="${not empty alert}">
        <p style="color:red">${alert}</p>
    </c:if>
</div>
</body>
</html>