package module.dashBoard.widgets;

import module.dashBoard.WidgetRequest;
import module.dashBoard.domain.DashBoardWidget;
import module.dashBoard.domain.Note;
import module.dashBoard.presentationTier.DashBoardManagementAction;
import myorg.util.ClassNameResolver;

import org.apache.struts.action.ActionForward;

import pt.ist.fenixframework.DomainObject;

public class NoteWidget implements WidgetController {

    static {
	ClassNameResolver.registerType(NoteWidget.class, "resources/DashBoardResources", "widget.title."
		+ NoteWidget.class.getSimpleName());
    }

    @Override
    public void onLoad(WidgetRequest request) {
	DomainObject objectState = request.getStateObject();
	request.setAttribute("note-" + objectState.getExternalId(), objectState);

    }

    @Override
    public ActionForward widgetSubmission(WidgetRequest request) {
	return DashBoardManagementAction.forwardToDashBoard(request);
    }

    @Override
    public void init(DashBoardWidget widget) {
	Note note = widget.getStateObject();
	if (note == null) {
	    note = new Note();
	    widget.setStateObject(note);
	}
    }

    @Override
    public void kill(DashBoardWidget widget) {
	Note note = widget.getStateObject();
	note.delete();
    }

    @Override
    public void requestEdit(WidgetRequest request) {
	DomainObject objectState = request.getStateObject();
	request.setAttribute("edit-note-" + objectState.getExternalId(), objectState);
    }

}
