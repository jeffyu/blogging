<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<body>
<div id="wrapper">
<jsp:include page="header.jsp" />

<div id="contents">
<div id="post">
	<h1><c:out value="${blog.blogTitle}" /></h1>
	<h2> <c:out value="${blog.blogContent}" /></h2>
	<h3>
	<span>published by:<c:out value="${blog.user.userName}" /> at <c:out value="${blog.blogDate}" /> </span>
	</h3>
</div>

<div id="comments">
 <h1> <span> <font color=red><c:out value="${blog.commentCount}" /></font> Comment(s)</span> </h1>
 <c:forEach var="comment" items="${blog.comments}">
 <h2> <span> <img src="resources/image/icon_comment.gif" /> <c:out value="${comment.commentUserName}" /> said...</span></h2>
 <h3> <c:out value="${comment.commentContent}" /></h3>
 <h4>Commented at: <c:out value="${comment.commentDate}" /></h4>
 </c:forEach>
</div>

<div id="formdiv">
<h1> <span> Leave your comment </span></h1>	
<form method="post" action="addComment.do">
	<label for="Name">Name:</label>
	<input type="text" name="userName" />

	<label for="Email">Email:</label>
	<input type="text" name="userEmail" />
	
	<label for="Message">Comment:</label>
	<textarea name="commentContent" rows="20" cols="20"></textarea>
	
	<input type="submit" name="submit" value="Submit" class="submit-button" />
	<input type="hidden" name="blogID" value='<c:out value="${blog.blogID}" />' />
</form>
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