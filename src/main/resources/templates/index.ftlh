<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Document</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="h-100 p-5" style="background-color: #eee;">
        <div id="carouselExampleRide" class="carousel slide m-auto" data-bs-ride="true" style="width: 65%">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/b1.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block fw-bold">
                    <h3>Такого вы еще не видели</h3>
                    <p style="font-size: 1.2rem"> У нас и только у нас вы сможете собрать свой собственный бургер онлайн</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/b2.webp" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block fw-bold">
                    <h3>Как это работает ?</h3>
                    <p style="font-size: 1.2rem">Просто соберите бургер по ингредиентам, доступным в нашей базе</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/b3.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block fw-bold">
                    <h3>Заказать бургер теперь можно по почте</h3>
                    <p style="font-size: 1.2rem">
                        Еще одна особенность нашего ресторана это возможность заказать бургер через почту.
                        Просто напишите сообщение на почту с заголовком 'BURGER' и укажите в теле ингредиенты.
                    </p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    </section>
</body>
</html>