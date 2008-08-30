<%@ include file="/common/taglibs.jsp" %>

    <div id="divider"><div></div></div>
    <span class="left">
	    <c:if test="${pageContext.request.remoteUser != null}">
    		&copy; <fmt:message key="copyright.year"/> <a href="<fmt:message key="webapp.company.url"/>"><fmt:message key="webapp.company.name"/></a> |
    	</c:if>
    	<fmt:message key="webapp.version"/> |
        <span id="validators">
            <a href="http://validator.w3.org/check?uri=referer">XHTML Valid</a> |
            <a href="http://jigsaw.w3.org/css-validator/validator-uri.html">CSS Valid</a>
        </span>
    </span>
    <span class="right">
        <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
    </span>
