package module.dashBoard.domain;


public class Note extends Note_Base {

    public Note() {
	super();
	setDashBoardController(DashBoardController.getInstance());
    }

    public void delete() {
	removeDashBoardController();
	deleteDomainObject();
    }

}
