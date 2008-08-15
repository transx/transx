<%@ include file="/common/taglibs.jsp"%>

<%--c:if test="${pageContext.request.locale.language != 'en'}">
    <div id="switchLocale">
    	<a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name.in.english"/></a>
	</div>
</c:if--%>
<c:if test="${pageContext.request.locale.language != 'fa'}">
    <div id="switchLocale">
		<a href="<c:url value='/?locale=fa'/>"><fmt:message key="webapp.name.in.farsi"/></a>	
	</div>
</c:if>

<div id="branding">
<!-- 
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
			    <img alt="<fmt:message key="webapp.tagline"/>" src="../images/logo.jpg"/>
			</td>
			<td style="padding-bottom: 30px;">
			    <h1><a style="color: white;" href="<c:url value='/'/>"><fmt:message key="webapp.name.customer"/></a></h1>
			</td>
		</tr>
		<tr>
			<td>
			    <p><fmt:message key="webapp.tagline"/></p>
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
 -->
	</table>
    <br/>
 
</div>
<hr />

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>