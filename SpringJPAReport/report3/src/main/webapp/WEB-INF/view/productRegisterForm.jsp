<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>

</head>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" name="product" action="/product/register" >

            <h1 class="h3 mb-3 fw-normal">상품 등록</h1>

            <div class="form-floating">
                <select name="category_id1" class="form-control">
                    <c:forEach var="category" items="${categories}" varStatus="status">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-floating">
                <input type="text" name="modelNumber" class="form-control" id="model_number" placeholder="모델 넘버" required>
                <label for="model_number">모델 넘버</label>
            </div>

            <div class="form-floating">
                <input type="text" name="modelName" class="form-control" id="model_name" placeholder="모델 이름" required>
                <label for="model_name">모델 이름</label>
            </div>

            <div class="form-floating">
                <input type="text" name="unitCost" class="form-control" id="unit_cost" placeholder="상품 가격" required>
                <label for="unit_cost">상품 가격</label>
            </div>

            <div class="form-floating">
                <input type="text" name="quantity" class="form-control" id="quantity" placeholder="수량" required>
                <label for="quantity">수량</label>
            </div>

            <div class="form-floating">
                <input type="text" name="description" class="form-control" id="description" placeholder="상품 설명">
                <label for="description">상품 설명</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Register</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>
        </form>
    </div>
</div>
