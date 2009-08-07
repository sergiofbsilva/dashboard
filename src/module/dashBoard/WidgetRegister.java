package module.dashBoard;

import java.util.HashSet;
import java.util.Set;

import module.dashBoard.widgets.WidgetController;

public class WidgetRegister {

    private static Set<Class<? extends WidgetController>> availableWidgets = new HashSet<Class<? extends WidgetController>>();

    public static Set<Class<? extends WidgetController>> getAvailableWidgets() {
	return availableWidgets;
    }

    public static void registerWidget(Class<? extends WidgetController> widgetClass) {
	availableWidgets.add(widgetClass);
    }
}
