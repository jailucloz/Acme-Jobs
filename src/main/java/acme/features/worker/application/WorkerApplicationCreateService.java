
package acme.features.worker.application;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		// job.employer
		request.unbind(entity, model, "referenceNumber", "status", "statement", "skills", "qualifications", "job", "worker", "job.employer");
		model.setAttribute("id", entity.getJob().getId());
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;
		// Solicite un trabajo siempre que haya sido publicado y su fecha límite no haya
		// transcurrido
		Application result;
		int principal, idJob;
		Worker worker;
		Job job;

		principal = request.getPrincipal().getAccountId();
		worker = this.repository.findOneWorkerById(principal);

		idJob = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(idJob);

		result = new Application();

		if (job != null) {
			result.setJob(job);
		}

		result.setWorker(worker);

		result.getJob().getFinalMode().equals(true);

		//		Application result;
		//		result = new Application();
		return result;

	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors()) {
			Boolean unique;
			unique = this.repository.findOneApplicationById(entity.getId()) != null;

			errors.state(request, !unique, "reference", "worker.application.error.reference");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date date = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(date);

		this.repository.save(entity);
	}

}
