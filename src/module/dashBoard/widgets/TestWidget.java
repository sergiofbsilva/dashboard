package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;
import module.dashBoard.domain.DashBoardWidget;
import myorg.util.ClassNameResolver;

import org.apache.struts.action.ActionForward;

public class TestWidget implements WidgetController {

    static {
	ClassNameResolver.registerType(TestWidget.class, "resources/DashBoardResources", "widget.title."
		+ TestWidget.class.getSimpleName());
    }

    @Override
    public void onLoad(WidgetRequest request) {
	request.setAttribute("xpto", request.getCurrentUser());
    }

    @Override
    public ActionForward widgetSubmission(WidgetRequest request) {
	throw new UnsupportedOperationException();
    }

    @Override
    public void init(DashBoardWidget widget) {
    }

    @Override
    public void kill(DashBoardWidget widget) {
    }

    @Override
    public void requestEdit(WidgetRequest request) {
	throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEditionModeSupported() {
	return false;
    }

}
