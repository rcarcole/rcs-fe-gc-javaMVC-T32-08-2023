package App;

import controller.ClienteController;
import dao.ClienteDAO;
import view.ClienteView;

public class App {

	public static void main(String[] args) {
		ClienteView view = new ClienteView();
        ClienteDAO dao = new ClienteDAO();
        ClienteController controller = new ClienteController(view, dao);
	}

}
