<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>Собрать самому</title>
</head>
<body>
    <#include "navbar.ftlh">

    <section class="h-100 p-5" style="background-color: #eee;">
        <div class="w-50 m-auto">
        <form method="post" action="/order/self">

            <div class="input-group mb-3">
                <label class="input-group-text" for="wrapper-select">Враппер</label>

                <select name="wrapper" class="form-select" id="wrapper-select">
                    <#list wrappers as wrapper>
                        <option value="${wrapper.getId()}" name="${wrapper.getPrice()}">${wrapper.getName()}</option>
                    </#list>
                </select>

            </div>

            <div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="protein-select">Протеин</label>

                    <select name="protein" class="form-select" id="protein-select">
                        <#list proteins as protein>
                            <option value="${protein.getId()}" name="${protein.getPrice()}">${protein.getName()}</option>
                        </#list>
                    </select>

                    <button name="protein-plus" type="button" class="btn btn-outline-primary plus-button">+</button>
                </div>
            </div>

            <div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="protein-select">Сыр</label>

                    <select name="cheese" class="form-select" id="cheese-select">
                        <#list cheeses as cheese>
                            <option value="${cheese.getId()}" name="${cheese.getPrice()}">${cheese.getName()}</option>
                        </#list>
                    </select>

                    <button name="cheese-plus" type="button" class="btn btn-outline-primary plus-button">+</button>
                </div>
            </div>

            <div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="sauce-select">Соусы</label>

                    <select name="sauce" class="form-select" id="sauce-select">
                        <#list sauces as sauce>
                            <option value="${sauce.getId()}" name="${sauce.getPrice()}">${sauce.getName()}</option>
                        </#list>
                    </select>

                    <button name="sauce-plus" type="button" class="btn btn-outline-primary plus-button">+</button>
                </div>
            </div>


            <div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="veggies-select">Овощи</label>

                    <select name="veggies" class="form-select" id="veggies-select">
                        <#list veggies as veggy>
                            <option value="${veggy.getId()}" name="${veggy.getPrice()}">${veggy.getName()}</option>
                        </#list>
                    </select>

                    <button name="veggies-plus" type="button" class="btn btn-outline-primary plus-button">+</button>
                </div>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Дайте название своему шедевру</span>
                </div>
                <input name="burgerName" type="text" class="form-control">
            </div>

            <button type="submit" class="btn btn-primary mb-3">Добавить в корзину</button>


            <div>
                <div class="input-group-prepend d-inline-block">
                    <span class="input-group-text">Сумма заказа</span>
                </div>
                <input name="total" readonly type="number" class="order-sum d-inline-block">
                <div class="input-group-append d-inline-block">
                    <span class="input-group-text">&#8381;</span>
                </div>
            </div>

        </form>
    </div>
    </section>

    <script>

        let orderSumElement = document.querySelector(".order-sum");

        function calculateTotalSum() {
            let orderSum = 0;

            document.querySelectorAll(".form-select").forEach(element => {
                const price = +element.options[element.selectedIndex].getAttribute("name");
                orderSum += price;

                orderSumElement.value = orderSum;
            });
        }

        document.addEventListener("DOMContentLoaded", function() {
            calculateTotalSum();
        });

        document.querySelectorAll(".form-select").forEach(element => {
            element.addEventListener("change", function() {
               calculateTotalSum();
            });
        })

        const plusButtons = document.querySelectorAll(".plus-button");
        plusButtons.forEach(button => {
            button.addEventListener("click", function (e) {

                const parentNode = this.parentNode;

                if (parentNode.parentNode.childNodes.length < 5) {

                    const clonedElement = parentNode.cloneNode(true);
                    clonedElement.childNodes.forEach(node => {
                        if (node.nodeName === "BUTTON") {
                            node.remove();
                        }
                    })

                    clonedElement.addEventListener("change", function() {
                        calculateTotalSum();
                    });
                    parentNode.parentNode.append(clonedElement);

                    calculateTotalSum();
                }

            });
        });

    </script>
</body>
</html>