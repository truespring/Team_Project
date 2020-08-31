<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        .container {
            width: 600px; margin: 30px auto; border: 1px solid black;
        }
        h1 {
            text-align: center; margin-top: 40px;
        }
        .login_win {
            text-align: center; padding: 20px; margin: 10px;
        }
        #user_id, #user_pw {
            width: 300px; height: 30px; padding: 10px; margin: 10px;
        }
        #login_btn, #facebook, #google {
            width: 320px; padding: 10px; margin: 10px; border: none;
            color: white; font-size: 1.1em;
        }
        #naver{width: 300px; height: 45px;}
        #login_btn {
            background-color: #ccb2e5;
        }
        #facebook {
            background-color: #1877f2;
        }
        #google {
            background-color: #ccc;
        }
        a {
            text-decoration: none; color: white; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>모두의 웹툰과 함께하기</h1>
        ${state}
        <div class="login_win">
            <form action="" method="post">
                <input type="text" id="user_id" placeholder="아이디" autofocus><br>
                <input type="password" id="user_pw" placeholder="비밀번호"><br>
                <button id="login_btn">들어가기</button>
            </form>
            <div><img src="/images/login_logo/kakao_login_medium_wide.png" id="kakao" onclick="goKakao()"></div>
            <div><img src="/images/login_logo/naver_login_green_wide.PNG" id="naver" onclick="goNaver('${state}')"></div>
            <div class="facebook"><button id="facebook">페이스북으로 들어가기</button></div>
            <div class="google"><button id="google">구글로 들어가기</button></div>
        </div>
    </div>
</body>
<script type="text/javascript">
	function goKakao() {
		location.href = 'https://kauth.kakao.com/oauth/authorize'
			    		+'?client_id=48c16d63af5493c7ae43a1433ec7760f'
			            +'&redirect_uri=http://localhost:8089/login?platNo=1'
			            +'&response_type=code'
	}
	function goNaver(state) {
		var encoding = encodeURIComponent('http://localhost:8089/naverAPI')
		location.href = 'https://nid.naver.com/oauth2.0/authorize?response_type=code'
						+'&client_id=gtb_8Ij5V31vLTCJA7F3'
						+'&redirect_uri='+encoding
						+'&state='+state
						
	}
</script>
</html>
