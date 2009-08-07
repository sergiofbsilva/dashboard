<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



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
			.addClass("ui-corner-top")
			.prepend('<span class="ui-icon ui-icon-closethick"></span>')
			.end()
		.find(".portlet-content");

		$(".portlet-header .ui-icon-closethick").click(function() {
			var form = $(this).next();
			var columnId = $(this).parent().parent().parent().attr('id');
			form.children("[name=dashBoardColumnIndex]").attr('value',columnId.substring(columnId.indexOf("-")+1));
			form.submit();
		});

		$(".column").disableSelection();

		
	});
	</script>
	
<div id="dashboard" class="mtop15">

	<logic:iterate id="column" indexId="index" name="dashBoard" property="orderedColumns">
		<div id="<%= "column-" + index %>" class="column">
			<bean:define id="columnId" name="column" property="externalId"/>
			<logic:iterate id="widget" name="column" property="widgets" type="module.dashBoard.domain.DashBoardWidget">
				<bean:define id="widget" name="widget" toScope="request" type="module.dashBoard.domain.DashBoardWidget"/>
				<bean:define id="widgetId" name="widget" property="externalId" type="java.lang.String" />
				<div id="<%= widgetId %>" class="portlet">
					<div class="portlet-header">
						<fr:view name="widget" property="name"/>
						<fr:form action="<%= "/dashBoardManagement.do?method=removeWidgetFromColumn&dashBoardId=" + dashBoardId + "&dashBoardWidgetId=" + widgetId %>">
							<html:hidden property="dashBoardColumnIndex" value=""/>
						</fr:form>
					</div>
					<div class="portlet-content">
						<jsp:include page="<%= WidgetBodyResolver.getBodyFor(widget.getController().getClass()) %>" flush="false"/>
					</div>
				</div>
			</logic:iterate>
		</div>
	</logic:iterate>
</div>