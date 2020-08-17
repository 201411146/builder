<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class=" bg-light" style="min-height: 80%;">
    <div class="container" style="max-width: 1920px;">
        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800 font-weight-bold">${sitename} <font class="font-italic font-weight-light text-primary text-sm text-blue-">BETA</font></h1>
            <a href="/allba/${sitename}/test" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i>이전버전보기</a>
        </div>

        <div class="row">                
            <div class="col-lg-12 mb-4">
                <div class="shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">오늘의 HOT! 소식</h6>
                    </div>
                    <div class="card-body">
                        <div class="text-center">
                            <c:forEach items="${list}" var="list">
                                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="height: 30rem;" src="${list.file}" alt="광고배너 이미지 영역입니다" />
                            </c:forEach>
                        </div>
                        <p>광고제목이 들어가는 영역입니다 (추가예정)</p>
                        <a target="_blank" rel="nofollow" href="#">해당 광고 자세히보기 (선택사항, 관리자가 광고링크를 등록시 해당 링크로 페이지 이동됨)</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-12 mb-4">
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-gray-800 font-weight-bold">어떤 알바가 등록되어있을까요?<font class="font-italic font-weight-light text-primary text-sm text-blue-"></font></h1>
            </div>
        </div>
        
        <div class="row">            
            <c:forEach items="${dto}" var="dto">
                <div class="col-md-3">
                    <div class="card mb-4 shadow-sm">
                        <img src="${dto.image}" class="bd-placeholder-img card-img-top" width="100%" height="300" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail">                        
                        <!-- <rect width="100%" height="100%" fill="#55595c" /> -->                        
                        <div class="card-body">                            
                            <div class="d-flex justify-content-between align-items-left">
                                <h2 class="text-primary">
                                    <a href="/allba/${sitename}/board/view?boardid=${dto.boardid}">
                                        ${dto.title}
                                    </a>
                                </h2>
                                <p class="ml-auto">
                                    <c:if test="${bi != NULL}">                                    
                                        <c:set var="loop_flag" value="false" />
                                            <c:forEach items="${bi}" var="bi">
                                                <c:if test="${bi == dto.boardid}">
                                                    <c:set var="loop_flag" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not loop_flag }">
                                                <a class="text-lg" href="/allba/${sitename}/board/regbookmark?boardid=${dto.boardid}"><i class="far fa-star"></i></a>
                                            </c:if>
                                            <c:if test="${loop_flag }">
                                                <a class="text-lg" href="/allba/${sitename}/bookmark/delete?boardid=${dto.boardid}"><i class="fas fa-star"></i></a>
                                            </c:if>
                                    </c:if>
                                    <c:if test="${bi == NULL}">
                                        <a class="text-lg" href="/allba/${sitename}/board/regbookmark?boardid=${dto.boardid}"><i class="far fa-star"></i></a>
                                    </c:if>
                                                                    
                                </p>                            
                            </div>                            
                            <p class="mt-2 card-text text-truncate" style=" white-space: nowrap;">
                                ${dto.content}
                            </p>                            
                            <div class="d-flex justify-content-between align-items-left">                                
                                <button type="button" class="ml-1 btn btn-sm btn-primary" disabled>지역 : ${dto.get("지역")}</button>
                                <button type="button" class="ml-1 btn btn-sm btn-secondary" disabled>시급 : ${dto.get("시급")}원</button>
                                <small class="ml-auto text-muted"> ${dto.regdate}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="d-sm-flex align-items-center justify-content-between mb-2 py-2">
            <h1 class="h3 mb-1 text-gray-800 font-weight-bold"><br/>내가 할 수 있는 일은 어떤게 있을까요?<font class="font-italic font-weight-light text-primary text-sm text-blue-"></font></h1>
        </div>


        <div class="col-lg-12 mb-4">
            <div class="shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">카테고리</h6>
                </div>
                <div class="card-body">
                    <div class="text-center">
                        <c:forEach var="category" items="${category}">
                            <div class="col-md-12">
                                <div class="card mb-4 shadow-sm">                                                      
                                    <div class="card-body">                            
                                        <div class="d-flex justify-content-between align-items-left">
                                            <h2 class="text-primary">
                                                ${category.categoryname}
                                            </h2>
                                            <p class="ml-auto">
                                                <a href="#"> 자세히보기</a>
                                                                                
                                            </p>                            
                                        </div>                            
                                        <p class="mt-2 card-text text-truncate" style=" white-space: nowrap;">
                                            
                                        </p>                            
                                        <div class="d-flex justify-content-between align-items-left">     
                                            <c:forEach var="detail" items="${detail}">
                                                <c:if test="${detail.parent  eq category.categoryname}">
                                                    <button type="button" class="ml-1 btn btn-sm btn-primary" disabled>
                                                    <a class="collapse-item text-white" href="/${c}/${sitename}/board?category=${category.categoryname}&value=${detail.categoryname}">${detail.categoryname}</a>
                                                </button>
                                                </c:if>
                                            </c:forEach>                             
                                            
                                            <small class="ml-auto text-muted"></small>
                                        </div>
                                    </div>
                                </div>
                            </div>                                       
                        </c:forEach>
                    </div>                    
                    <a target="_blank" rel="nofollow" href="#">카테고리별로 공고를 확인해보세요!</a>
                </div>
            </div>
        </div>



    </div>
</div>




            