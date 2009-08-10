<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<script src="<%= request.getContextPath()%>/javaScript/jquery.alerts.js" type="text/javascript"></script>
<script src="<%= request.getContextPath()%>/javaScript/alertHandlers.js" type="text/javascript"></script>

<%@page import="module.dashBoard.presentationTier.WidgetBodyResolver"%>
<%@page import="myorg.presentationTier.servlets.filters.contentRewrite.ContentContextInjectionRewriter"%>
<%@page import="pt.ist.fenixWebFramework.servlets.filters.contentRewrite.GenericChecksumRewriter"%>

<h2> DashBoard</h2>

<bean:size id="numberOfColumns" name="dashBoard" property="dashBoardColumns"/>
<bean:define id="dashBoardId" name="dashBoard" property="externalId" type="java.lang.String"/>

<ul>
	<li>
		<html:link page="/dashBoardManagement.do?method=prepareAddWidget" paramId="dashBoardId"  paramName="dashBoard" paramProperty="externalId">
			<bean:message key="link.add" bundle="MYORG_RESOURCES"/>
		</html:link>
	</li>
</ul>

<script type="text/javascript">

$(function() {

	$(".column").sortable({
			connectWith: '.column',
			stop: function(event, ui) {

				var size = <%= numberOfColumns %>;			
				var modification = "";

				for (i=0; i < size; i++) {
					portletContainersArray = $("#column-" + i).find(".portlet");
					portletsStrings = "";
						jQuery.each(portletContainersArray, function() {
							portletsStrings = portletsStrings + $(this).attr('id') + ' ';
						});
					modification = modification + i + ":" + portletsStrings;
					if (i + 1 < size) {
						modification = modification + ",";
					}

				}

				<%= "$.get(\"" + request.getContextPath() + "/dashBoardManagement.do?method=order&dashBoardId="+ dashBoardId + "\", { ordering: modification });"%>
			}
		});

		$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
		.find(".portlet-header")
			.addClass("ui-widget-header ui-corner-top").end()
		.find(".portlet-content");

		$(".portlet-header .ui-icon-close").click(function() {
			var form = $(this).nextAll("form.close");
			$.alerts.okButton = '<bean:message key="true" bundle="MYORG_RESOURCES"/>'; 
			$.alerts.cancelButton = '<bean:message key="false" bundle="MYORG_RESOURCES"/>'; 
			requestConfirmationForJQueryForm(form,'<bean:message key="message.removeWidget" bundle="DASH_BOARD_RESOURCES"/>', '<bean:message key="title.removeWidget" bundle="DASH_BOARD_RESOURCES"/>');
		});
				
		$(".portlet-header .ui-icon-pencil").click(function() {
			var form = $(this).nextAll("form.edit");
			form.submit();
		});

		$(".portlet-header .ui-icon-help").click(function() {
			var form = $(this).nextAll("form.help");
			form.submit();
			
		});
		
		$(".column").disableSelection();

		
	});
	</script>

<logic:present name="widget-help">
	<div class="highlightBox">
		<fr:view name="widget-help" property="widgetController.help"/>
	</div>	
</logic:present>	

<div id="dashboard" class="mtop15">

	<logic:iterate id="column" indexId="index" name="dashBoard" property="orderedColumns">
		<div id="<%= "column-" + index %>" class="column">
			<bean:define id="columnId" name="column" property="externalId"/>
			<logic:iterate id="widget" name="column" property="orderedWidgets" type="module.dashBoard.domain.DashBoardWidget">
				<bean:define id="widget" name="widget" toScope="request" type="module.dashBoard.domain.DashBoardWidget"/>
				<bean:define id="widgetId" name="widget" property="externalId" type="java.lang.String" />
				<div id="<%= widgetId %>" class="portlet">
					<div class="portlet-header">
						<logic:equal name="widget" property="closable" value="true">
							<span class="ui-icon ui-icon-close"></span>
							<form class="close" method="post" action="<%= request.getContextPath() + "/dashBoardManagement.do?method=removeWidgetFromColumn&dashBoardWidgetId=" + widgetId %>">
							</form>
						</logic:equal>
						<logic:equal name="widget" property="editionModeSupported" value="true">
							<span class="ui-icon ui-icon-pencil"></span>
							<form class="edit" method="post" action="<%= request.getContextPath() + "/dashBoardManagement.do?method=requestWidgetEdit&dashBoardWidgetId=" + widgetId %>">
							</form>
						</logic:equal>
						<logic:equal name="widget" property="helpModeSupported" value="true">
							<span class="ui-icon ui-icon-help"></span>
							<form class="help" method="post" action="<%= request.getContextPath() + "/dashBoardManagement.do?method=requestWidgetHelp&dashBoardWidgetId=" + widgetId %>">
							</form>
						</logic:equal>
						<fr:view name="widget" property="widgetController.class" layout="name-resolver"/>
					</div>
					<div class="portlet-content">
						<jsp:include page="<%= WidgetBodyResolver.getBodyFor(widget.getWidgetController().getClass()) %>" flush="false"/>
					</div>
				</div>
			</logic:iterate>
		</div>
	</logic:iterate>
</div>