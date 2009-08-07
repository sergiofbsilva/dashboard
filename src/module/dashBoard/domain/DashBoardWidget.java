package module.dashBoard.domain;

import module.dashBoard.widgets.StrutsWidget;

public class DashBoardWidget extends DashBoardWidget_Base {

    private StrutsWidget instance;

    public DashBoardWidget(Class<? extends StrutsWidget> widgetClass) {
	super();
	setDashBoardController(DashBoardController.getInstance());
	setWidgetClassName(widgetClass.getName());
    }

    public StrutsWidget getController() {
	if (instance == null) {
	    initializeWidget();
	}
	return instance;
    }

    private synchronized void initializeWidget() {
	try {
	    instance = (StrutsWidget) Class.forName(getWidgetClassName()).newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getName() {
	return getController().getName();
    }
}
