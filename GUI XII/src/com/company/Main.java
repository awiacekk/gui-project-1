package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(TextEditor::new);
        //SwingUtilities.invokeLater(StrokePainter::new);
        //SwingUtilities.invokeLater(StrokePainterColor::new);

    }
}
