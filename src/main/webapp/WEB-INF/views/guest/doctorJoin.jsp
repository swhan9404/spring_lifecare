<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
 .title {
	  display: inline-block;
	  width: 100%;
	  text-align: center;
	  font-weight: 500;
	  color: #1a1a1a;
	  font-size: 34px;
	  line-height: 1;
	  margin-bottom: 30px;
 }
 
  .djoinIn_area {
    width: 900px;
    margin: 0px auto 50px;
    text-align: center;
    padding: 50px 170px;
    border: 1px solid #ebebeb;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
  }
  


  .djoinIn_area p {
    color: #424242;
    font-size: 30px;
    line-height: 1;
    margin-bottom: 30px;
   }
  
  .djoinIn_area input {
    display: inline-block;
    box-sizing: border-box;
    border: 1px solid #d9d9d9;
    background: #fff;
    width: 100%;
    height: 45px;
    padding: 10px;
    color: #858585;
    font-size: 14px;
    line-height: 1;
    font-weight: 200;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
 }
 .mb10 {
    margin-bottom: 10px !important;
    margin-top: 10px;
    
  }
  .press {
     text-align: center;
  }
  .btn-type.btn2.blue  {
    color: blue !important;
    border: 1px #2e3033;
    background: #009DFF;
  }
  
  .btn-type.btn2.blue {
    color: #fff !important;
    border: 1px  #2e3033;
    background: #009DFF;
  }
  .btn-type.btn2 {
    width: 15%;
    height: 45px;
    line-height: 40px;
    font-size: 18px;
    text-transform: uppercase;
    font-family: 'NotoSansKR';
    font-style: normal;
    font-weight: 400;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
  }
  .btn-type {
    display: inline-block;
    position: relative;
    cursor: pointer;
    transition: all 0.3s;
  }
  .mr_2 {
    margin-right: 2% !important;
  }
  .id {
    width:300px;
     padding:5px;
     border:1px solid #999;
     font-family:'Nanumgothic';
     border-radius:3px;
     -webkit-appearance: none;
     -moz-appearance: none;
     appearance : none; 
  }
 
</style>

<title>의사 회원가입</title>
</head>
<%@include file="../common/header.jsp" %>
<body>
	<form action="" method="post" name="joinInform" onsubmit="">
	    <div class="title">의사 회원가입</div>
        <div class="djoinIn_area">
          <p>WELCOME TO LIFECARE</p>
               <input type="text" name="doctor_id"  id="" placeholder="아이디" class="mb10" value="" required>
               <div class="idChk">
                ajax화면 
               </div>    
               <input type="password" name="doctor_pw" id="" placeholder="비밀번호" class="mb10" value="" required> 
               <input type="password" name="doctor_rePw" id="" placeholder="비밀번호확인" class="mb10" value="" required>       
               <input type="text" name="docotr_name" id="" placeholder="이름" class="mb10" value="" required> 
               <input type="text" name="doctor_num" id="" placeholder="면허번호를 입력해주세요." class="mb10" value="" required>     
               <input type="text" name="doctor_phone" id="" placeholder="휴대폰 번호" class="mb10" value="" required>        
               <input type="text" name="doctor_email" id="" placeholder="이메일 주소" class="mb10" value="" required >
                                프로필 사진을 올려주세요.           
               <input type="file" name="doctor_faceimg" id="img" placeholder="">                                   
         </div>
          
        <div class="press">
              <button type="submit" class="btn-type btn2 blue mr_2">승인 요청</button>
              <button type="reset" class="btn-type btn2 blue">취소</button>
          </div>
    </form>
</body>
<%@include file="../common/footer.jsp" %>
</html>