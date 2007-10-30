<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link href="resources/js/jquery.js"  type="text/javascript" >
<body>
<div id="wrapper">
	
	<jsp:include page="header.jsp" />
		
	<div id="contents">
	
	<div id="post">
	  <c:forEach var="blog" items="${blogs}" >
		<h1><a href='blog.do?id=<c:out value="${blog.blogID}"/>'><c:out value="${blog.blogTitle}" /></a></h1>
		<h2> <c:out value="${blog.blogContent}" /></h2>
		<h3>
		<span>published by:<c:out value="${blog.user.userName}" /> at <c:out value="${blog.blogDate}" /> </span>
		<span class="post-comment"><img src="resources/image/icon_comment.gif" /> <a href='blog.do?id=<c:out value="${blog.blogID}"/>'><c:out value="${blog.commentCount}" /> Comments</a></span>
		<span>Labels: 
		 <c:forEach var="label" items="${blog.labels}">
		  <a href='<%=request.getContextPath()%>/index.do?labelID=<c:out value="${label.labelID}"/>'>
		  <c:out value="${label.labelName}" />
		  </a>
		 </c:forEach>
		 </span>	 
		</h3>
	  </c:forEach>
	</div>
	
	</div>
	
	<div id="widgets">
	 <jsp:include page="profile-widget.jsp" />
	 <jsp:include page="labels-widget.jsp" />
	</div>
	
	<jsp:include page="footer.jsp" />

</div>
</body>
</html>