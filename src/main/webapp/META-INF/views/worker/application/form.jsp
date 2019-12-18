<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" />
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="worker.application.form.label.creationMoment" path="creationMoment" />
		<acme:form-textbox code="worker.application.form.label.status" path="status" />
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textbox code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />


	<acme:form-submit test="${command == 'create'}" code="worker.application.form-buttom.create"
		action="/worker/application/create?jobId=${jobId}" />
	<acme:form-return code="worker.application.form.button.return" />
</acme:form>