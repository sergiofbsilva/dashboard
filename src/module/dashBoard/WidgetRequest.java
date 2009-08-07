package module.dashBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.ist.fenixWebFramework.renderers.components.state.IViewState;
import pt.ist.fenixWebFramework.renderers.model.MetaObject;
import pt.ist.fenixWebFramework.renderers.utils.RenderUtils;

import module.dashBoard.domain.DashBoardPanel;
import myorg.domain.User;

public class WidgetRequest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private DashBoardPanel panel;
    private User currentUser;

    public WidgetRequest(HttpServletRequest request, HttpServletResponse response, DashBoardPanel panel, User user) {
	this.request = request;
	this.response = response;
	this.panel = panel;
	this.currentUser = user;
    }

    public User getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
    }

    public HttpServletRequest getRequest() {
	return request;
    }

    public void setRequest(HttpServletRequest request) {
	this.request = request;
    }

    public HttpServletResponse getResponse() {
	return response;
    }

    public void setResponse(HttpServletResponse response) {
	this.response = response;
    }

    public DashBoardPanel getPanel() {
	return panel;
    }

    public void setPanel(DashBoardPanel panel) {
	this.panel = panel;
    }

    public void setAttribute(String name, Object object) {
	request.setAttribute(name, object);
    }

    public <T extends Object> T getRenderedObject(final String id) {
	final IViewState viewState = RenderUtils.getViewState(id);
	return (T) getRenderedObject(viewState);
    }

    private <T extends Object> T getRenderedObject(final IViewState viewState) {
	if (viewState != null) {
	    MetaObject metaObject = viewState.getMetaObject();
	    if (metaObject != null) {
		return (T) metaObject.getObject();
	    }
	}
	return null;
    }

}
