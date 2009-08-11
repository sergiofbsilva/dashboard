package module.dashBoard.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import myorg.applicationTier.Authenticate.UserView;
import myorg.domain.User;
import myorg.domain.exceptions.DomainException;
import pt.ist.fenixWebFramework.services.Service;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public abstract class DashBoardPanel extends DashBoardPanel_Base {

    public DashBoardPanel() {
	super();
	for (int order = 0; order < 3; order++) {
	    new DashBoardColumn(order, this);
	}
	setDashBoardController(DashBoardController.getInstance());

    }

    public DashBoardPanel(MultiLanguageString name) {
	this();
	setName(name);
    }

    public DashBoardColumn getColumn(int order) {
	for (DashBoardColumn column : getDashBoardColumns()) {
	    if (column.getColumnOrder() == order) {
		return column;
	    }
	}
	return null;
    }

    public void addWidgetToColumn(int column, DashBoardWidget widget) {
	getColumn(column).addWidget(widget);
    }

    public void removeWidgetFromColumn(int column, DashBoardWidget widget) {
	getColumn(column).removeWidget(widget);
    }

    public boolean isAccessibleToCurrentUser() {
	return isAccessibleToUser(UserView.getCurrentUser());
    }

    public abstract boolean isAccessibleToUser(User user);

    @Service
    public void edit(List<DashBoardColumnBean> beans) {
	if (!isAccessibleToCurrentUser()) {
	    throw new DomainException("error.permission.denied");
	}
	for (DashBoardColumnBean bean : beans) {
	    DashBoardColumn column = getColumn(bean.getOrder());
	    column.rearrangeColumnTo(bean.getWidgets());
	}
    }

    public Set<DashBoardColumn> getOrderedColumns() {
	Set<DashBoardColumn> columns = new TreeSet<DashBoardColumn>(DashBoardColumn.IN_PANEL_COMPARATOR);
	columns.addAll(getDashBoardColumns());
	return columns;
    }

    public Set<DashBoardWidget> getWidgetsSet() {
	Set<DashBoardWidget> widgets = new HashSet<DashBoardWidget>();
	for (DashBoardColumn column : getDashBoardColumns()) {
	    widgets.addAll(column.getWidgets());
	}

	return widgets;
    }
}
