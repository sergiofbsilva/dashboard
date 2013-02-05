/*
 * @(#)WidgetLayoutContext.java
 *
 * Copyright 2010 Instituto Superior Tecnico
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

import module.dashBoard.domain.DashBoardWidget;

import org.apache.struts.action.ActionForward;

import pt.ist.bennu.core.presentationTier.Context;

/**
 * 
 * @author Paulo Abrantes
 * 
 */
public class WidgetLayoutContext extends Context {

    private String widgetBody;

    @Override
    public ActionForward forward(String forward) {
        return new ActionForward(this.widgetBody);
    }

    public ActionForward forward() {
        return forward("");
    }

    public WidgetLayoutContext(DashBoardWidget widget) {
        this.widgetBody = WidgetBodyResolver.getBodyFor(widget.getWidgetController().getClass());
    }
}
