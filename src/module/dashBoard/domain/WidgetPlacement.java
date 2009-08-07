package module.dashBoard.domain;

import pt.ist.fenixframework.pstm.Transaction;

public class WidgetPlacement extends WidgetPlacement_Base {

    public WidgetPlacement() {
	super();
	setDashBoardController(DashBoardController.getInstance());
    }

    public WidgetPlacement(DashBoardColumn dashBoardColumn, DashBoardWidget widget) {
	this();
	setDashBoardColumn(dashBoardColumn);
	setWidget(widget);
    }

    public void delete() {
	removeDashBoardColumn();
	removeWidget();
	Transaction.deleteObject(this);
    }
}
