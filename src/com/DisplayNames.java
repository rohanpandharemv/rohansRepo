package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayNames {

    public static boolean RIGHT_TO_LEFT = false;
    NameFinder finder = null;

   public DisplayNames() throws IOException {
        finder = new NameFinder();
    }

    public static void main(String[] args) throws IOException {
        DisplayNames names = new DisplayNames();
        names.process();
    }

    private void process() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public void addComponentsToPane(Container contentPane) {
//     Use BorderLayout. Default empty constructor with no horizontal and vertical
//     gaps
        contentPane.setLayout(new BorderLayout(5, 5));
        if (!(contentPane.getLayout() instanceof BorderLayout)) {
            contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        if (RIGHT_TO_LEFT) {
            contentPane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        JLabel label = new JLabel("Name");
        label.setOpaque(true);
        label.setBackground(Color.white);
        contentPane.add(label, BorderLayout.CENTER);

        JButton jbnSampleButtons = new JButton("Next name");
        contentPane.add(jbnSampleButtons, BorderLayout.PAGE_END);

        jbnSampleButtons.addActionListener(new MyActionListener(label, finder));
    }


    private void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("BorderLayout Source Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane and add swing components to it
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
}


class MyActionListener implements ActionListener {

    JLabel label;
    NameFinder finder;

    MyActionListener(JLabel label, NameFinder finder) {
        this.label = label;
        this.finder = finder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = finder.getName();
        label.setText(name);
        System.out.println(name);
        increaseFont(label);
        increaseFont(label);
    }

    private void increaseFont(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

}

class NameFinder {
    ArrayList<String> empList = new ArrayList();
    int counter = 0;

    NameFinder() throws IOException {
        readList(empList, "C:\\Users\\Rohan.Pandhare\\Desktop\\EmpList.txt");
        System.out.println("EMP list size: " + empList.size());
        
    }

    private void readList(ArrayList<String> list, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str = null;

        while ((str = br.readLine()) != null) {
            if (str != null && !str.trim().equals("")) {
                list.add(str.trim().toUpperCase());
           }
        }
    }

    public String getName() {

            return getName(empList);

    }

    private String getName(ArrayList<String> list) {
        long time = System.currentTimeMillis();
        time = time % list.size();
        String name = list.get((int) time);
        list.remove((int) time);
        return name;
    }


}
