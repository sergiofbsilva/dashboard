<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<h2> <bean:message key="title.addWidget" bundle="DASH_BOARD_RESOURCES"/></h2>

<ul>
	<li>
		<html:link page="/dashBoardManagement.do?method=viewDashBoardPanel" paramId="dashBoardId" paramName="dashBoard" paramProperty="externalId">
			<bean:message key="link.back" bundle="MYORG_RESOURCES"/>
		</html:link>
	</li>
</ul>


<table>
<logic:iterate id="widget" name="widgets">
	<bean:define id="widgetClassName" name="widget" property="name"/>
	<tr>
		<td><fr:view name="widget" layout="name-resolver"/></td>
		<td>
			<html:link page="<%= "/dashBoardManagement.do?method=addWidget&dashBoardWidgetClass=" + widgetClassName%>" paramId="dashBoardId" paramName="dashBoard" paramProperty="externalId">
				<bean:message key="link.add" bundle="MYORG_RESOURCES"/>
			</html:link>
		</td>
	</tr>
</logic:iterate> 
</table>