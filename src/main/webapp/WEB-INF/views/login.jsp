<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<html>
<head>
<meta charset="utf-8" />
<script type="text/javascript" src="resources/jquery-1.8.0.min.js"></script>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<div>
		<form id="loginForm" action="" method="post">
			<div>
				<input type="text" name="name" value="请输入账号">
			</div>
			<div>
				<input type="password" name="originalPassword" value="请输入密码">
			</div>
			 <div onclick="login()">
                                登&nbsp;&nbsp;&nbsp;录</div>
		</form>
	</div>
</body>
<script type="text/javascript">
function login() {
    var uname = $("input[name='username']").val();
    var pwd = $("input[name='password']").val();
    if(uname == "" || pwd == "") {
        alert("用户名或密码为空!");
        $("input[name='login']").focus();
    } else {
        $('#loginForm').submit();
    }
}
</script>
</html>