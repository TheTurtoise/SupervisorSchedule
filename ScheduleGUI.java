import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleGUI {
    private JFrame f = new JFrame("Schedule!");

    private JLabel datePanel = new JLabel("");
    public ScheduleGUI() {
        f.setSize(850,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
