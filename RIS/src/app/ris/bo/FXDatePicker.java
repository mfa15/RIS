package app.ris.bo;

import app.ris.src.EmployeeRegistration;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.JDialog;

public class FXDatePicker {

    private String selectedDate = "";

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }
    private JDialog frame;
    private DatePicker inPicker;
//    private DatePicker fmPicker;
//    private DatePicker toPicker;
    private static final String pattern = "dd-MMM-yy";

    enum DateParameterType {
        FROM_DATE, TO_DATE
    };
    JFXPanel fxPanel = new JFXPanel();

    public static void main(String... args) {

        SwingUtilities.invokeLater(() -> {

            new FXDatePicker().initAndShowGUI();
        });
    }

    /*
	 * Builds and displays the JFrame with the JFXPanel.
	 * This method is invoked on the Swing Event Dispatch Thread.
     */
    public void initAndShowGUI() {

        frame = new JDialog();

        frame.add(fxPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(EmployeeRegistration.jPanel5);

//frame.setLocation(new Point(400, 200));
//		Platform.runLater(() -> {
//
//			
//		});
        frame.repaint();
        frame.requestFocus();
        fxPanel.setScene(createScene());
    }

    private Scene createScene() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Title
        Label title1 = new Label("Enter Employee Date of Birth");
        //Label title2 = new Label("In case of manual entry use format "+pattern);
        VBox titleVb = new VBox();
        titleVb.setAlignment(Pos.CENTER);
        titleVb.getChildren().addAll(title1);

        // Input date picker
        Label inPickLabel = new Label("Date of Birth:");
        inPicker = new DatePicker();
        inPicker.setPromptText(pattern);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        StringConverter<LocalDate> converter
                = new LocalDateStringConverter(formatter, null);
        inPicker.setConverter(converter);
        grid.add(inPickLabel, 0, 0);
        grid.add(inPicker, 1, 0);

        // Cell factory for picker date validation
        inPicker.setDayCellFactory(getCustomDateCellFactory(null));

        // Button
        Button btn = new Button("Ok");
        //btn.setTooltip(new Tooltip("Find if the input date is within the from and to dates."));
        btn.setOnAction(event -> {

            buttonActionListenerRoutine();
        });
        HBox btnHb = new HBox();
        btnHb.setAlignment(Pos.CENTER);
        btnHb.getChildren().add(btn);

        // Vbox and scene
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.getChildren().addAll(titleVb, grid, btnHb);

        return new Scene(vbox);
    }

    /* 
	 * Returns a Callback with DateCell as return value for the DatePicker. The
	 * DateCell determines that the DatePicker date values are disabled. This applies
	 * for the three date picker widgets.
	 * This allows the following validation:
	 * * The selected date cannot be greater than today.
	 * * From-date cannot be greater than to-date.
     */
    private Callback<DatePicker, DateCell> getCustomDateCellFactory(DateParameterType dateParamType) {

        Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(DatePicker datePicker) {

                return new DateCell() {

                    @Override
                    public void updateItem(LocalDate select, boolean empty) {

                        super.updateItem(select, empty);

                        // Date cannot be after today.
                        // This applies for all the date pickers.
                        if (select.isAfter(LocalDate.now())) {

                            setDisable(true);
                            return;
                        }

                    }
                };
            }
        };

        return dayCellFactory;
    }

    // Validate button's action listener routine.
    private void buttonActionListenerRoutine() {
        selectedDate = inPicker.getValue().getYear() + "-" + inPicker.getValue().getMonthValue() + "-"
                + inPicker.getValue().getDayOfMonth();
        System.out.println(selectedDate);
       // fxPanel.getScene().getWindow().hide();
        frame.dispose();
        frame = null;
    }

}
