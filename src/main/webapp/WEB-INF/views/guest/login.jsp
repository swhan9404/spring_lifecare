<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/setting/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${path_resources_customcss}login.css">
<meta charset="UTF-8">
<!-- Site Icons -->
<link rel="shortcut icon" href="${path_resources_lifecare}images/fevicon.ico.png" type="image/x-icon" />
<link rel="apple-touch-icon" href="${path_resources_lifecare}images/apple-touch-icon.png">
<!-- Site Icons -->
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="divclass1" align="center">
		<form action="${path}/login_check" method="post" name="login">
			<input type ="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<div class="loginform">
				<div class="titles">로그인</div>
				<h1 id="msg">Welcome Life Care</h1>
				<input type="text" name="userid" class="idbox" placeholder="아이디"><br>
				<input type="password" name="passwd" class="pwdbox" placeholder="패스워드"><br>
				<input type="checkbox" name="idsave" checked=""> 아이디 저장
				<input type="checkbox" name="idsave" checked=""> 자동 로그인
				<ul class="idpwdfind">
					<li>&nbsp;&nbsp;<a href="">아이디 찾기</a>&nbsp; | <a href="">비밀번호찾기</a></li>
				</ul>
				<input type="submit" class="btnclass1" value="로그인" style="cursor: pointer"> 
				<input type="button" class="btnclass2" value="회원가입" style="cursor: pointer">
				<div class="socialLogin">
					<a href="https://nid.naver.com/oauth2.0/authorize?client_id={g_0JHC9Ov3W6TK_HKZHh}&response_type=code&redirect_uri={http://localhost/lifecare/callback/ex}&state={nid/getUserProfile}">
						<img src="${path_resources}img/btn_naver.png" border="0" title="네이버 아이디로 로그인" width="185" height="40" style="cursor: pointer">
					</a> 
					<a>
						<img src="${path_resources}img/btn_kakao.png" alt="" style="cursor: pointer">
					</a>
				</div>
			</div>
		</form>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>