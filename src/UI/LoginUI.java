package UI;

import UI.custom.JImagePanel;
import UI.custom.Toast;
import de.craften.ui.swingmaterial.*;
import helper.ColorUtils;
import helper.InstanceHelper;
import helper.Utils;
import helper.db.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class LoginUI extends JPanel {

    private JLabel title;

    public LoginUI(int width, int height) {
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(ColorUtils.background);

        title = new JLabel();
        title.setText("Login");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setSize(new Dimension(width, height / 4));
        title.setPreferredSize(new Dimension(width, height / 4));
        title.setForeground(Color.WHITE);
        title.setBackground(MaterialColor.TRANSPARENT);

        MaterialTextField usernameField = new MaterialTextField();
        MaterialPasswordField passwordField = new MaterialPasswordField();

        int field_width = width - 100;
        int field_height = 30;

        usernameField.setBackground(new Color(225, 225, 225, 40));
        passwordField.setBackground(new Color(225, 225, 225, 40));

        usernameField.setCaretColor(Color.WHITE);
        usernameField.setSelectedTextColor(Color.DARK_GRAY);
        usernameField.setSelectionColor(Color.LIGHT_GRAY);

        usernameField.setMinimumSize(new Dimension(field_width, field_height));
        usernameField.setPreferredSize(new Dimension(field_width, field_height));
        passwordField.setMinimumSize(new Dimension(field_width, field_height));
        passwordField.setPreferredSize(new Dimension(field_width, field_height));

        usernameField.setHint("Enter Username");
        passwordField.setHint("Enter Password");

        usernameField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);

        JImagePanel separator = new JImagePanel();
        JImagePanel separator1 = new JImagePanel();
        JImagePanel separator2 = new JImagePanel();
        separator.setBackgroundColor(new Color(0, 0, 0, 0));
        separator1.setBackgroundColor(new Color(0, 0, 0, 0));
        separator2.setBackgroundColor(new Color(0, 0, 0, 0));
        separator.setMinimumSize(new Dimension(width, 20));
        separator1.setMinimumSize(new Dimension(width, 10));
        separator2.setMinimumSize(new Dimension(width, 80));
        Utils.centerWindow(passwordField);
        Utils.centerWindow(usernameField);
        this.setLayout(new GridBagLayout());

        JButton loginButton = new JButton();
        loginButton.setBorderPainted(false);
        loginButton.setText("LOGIN");
        loginButton.setBackground(Color.WHITE);
        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = Utils.getMd5(Arrays.toString(passwordField.getPassword()));

            if (validatedLogin(username, password)) {
                DatabaseHelper db = new DatabaseHelper();
                if (db.checkLogin(username, password)) {
                    InstanceHelper.gotoDashboard();
                    new Toast("Login Success", false).showtoast();
                } else new Toast("Login Failed : Incorrect Credentials", true).showtoast();
            }

        });
        //here comes the Actual Design
        GridBagConstraints c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);
        c.gridy = 1;
        this.add(separator, c);
        c.gridy = 2;
        this.add(usernameField, c);
        c.gridy = 3;
        this.add(separator1, c);
        c.gridy = 4;
        this.add(passwordField, c);
        c.gridy = 5;
        this.add(separator2, c);
        c.gridy = 6;
        this.add(loginButton, c);

    }

    private void gotoDashboard() {
        System.out.println("Opening Dashboard");
        JPanel jPanel = new DashboardUI();
        jPanel.setSize(new Dimension(InstanceHelper.dashboard_width, InstanceHelper.dashboard_height));
        jPanel.setPreferredSize(new Dimension(InstanceHelper.dashboard_width, InstanceHelper.dashboard_height));
        // Main.switchWindow(jPanel);

        StrippedUI strippedUI = new StrippedUI(InstanceHelper.dashboard_width, InstanceHelper.dashboard_height);
        strippedUI.setContentPane(jPanel);
        strippedUI.contentPane = jPanel;

        Main.switchWindow(strippedUI);
        //revalidate();
    }

    private boolean validatedLogin(String email, String passwordFieldText) {

        if (!Utils.validateEmail(email)) {
            new Toast("Not a Valid Email", true).showtoast();
            return false;
        } else if (!Utils.validatePassword(passwordFieldText)) {
            new Toast("Not a Valid Email", true).showtoast();
            return false;
        } else
            return true;
    }

}
