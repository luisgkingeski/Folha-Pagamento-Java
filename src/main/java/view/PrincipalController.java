package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Gerente;
import model.Administrativo;
import model.Vendedor;
import model.Funcionario;

public class PrincipalController implements Initializable {

    private List<Funcionario> lstFuncs = new ArrayList<Funcionario>();
    private double totalPagamento = 0;
    private double totalExtras = 0;
    private double totalSalario = 0;
    private final NumberFormat nf
            = NumberFormat.getInstance(Locale.getDefault());
    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    @FXML
    private TextField txtFldNome;
    @FXML
    private TextField txtFldSalario;
    @FXML
    private TextField txtFldExtra;
    @FXML
    private RadioButton rdBtnMasc;
    @FXML
    private RadioButton rdBtnFem;
    @FXML
    private RadioButton rdBtnAdm;
    @FXML
    private RadioButton rdBtnVen;
    @FXML
    private RadioButton rdBtnGer;
    @FXML
    private Button btnAdicionar;
    @FXML
    private HBox hbxExtra;
    @FXML
    private TableView tblVwFuncs;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private ToggleGroup tipos;
    @FXML
    private Label lblNumFunc;
    @FXML
    private Label lblTotPag;
    @FXML
    private Label lblTotExt;
    @FXML
    private Label lblExtra;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblTotSal;

    private String getSexo() {
        if (rdBtnMasc.isSelected()) {
            return "M";
        } else {
            return "F";
        }
    }

    @FXML
    private void limpaCampos() {
        txtFldNome.clear();
        txtFldSalario.clear();
        txtFldExtra.clear();
    }

    @FXML
    private void atualizaCampos() {

        totalExtras += lstFuncs.get(0).getExtras();
        totalPagamento += lstFuncs.get(0).getSalarioFinal();
        totalSalario += lstFuncs.get(0).getSalario();

        tblVwFuncs.setItems(FXCollections.observableList(lstFuncs));
        lblNumFunc.setText((String) (nf.format(lstFuncs.size())));
        lblTotExt.setText((String) ("R$ " + nf.format(totalExtras)));
        lblTotSal.setText((String) ("R$ " + nf.format(totalSalario)));
        lblTotPag.setText((String) ("R$ " + nf.format(totalPagamento)));

    }

    @FXML
    private void btnAdicionarClick(Event event) {

        try {
            if (rdBtnAdm.isSelected()) {
                lstFuncs.add(0, new Administrativo(txtFldNome.getText(), nf.parse(txtFldSalario.getText()).doubleValue(), getSexo()));
                limpaCampos();
            } else if (rdBtnGer.isSelected()) {
                lstFuncs.add(0, new Gerente(txtFldNome.getText(), nf.parse(txtFldSalario.getText()).doubleValue(), getSexo(), nf.parse(txtFldExtra.getText()).doubleValue()));
                limpaCampos();
            } else if (rdBtnVen.isSelected()) {
                lstFuncs.add(0, new Vendedor(txtFldNome.getText(), nf.parse(txtFldSalario.getText()).doubleValue(), getSexo(), nf.parse(txtFldExtra.getText()).doubleValue()));
                limpaCampos();
            }
        } catch (ParseException ex) {

        }
        if (!lstFuncs.isEmpty()) {
            atualizaCampos();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtFldNome.textProperty().addListener(listenerNome);
        txtFldSalario.textProperty().addListener(listenerSalario);
        txtFldExtra.textProperty().addListener(listenerExtra);

        tipos.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (rdBtnVen.isSelected()) {
                    lblExtra.setText("Vendas: ");
                    hbxExtra.setVisible(true);
                    btnAdicionar.disableProperty().bind(txtFldNome.textProperty().isEmpty().or(txtFldSalario.textProperty().isEmpty()).or(txtFldExtra.textProperty().isEmpty()));
                } else if (rdBtnGer.isSelected()) {
                    lblExtra.setText("Bonificação: ");
                    hbxExtra.setVisible(true);
                    btnAdicionar.disableProperty().bind(txtFldNome.textProperty().isEmpty().or(txtFldSalario.textProperty().isEmpty()).or(txtFldExtra.textProperty().isEmpty()));
                } else {
                    hbxExtra.setVisible(false);
                    btnAdicionar.disableProperty().bind(txtFldNome.textProperty().isEmpty().or(txtFldSalario.textProperty().isEmpty()));
                }
            }
        });
    }
    private final ChangeListener<? super String> listenerNome
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("([A-Z].*\\s*)*")
                && !newValue.isEmpty()) {
                    txtFldNome.setText(oldValue);
                } else {
                    txtFldNome.setText(newValue);
                }
            };
    private final ChangeListener<? super String> listenerSalario
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                && !newValue.isEmpty()) {
                    txtFldSalario.setText(oldValue);
                } else {
                    txtFldSalario.setText(newValue);
                }
            };
    private final ChangeListener<? super String> listenerExtra
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                && !newValue.isEmpty()) {
                    txtFldExtra.setText(oldValue);
                } else {
                    txtFldExtra.setText(newValue);
                }
            };

}
