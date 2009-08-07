package module.dashBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.ist.fenixWebFramework.renderers.components.state.IViewState;
import pt.ist.fenixWebFramework.renderers.model.MetaObject;
import pt.ist.fenixWebFramework.renderers.utils.RenderUtils;
import pt.ist.fenixframework.DomainObject;

import module.dashBoard.domain.DashBoardPanel;
import myorg.domain.User;

public class WidgetRequest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private DashBoardPanel panel;
    private User currentUser;
    private DomainObject stateObject;

    public WidgetRequest(HttpServletRequest request, HttpServletResponse response, DashBoardPanel panel, User user,
	    DomainObject objectState) {
	this.request = request;
	this.response = response;
	this.panel = panel;
	this.currentUser = user;
	this.stateObject = objectState;
    }

    public DomainObject getStateObject() {
	return stateObject;
    }

    public void setStateObject(DomainObject objectState) {
	this.stateObject = objectState;
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
