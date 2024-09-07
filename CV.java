package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class CV extends JFrame implements ActionListener {

    private JPanel cvPanel;
    private JTextField nameField;
    private JTextField contactField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField skillsField;
    private JTextField collegeField;
    private JTextField qualificationField;
    private JTextField workField;
    private JButton generateButton;

    public CV() {
        super("Simple CV Builder");

        cvPanel = new JPanel();
        cvPanel.setLayout(new GridLayout(8, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        cvPanel.add(nameLabel);
        nameField = new JTextField();
        cvPanel.add(nameField);

        JLabel contactLabel = new JLabel("Contact:");
        cvPanel.add(contactLabel);
        contactField = new JTextField();
        cvPanel.add(contactField);

        JLabel addressLabel = new JLabel("Address:");
        cvPanel.add(addressLabel);
        addressField = new JTextField();
        cvPanel.add(addressField);

        JLabel emailLabel = new JLabel("Email:");
        cvPanel.add(emailLabel);
        emailField = new JTextField();
        cvPanel.add(emailField);

        JLabel skillsLabel = new JLabel("Skills:");
        cvPanel.add(skillsLabel);
        skillsField = new JTextField();
        cvPanel.add(skillsField);

        JLabel collegeLabel = new JLabel("College:");
        cvPanel.add(collegeLabel);
        collegeField = new JTextField();
        cvPanel.add(collegeField);

        JLabel qualificationLabel = new JLabel("Qualification(s):");
        cvPanel.add(qualificationLabel);
        qualificationField = new JTextField();
        cvPanel.add(qualificationField);

        JLabel workLabel = new JLabel("Work Experience:");
        cvPanel.add(workLabel);
        workField = new JTextField();
        cvPanel.add(workField);

        generateButton = new JButton("Generate CV");
        generateButton.addActionListener(this);
        cvPanel.add(generateButton);

        add(cvPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);// ... (rest of the UI setup as in the previous response)

        generateButton.addActionListener(this);
        cvPanel.add(generateButton);

        add(cvPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            try {
                String name = nameField.getText().trim();
                String contact = contactField.getText().trim();
                String address = addressField.getText().trim();
                String email = emailField.getText().trim();
                String skills = skillsField.getText().trim();
                String college = collegeField.getText().trim();
                String qualifications = qualificationField.getText().trim();
                String work = workField.getText().trim();

                if (name.isEmpty() || contact.isEmpty() || address.isEmpty() || email.isEmpty()) {
                    throw new IllegalArgumentException("Please enter all mandatory details.");
                }

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save CV as PDF");
                fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf"));
                int result = fileChooser.showSaveDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().toLowerCase().endsWith(".pdf")) {
                        file = new File(file.getPath() + ".pdf");
                    }

                    try (FileOutputStream fos = new FileOutputStream(file);
                         PrintStream ps = new PrintStream(fos)) {
                        ps.println("---------------------------------------------------");
                        ps.println("Resume");
                        ps.println("---------------------------------------------------");
                        ps.println("Name: " + name);
                        ps.println("Contact: " + contact);
                        ps.println("Address: " + address);
                        ps.println("Email: " + email);
                        ps.println("Skills: " + skills);
                        ps.println("---------------------------------------------------");
                        ps.println("Education:");
                        ps.println(college);
                        ps.println("Qualifications: " + qualifications);
                        ps.println("---------------------------------------------------");
                        ps.println("Work Experience:");
                        ps.println(work);
                    }

                    JOptionPane.showMessageDialog(null, "CV generated successfully!");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving CV: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}