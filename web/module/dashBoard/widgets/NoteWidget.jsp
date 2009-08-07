<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="dashBoardId" name="dashBoard" property="externalId"/>
<bean:define id="widgetId" name="widget" property="externalId"/>

<bean:define id="stateObjectId" name="widget" property="stateObjectId" type="java.lang.String"/>

<fr:form action="<%= "/dashBoardManagement.do?method=widgetSubmition&dashBoardId=" + dashBoardId + "&dashBoardWidgetId=" + widgetId%>">
	<fr:edit id="<%= "note-" + stateObjectId %>"  name="<%= "note-" + stateObjectId %>" slot="text" type="java.lang.String">
		<fr:layout name="longText">
			<fr:property name="columns" value="30"/>
			<fr:property name="rows" value="10"/>
		</fr:layout>
	</fr:edit>

<html:submit styleClass="inputbutton">Guardar</html:submit>
</fr:form>