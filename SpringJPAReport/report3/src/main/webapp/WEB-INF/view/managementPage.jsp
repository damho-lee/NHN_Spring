<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <div class="col">
        <div class="card shadow-sm">
            <div class="card-body">
                <p class="card-text">카테고리</p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button type="button" onclick="location.href='/category'" class="btn btn-sm btn-outline-secondary">조회</button>
                        <button type="button" onclick="location.href='/category/register'" class="btn btn-sm btn-outline-secondary">생성</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card shadow-sm">
            <div class="card-body">
                <p class="card-text">상품</p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button type="button" onclick="location.href='/product'" class="btn btn-sm btn-outline-secondary">조회</button>
                        <button type="button" onclick="location.href='/product/register'" class="btn btn-sm btn-outline-secondary">생성</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
