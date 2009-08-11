package module.dashBoard.presentationTier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.ist.fenixWebFramework.renderers.components.state.IViewState;
import pt.ist.fenixWebFramework.renderers.model.MetaObject;
import pt.ist.fenixWebFramework.renderers.utils.RenderUtils;
import pt.ist.fenixframework.DomainObject;

import module.dashBoard.domain.DashBoardPanel;
import module.dashBoard.domain.DashBoardWidget;
import myorg.domain.User;

public class WidgetRequest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private User currentUser;
    private DashBoardWidget widget;

    public WidgetRequest(HttpServletRequest request, HttpServletResponse response, DashBoardWidget widget, User user) {
	this.request = request;
	this.response = response;
	this.currentUser = user;
	this.widget = widget;
    }

    public DomainObject getStateObject() {
	return this.widget.getStateObject();
    }

    public User getCurrentUser() {
	return currentUser;
    }

    protected HttpServletRequest getRequest() {
	return request;
    }

    public HttpServletResponse getResponse() {
	return response;
    }

    public DashBoardPanel getPanel() {
	return this.widget.getDashBoardPanel();
    }

    public void setAttribute(String name, Object object) {
	request.setAttribute(name, object);
    }

    public DashBoardWidget getWidget() {
	return widget;
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
