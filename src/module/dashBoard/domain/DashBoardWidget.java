package module.dashBoard.domain;

import module.dashBoard.widgets.WidgetController;
import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.pstm.Transaction;

public class DashBoardWidget extends DashBoardWidget_Base {

    static {

    }

    private WidgetController instance;

    public DashBoardWidget(Class<? extends WidgetController> widgetClass) {
	super();
	setDashBoardController(DashBoardController.getInstance());
	setWidgetClassName(widgetClass.getName());
    }

    public WidgetController getWidgetController() {
	if (instance == null) {
	    initializeWidget();
	}
	return instance;
    }

    private synchronized void initializeWidget() {
	try {
	    instance = (WidgetController) Class.forName(getWidgetClassName()).newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getName() {
	return getWidgetController().getName();
    }

    @Service
    public void delete() {
	removeDashBoardColumn();
	removeDashBoardController();
	Transaction.deleteObject(this);
    }

    @Service
    public static DashBoardWidget newWidget(Class<? extends WidgetController> widgetClass) {
	return new DashBoardWidget(widgetClass);
    }
}
