package module.dashBoard.domain;

import module.dashBoard.WidgetRegister;
import module.dashBoard.widgets.EasyAccessWidget;
import module.dashBoard.widgets.NoteWidget;
import module.dashBoard.widgets.TestWidget;
import myorg.domain.MyOrg;
import pt.ist.fenixWebFramework.services.Service;

public class DashBoardController extends DashBoardController_Base {

    private static boolean isInitialized = false;

    private static ThreadLocal<DashBoardController> init = null;

    private DashBoardController() {
	setMyOrg(MyOrg.getInstance());
    }

    public static DashBoardController getInstance() {
	if (init != null) {
	    return init.get();
	}

	if (!isInitialized) {
	    initialize();
	}
	final MyOrg myOrg = MyOrg.getInstance();
	return myOrg.getDashBoardController();
    }

    @Service
    public synchronized static void initialize() {
	if (!isInitialized) {
	    try {
		final MyOrg myOrg = MyOrg.getInstance();
		final DashBoardController controller = myOrg.getDashBoardController();
		if (controller == null) {
		    new DashBoardController();
		}
		init = new ThreadLocal<DashBoardController>();
		init.set(myOrg.getDashBoardController());

		registerKnownWidgets();
		isInitialized = true;
	    } finally {
		init = null;
	    }
	}
    }

    private static void registerKnownWidgets() {
	WidgetRegister.registerWidget(TestWidget.class);
	WidgetRegister.registerWidget(EasyAccessWidget.class);
	WidgetRegister.registerWidget(NoteWidget.class);
    }

}
