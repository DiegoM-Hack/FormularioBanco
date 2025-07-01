package Interfas;

import javax.swing.*;
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
    private double saldoInicial = 1000.0;

    //double saldoInicial = Double.parseDouble(saldo.getText());

    public BancoForm() {
        setTitle("Test de Compras - Semi-Factura");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(principal2);
        setLocationRelativeTo(null);
        setVisible(true);

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
    }

    private void deposito() {
        try {
            double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Valor a depositar:"));
            if (valor >= 0) {
                saldoInicial += valor;
                actualizarSaldo();

                historial.setText(historial.getText() + "\nDepósito: $" + valor);
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
            double monto = Double.parseDouble(input);

            if (monto < 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor o igual a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cálculo con saldoFinal
            double saldoFinal = saldoInicial - monto;

            if (saldoFinal >= 0) {
                actualizarSaldo();

                historial.setText(historial.getText() + "\nTransferencia a " + destinatario + ": $" + monto);
                JOptionPane.showMessageDialog(this, "Transferencia exitosa a " + destinatario + " por $" + monto, "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
