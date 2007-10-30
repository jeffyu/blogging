<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
#loginLayer {
  width: 50%;
  margin-left: 25%;
  border: 2px solid #719B1E;
  margin-top: 50px;
}

#loginLayer h1 {
 border-bottom: 1px dotted #bbbbbb;
 margin: 5px;
 font-size: 14px;
 color: red;
}

#loginLayer h2 {
 border-bottom: 1px dotted #bbbbbb;
 margin: 5px;
 font-size: 16px;
 text-align:center;
}

#loginLayer table {
 border: 1px dotted #bbbbbb;
}

#loginLayer input{
	padding: 3px;
	width: 150px;
	font-size: 10px;
	margin: 0px 0px 10px 10px;
	border: 2px solid #719B1E;
}


#loginLayer input:focus {
	border: 2px solid #900;
}

#loginLayer input.submit-button {
	width: 80px;
	margin: 0 200 0 100px;
	font-size: 10px;
}


</style>

<script language="javascript">
function changeJcaptchaImage() {
 var image = document.getElementById('jcaptchaImage');
 image.innerHtml='<img src="jcaptchaImage.do" />';
 alert('aa');
}
</script>

<body>
<div id="wrapper">
	
	<jsp:include page="header.jsp" />
		
	<div id="loginLayer">
	    <c:if test="${not empty param.login_error}">
	    <h1>Your loginName or password is not correct, please try again!</h1>
		</c:if>
		<h2>Login Page </h2>
		<form method="post" action="security_check">
		  <table width="100%" border="0">
			<tr>
				<td width="22%"> <div align="right">loginName:</div></td>
			  <td width="78%"><input type="text" name="j_username" /></td>
			</tr>
			<tr>
				<td> <div align="right">password:</div></td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
				<td> <div align="right" id="jcaptchaImage"><img src="jcaptchaImage.do" /></div></td>
				<td><input type="text" name="j_captcha" /></td>
			</tr>
			<tr>
			 <td colspan="2" align="center">
				<input type="submit" name="submit" value="Login" class="submit-button" />			</td>
			</tr>
		  </table>
		</form>
	</div>
	
	<jsp:include page="footer.jsp" />

</div>
</body>
</html>
