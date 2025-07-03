package Login;

import Interfas.BancoForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel principal;
    private JLabel titulo;
    private JTextField usuario;
    private JPasswordField clave;
    private JButton ingreso;
    public LoginForm() {
        setTitle("Test de Compras - Semi-Factura");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(principal);
        setLocationRelativeTo(null);
        setVisible(true);

        ingreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }
        });
    }

    private void verificar() {
        String u = usuario.getText();
        char[] pass = clave.getPassword();
        if (u.isEmpty() || pass.length == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (u.equals("cliente123") && String.valueOf(pass).equals("clave456")) {
            BancoForm banco = new BancoForm();
            //BancoForm banco = new BancoForm(usuario.getText());
            dispose();
            //banco.setSize(500, 300);
            //banco.setVisible(true);
            ingreso.addActionListener(e -> verificar());

        }else{
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
