<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>
</head>
<div style="margin: auto; width: 100%;">
    <div>
        <a href="/product/register" class="btn btn-primary">상품 등록</a>
    </div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">상품 ID</th>
            <th scope="col">모델 넘버</th>
            <th scope="col">모델 이름</th>
            <th scope="col">상품 가격</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.modelNumber}</td>
                <td>${product.modelName}</td>
                <td>${product.unitCost}</td>
                <td>
                    <form method="post" action="/product/${product.productId}/delete">
                        <button class="btn btn-secondary" type="submit">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation example">
    <form>
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${end_page}" step="1">
                <li class="page-item"><a class="page-link" href="/product?page=${page-1}">${page}</a></li>
            </c:forEach>
        </ul>
    </form>
</nav>