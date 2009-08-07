package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;
import module.dashBoard.presentationTier.DashBoardManagementAction;

import org.apache.struts.action.ActionForward;

public class TestWidget implements StrutsWidget {

    @Override
    public String getName() {
	return getClass().getSimpleName();
    }

    @Override
    public void onLoad(WidgetRequest request) {
	request.setAttribute("xpto", request.getCurrentUser());
    }

    @Override
    public ActionForward widgetSubmission(WidgetRequest request) {
	return DashBoardManagementAction.forwardToDashBoard(request);

    }

}
