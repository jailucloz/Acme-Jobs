<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="employer.duty.list.label.title" path="title" width="50%"/>
	<acme:list-column code="employer.duty.list.label.percentage" path="percentage" width="50%"/>
		
</acme:list>

<!-- <acme:button code="Code.of.duty.create" action="/acme-jobs/employer/duty/create?id=${idJob}"/>

<acme:form>
	<acme:form-hidden path="idJob"/>
	<acme:form-submit code="employer.duty.list.button.create" action="/employer/duty/create?id=${idJob}" method="get"/>
</acme:form>-->