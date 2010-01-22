<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="dashBoardId" name="widget" property="dashBoardPanel.externalId"/>
<bean:define id="widgetId" name="widget" property="externalId"/>

<bean:define id="noteEdit" value="<%= "edit-note-" + widgetId %>" type="java.lang.String" toScope="page"/>
<bean:define id="noteView" value="<%= "note-" + widgetId %>" type="java.lang.String" toScope="page"/>

<logic:present name="<%= noteEdit %>" scope="request">
		<fr:form action="<%= "/dashBoardManagement.do?method=widgetSubmition&dashBoardId=" + dashBoardId + "&dashBoardWidgetId=" + widgetId%>">
			<fr:edit id="<%= noteView %>"  name="<%= noteEdit %>" slot="text" type="java.lang.String" scope="request">
				<fr:layout name="longText">
					<fr:property name="columns" value="30"/>
					<fr:property name="rows" value="10"/>
				</fr:layout>
			</fr:edit>
			<p>
				<html:submit styleClass="inputbutton">Guardar</html:submit>
			</p>
		</fr:form>
</logic:present>

<logic:notPresent name="<%= noteEdit %>">
	<p>
	<fr:view name="<%= noteView %>" property="text" type="java.lang.String"/>
	</p>
</logic:notPresent>