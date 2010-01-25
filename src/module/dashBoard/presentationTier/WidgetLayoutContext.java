package module.dashBoard.presentationTier;

import org.apache.struts.action.ActionForward;

import module.dashBoard.domain.DashBoardWidget;
import myorg.presentationTier.Context;

public class WidgetLayoutContext extends Context {

    private String widgetBody;

    @Override
    public ActionForward forward(String forward) {
	return new ActionForward(this.widgetBody);
    }

    public ActionForward forward() {
	return forward("");
    }

    public WidgetLayoutContext(DashBoardWidget widget) {
	this.widgetBody = WidgetBodyResolver.getBodyFor(widget.getWidgetController().getClass());
    }
}
