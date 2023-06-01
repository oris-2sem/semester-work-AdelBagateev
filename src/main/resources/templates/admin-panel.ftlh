<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Модерация</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="h-100" style="background-color: #eee; padding: 10px">

        <#list orders as order>
            <#assign type = "SELF" />
            <#list order.burgers as burger>
                <#if burger.type?? && burger.type == type>
                    <div class="card w-50 m-auto mb-lg-5">
                    <div class="card-header">${burger.name}</div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush mb-3">
                            <li class="list-group-item"><b>ID:</b> ${burger.id}</li>
                            <li class="list-group-item"><b>Дата размещения:</b> ${burger.createdAt}</li>
                            <li class="list-group-item"><b>ID автора:</b> ${burger.author.id}</li>
                        </ul>

                        <ul class="list-group">
                            <li class="list-group-item list-group-item-secondary">Ингредиенты</li>
                            <#list burger.ingredients as ingredient>
                                <li class="list-group-item">${ingredient.name}</li>
                            </#list>
                        </ul>

                    </div>
                    <div class="card-footer">
                        <form method="post" action="/moderation">
                            <button name="accepted" value="${burger.id}" class="btn btn-success" style="margin-right: 10px">Принять</button>
                            <button name="rejected" value="${burger.id}" class="btn btn-danger">Отклонить</button>
                            <input name="orderId" value="${order.id}" type="hidden" />
                        </form>
                    </div>
                </div>
                </#if>
            </#list>
        </#list>

    </section>
</body>
</html>