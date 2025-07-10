package Interfas;

import javax.swing.*;
import java.awt.*;

public class Resultado extends JFrame {
    private JPanel principal;
    private JTextArea historialArea;

    public Resultado(java.util.List<String> historial) {
        setTitle("Historial de Transacciones");
        setContentPane(principal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/bank-icon.jpg").getImage());

        principal.setBackground(new Color(255, 220, 200)); // o cualquier otro color
        setContentPane(principal);

        StringBuilder texto = new StringBuilder();
        for (String linea : historial) {
            texto.append(linea).append("\n");
        }
        historialArea.setText(texto.toString());
        historialArea.setEditable(false);

        setVisible(true);
    }
}
/*
public class Resultado extends JFrame {
    private JPanel principal;
    private JLabel lblTitular;
    private JLabel lblTransaccion;
    private JLabel lblSaldo;

    public Resultado(String nombreTitular, double monto, double saldoActual) {
        setTitle("Resumen de la Transacción");
        setContentPane(principal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        lblTitular.setText("Cliente: " + nombreTitular);
        lblTransaccion.setText("Monto: $" + String.format("%.2f", monto));
        lblSaldo.setText("Saldo actual: $" + String.format("%.2f", saldoActual));

        setVisible(true);
    }
}
*/
