<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh" >
    <title>Ошибочка</title>
</head>
<body>
    <#include "navbar.ftlh">
    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="text-center">
            <h1 class="display-1 fw-bold">Опаньки</h1>
            <p class="fs-3"> <span class="text-danger">Что-то пошло не так</span> Но мы уже этим занимаемся.</p>
            <p class="lead">
                (шутка)
            </p>
            <a href="/home" class="btn btn-primary">На главную</a>
        </div>
    </div>
</body>
</html>