package module.dashBoard.domain;

import java.util.List;

import myorg.domain.exceptions.DomainException;
import pt.ist.fenixWebFramework.services.Service;

public class DashBoardColumn extends DashBoardColumn_Base {

    public DashBoardColumn(int order, DashBoardPanel panel) {
	super();
	setDashBoardController(DashBoardController.getInstance());
	setColumnOrder(order);
	setDashBoardPanel(panel);
    }

    @Service
    public void addWidget(DashBoardWidget widget) {
	if (!getDashBoardPanel().isCurrentUserAbleToEdit()) {
	    throw new DomainException("error.permission.denied");
	}
	super.addWidgets(widget);
    }

    @Service
    public void removeWidget(DashBoardWidget widget) {
	if (!getDashBoardPanel().isCurrentUserAbleToEdit()) {
	    throw new DomainException("error.permission.denied");
	}
	super.removeWidgets(widget);
    }

    public void rearrangeColumnTo(List<DashBoardWidget> widgets) {
	getWidgets().clear();
	getWidgets().addAll(widgets);
    }
}
