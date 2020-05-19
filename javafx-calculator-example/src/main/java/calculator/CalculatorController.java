package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (digitPressed.equals(".")) {
            if (display.getText().contains("."))
                return;
        }
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        switch(operatorPressed) {
            case "=":
                if (operator.isEmpty()) {
                    return;
                }
                double number2 = Double.parseDouble(display.getText());
                double result = calculator.calculate(number1, number2, operator);
                display.setText(String.format("%.0f", result));
                operator = "";
                break;
            case "AC":
                display.setText("0");
                startNumber = false;
                operator = "";
                break;
            case "±":
                if (display.getText().startsWith("-")) {
                    display.setText(display.getText().substring(1));
                } else {
                    display.setText("-" + display.getText());
                }
                break;
            default:
                if (! operator.isEmpty()) {
                    return;
                }
                number1 = Double.parseDouble(display.getText());
                operator = operatorPressed;
                startNumber = true;
                break;
        }
    }

}
