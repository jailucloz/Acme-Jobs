
package acme.features.employer.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractUpdateService<Employer, Job> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "employer");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors()) {
			Boolean isFuture, isPositive, isEuro, isFinal;

			// Validación del modo no final
			isFinal = entity.getFinalMode();
			errors.state(request, !isFinal, "finalMode", "errors.job.finalMode.no", "Can't edit a job in final mode");

			// Validación referencia unica
			//isDuplicated = this.repository.findOneReferenceByJobId(entity.getReference()) != null;
			//errors.state(request, !isDuplicated, "reference", "errors.job.reference.unique", "The reference must be unique");

			// Validación de fecha futura
			Date fechaActual;
			fechaActual = new Date();
			isFuture = entity.getDeadline().after(fechaActual);
			errors.state(request, isFuture, "deadline", "errors.job.deadline.future", "Deadline must be in future");

			// Validación dinero positivo
			isPositive = entity.getSalary().getAmount() > 0;
			errors.state(request, isPositive, "salary", "errors.job.salary.money.amount-positive", "The amount must be positive");

			// Validación moneda
			isEuro = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, isEuro, "salary", "errors.job.salary.money.euro", "The money must be in euro '€' / 'EUR'");

		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
