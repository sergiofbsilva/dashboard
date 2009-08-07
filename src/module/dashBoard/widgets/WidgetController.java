package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;
import module.dashBoard.domain.DashBoardWidget;

import org.apache.struts.action.ActionForward;

public interface WidgetController {

    public void init(DashBoardWidget widget);

    public void kill(DashBoardWidget widget);

    public void onLoad(WidgetRequest request);

    public ActionForward widgetSubmission(WidgetRequest request);

}
