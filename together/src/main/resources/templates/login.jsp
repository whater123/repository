<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

</head>
<body onload="init()">
<form action="/getMailCode" method="post">
    请输入注册邮箱：<input type="text" name="userMail">
    <input type="submit" value="获取验证码">
</form>
<form action="/submit" method="post">
    请输入注册邮箱：<input type="text" name="userMail">
    输入验证码(不区分大小写)：<input type="text" name="code">
    <input type="submit" value="确定">
</form>
</body>
</html>