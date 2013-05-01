<%@ page import="uo.miw.gcw.Constants" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>
<portlet:defineObjects/>

Calculadora
<br>
<jsp:useBean id="actionUrlEnter" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlPlus" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlMinus" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlClr" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlMult" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlDiv" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlMi" scope="request" class="java.lang.String" />
<jsp:useBean id="actionUrlMo" scope="request" class="java.lang.String" />

<table>
	<tr>
		<td colspan="4">
		<form action="<%=actionUrlEnter%>" method="post">
		<table>
			<tr>
				<td><input type="text" align="left" size="20"
					style="width: 164px; text-align: right; font-weight: bold" 
					name="<%= Constants.VALUE %>" 
					value="<%=request.getAttribute(Constants.RESULT)%>"/>
				</td>
				<td><input type="submit" value="Ent"/></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>

	<tr>
		<td>
		<form action="<%=actionUrlPlus%>" method="post">
			<input type="submit" value="+" style="width: 40px"/></form>
		</td>
		<td>
		<form action="<%=actionUrlMinus%>" method="post">
			<input type="submit" value="-" style="width: 40px"/></form>
		</td>
		<td>
		<form action="<%=actionUrlClr%>" method="post">
			<input type="submit" value="Clr" style="width: 40px"/></form>
		</td>
		<td></td>
	</tr>

	<tr>
		<td>
		<form action="<%=actionUrlMult%>" method="post">
			<input type="submit" value="*"  style="width: 40px"/></form>
		</td>
		<td>
		<form action="<%=actionUrlDiv%>" method="post">
			<input type="submit" value="/" style="width: 40px"/></form>
		</td>
		<td>
		<form action="<%=actionUrlMi%>" method="post">
			<input type="submit" value="Mi" style="width: 40px"/></form>
		</td>
		<td>
		<form action="<%=actionUrlMo%>" method="post">
			<input type="submit" value="Mo"  style="width: 40px"/></form>
		</td>
	</tr>

</table>
