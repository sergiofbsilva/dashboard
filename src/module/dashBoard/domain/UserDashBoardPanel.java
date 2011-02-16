package module.dashBoard.domain;

import myorg.domain.User;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public class UserDashBoardPanel extends UserDashBoardPanel_Base {

    protected UserDashBoardPanel() {
	super();
    }

    public UserDashBoardPanel(MultiLanguageString name, User user) {
	super();
	setName(name);
	setUser(user);
    }

    @Override
    public boolean isAccessibleToUser(User user) {
	return user != null && user == getUser();
    }

    public void delete() {
	removeUser();
	removeDashBoardController();
	for (final DashBoardColumn dashBoardColumn : getDashBoardColumnsSet()) {
	    dashBoardColumn.delete();
	}
	deleteDomainObject();
    }

}
