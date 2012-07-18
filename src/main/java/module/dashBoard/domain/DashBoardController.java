/*
 * @(#)DashBoardController.java
 *
 * Copyright 2009 Instituto Superior Tecnico
 * Founding Authors: Paulo Abrantes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the Dashboard Module.
 *
 *   The Dashboard Module is free software: you can
 *   redistribute it and/or modify it under the terms of the GNU Lesser General
 *   Public License as published by the Free Software Foundation, either version 
 *   3 of the License, or (at your option) any later version.
 *
 *   The Dashboard Module is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with the Dashboard Module. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package module.dashBoard.domain;

import module.dashBoard.WidgetRegister;
import module.dashBoard.widgets.NoteWidget;
import pt.ist.bennu.core.domain.ModuleInitializer;
import pt.ist.bennu.core.domain.MyOrg;
import pt.ist.fenixWebFramework.services.Service;

/**
 * 
 * @author Paulo Abrantes
 * 
 */
public class DashBoardController extends DashBoardController_Base implements ModuleInitializer {

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

		isInitialized = true;
	    } finally {
		init = null;
	    }
	}
    }

    @Override
    public void init(MyOrg root) {
	WidgetRegister.registerWidget(NoteWidget.class);
    }

}
