import Login.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}