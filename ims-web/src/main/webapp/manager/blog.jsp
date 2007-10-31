<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/labels.css" rel="stylesheet" type="text/css" />
<body>
<div id="wrapper">

<div id="logo">
 <h1> How Much Coding is Enough! </h1>
</div>

<div id="tabMenu">
  <ul>
	<li><a href="<%=request.getContextPath()%>/index.do"><span>Home</span></a></li>
	<li><a href="#"><span>News</span></a></li>
	<li><a href="#"><span>Forum</span></a></li>
	<li><a href="<%=request.getContextPath()%>/index.do"><span>Blog</span></a></li>
	<li><a href="#"><span>Wiki</span></a></li>
	<li><a href="#"><span>Shop</span></a></li>
  </ul>
</div>

<div id="contents">

<div id="postFormDiv">	
<form method="post" action="updateBlog.do">
	<label for="Title">Blog Title:</label>
	<input type="text" name="blogTitle" />
	
	<label for="Message">Content:</label>
	<textarea name="blogContent" rows="20" cols="120"></textarea>
	
	<label for="labels">Labels:</label>
	<input type="text" name="labels" />
	
	<input type="submit" name="submit" value="Submit" class="submit-button" />
	<input type="hidden" name="blogID" value='<c:out value="${blog.blogID}" />' />
</form>
</div>
 
 </div>
 <div id="widgets">
	<div id="labels">
	  <div id="label-title">
		  <span>Posts </span>
	 </div>
	  <ul>
		 <li> <a href="<%=request.getContextPath()%>/manager/createBlog.do"/>New Post</a> </li>
	  </ul>
	</div>
 </div>
 <jsp:include page="../footer.jsp" />
</div>
</body>
</html>