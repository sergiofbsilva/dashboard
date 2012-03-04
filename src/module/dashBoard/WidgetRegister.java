/*
 * @(#)WidgetRegister.java
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
package module.dashBoard;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import module.dashBoard.domain.DashBoardPanel;
import module.dashBoard.widgets.WidgetController;
import myorg.domain.User;
import myorg.util.BundleUtil;

/**
 * 
 * @author João Neves
 * @author Paulo Abrantes
 * 
 */
public class WidgetRegister {

    public static WidgetAditionPredicate ALWAYS_ADD_PREDICATE = new WidgetAditionPredicate() {

	@Override
	public boolean canBeAdded(DashBoardPanel panel, User userAdding) {
	    return true;
	}

    };

    public static Comparator<Class<? extends WidgetController>> WIDGET_NAME_COMPARATOR = new Comparator<Class<? extends WidgetController>>() {

	@Override
	public int compare(Class<? extends WidgetController> o1, Class<? extends WidgetController> o2) {
	    return BundleUtil.getLocalizedNamedFroClass(o1).compareTo(BundleUtil.getLocalizedNamedFroClass(o2));
	}

    };

    private static Set<WidgetControllerHolder> availableWidgets = new HashSet<WidgetControllerHolder>();

    public static Set<Class<? extends WidgetController>> getAvailableWidgets(DashBoardPanel panel, User userAdding) {

	Set<Class<? extends WidgetController>> widgetControllers = new TreeSet<Class<? extends WidgetController>>(
		WIDGET_NAME_COMPARATOR);
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
	private final Class<? extends WidgetController> controller;
	private final WidgetAditionPredicate predicate;

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
