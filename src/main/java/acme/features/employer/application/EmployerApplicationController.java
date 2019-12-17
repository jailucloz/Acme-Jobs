
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationShowService					showService;

	@Autowired
	private EmployerApplicationListMineService				listMineService;

	@Autowired
	private EmployerApplicationListByReferenceNumberService	listByReferenceNumberService;

	@Autowired
	private EmployerApplicationListByCreationMomentService	listByCreationMomentService;

	@Autowired
	private EmployerApplicationListByStatusService			listByStatusService;

	@Autowired
	private EmployerApplicationAcceptService	acceptService;

	@Autowired
	private EmployerApplicationRejectService	rejectService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);

		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);
		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.UPDATE, this.rejectService);

		super.addCustomCommand(CustomCommand.LIST_REFERENCE, BasicCommand.LIST, this.listByReferenceNumberService);
		super.addCustomCommand(CustomCommand.LIST_MOMENT, BasicCommand.LIST, this.listByCreationMomentService);
		super.addCustomCommand(CustomCommand.LIST_STATUS, BasicCommand.LIST, this.listByStatusService);


	}

}
