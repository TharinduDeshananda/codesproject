package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.util.Base64;

public class HashPanel extends JPanel {

    private JTextArea inputTextArea;
    private JTextArea hashTextArea;
    private JTextField validityInputField;
    private JTextField validityResultField;
    private JComboBox<String> algorithmComboBox;

    public HashPanel() {
        super(new BorderLayout());
        this.setBackground(MyColors.MAIN_BG_CL);

        // Create a panel for input and hashing
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputTextArea = new JTextArea(5, 20);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setBorder(BorderFactory.createTitledBorder("Input Text"));

        // Create a panel for selecting hashing algorithm
        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel algorithmLabel = new JLabel("Hashing Algorithm:");
        String[] algorithms = {"MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(algorithmComboBox);

        // Create a panel for output hash
        hashTextArea = new JTextArea(3, 20);
        hashTextArea.setLineWrap(true);
        hashTextArea.setWrapStyleWord(true);
        hashTextArea.setEditable(false);
        JScrollPane hashScrollPane = new JScrollPane(hashTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        hashScrollPane.setBorder(BorderFactory.createTitledBorder("Generated Hash"));

        // Create a panel for validity checking
        JPanel validityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel validityLabel = new JLabel("Check Validity:");
        validityInputField = new JTextField(20);
        validityResultField = new JTextField(10);
        validityResultField.setEditable(false);
        JButton checkValidityButton = new JButton("Check");
        checkValidityButton.addActionListener(e -> checkValidity());
        validityPanel.add(validityLabel);
        validityPanel.add(validityInputField);
        validityPanel.add(checkValidityButton);
        validityPanel.add(validityResultField);

        // Hash Button
        JButton hashButton = new JButton("Generate Hash");
        hashButton.addActionListener(e -> generateHash());

        // Adding panels and button to the main panel
        inputPanel.add(inputScrollPane, BorderLayout.NORTH);
        inputPanel.add(algorithmPanel, BorderLayout.CENTER);
        inputPanel.add(hashScrollPane, BorderLayout.SOUTH);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(validityPanel, BorderLayout.SOUTH);
        this.add(hashButton, BorderLayout.NORTH);
    }

    private void generateHash() {
        try {
            String inputText = inputTextArea.getText();
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            MessageDigest messageDigest = MessageDigest.getInstance(selectedAlgorithm);
            byte[] hashBytes = messageDigest.digest(inputText.getBytes());
            String hashString = Base64.getEncoder().encodeToString(hashBytes);
            hashTextArea.setText(hashString);
        } catch (Exception e) {
            hashTextArea.setText("Error: " + e.getMessage());
        }
    }

    private void checkValidity() {
        try {
            String originalHash = hashTextArea.getText();
            String inputToCheck = validityInputField.getText();
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            MessageDigest messageDigest = MessageDigest.getInstance(selectedAlgorithm);
            byte[] hashBytes = messageDigest.digest(inputToCheck.getBytes());
            String generatedHash = Base64.getEncoder().encodeToString(hashBytes);

            if (originalHash.equals(generatedHash)) {
                validityResultField.setText("Valid");
            } else {
                validityResultField.setText("Invalid");
            }
        } catch (Exception e) {
            validityResultField.setText("Error");
        }
    }
}
