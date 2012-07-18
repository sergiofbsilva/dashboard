/*
 * @(#)DashBoardColumnBean.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Paulo Abrantes
 * 
 */
public class DashBoardColumnBean {

    private int order;
    private List<DashBoardWidget> widgets;

    public DashBoardColumnBean(int order) {
	this.order = order;
	this.widgets = new ArrayList<DashBoardWidget>();
    }

    public void addWidget(DashBoardWidget widget) {
	widgets.add(widget);
    }

    public int getOrder() {
	return order;
    }

    public void setOrder(int order) {
	this.order = order;
    }

    public List<DashBoardWidget> getWidgets() {
	return widgets;
    }

    public void setWidgets(List<DashBoardWidget> widgets) {
	this.widgets = widgets;
    }

}
