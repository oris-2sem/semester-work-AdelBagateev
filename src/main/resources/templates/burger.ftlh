<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "bootstrap.ftlh">
    <title>${burger.getName()}</title>
</head>
<style>
    /*****************globals*************/
    body {
        overflow-x: hidden; }

    img {
        max-width: 100%; }


    @media screen and (max-width: 996px) {
        }
    .preview-thumbnail.nav-tabs li {
        width: 18%;
        margin-right: 2.5%; }
    .preview-thumbnail.nav-tabs li img {
        max-width: 100%;
        display: block; }
    .preview-thumbnail.nav-tabs li a {
        padding: 0;
        margin: 0; }
    .preview-thumbnail.nav-tabs li:last-of-type {
        margin-right: 0; }

    .tab-content {
        overflow: hidden; }
    .tab-content img {
        width: 100%;
        -webkit-animation-name: opacity;
        animation-name: opacity;
        -webkit-animation-duration: .3s;
        animation-duration: .3s; }

    .card {
        margin-top: 50px;
        background: #eee;
        padding: 3em;
        line-height: 1.5em; }

    @media screen and (min-width: 997px) {
        .wrapper {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex; } }

    .details {
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column; }


    .product-title, .price, .sizes, .colors {
        text-transform: UPPERCASE;
        font-weight: bold; }

    .checked {
        color: #ff9f1a; }

    .product-title, .rating, .product-description, .price, .vote, .sizes {
        margin-bottom: 15px; }

    .product-title {
        margin-top: 0; }


    .add-to-cart {
        background: #ff9f1a;
        padding: .5em .6em;
        border: none;
        color: #fff;
        -webkit-transition: background .3s ease;
        transition: background .3s ease; }
    .add-to-cart:hover, .like:hover {
        background: #b36800;
        color: #fff; }


    @-webkit-keyframes opacity {
        0% {
            opacity: 0;
            -webkit-transform: scale(3);
            transform: scale(3); }
        100% {
            opacity: 1;
            -webkit-transform: scale(1);
            transform: scale(1); } }

    @keyframes opacity {
        0% {
            opacity: 0;
            -webkit-transform: scale(3);
            transform: scale(3); }
        100% {
            opacity: 1;
            -webkit-transform: scale(1);
            transform: scale(1); } }
</style>
<body>
    <#include "navbar.ftlh">
    <div class="container">
        <div class="card">
            <div class="container-fliud">
                <div class="wrapper row">

                    <div class="details col-md-6">
                        <h3 class="product-title">${burger.getName()}</h3>
                        <div class="rating">
                            <div class="stars">
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                            <span class="review-no">41 reviews</span>
                        </div>
                        <#if burger.description??>
                            <p class="product-description">${burger.getDescription()}</p>
                        </#if>

                        <h5 class="mb-3">Ингредиенты</h5>
                        <ul class="list-group">
                            <#list burger.ingredients as ingredient>
                                <li class="list-group-item">${ingredient.name}</li>
                            </#list>
                        </ul>


                        <h4 class="price mt-3"><span>${burger.getPrice()} &#8381;</span></h4>

                        <div class="action">
                            <form method="post" action="/order/select/${burger.id}">
                                <button class="add-to-cart btn btn-primary" type="submit">Добавить в корзину</button>
                            </form>
                        </div>


<#--                        <#if burger.comments??>-->
<#--                            <ul>-->
<#--                                <#list burger.comments as comment>-->
<#--                                    <li>${comment.text}</li>-->
<#--                                </#list>-->
<#--                            </ul>-->
<#--                        </#if>-->

                    </div>
                </div>
            </div>
        </div>
    </div>
    <section style="background-color: #eee;">
        <div class="container my-5 py-5">
            <div class="row d-flex justify-content-center">
                <div class="col-md-12 col-lg-10 col-xl-8">
                    <div class="card">

                        <#if burger.comments??>
                            <#list burger.comments as comment>
                                <div class="card-body">
                                    <div class="d-flex flex-start align-items-center">

                                        <div>
                                            <h6 class="fw-bold text-primary mb-1">${comment.author.firstName}</h6>
                                            <p class="text-muted small mb-0">
                                                Shared publicly - ${comment.createdAt}
                                            </p>
                                        </div>
                                    </div>

                                    <p class="mt-3 mb-4 pb-2">
                                        ${comment.text}
                                    </p>

                                </div>
                            </#list>
                        </#if>


                        <div class="card-footer py-3 border-0" style="background-color: #f8f9fa;">
                            <form method="post" action="/comment">
                                <div class="d-flex flex-start w-100">

                                    <div class="form-outline w-100">

                                    <textarea name="text" class="form-control" id="textAreaExample" rows="4" style="background: #fff;">

                                    </textarea>

                                    <label class="form-label" for="textAreaExample">Комментарий</label>
                                    </div>
                                </div>
                                <div class="float-end mt-2 pt-1">
                                    <button name="burgerId" value="${burger.id}" type="submit" class="btn btn-primary btn-sm">Запостить</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>