package org.tdesh.panels;

import org.tdesh.util.MyColors;

import javax.swing.*;
import java.awt.*;
import java.util.Base64;

public class JWTPanel extends JPanel {

    private JTextArea headerArea;
    private JTextArea payloadArea;
    private JTextField secretField;
    private JTextArea tokenArea;

    private JTextArea decodedHeaderArea;
    private JTextArea decodedPayloadArea;
    private JTextArea decodedSignatureArea;
    private JTextField tokenInputField;

    public JWTPanel() {
        super(new BorderLayout());
        this.setBackground(MyColors.MAIN_BG_CL);

        JTabbedPane tabbedPane = new JTabbedPane();

        // JWT Encoding Tab
        JPanel encodePanel = createEncodePanel();
        tabbedPane.addTab("Encode JWT", encodePanel);

        // JWT Decoding Tab
        JPanel decodePanel = createDecodePanel();
        tabbedPane.addTab("Decode JWT", decodePanel);

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createEncodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MyColors.MAIN_BG_CL);

        // Header Area
        headerArea = new JTextArea(5, 20);
        headerArea.setLineWrap(true);
        headerArea.setWrapStyleWord(true);
        JScrollPane headerScrollPane = new JScrollPane(headerArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        headerScrollPane.setBorder(BorderFactory.createTitledBorder("Header"));

        // Payload Area
        payloadArea = new JTextArea(5, 20);
        payloadArea.setLineWrap(true);
        payloadArea.setWrapStyleWord(true);
        JScrollPane payloadScrollPane = new JScrollPane(payloadArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        payloadScrollPane.setBorder(BorderFactory.createTitledBorder("Payload"));

        // Secret Input
        JPanel secretPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel secretLabel = new JLabel("Secret:");
        secretField = new JTextField(20);
        secretPanel.add(secretLabel);
        secretPanel.add(secretField);

        // Token Output Area
        tokenArea = new JTextArea(3, 20);
        tokenArea.setLineWrap(true);
        tokenArea.setWrapStyleWord(true);
        tokenArea.setEditable(false);
        JScrollPane tokenScrollPane = new JScrollPane(tokenArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tokenScrollPane.setBorder(BorderFactory.createTitledBorder("Encoded JWT"));

        // Encode Button
        JButton encodeButton = new JButton("Encode");
        encodeButton.addActionListener(e -> encodeJWT());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encodeButton);

        // Add components to the encoding panel
        panel.add(headerScrollPane, BorderLayout.NORTH);
        panel.add(payloadScrollPane, BorderLayout.CENTER);
        panel.add(secretPanel, BorderLayout.WEST);
        panel.add(tokenScrollPane, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createDecodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MyColors.MAIN_BG_CL);

        // Token Input Field
        JPanel tokenInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tokenLabel = new JLabel("JWT:");
        tokenInputField = new JTextField(30);
        tokenInputPanel.add(tokenLabel);
        tokenInputPanel.add(tokenInputField);

        // Decoded Header Area
        decodedHeaderArea = new JTextArea(3, 20);
        decodedHeaderArea.setLineWrap(true);
        decodedHeaderArea.setWrapStyleWord(true);
        decodedHeaderArea.setEditable(false);
        JScrollPane headerScrollPane = new JScrollPane(decodedHeaderArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        headerScrollPane.setBorder(BorderFactory.createTitledBorder("Decoded Header"));

        // Decoded Payload Area
        decodedPayloadArea = new JTextArea(3, 20);
        decodedPayloadArea.setLineWrap(true);
        decodedPayloadArea.setWrapStyleWord(true);
        decodedPayloadArea.setEditable(false);
        JScrollPane payloadScrollPane = new JScrollPane(decodedPayloadArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        payloadScrollPane.setBorder(BorderFactory.createTitledBorder("Decoded Payload"));

        // Decoded Signature Area
        decodedSignatureArea = new JTextArea(3, 20);
        decodedSignatureArea.setLineWrap(true);
        decodedSignatureArea.setWrapStyleWord(true);
        decodedSignatureArea.setEditable(false);
        JScrollPane signatureScrollPane = new JScrollPane(decodedSignatureArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        signatureScrollPane.setBorder(BorderFactory.createTitledBorder("Decoded Signature"));

        // Decode Button
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(e -> decodeJWT());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(decodeButton);

        // Add components to the decoding panel
        panel.add(tokenInputPanel, BorderLayout.NORTH);
        panel.add(headerScrollPane, BorderLayout.WEST);
        panel.add(payloadScrollPane, BorderLayout.CENTER);
        panel.add(signatureScrollPane, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void encodeJWT() {
        // Basic JWT Encoding (without a specific library)
        String header = headerArea.getText();
        String payload = payloadArea.getText();
        String secret = secretField.getText();

        try {
            String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());
            String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
            String signature = ""; // Implement signature encoding here if needed

            String token = encodedHeader + "." + encodedPayload + "." + signature;
            tokenArea.setText(token);
        } catch (Exception e) {
            tokenArea.setText("Error: " + e.getMessage());
        }
    }

    private void decodeJWT() {
        String token = tokenInputField.getText();

        try {
            String[] parts = token.split("\\.");
            String decodedHeader = new String(Base64.getUrlDecoder().decode(parts[0]));
            String decodedPayload = new String(Base64.getUrlDecoder().decode(parts[1]));
            String decodedSignature = parts.length > 2 ? parts[2] : "";

            decodedHeaderArea.setText(decodedHeader);
            decodedPayloadArea.setText(decodedPayload);
            decodedSignatureArea.setText(decodedSignature);
        } catch (Exception e) {
            decodedHeaderArea.setText("Error: " + e.getMessage());
        }
    }
}
