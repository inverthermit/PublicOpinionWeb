<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSON测试</title>
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){ 
    $("#loginBtn").click(function(){
         var username = $("#username").val(); 
        var password = $("#password").val(); 
         var userJosn = {"username":username,"password":password};
         $.ajax({ 
             url:"/PublicOpinionWeb/ok", 
             type:"post", 
             data:userJosn, 
             success: function(data){ 
                 $("#msg").html("name="+data.name+"   ,pwd="+data.pwd); 
             }
         });
    });
});
 //由此我们可以看出，jquary中的ajax也是json格式的
</script>
</head>
<body>
    <h3>用户登录</h3>
    <form name="userForm" action="">
        用户名：<input type="text" id="username" name="username">
        密码：<input type="text" id="password" name="password">
        <input type="button" value="登录" id="loginBtn" />
    </form>
    <br/>
    结果:<span id="msg"></span>
</body>
</html>  