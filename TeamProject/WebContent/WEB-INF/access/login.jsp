<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v8.0&appId=3176381649143544&autoLogAppEvents=1" nonce="JrytjmFN"></script>
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
        #login_btn{
            width: 320px; padding: 10px; margin: 10px; border: none;
            color: white; font-size: 1.1em;
        }
        .snsimg{width: 360px; height: 60px;}
        .snsimg:hover{cursor: pointer;}
        #login_btn {background-color: #ccb2e5;}
        a {text-decoration: none; color: white;}
    </style>
</head>
<body>
	<div id="fb-root"></div>
    <div class="container">
        <h1>모두의 웹툰과 함께하기</h1>
        <div class="err">${msg }</div>
        <div class="login_win">
            <form action="/login" method="post">
                <input type="text" id="user_id" name="user_id" placeholder="아이디" value="${user_id}" autofocus><br>
                <input type="password" id="user_pw" name="user_pw" placeholder="비밀번호"><br>
                <input id="login_btn" type="submit" value="로그인">
            </form>
            <div class="snsbtn"><img class="snsimg" src="/images/login_logo/kakao_btn.PNG" id="kakao" onclick="goKakao()"></div>
            <div class="snsbtn"><img class="snsimg" src="/images/login_logo/naver_btn.PNG" id="naver" onclick="goNaver('${state}')"></div>
            <div class="snsbtn"><img class="snsimg" src="/images/login_logo/google_btn.PNG" id="google" onclick="goGoogle()"></div>
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
	function goGoogle() {
		location.href = 'https://accounts.google.com/o/oauth2/v2/auth?'
			 + 'scope=https%3A//www.googleapis.com/auth/contacts'
			 + '&access_type=offline'
			 + '&include_granted_scopes=true'
			 + '&response_type=code'
			 + '&state=state_parameter_passthrough_value'
			 + '&redirect_uri=http://localhost:8089/googleAPI'
			 + '&client_id=659641044041-d8d9d26ubldu5veldv2g3cqaqedv6htq.apps.googleusercontent.com'
	}
</script>
</html>
