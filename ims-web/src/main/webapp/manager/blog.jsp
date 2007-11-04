<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/labels.css" rel="stylesheet" type="text/css" />

<script language="javascript">
   function showLabels() {
    var labelSpan = document.getElementById('allLabels');
	var showAllLabel = document.getElementById('show');
	var theVisible = (labelSpan.style.visibility == 'hidden' ? 'visible' : 'hidden');
	if (theVisible == 'hidden') {
	  showAllLabel.innerHTML = 'Show All';
	} else {
	  showAllLabel.innerHTML = 'Hidden All';
	}
	labelSpan.style.visibility= theVisible;
   }
   
   function addLabel(label) {
   var labelInput = document.getElementsByName('labels')[0];
   var labelValue = labelInput.value;
   var theLabels = labelValue.split(',');
   var found = false;
    for(var i=0; i<theLabels.length; i++) {
     if (theLabels[i] == label){
	    found = true;
	 }
    }
	
	if (found == false) {
	 labelInput.value = labelValue + label + ',';
	}
   }
</script>

<body>
<div id="wrapper">

<jsp:include page="header.jsp" />

<div id="contents">

<div id="postFormDiv">	
<form method="post" action="updateBlog.do">
	<label for="Title">Blog Title:</label>
	<input type="text" name="blogTitle" value='<c:out value="${blog.blogTitle}"/>'/>
	
	<label for="Message">Content:</label><textarea name="blogContent" rows="20" cols="120"><c:out value="${blog.blogContent}"/></textarea>
	
	<label for="labels">Labels:</label>
	<input type="text" name="labels" value='<c:out value="${blogLabels}" />'/>  <a href="#" onclick="showLabels();"><span id='show'>Show All</span> </a>
	<h4><span id="allLabels" style="visibility:hidden">
	all labels: 
	<c:forEach var="label" items="${allLabels}">
		<a href="#" onclick="addLabel('<c:out value="${label.labelName}" />');"> <c:out value="${label.labelName}" /> </a>,
	</c:forEach>
	</span></h4>
	<input type="submit" name="submit" value="Submit" class="submit-button" />
	<input type="hidden" name="blogID" value='<c:out value="${blog.blogID}" />' />
</form>
</div>
 
<div id="comments">
 <h1> <span> <font color=red><c:out value="${blog.commentCount}" /></font> Comment(s)</span> </h1>
 <c:forEach var="comment" items="${blog.comments}">
 <tr>
  <td>
   <h2><span> <img src="../resources/image/icon_comment.gif" /> <c:out value="${comment.commentUserName}" /> said...</span></h2>
	  </td>
	  <td align="right">
	    <h2><span id="deleteComment"> <a href='<%=request.getContextPath()%>/manager/deleteComment.do?blogID=<c:out value="${blog.blogID}"/>&commentID=<c:out value="${comment.commentID}" />'>Delete</a></span></h2>
	  </td>
 </tr>
 <h3> <c:out value="${comment.commentContent}" /></h3>
 <h4>Commented at: <c:out value="${comment.commentDate}" /></h4>
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