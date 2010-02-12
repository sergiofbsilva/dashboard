package module.dashBoard.domain;

public class WidgetOptions extends WidgetOptions_Base {

    public WidgetOptions() {
	super();
    }

    public void delete() {
	removeDashBoardController();
	removeWidget();
	deleteDomainObject();
    }

}
