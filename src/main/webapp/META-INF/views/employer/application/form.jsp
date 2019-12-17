<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form readonly="true">

	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-moment code="employer.application.form.label.creationMoment" path="creationMoment"/>
	
	<acme:form-textarea code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications"/>
	<acme:form-textarea code="employer.application.form.label.job.reference" path="job.reference"/>
	<acme:form-textarea code="employer.application.form.label.worker.username" path="worker.userAccount.username"/>
	<acme:form-textarea code="employer.application.form.label.employer.username" path="employer.userAccount.username"/>
</acme:form>	
<acme:form>		
	
		<acme:form-select  code="employer.application.form.label.status"  path="status" >
			<acme:form-option code="ACCEPTED" value="setApplicationStatus(ApplicationStatus.ACCEPTED)"/>
		<%-- 	<acme:form-option code="PENDING" value="PENDING"/> --%>
			<acme:form-option code="REJECTED" value="REJECTED"/>		
		</acme:form-select>	
	
		
	<acme:form-textarea code="employer.application.form.label.rejectJustification" path="rejectJustification"/>
	
	
   <%--  <acme:form-select  code="employer.application.form.label.status" path="status"  		
		</acme:form-select>		
	 --%>
	
  	<acme:form-submit test= "${command == 'show' }"
  	 code="employer.application.form-buttom.update" action="/employer/application/update"/>	 
  	 
   	 <acme:form-submit test= "${command == 'update' }"
  	 code="employer.application.form-buttom.update"  action="/employer/application/update"/>
  
  	 
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>