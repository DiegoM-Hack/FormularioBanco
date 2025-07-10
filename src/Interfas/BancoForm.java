package Interfas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoForm extends JFrame {
    private JPanel principal2;
    private JButton DEPOSITOButton;
    private JButton RETIROButton;
    private JButton TRANSFERENCIAButton;
    private JButton SALIRButton;
    private JLabel saldo;
    private JTextArea historial;
    private JButton historialButton;
    private JButton buscarButton;
    private JTextField codigoField;
    private double saldoInicial = 1000.0;
    private java.util.List<String> historialTransacciones = new java.util.ArrayList<>();
    private String nombreCliente;
    //double saldoInicial = Double.parseDouble(saldo.getText());

    public BancoForm() {
    //public BancoForm(String nombreCliente) {
            //this.nombreCliente = nombreCliente;
        setTitle("Formulario");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(principal2);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon("src/bank-icon.jpg").getImage());

        principal2.setBackground(new Color(160, 232, 150 )); // o cualquier otro color
        setContentPane(principal2);

        saldo.setText(String.valueOf(saldoInicial));

        DEPOSITOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposito();
            }
        });
        RETIROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retiro();
            }
        });
        TRANSFERENCIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    transfer();
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        historialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Resultado(historialTransacciones);

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText().trim();

                if (codigo.equals("123")) {
                    JOptionPane.showMessageDialog(BancoForm.this,
                            "Titular: Diego\nSaldo actual: $" + String.format("%.2f", saldoInicial),
                            "Cliente encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un código.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(BancoForm.this, "No se encontró ningún cliente con ese código.", "No encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void deposito() {
        try {
            double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Valor a depositar:"));
            if (valor >= 0) {
                saldoInicial += valor;
                actualizarSaldo();
                historial.setText(historial.getText() + "\nDepósito: $" + valor);
                //new Resultado("Depósito", valor);  // ← Muestra la nueva ventana
                historialTransacciones.add("Depósito: $" + valor);

            } else {
                JOptionPane.showMessageDialog(this, "El valor debe ser mayor o igual a 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retiro() {
        try {
            double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Valor a retirar:"));
            if (valor >= 0 && valor <= saldoInicial) {
                saldoInicial -= valor;
                actualizarSaldo();

                historial.setText(historial.getText() + "\nRetiro: $" + valor);
                historialTransacciones.add("Retiro: $" + valor);

            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente o valor inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean haySaldo() {
        return Double.parseDouble(saldo.getText()) >= 0;
    }

    private void transfer() {
        try {
            // Solicita nombre del destinatario
            String destinatario = JOptionPane.showInputDialog(this, "Nombre del destinatario:");

            if (destinatario == null || destinatario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre válido.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Solicita monto a transferir
            String input = JOptionPane.showInputDialog(this, "Monto a transferir:");
            double valor = Double.parseDouble(input);
            historialTransacciones.add("Tranfer: $" + valor);

            if (valor < 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor o igual a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cálculo con saldoFinal
            double saldoFinal = saldoInicial - valor;
            historialTransacciones.add("Tranfer: $" + valor);

            if (saldoFinal >= 0) {
                saldoInicial = saldoFinal;
                actualizarSaldo();

                //historialTransacciones.add("Transferencia a " + destinatario + ": $" + valor);
                historial.setText(historial.getText()+ "\nTransferencia1 a " + destinatario + ": $" + valor);

                //new Resultado(nombreCliente, valor, saldoInicial);
                JOptionPane.showMessageDialog(this, "Transferencia exitosa a " + destinatario + " por $" + valor, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente para la transferencia", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarSaldo() {
        saldo.setText(String.format("%.2f", saldoInicial));
    }


}
