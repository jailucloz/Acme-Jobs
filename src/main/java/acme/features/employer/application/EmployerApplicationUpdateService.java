
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status", "rejectedJustication");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "rejectJustification", "statement", "skills", "qualifications", "job.reference", "worker.userAccount.username", "employer.userAccount.username");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.getModel().getString("status").equals("REJECTED") && !errors.hasErrors("rejectedJustication")) {
			errors.state(request, !request.getModel().getString("rejectedJustication").isEmpty(), "rejectedJustication", "employer.application.error.notBlank");
			;
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Application> request, final Response<Application> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
