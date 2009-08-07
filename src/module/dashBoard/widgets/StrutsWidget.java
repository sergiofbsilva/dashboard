package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;

import org.apache.struts.action.ActionForward;

public interface StrutsWidget {

    public void onLoad(WidgetRequest request);

    public ActionForward widgetSubmission(WidgetRequest request);

    public String getName();
}
