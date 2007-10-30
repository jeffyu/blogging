<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<link href="resources/css/labels.css" rel="stylesheet" type="text/css" />
<div id="labels">
  <div id="label-title">
	  <span>Labels </span>  </div>
  <ul>
     <c:forEach var="label" items="${labels}">
	 <li> <a href='<%=request.getContextPath()%>/index.do?labelID=<c:out value="${label.labelID}"/>'><c:out value="${label.labelName}" /></a> </li>
	 </c:forEach>
  </ul>
</div>