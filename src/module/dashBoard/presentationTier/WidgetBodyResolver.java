package module.dashBoard.presentationTier;

import java.util.HashMap;
import java.util.Map;

import module.dashBoard.widgets.WidgetController;

public class WidgetBodyResolver {

    private static Map<Class<? extends WidgetController>, String> bodyResolver = new HashMap<Class<? extends WidgetController>, String>();

    public static void register(Class<? extends WidgetController> widgetClass, String jspBody) {
	bodyResolver.put(widgetClass, jspBody);
    }

    public static String getBodyFor(Class<? extends WidgetController> widgetClass) {
	String body = bodyResolver.get(widgetClass);
	return body != null ? body : "/" + widgetClass.getName().replace(".", "/") + ".jsp";
    }
}
