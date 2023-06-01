<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Login</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="">
        <!-- Jumbotron -->
        <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%)">
            <div class="container">
                <div class="row gx-lg-5 align-items-center">
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <h1 class="my-5 display-3 fw-bold ls-tight">
                            Лучшее<br />
                            <span class="text-primary">только у нас</span>
                        </h1>
                        <p style="color: hsl(217, 10%, 50.8%)">
                            Войдите или зарегестрируйтесь, чтобы начать пользоваться услугами сайта.
                        </p>
                    </div>

                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="card">
                            <div class="card-body py-5 px-md-5">
                                <form method="post" action="/login">

                                    <!-- Email input -->
                                    <div class="form-outline mb-4">
                                        <input name="username" type="email" id="form3Example3" class="form-control" />
                                        <label class="form-label" for="form3Example3">Почта</label>
                                    </div>

                                    <!-- Password input -->
                                    <div class="form-outline mb-4">
                                        <input name="password" type="password" id="form3Example4" class="form-control" />
                                        <label class="form-label" for="form3Example4">Пароль</label>
                                    </div>

                                    <!-- Submit button -->
                                    <button type="submit" class="btn btn-primary btn-block mb-4">
                                        Войти
                                    </button>


                                    <div class="text-center">
                                        <p>или войти через:</p>
                                        <a href="/oauth2/authorization/google"><i class="fa-brands fa-google"></i></a>

                                    </div>

                                    <div>
                                        <div><a href="/register">Регистрация</a></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>