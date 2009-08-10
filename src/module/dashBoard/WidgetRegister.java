package module.dashBoard;

import java.util.HashSet;
import java.util.Set;

import module.dashBoard.domain.DashBoardPanel;
import module.dashBoard.widgets.WidgetController;
import myorg.domain.User;

public class WidgetRegister {

    public static WidgetAditionPredicate ALWAYS_ADD_PREDICATE = new WidgetAditionPredicate() {

	@Override
	public boolean canBeAdded(DashBoardPanel panel, User userAdding) {
	    return true;
	}

    };

    private static Set<WidgetControllerHolder> availableWidgets = new HashSet<WidgetControllerHolder>();

    public static Set<Class<? extends WidgetController>> getAvailableWidgets(DashBoardPanel panel, User userAdding) {
	Set<Class<? extends WidgetController>> widgetControllers = new HashSet<Class<? extends WidgetController>>();
	for (WidgetControllerHolder holder : availableWidgets) {
	    if (holder.canBeAdded(panel, userAdding)) {
		widgetControllers.add(holder.getController());
	    }
	}
	return widgetControllers;
    }

    public static void registerWidget(Class<? extends WidgetController> widgetClass) {
	availableWidgets.add(new WidgetControllerHolder(widgetClass, ALWAYS_ADD_PREDICATE));
    }

    public static void registerWidget(Class<? extends WidgetController> widgetClass, WidgetAditionPredicate predicate) {
	availableWidgets.add(new WidgetControllerHolder(widgetClass, predicate));
    }

    public static interface WidgetAditionPredicate {
	public boolean canBeAdded(DashBoardPanel panel, User userAdding);
    }

    private static class WidgetControllerHolder {
	private Class<? extends WidgetController> controller;
	private WidgetAditionPredicate predicate;

	public WidgetControllerHolder(Class<? extends WidgetController> controller, WidgetAditionPredicate predicate) {
	    this.controller = controller;
	    this.predicate = predicate;
	}

	public boolean canBeAdded(DashBoardPanel panel, User userAdding) {
	    return predicate.canBeAdded(panel, userAdding);
	}

	public Class<? extends WidgetController> getController() {
	    return controller;
	}

	/*
	 * (non-Javadoc) Delegating equals and hashCode to class, since we want
	 * the class to be the identity of this object. This way only one
	 * instance of each class controller is allowed to be in the
	 * availableWidgets set at a time.
	 */
	@Override
	public boolean equals(Object obj) {
	    return controller.equals(obj);
	}

	@Override
	public int hashCode() {
	    return controller.hashCode();
	}
    }
}
