<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Prices</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="h-100 p-5" style="background-color: #eee;">
        <table class="table w-75 m-auto">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Название</th>
                <th scope="col">Тип</th>
                <th scope="col">Цена</th>
            </tr>
        </thead>
        <tbody>
            <#list ingredients as ingredient>
                <tr>
                    <th scope="row">${ingredient.getId()}</th>
                    <td>${ingredient.getName()}</td>
                    <td>${ingredient.getType()}</td>
                    <td>${ingredient.getPrice()} &#8381;</td>
                </tr>
            </#list>

        </tbody>
    </table>
    </section>
</body>
</html>