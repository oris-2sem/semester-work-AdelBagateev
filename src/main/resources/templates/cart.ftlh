<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Корзина</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="h-100" style="background-color: #eee;">
        <div class="container h-100 py-5">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-10">

                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h3 class="fw-normal mb-0 text-black">Корзина</h3>
                        <div>
                            <p class="mb-0"><span class="text-muted">Отсортировать по:</span> <a href="#!" class="text-body">цене <i
                                            class="fas fa-angle-down mt-1"></i></a></p>
                        </div>
                    </div>

                    <#list burgerOrders as burgerOrder>
                        <#if burgerOrder.getBurgers()?size != 0 >
                            <h4 class="fw-normal mb-3 text-black">Заказ #${burgerOrder.getId()}</h4>
                        </#if>

                        <#list burgerOrder.getBurgers() as burger>
                            <div class="card rounded-3 mb-4">
                                <div class="card-body p-4">
                                    <div class="row d-flex justify-content-between align-items-center">
<#--                                        <div class="col-md-2 col-lg-2 col-xl-2">-->
<#--                                            <img-->
<#--                                                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img1.webp"-->
<#--                                                    class="img-fluid rounded-3" alt="Cotton T-shirt">-->
<#--                                        </div>-->
                                        <div class="col-md-3 col-lg-3 col-xl-3">
                                            <p class="lead fw-normal mb-2">${burger.getName()}</p>
                                            <p><span class="text-muted">Автор: </span>${burger.getAuthor().firstName}</p>
                                        </div>

                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                            <h5 class="mb-0">${burger.getPrice()} &#8381;</h5>
                                        </div>
                                        <div style="margin-right: 10px" class="col-md-1 col-lg-1 col-xl-1 text-end">
                                            <form action="/cart/delete?burgerId=${burger.getId()}&orderId=${burgerOrder.getId()}" method="post">
                                                <button class="btn btn-danger">Удалить</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#list>

                    <#if burgerOrders?size != 0>
                        <div class="card">
                            <div class="card-body" style="display: flex; justify-content: space-between; align-items: center">
                                <form method="post" action="/cart/pay?price=${totalPrice}">
                                    <button type="submit" class="btn btn-warning btn-block btn-lg">Оформить заказ</button>
                                </form>
                                <h5 class="mb-1">${totalPrice} &#8381;</h5>
                                <h5 id="dollar" class="mb-1"></h5>
                            </div>
                        </div>
                    </#if>

                </div>
            </div>
        </div>
    </section>

    <script>
        var myHeaders = new Headers();
        myHeaders.append("apikey", "u4BM84SlVlLA27e1q3rwwnKEO45wQ7mM");

        const totalPrice = ${totalPrice};
        console.log(totalPrice);

        var requestOptions = {
            method: 'GET',
            redirect: 'follow',
            headers: myHeaders
        };

        if (totalPrice !== 0) {
            document.querySelector("#dollar").innerHTML = "Загрузка...";
            fetch("https://api.apilayer.com/currency_data/convert?to=USD&from=RUB&amount=" + totalPrice, requestOptions)
                .then(response => response.json())
                .then(result => {
                    document.querySelector("#dollar").innerHTML = result.result + " &#36;";
                })
                .catch(error => console.error('error', error));
        }



    </script>

</body>
</html>