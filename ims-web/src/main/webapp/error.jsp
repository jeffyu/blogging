<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
#errorLayer {
  margin-top: 30px;
  width: 60%;
  margin-left: 25%;
  border: 2px solid #719B1E;
}

#errorLayer h1 {
 border-bottom: 2px dotted #bbbbbb;
 margin: 5px;
 font-size: 16px;
 color: red;
}

#errorLayer ul {
 text-align:left;
 margin: 0px;
}

#errorLayer li {
 border-bottom: 1px dotted #bbbbbb;
 margin: 5px;
 font-size: 12px;
 text-align:left;
}


</style>

<body>
<div id="wrapper">
	
	<jsp:include page="header.jsp" />
		
	<div id="errorLayer">
	    <h1>You've been here might be caused by one of following reasons</h1>
		<ul>
		  <li> You do not have permission on the requested resource</li>
		  <li> reason2 </li>
		</ul>

	</div>
	
	<jsp:include page="footer.jsp" />

</div>
</body>
</html>
