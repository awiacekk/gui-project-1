package com.company;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class TextEditor extends JFrame {

    public TextEditor() {

        this.setLayout(new GridLayout(1, 2, 10, 10));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Word wanna be");

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(4, 2, 8, 8));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 10));

        JPanel textPanel = new JPanel();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 2));

        JTextArea textArea = new JTextArea(8, 24);
        textArea.setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane scroll = new JScrollPane(textArea);
        textPanel.add(scroll, BorderLayout.CENTER);

        List<JButton> colorButtonList = Arrays.asList(
                new JButton("black"),
                new JButton("red"),
                new JButton("blue"),
                new JButton("green")
        );
        List<JButton> fontButtonList = Arrays.asList(
                new JButton("Arial"),
                new JButton("Times New Roman"),
                new JButton("Comic Sans MS"),
                new JButton("Cambria")
        );
        colorButtonList.get(0).setBackground(Color.CYAN);
        fontButtonList.get(0).setBackground(Color.CYAN);

        fontButtonList
                .forEach(button -> button.addActionListener(e -> {
                    textArea.setFont(new Font(button.getText(), Font.BOLD, 16));
                    setButtonsNormal("fonts");
                    button.setBackground(Color.CYAN);
                }));

        colorButtonList
                .forEach(button -> button.addActionListener(e -> {
                    try {
                        Field colorField = Color.class.getField(button.getText());
                        textArea.setForeground((Color) colorField.get(null));
                    } catch (NoSuchFieldException | IllegalAccessException exception) {
                        exception.printStackTrace();
                    }
                    setButtonsNormal("colours");
                    button.setBackground(Color.CYAN);
                }));

        for (int i = 0; i < colorButtonList.size(); i++) {
            editPanel.add(colorButtonList.get(i));
            editPanel.add(fontButtonList.get(i));
        }

        this.getContentPane().add(textPanel);
        this.getContentPane().add(editPanel);

        this.pack();
        this.setVisible(true);

    }

    public void setButtonsNormal(String type) {

        Component[] components;
        synchronized (this.getTreeLock()) {
            components = this.getContentPane().getComponents();
        }

        JPanel editPanel = (JPanel) components[1];
        synchronized (editPanel.getTreeLock()) {
            components = editPanel.getComponents();
        }

        int i = (type.equals("fonts")) ? 1 : 0;
        for (; i < components.length; i += 2) {
            components[i].setBackground(null);
        }
    }
}