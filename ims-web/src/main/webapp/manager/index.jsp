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
  	<div id="post">
	  <c:forEach var="blog" items="${blogs}" >
	  <h4>
	      <input type="checkbox" name="chk" />
		  <a href='<%=request.getContextPath()%>/manager/deleteBlog.do?id=<c:out value="${blog.blogID}"/>'>Delete</a>
		  <a href='<%=request.getContextPath()%>/manager/editBlog.do?id=<c:out value="${blog.blogID}"/>'>Edit</a> 
		  <a href='<%=request.getContextPath()%>/blog.do?id=<c:out value="${blog.blogID}"/>' target="_blank">View</a>		  
		<span id="postTitle">
		  <c:out value="${blog.blogTitle}" />
		</span>
		  <span id="postLabels">
          (
		  <c:forEach var="label" items="${blog.labels}">
            <c:out value="${label.labelName}" />
          </c:forEach>
		  )
          </span>
		  <span id="postDate">
		  <c:out value="${blog.blogDate}" />
		  </span>
	   </h4>
	   </c:forEach>
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