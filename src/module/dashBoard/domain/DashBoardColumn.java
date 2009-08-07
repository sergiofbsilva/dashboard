package module.dashBoard.domain;

import java.util.ArrayList;
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
	new WidgetPlacement(this, widget);
    }

    @Service
    public void removeWidget(DashBoardWidget widget) {
	if (!getDashBoardPanel().isCurrentUserAbleToEdit()) {
	    throw new DomainException("error.permission.denied");
	}
	getFirstWidgetPlacementFor(widget).delete();
    }

    private WidgetPlacement getFirstWidgetPlacementFor(DashBoardWidget widget) {
	for (WidgetPlacement placement : getPlacements()) {
	    if (placement.getWidget() == widget) {
		return placement;
	    }
	}
	return null;
    }

    public List<DashBoardWidget> getWidgets() {
	List<DashBoardWidget> widgets = new ArrayList<DashBoardWidget>();
	for (WidgetPlacement placement : getPlacements()) {
	    widgets.add(placement.getWidget());
	}
	return widgets;
    }

    public void rearrangeColumnTo(List<DashBoardWidget> widgets) {
	for (; !getPlacements().isEmpty(); getPlacements().get(0).delete())
	    ;

	for (DashBoardWidget widget : widgets) {
	    addWidget(widget);
	}
    }
}
