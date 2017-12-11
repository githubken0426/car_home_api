<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		This is my JSP page.
		<br>
		<table style="border: 1px solid;">
			<tr>
				<td>返回码</td>
				<td>说明</td>
				<td>用户提示信息</td>
			</tr>
			<tr>
				<td></td>
				<td style='font-weight: bold;color:red;'>全局</td>
				<td></td>
			</tr>
			<tr>
				<td>0</td>
				<td>请求成功</td>
				<td></td>
			</tr>
			<tr>
				<td>-1</td>
				<td>程序异常</td>
				<td>系统繁忙，请 稍后再试。</td>
			</tr>
			<tr>
				<td>-2</td>
				<td>程序异常</td>
				<td>文件不存在</td>
			</tr>
			<tr>
				<td>-3</td>
				<td>程序数据异常</td>
				<td>系统繁忙，请稍后再试。</td>
			</tr>
			 <tr>
				<td>-4</td>
				<td>参数为空错误</td>
				<td>输入有误,请核对你输入的信息</td>
			</tr>
			<tr>
				<td></td>
				<td style='font-weight: bold;color:red;'>签名模块</td>
				<td></td>
			</tr>
			<tr>
				<td>13000</td>
				<td>请求过期</td>
				<td>系统繁忙,请稍后再试...</td>
			</tr>
			<tr>
				<td>13001</td>
				<td>签名不匹配</td>
				<td>系统繁忙,请稍后再试...</td>
			</tr>
			<tr>
				<td></td>
				<td style='font-weight: bold;color:red;'>用户模块</td>
				<td></td>
			</tr>
			<tr>
				<td>6005</td>
				<td>参数格式错误</td>
				<td>请选择头像</td>
			</tr>
			<tr>
				<td>6006</td>
				<td>参数格式错误</td>
				<td>请输入正确的手机号</td>
			</tr>
			<tr>
				<td>6007</td>
				<td>参数格式错误</td>
				<td>昵称不能包含特殊字符</td>
			</tr>
			<tr>
				<td>6020</td>
				<td>登陆成功</td>
				<td>欢迎回来</td>
			</tr>
			<tr>
				<td>6021</td>
				<td>登陆错误</td>
				<td>请输入正确的密码</td>
			</tr>
			<tr>
				<td>6022</td>
				<td>登陆错误</td>
				<td>已在别的设备登陆</td>
			</tr>
			<tr>
				<td>6023</td>
				<td>密码错误</td>
				<td>原密码输入有误</td>
			</tr>
			<tr>
				<td>6030</td>
				<td>账号错误</td>
				<td>手机号未注册</td>
			</tr>
			<tr>
				<td>6031</td>
				<td>账号错误</td>
				<td>手机号已注册</td>
			</tr>
			
			<tr>
				<td>6040</td>
				<td>验证码错误</td>
				<td>请输入正确的验证码</td>
			</tr>
			<tr>
				<td>6041</td>
				<td>获取头像错误</td>
				<td>用户头像不存在</td>
			</tr>
		</table>
	</body>
</html>
