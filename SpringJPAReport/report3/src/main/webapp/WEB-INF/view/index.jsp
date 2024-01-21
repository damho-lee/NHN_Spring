<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>

</head>
<body>

<div class="mainContainer">
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="/management" class="nav-link px-2 text-white">관리</a></li>
                </ul>

                <form class="d-flex mb-3 mb-lg-0" method="get" action="/index.do">
                    <input name="keyword" type="search" class="form-control form-control-dark me-2" placeholder="Search" aria-label="Search" style="flex: 1;">
                    <button type="submit" class="btn btn-outline-light me-2">검색</button>
                </form>
            </div>
        </div>
    </header>

    <main>
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        카테고리 선택
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <li><a class="dropdown-item" href="/${category.categoryId}">${category.categoryName}</a></li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <c:forEach var="product" items="${products}">
                        <div class="col">
                            <div class="card shadow-sm">
                                <c:choose>
                                    <c:when test="${empty product.productImage}">
                                        <img src="/resources/no-image.png" width="100%" height="225" class="bd-placeholder-img card-img-top"/>
                                    </c:when>
                                    <c:otherwise>
<%--                                        <img src="/resources/image/${product.productImage}" width="100%" height="225" class="bd-placeholder-img card-img-top"/>--%>
                                        이미지 : <span>${product.productImage}</span>
                                    </c:otherwise>
                                </c:choose>
                                <div class="card-body">
                                    <p class="card-text">${product.modelName}</p>
                                    <fmt:setLocale value="ko_KR"/><fmt:formatNumber value="${product.unitCost}" type="currency"/>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='/productView.do?product_id=${product.productId}'">View</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <nav aria-label="Page navigation example">
                    <form>
                        <ul class="pagination justify-content-center">
                            <c:forEach var="page" begin="1" end="${end_page}" step="1">
                                <li class="page-item"><a class="page-link" href="/?page=${page-1}&size=5">${page}</a></li>
                            </c:forEach>
                        </ul>
                    </form>
                </nav>
            </div>
        </div>
    </main>

    <footer class="text-muted py-5">
        <div class="container">
            <p class="float-end mb-1">
                <a href="#">Back to top</a>
            </p>
            <p class="mb-1">shoppingmall example is © nhnacademy.com</p>
        </div>
    </footer>

</div>

</body>
</html>