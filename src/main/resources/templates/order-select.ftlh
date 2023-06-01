<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh" >
    <title>Сделать заказ</title>
</head>
<body>
    <#include "navbar.ftlh" >

    <section class="h-100 p-5" style="background-color: #eee;">
        <table class="table w-75 m-auto">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Цена</th>
        </tr>
        </thead>
        <tbody>
        <#list burgers as burger>
            <tr>
                <th scope="row">${burger.getId()}</th>
                <td><a href="/burger/${burger.getId()}">${burger.getName()}</a></td>
                <td>${burger.getPrice()} &#8381;</td>
            </tr>
        </#list>

        </tbody>
    </table>
    </section>
</body>
</html>