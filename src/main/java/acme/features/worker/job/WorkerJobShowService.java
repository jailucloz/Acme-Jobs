
package acme.features.worker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	// Internal state -------------------------------------------------------------

	@Autowired
	WorkerJobRepository repository;


	// AbstractShowService<Worker, Job> interface -------------------------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Worker worker;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		worker = job.getWorker();
		principal = request.getPrincipal();

		//result = job.getStatus() == JobStatus.PUBLISHED || job.getStatus() == JobStatus.DRAFT && worker.getUserAccount().getId() == principal.getAccountId();

		result = job.getFinalMode() || !job.getFinalMode() && worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "deadline", "title", "salary", "moreInfo", "description", "finalMode", "descriptorDescription");
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

}
