<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<h2> Adicionar ao DashBoard </h2>

<ul>
	<li>
		<html:link page="/dashBoardManagement.do?method=viewDashBoardPanel" paramId="dashBoardId" paramName="dashBoard" paramProperty="externalId">
			<bean:message key="link.back" bundle="MYORG_RESOURCES"/>
		</html:link>
	</li>
</ul>


<table>
<logic:iterate id="widget" name="widgets">
	<bean:define id="widgetId" name="widget" property="externalId"/>
	<tr>
		<td><fr:view name="widget" property="name"/></td>
		<td>
			<html:link page="<%= "/dashBoardManagement.do?method=addWidget&dashBoardWidgetId=" + widgetId%>" paramId="dashBoardId" paramName="dashBoard" paramProperty="externalId">
				<bean:message key="link.add" bundle="MYORG_RESOURCES"/>
			</html:link>
		</td>
	</tr>
</logic:iterate> 
</table>