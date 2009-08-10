package module.dashBoard.widgets;

import java.util.Set;

import module.dashBoard.WidgetRequest;
import module.dashBoard.presentationTier.DashBoardManagementAction;
import module.metaWorkflow.domain.WorkflowMetaProcess;
import module.workflow.domain.WorkflowProcess;
import module.workflow.presentationTier.actions.ProcessManagement;
import myorg.util.ClassNameResolver;
import myorg.util.VariantBean;

import org.apache.commons.collections.Predicate;
import org.apache.struts.action.ActionForward;

public class EasyAccessWidget extends WidgetController {

    static {
	ClassNameResolver.registerType(EasyAccessWidget.class, "resources/DashBoardResources", "widget.title."
		+ EasyAccessWidget.class.getSimpleName());
    }

    @Override
    public void doView(WidgetRequest request) {
	request.setAttribute("bean", new VariantBean());
    }

    @Override
    public ActionForward doSubmit(WidgetRequest request) {
	final String processNumber = request.getRenderedObject("processId");
	Set<WorkflowMetaProcess> allProcesses = WorkflowProcess.getAllProcesses(WorkflowMetaProcess.class, new Predicate() {

	    @Override
	    public boolean evaluate(Object arg0) {
		return ((WorkflowMetaProcess) arg0).getProcessNumber().equals(processNumber);
	    }

	});
	if (allProcesses.size() == 1) {
	    return ProcessManagement.forwardToProcess(allProcesses.iterator().next());
	}

	return DashBoardManagementAction.forwardToDashBoard(request);
    }

}
