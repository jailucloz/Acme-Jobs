
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.userAccount.id = ?1")
	Employer findOneEmployerByUserAccount(int userAccountId);

	/*
	 * @Query("select j from Job j where j.reference = '?1'")
	 * Job findOneReferenceByJobId(String reference);
	 */

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findApplicationWithThisJob(int jobId);

}
