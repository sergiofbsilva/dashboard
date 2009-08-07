package module.dashBoard.presentationTier;

import java.util.HashMap;
import java.util.Map;

import module.dashBoard.widgets.StrutsWidget;

public class WidgetBodyResolver {

    private static Map<Class<? extends StrutsWidget>, String> bodyResolver = new HashMap<Class<? extends StrutsWidget>, String>();

    public static void register(Class<? extends StrutsWidget> widgetClass, String jspBody) {
	bodyResolver.put(widgetClass, jspBody);
    }

    public static String getBodyFor(Class<? extends StrutsWidget> widgetClass) {
	String body = bodyResolver.get(widgetClass);
	return body != null ? body : "/" + widgetClass.getName().replace(".", "/") + ".jsp";
    }
}
