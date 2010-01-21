package module.dashBoard.widgets;

import module.dashBoard.domain.DashBoardWidget;
import module.dashBoard.domain.Note;
import module.dashBoard.presentationTier.WidgetRequest;
import myorg.domain.User;
import myorg.util.BundleUtil;
import myorg.util.ClassNameBundle;

@ClassNameBundle(bundle = "resources/DashBoardResources", key = "widget.title.NoteWidget")
public class NoteWidget extends WidgetController {

    @Override
    public void init(DashBoardWidget widget, User user) {
	Note note = widget.getStateObject();
	if (note == null) {
	    note = new Note();
	    widget.setStateObject(note);
	}
    }

    @Override
    public void kill(DashBoardWidget widget, User user) {
	Note note = widget.getStateObject();
	note.delete();
    }

    @Override
    public void doView(WidgetRequest request) {
	DashBoardWidget widget = request.getWidget();
	request.setAttribute("note-" + widget.getExternalId(), widget.getStateObject());

    }

    @Override
    public boolean isEditionModeSupported() {
	return true;
    }

    @Override
    public void doEdit(WidgetRequest request) {
	DashBoardWidget widget = request.getWidget();
	request.setAttribute("edit-note-" + widget.getExternalId(), widget.getStateObject());
    }

    @Override
    public boolean isHelpModeSupported() {
	return true;
    }

    @Override
    public String getHelp() {
	return BundleUtil.getStringFromResourceBundle("resources/DashBoardResources", "widget.help.NoteWidget");
    }
}
