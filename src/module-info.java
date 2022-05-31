module miniProjet {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	opens application.Models to javafx.fxml, javafx.base;
	opens application.log to javafx.fxml;
	opens application.dashboard to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}
