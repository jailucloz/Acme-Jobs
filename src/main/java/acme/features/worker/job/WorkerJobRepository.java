
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select w from Worker w where w.userAccount.id = ?1")
	Worker findWorker(int accountId);

	@Query("select j from Job j where j.finalMode=true and j.deadline> CURRENT_TIMESTAMP")
	Collection<Job> findManyJob();

}
