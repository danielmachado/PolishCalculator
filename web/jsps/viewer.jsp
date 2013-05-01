<%@ taglib uri='http://java.sun.com/portlet' prefix='portlet'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@page import="uo.miw.gcw.Constants" %>

<portlet:defineObjects />

Polish Calculator Viewer (Stack)
<br>
<c:set var="lista" value="<%=request.getAttribute(Constants.LINES) %>" />
<p>
	<c:forEach var="thing" items="${lista}">
		<li><c:out value="${thing}" /><br></li>
	</c:forEach>
</p>
