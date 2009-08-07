package module.dashBoard.presentationTier;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dashBoard.WidgetRegister;
import module.dashBoard.WidgetRequest;
import module.dashBoard.domain.DashBoardColumn;
import module.dashBoard.domain.DashBoardColumnBean;
import module.dashBoard.domain.DashBoardController;
import module.dashBoard.domain.DashBoardPanel;
import module.dashBoard.domain.DashBoardWidget;
import module.dashBoard.widgets.WidgetController;
import module.dashBoard.widgets.TestWidget;
import myorg.applicationTier.Authenticate.UserView;
import myorg.domain.User;
import myorg.presentationTier.actions.ContextBaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixWebFramework.servlets.filters.contentRewrite.GenericChecksumRewriter;
import pt.ist.fenixWebFramework.servlets.filters.contentRewrite.RequestChecksumFilter;
import pt.ist.fenixWebFramework.servlets.filters.contentRewrite.RequestChecksumFilter.ChecksumPredicate;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

@Mapping(path = "/dashBoardManagement")
public class DashBoardManagementAction extends ContextBaseAction {

    static {
	RequestChecksumFilter.registerFilterRule(new ChecksumPredicate() {
	    public boolean shouldFilter(HttpServletRequest httpServletRequest) {
		return !(httpServletRequest.getRequestURI().endsWith("/dashBoardManagement.do")
			&& httpServletRequest.getQueryString() != null && httpServletRequest.getQueryString().contains(
			"method=order"));
	    }
	});
    }

    public ActionForward order(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) throws Exception {

	DashBoardPanel panel = getDomainObject(request, "dashBoardId");

	List<DashBoardColumnBean> beans = new ArrayList<DashBoardColumnBean>();
	String modification = request.getParameter("ordering");
	String[] columns = modification.split(",");

	for (String column : columns) {
	    String[] values = column.split(":");
	    int columnIndex = Integer.valueOf(values[0]);
	    DashBoardColumnBean dashBoardColumnBean = new DashBoardColumnBean(columnIndex);
	    if (values.length == 2) {
		dashBoardColumnBean.setWidgets(getWidgets(values[1]));
	    }
	    beans.add(dashBoardColumnBean);
	}

	panel.edit(beans);
	return null;
    }

    public static ActionForward forwardToDashBoard(WidgetRequest request) {
	return forwardToDashBoard(request.getPanel(), request.getRequest());
    }

    public static ActionForward forwardToDashBoard(DashBoardPanel panel, HttpServletRequest request) {
	ActionForward forward = new ActionForward();
	forward.setRedirect(true);
	String realPath = "/dashBoardManagement.do?method=viewDashBoardPanel&dashBoardId=" + panel.getExternalId() + "&"
		+ CONTEXT_PATH + "=" + getContext(request).getPath();
	forward.setPath(realPath + "&" + GenericChecksumRewriter.CHECKSUM_ATTRIBUTE_NAME + "="
		+ GenericChecksumRewriter.calculateChecksum(request.getContextPath() + realPath));
	return forward;
    }

    private List<DashBoardWidget> getWidgets(String column) {
	List<DashBoardWidget> widgetsInColumn = new ArrayList<DashBoardWidget>();
	for (String externalId : column.substring(0, column.length()).split(" ")) {
	    DashBoardWidget widget = AbstractDomainObject.fromExternalId(externalId);
	    widgetsInColumn.add(widget);
	}
	return widgetsInColumn;
    }

    public ActionForward viewDashBoardPanel(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {

	DashBoardPanel panel = getDomainObject(request, "dashBoardId");
	request.setAttribute("dashBoard", panel);
	User currentUser = UserView.getCurrentUser();

	for (DashBoardWidget widget : panel.getWidgetsSet()) {
	    WidgetRequest widgetRequest = new WidgetRequest(request, response, panel, currentUser, widget.getStateObject());
	    widget.getWidgetController().onLoad(widgetRequest);
	}
	return forward(request, "/dashBoardPanel/viewDashBoard.jsp");
    }

    public ActionForward removeWidgetFromColumn(final ActionMapping mapping, final ActionForm form,
	    final HttpServletRequest request, final HttpServletResponse response) {

	DashBoardWidget widget = getDomainObject(request, "dashBoardWidgetId");
	DashBoardPanel dashBoardPanel = widget.getDashBoardColumn().getDashBoardPanel();
	widget.delete();
	return forwardToDashBoard(dashBoardPanel, request);
    }

    public ActionForward prepareAddWidget(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {

	DashBoardPanel panel = getDomainObject(request, "dashBoardId");
	request.setAttribute("dashBoard", panel);
	request.setAttribute("widgets", WidgetRegister.getAvailableWidgets());
	return forward(request, "/dashBoardPanel/addWidget.jsp");
    }

    public ActionForward addWidget(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {

	String widgetClassName = request.getParameter("dashBoardWidgetClass");
	Class<? extends WidgetController> className = null;
	try {
	    className = (Class<? extends WidgetController>) Class.forName(widgetClassName);
	} catch (Exception e) {
	    // TODO ADD ERROR MESSAGE
	    // and return
	    e.printStackTrace();
	}

	DashBoardWidget widget = DashBoardWidget.newWidget(className);
	DashBoardPanel panel = getDomainObject(request, "dashBoardId");
	panel.addWidgetToColumn(0, widget);
	return forwardToDashBoard(panel, request);
    }

    public ActionForward widgetSubmition(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {

	DashBoardPanel panel = getDomainObject(request, "dashBoardId");
	DashBoardWidget widget = getDomainObject(request, "dashBoardWidgetId");
	WidgetController strutsWidget = widget.getWidgetController();
	return strutsWidget.widgetSubmission(new WidgetRequest(request, response, panel, UserView.getCurrentUser(), widget
		.getStateObject()));
    }

    public final ActionForward doTest(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
	    final HttpServletResponse response) {

	DashBoardPanel panel = Test();

	return forwardToDashBoard(panel, request);
    }

    @Service
    private DashBoardPanel Test() {
	List<DashBoardPanel> panels = DashBoardController.getInstance().getPanels();
	if (!panels.isEmpty()) {
	    return panels.get(0);
	}

	DashBoardPanel panel = new DashBoardPanel();
	return panel;
    }
}
