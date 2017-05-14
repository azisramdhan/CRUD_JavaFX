import view.Table;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Alfatih on 20/04/2016.
 * This is the Main class of MVC
 * Use to call a View and be the central of MVC
 */
public class Main extends Application{

	/**
	 * Application Launch
	 */
	public static void main(String[] args){
		launch(args);
	}

	/**
	 * Call a view class (Table)
	 */
	@Override
	public void start(Stage stage){
		Table createView = new Table();
		createView.setView(stage);
	}

}

/**
 * Saya Azis Ramdhan Darojat berjanji tidak melakukan kecurangan seperti yang telah dispesifikasikan pada
 * Mata Kuliah PBO
 */