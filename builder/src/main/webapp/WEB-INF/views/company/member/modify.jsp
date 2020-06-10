<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 <title>회원정보 수정</title> 
</head>

<style>
body {
    font-family: "Lato", sans-serif;
}

.main-head{
    height: 150px;
    background: #333;
   
}

.sidenav {
    height: 100%;
    background-color: #333;
    overflow-x: hidden;
    padding-top: 20px;
}


.main {
    padding: 0px 10px;
}

@media screen and (max-height: 450px) {
    .sidenav {padding-top: 15px;}
}

@media screen and (max-width: 450px) {
    .login-form{
        margin-top: 10%;
    }
}

@media screen and (min-width: 768px){
    .main{
        margin-left: 20%; 
    }

    .sidenav{
        width: 20%;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
    }

    .register-form{
        margin-top: 50%;
    }
}


.register-main-text{
    margin-top: 20%;
    padding: 60px;
    color: #fff;
}

.register-main-text h2{
    font-weight: 300;
}

.btn-black{
    background-color: #333 !important;
    color: #fff;
    }

</style>
<body>
<h1 style = "text-align : center"><a href="/"> <img src="../../resources/logo/logo.jpg/"/></a></h1>
 
 <div class="sidenav">
 
         <div class="register-main-text">
            <h2>3LEE1CHAE<br> 회원정보수정 </h2>
            <p> M O D I F Y</p>
         </div>
      </div>
      
      
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="register-form">
               <form method="post">
                  <div class="form-group">
                     <label for="userid">아이디</label>
                     <input type="text" class="form-control" placeholder="아이디" id = "userid" name = "userid" value="${mo.userid}">
                  </div>
                  <div class="form-group">
                     <label>비밀번호</label>
                     <input type="password" class="form-control" placeholder="비밀번호" id = "userpass" name = "userpass" value="${mo.userpass}">
                  </div>
                  <input type="hidden" id="userno" name="userno" value="${mo.userno}"/>

                  <button type="submit" class="btn btn-black" id = "submit">수정</button>
                  
               </form>
               
            </div>
         </div>
      </div>
      
</body>
</html>

<%-- <body>
회원정보수정
<form method="post">
 <p>
  <label for="userId">아이디</label>
  <input type="text" id="userid" name="userid" value="${mo.userid}"/>
 </p>  
 <p>
  <label for="userPass">패스워드</label>
  <input type="password" id="userpass" name="userpass" value="${mo.userpass}"/>
 </p>
<input type="hidden" id="userno" name="userno" value="${mo.userno}"/>
 <p>
   <button type="submit" id="submit" >수정</button>  
 </p>
 </form>
 <p>
  <a href="/">메인 화면</a>
 </p>
 
</body>
</html> --%>
