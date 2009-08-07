package module.dashBoard.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import myorg.domain.exceptions.DomainException;
import pt.ist.fenixWebFramework.services.Service;

public class DashBoardColumn extends DashBoardColumn_Base {

    public static final Comparator<DashBoardColumn> IN_PANEL_COMPARATOR = new Comparator<DashBoardColumn>() {

	@Override
	public int compare(DashBoardColumn column1, DashBoardColumn column2) {
	    return Integer.valueOf(column1.getColumnOrder()).compareTo(column2.getColumnOrder());
	}

    };

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
	for (DashBoardWidget existingWidget : getWidgets()) {
	    existingWidget.setOrderInColumn(existingWidget.getOrderInColumn() + 1);
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
	int order = 0;
	for (DashBoardWidget widget : widgets) {
	    widget.setOrderInColumn(order++);
	    getWidgets().add(widget);
	}
    }

    public Set<DashBoardWidget> getOrderedWidgets() {
	Set<DashBoardWidget> widgets = new TreeSet<DashBoardWidget>(DashBoardWidget.IN_COLUMN_COMPARATOR);
	widgets.addAll(getWidgets());
	return widgets;
    }
}
