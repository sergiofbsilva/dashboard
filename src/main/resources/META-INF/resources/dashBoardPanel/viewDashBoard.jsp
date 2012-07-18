<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>

<bean:define id="theme" name="virtualHost" property="theme.name"/>
					
<div id="preload" style="display: none;">
	<img src="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath() + "/CSS/" + theme + "jqTheme/images/autocomplete.gif"%>"/>
</div>

<%@page import="pt.ist.bennu.core.util.BundleUtil"%><script src="<%= request.getContextPath()%>/javaScript/jquery.alerts.js" type="text/javascript"></script>
<script src="<%= request.getContextPath()%>/javaScript/alertHandlers.js" type="text/javascript"></script>

<%@page import="module.dashBoard.presentationTier.WidgetBodyResolver"%>
<%@page import="pt.ist.bennu.core.presentationTier.servlets.filters.contentRewrite.ContentContextInjectionRewriter"%>
<%@page import="pt.ist.fenixWebFramework.servlets.filters.contentRewrite.GenericChecksumRewriter"%>

<h2> <fr:view name="dashBoard" property="name"/> </h2>

<bean:size id="numberOfColumns" name="dashBoard" property="dashBoardColumns"/>
<bean:define id="dashBoardId" name="dashBoard" property="externalId" type="java.lang.String"/>

<ul>
	<li>
		<html:link page="/dashBoardManagement.do?method=prepareAddWidget" paramId="dashBoardId"  paramName="dashBoard" paramProperty="externalId">
			<bean:message key="link.add" bundle="MYORG_RESOURCES"/>
		</html:link>
	</li>
</ul>


<script  type="text/javascript" src="<%=  request.getContextPath() + "/javaScript/dashboard.js"%>"></script>
<script  type="text/javascript">

<%
	String title = BundleUtil.getStringFromResourceBundle("resources.DashBoardResources","title.removeWidget");
%>

startDashBoard(<%= numberOfColumns %>, 
				   '<bean:message key="true" bundle="MYORG_RESOURCES"/>', 
				   '<bean:message key="false" bundle="MYORG_RESOURCES"/>',
				   '<bean:message key="message.removeWidget" bundle="DASH_BOARD_RESOURCES"/>',
				   '<%= title %>', 
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=order&dashBoardId="+ dashBoardId %>',
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=removeWidgetFromColumn" %>',
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=requestWidgetHelp" %>',
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=viewWidget" %>',
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=editWidget" %>',
				   '<%= request.getContextPath() + "/dashBoardManagement.do?method=editOptions" %>',
				   '<bean:message key="error.removeWidget" bundle="DASH_BOARD_RESOURCES"/>',
				   '<bean:message key="error.loadingWidget" bundle="DASH_BOARD_RESOURCES"/>');
</script>

<div id="dashBoardMessageContainer">
	<html:messages id="message" message="true" bundle="DASH_BOARD_RESOURCES">
		<div class="errorBox"> <bean:write name="message" /> </div>
	</html:messages>
</div>


<div id="dashboard" class="mtop15">

	<logic:iterate id="column" indexId="index" name="dashBoard" property="orderedColumns">
		<div id="<%= "column-" + index %>" class="column">
			<bean:define id="columnId" name="column" property="externalId"/>
			<logic:iterate id="widget" name="column" property="orderedWidgets" type="module.dashBoard.domain.DashBoardWidget">
				<bean:define id="widget" name="widget" toScope="request" type="module.dashBoard.domain.DashBoardWidget"/>
				<bean:define id="widgetId" name="widget" property="externalId" type="java.lang.String" />
				<div id="<%= widgetId %>" class="portlet">
					<div class="portlet-header" style="cursor: move;">
						<logic:equal name="widget" property="closable" value="true">
							<span class="ui-icon ui-icon-close" title="<bean:message key="icon.title.close" bundle="DASH_BOARD_RESOURCES"/>"></span>
						</logic:equal>
						<logic:equal name="widget" property="optionsModeSupported" value="true">
							<span class="ui-icon ui-icon-wrench" title="<bean:message key="icon.title.options" bundle="DASH_BOARD_RESOURCES"/>"></span>
						</logic:equal>
						<logic:equal name="widget" property="editionModeSupported" value="true">
							<span class="ui-icon ui-icon-pencil" title="<bean:message key="icon.title.edit" bundle="DASH_BOARD_RESOURCES"/>"></span>
						</logic:equal>
						<logic:equal name="widget" property="helpModeSupported" value="true">
							<span class="ui-icon ui-icon-help" title="<bean:message key="icon.title.help" bundle="DASH_BOARD_RESOURCES"/>"></span>
						</logic:equal>
						<span class="widgetName"><fr:view name="widget" property="widgetController.class" layout="name-resolver"/></span>
					</div>
					<div class="portlet-content">
						<div style="text-align: center">
							<bean:message key="label.widget.loading" bundle="DASH_BOARD_RESOURCES"/>
						</div>
					 	<div style="background-image: url(<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath() + "/CSS/" + theme + "/images/autocomplete.gif"%>); background-position: center; background-repeat: no-repeat">
							&nbsp;
						</div> 
					</div>
				</div>
			</logic:iterate>
		</div>
	</logic:iterate>
</div>

<div class="clear"></div>
