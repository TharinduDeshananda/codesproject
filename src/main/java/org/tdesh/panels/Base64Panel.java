package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class Base64Panel extends JPanel {

    private JTextArea textInputArea;
    private JTextArea textOutputArea;

    private JTextField fileInputField;
    private JTextArea fileOutputArea;

    public Base64Panel() {
        super(new BorderLayout());
        this.setBackground(MyColors.MAIN_BG_CL);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Text Encoding/Decoding Tab
        JPanel textPanel = createTextPanel();
        tabbedPane.addTab("Text", textPanel);

        // File Encoding/Decoding Tab
        JPanel filePanel = createFilePanel();
        tabbedPane.addTab("File", filePanel);

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MyColors.MAIN_BG_CL);

        // Input Area
        textInputArea = new JTextArea(5, 20);
        textInputArea.setLineWrap(true);
        textInputArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(textInputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setBorder(BorderFactory.createTitledBorder("Input Text"));

        // Output Area
        textOutputArea = new JTextArea(5, 20);
        textOutputArea.setLineWrap(true);
        textOutputArea.setWrapStyleWord(true);
        textOutputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(textOutputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Output Text"));

        // Buttons
        JButton encodeButton = new JButton("Encode");
        encodeButton.addActionListener(e -> encodeTextBase64());

        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(e -> decodeTextBase64());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);

        // Add components to the text panel
        panel.add(inputScrollPane, BorderLayout.NORTH);
        panel.add(outputScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MyColors.MAIN_BG_CL);

        // File Input Field
        fileInputField = new JTextField(20);
        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(e -> selectFile());

        JPanel fileInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fileInputPanel.add(new JLabel("Select File:"));
        fileInputPanel.add(fileInputField);
        fileInputPanel.add(browseButton);

        // Output Area
        fileOutputArea = new JTextArea(10, 20);
        fileOutputArea.setLineWrap(true);
        fileOutputArea.setWrapStyleWord(true);
        fileOutputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(fileOutputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.setBorder(BorderFactory.createTitledBorder("Encoded File Output"));

        // Buttons
        JButton encodeButton = new JButton("Encode File");
        encodeButton.addActionListener(e -> encodeFileBase64());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);

        // Add components to the file panel
        panel.add(fileInputPanel, BorderLayout.NORTH);
        panel.add(outputScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void encodeTextBase64() {
        try {
            String input = textInputArea.getText();
            String encoded = Base64.getEncoder().encodeToString(input.getBytes());
            textOutputArea.setText(encoded);
        } catch (Exception e) {
            textOutputArea.setText("Error: " + e.getMessage());
        }
    }

    private void decodeTextBase64() {
        try {
            String input = textInputArea.getText();
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            String decoded = new String(decodedBytes);
            textOutputArea.setText(decoded);
        } catch (Exception e) {
            textOutputArea.setText("Error: " + e.getMessage());
        }
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileInputField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void encodeFileBase64() {
        try {
            String filePath = fileInputField.getText();
            if (filePath.isEmpty()) {
                fileOutputArea.setText("Error: No file selected.");
                return;
            }
            File file = new File(filePath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String encoded = Base64.getEncoder().encodeToString(fileContent);
            fileOutputArea.setText(encoded);
        } catch (Exception e) {
            fileOutputArea.setText("Error: " + e.getMessage());
        }
    }
}
