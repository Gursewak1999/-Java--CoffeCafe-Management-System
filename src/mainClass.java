import javax.swing.*;
import java.awt.*;

public class mainClass {

    static JFrame frame;
    static JPanel userPan, passwordPan, btnPan;
    static JLabel userLab, passwordLab;
    static JTextField userText, passwordText;
    static JButton submitBtn;

    public static void main(String[] args) {

        int[] num =new int[]{};
        int a  = num.length;

        frame = new JFrame ("Login Form");

        frame.setSize(400,400);
        userPan = new JPanel();
        passwordPan = new JPanel();
        btnPan = new JPanel();

        userLab = new JLabel("Username");
        passwordLab = new JLabel("Password");

        userText = new JTextField(20);
        passwordText = new JTextField(20);

        submitBtn = new JButton("Login");

        frame.add(userPan, BorderLayout.NORTH);
        frame.add(passwordPan, BorderLayout.CENTER);
        frame.add(btnPan, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}