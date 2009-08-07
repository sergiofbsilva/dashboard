package module.dashBoard.domain;

import java.util.ArrayList;
import java.util.List;

public class DashBoardColumnBean {

    private int order;
    private List<DashBoardWidget> widgets;

    public DashBoardColumnBean(int order) {
	this.order = order;
	this.widgets = new ArrayList<DashBoardWidget>();
    }

    public void addWidget(DashBoardWidget widget) {
	widgets.add(widget);
    }

    public int getOrder() {
	return order;
    }

    public void setOrder(int order) {
	this.order = order;
    }

    public List<DashBoardWidget> getWidgets() {
	return widgets;
    }

    public void setWidgets(List<DashBoardWidget> widgets) {
	this.widgets = widgets;
    }

}
