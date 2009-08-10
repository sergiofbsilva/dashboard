package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;
import myorg.util.ClassNameResolver;

public class TestWidget extends WidgetController {

    static {
	ClassNameResolver.registerType(TestWidget.class, "resources/DashBoardResources", "widget.title."
		+ TestWidget.class.getSimpleName());
    }

    @Override
    public void doView(WidgetRequest request) {
	request.setAttribute("xpto", request.getCurrentUser());
    }

    @Override
    public boolean isClosable() {
	return false;
    }

}
