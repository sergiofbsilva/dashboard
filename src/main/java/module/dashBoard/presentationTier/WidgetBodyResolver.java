/*
 * @(#)WidgetBodyResolver.java
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
package module.dashBoard.presentationTier;

import java.util.HashMap;
import java.util.Map;

import module.dashBoard.widgets.WidgetController;

/**
 * 
 * @author Paulo Abrantes
 * 
 */
public class WidgetBodyResolver {

	private static Map<Class<? extends WidgetController>, String> bodyResolver =
			new HashMap<Class<? extends WidgetController>, String>();

	public static void register(Class<? extends WidgetController> widgetClass, String jspBody) {
		bodyResolver.put(widgetClass, jspBody);
	}

	public static String getBodyFor(Class<? extends WidgetController> widgetClass) {
		String body = bodyResolver.get(widgetClass);
		return body != null ? body : "/" + widgetClass.getName().replace(".", "/") + ".jsp";
	}
}
