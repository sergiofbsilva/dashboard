/**
 * 
 * Javascript that deals with the generation of the dashboard.
 * There are a few conventions between this script and the viewDashBoard JSP,
 * so be careful when changing any element classes or ids.
 * 
 * 11/08/2009 Paulo Abrantes
 * 
 * @param numberOfColumns: Number of columns of the dashboard
 * @param yesLabel: The label for the yes button in the "Do you wish to delete the widget" dialog
 * @param noLabel: The label for the no button in the "Do you wish to delete the widget" dialog
 * @param removeMessage: The label that will be shown in the dialog text.
 * @param removeTitle: The label that will be shown on the title of the dialog (it supports formating to insert the name of the widget in it, example "widget {0} deletion")
 * @param orderURL: The url for the ordering
 * @param closeURL: The url for closing the widget
 * @param helpURL: The url for showing the help
 * @param errorMessage: The message that is displayed when widget deletion AJAX request failed
 */
function startDashBoard(numberOfColumns, yesLabel, noLabel, removeMessage, removeTitle, orderURL,closeURL,helpURL, errorMessage) {
	$(function() {
	$(".column").sortable({
			handle: '.portlet-header',
			cursor: 'move',
			connectWith: '.column',
			opacity: 0.9,
			stop: function(event, ui) {

				var modification = "";

				for (i=0; i < numberOfColumns; i++) {
					portletContainersArray = $("#column-" + i).find(".portlet");
					portletsStrings = "";
						jQuery.each(portletContainersArray, function() {
							portletsStrings = portletsStrings + $(this).attr('id') + ' ';
						});
					modification = modification + i + ":" + portletsStrings;
					if (i + 1 < numberOfColumns) {
						modification = modification + ",";
					}

				}
				$.get(orderURL, { ordering: modification });
			}
		});

		$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
		.find(".portlet-header")
			.addClass("ui-widget-header ui-corner-top").end()
		.find(".portlet-content");

		$(".portlet-header .ui-icon-close").click(function() {
			var widget = $(this).parent().parent();
			var widgetName = getWidgetName(widget);
			var title = formatString(removeTitle,[widgetName])
		
			$.alerts.okButton = yesLabel; 
			$.alerts.cancelButton = noLabel; 
			$.alerts.overlayOpacity= 0.4;
			$.alerts.overlayColor= '#333';
			jConfirm(removeMessage, 
					title,function(userInput) {
				  if(userInput) {
					  $.getJSON(closeURL,
						 	    { dashBoardWidgetId: widget.attr('id') },
						    	function(data) { 
									if(data['status'] == 'OK') {
							 	   		 widget.fadeOut();  
							 	   		/* widget.children(".portlet-content").slideUp("slow", function callback() { widget.fadeOut(); }); */
						 	    	} else {
						 	    		$("#dashBoardMessageContainer").empty();
										$("#dashBoardMessageContainer").append('<div class="errorBox">' + errorMessage + '</div>');
						 	    	}});
						 			
			      }
		        });

		});
				
		$(".portlet-header .ui-icon-pencil").click(function() {
			var form = $(this).nextAll("form.edit");
			form.submit();
		});

		$(".portlet-header .ui-icon-help").click(function() {
			var widget = $(this).parent().parent();
			$.getJSON(helpURL,
				 	    { dashBoardWidgetId: widget.attr('id') },
				    	function(data) { 
				 	    	$("#dashBoardMessageContainer").empty();
							$("#dashBoardMessageContainer").append('<div class="highlightBox" style="display: none;"><span class="ui-icon ui-icon-closethick" style="float: right;"></span>' + data['helpText'] + ' </div>')
							.find(".highlightBox").slideDown("slow").end()
							.find(".ui-icon").click(function() {
								$("#dashBoardMessageContainer .highlightBox").slideUp("slow");
							}).end();
				 	    }
				 	    );
		});

		$(".column").disableSelection();	
	});
}

function getWidgetName(widget) {
	return widget.children(".portlet-header").children(".widgetName").text();
}