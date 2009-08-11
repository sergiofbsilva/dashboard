package module.dashBoard.widgets;

import org.apache.struts.action.ActionForward;

import module.dashBoard.domain.DashBoardWidget;
import module.dashBoard.presentationTier.DashBoardManagementAction;
import module.dashBoard.presentationTier.WidgetRequest;
import myorg.domain.User;

public abstract class WidgetController {

    /**
     * This method is called when a new DashBoardWidget is being created. The
     * controller is initiated and it can be used for example to store something
     * in the StateObject.
     * 
     * 
     * @param widget
     *            The widget that will store this WidgetController
     * @param user
     *            The user that is creating the widget
     */
    public void init(DashBoardWidget widget, User user) {

    }

    /**
     * This method is called when a DashBoardWidget is being deleted. The
     * controller is killed this is used for example to also delete the
     * StateObject
     * 
     * @param widget
     *            The widget that is being deleted
     * @param user
     *            The user that is deleting the widget
     */
    public void kill(DashBoardWidget widget, User user) {

    }

    /**
     * This method is called when there is a request to view a widget in the
     * DashBoardPanel
     * 
     * @param request
     */

    public abstract void doView(WidgetRequest request);

    /**
     * This method is called when there is a request to edit a widget in the
     * DashBoardPanel
     * 
     * By default throws UnsupportedOperationException.
     * 
     * @param request
     */
    public void doEdit(WidgetRequest request) {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is called when there is a request to view the widget's help
     * in the DashBoardPanel
     * 
     * By default throws UnsupportedOperationException.
     * 
     * @param request
     */
    public void doHelp(WidgetRequest request) {
	throw new UnsupportedOperationException();
    }

    /**
     * This method is called when there is a request to submit the widget in the
     * DashBoardPanel.
     * 
     * Returns the ActionForward to where the client should be sent after
     * submit.
     * 
     * By default returns to the dashBoard view.
     * 
     * @param request
     */
    public ActionForward doSubmit(WidgetRequest request) {
	return DashBoardManagementAction.forwardToDashBoard(request);
    }

    /**
     * This allows the DashBoardPanel to know if the edit icon (and it's
     * functionality) should be rendered in the DashBoardWidget or not.
     * 
     */
    public boolean isEditionModeSupported() {
	return false;
    }

    /**
     * This allows the DashBoardPanel to know if the help icon (and it's
     * functionality) should be rendered in the DashBoardWidget or not.
     * 
     */
    public boolean isHelpModeSupported() {
	return false;
    }

    /**
     * This contains the help text that will be used when helpMode is supported
     * 
     */
    public String getHelp() {
	return null;
    }

    /**
     * This allows the DashBoardPanel to know if the close icon (and it's
     * functionality) should be rendered in the DashBoardWidget or not.
     * 
     */
    public boolean isClosable() {
	return true;
    }

}
