import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyChart {
//this is the tallychart
    private JFrame frame;
    private DefaultTableModel model;
    private JTable staffChartTable;
    private JTextField searchField;
    public TallyChart() {
        frame = new JFrame("Staff Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//these are the columns
        model = new DefaultTableModel();
        model.addColumn("Staff Name");
        model.addColumn("Number of Shifts");
        model.addColumn("Availability");
//these are the rows for the amount of teachers, this is the default amount. You can increase the default here
        for (int i = 1; i <= 120; i++) {
            model.addRow(new Object[]{" ", i, "Available/Unavailable"});
        }
//search button, that highlights stuff searched
        staffChartTable = new JTable(model);
        staffChartTable.setDefaultRenderer(Object.class, new HighlightRenderer());
        JScrollPane scrollPane = new JScrollPane(staffChartTable);
        staffChartTable.setRowHeight(25);
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
// addrow button
        JButton addRowButton = new JButton("Add Row");
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
            }
        });
// delete row button
        JButton deleteRowButton = new JButton("Delete Row");
        deleteRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
            }
        });
//panel for all of the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Search: "));
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(addRowButton);
        buttonPanel.add(deleteRowButton);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(buttonPanel, BorderLayout.WEST);

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
// serach function
    private void search() {
        String searchTerm = searchField.getText().toLowerCase();

        for (int row = 0; row < model.getRowCount(); row++) {
            String staffName = model.getValueAt(row, 0).toString().toLowerCase();

            if (staffName.contains(searchTerm)) {
                staffChartTable.setRowSelectionInterval(row, row);
            } else {
                staffChartTable.removeRowSelectionInterval(row, row);
            }
        }
    }
// add row function
    private void addRow() {
        model.addRow(new Object[]{" ", model.getRowCount() + 1, "Available/Unavailable"});
    }
// delete row function
    private void deleteRow() {
        int selectedRow = staffChartTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        }
    }
// search function, mainly highlighting part
    private class HighlightRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String searchTerm = searchField.getText().toLowerCase();
// by default they will have no colour
            if (!searchTerm.isEmpty()) {
                String staffName = model.getValueAt(row, 0).toString().toLowerCase();
//searched terms will be yellow, all else will be default
                if (staffName.contains(searchTerm)) {
                    cellComponent.setBackground(Color.YELLOW);
                } else {
                    cellComponent.setBackground(table.getBackground());
                }
            } else {
                cellComponent.setBackground(table.getBackground());
            }

            return cellComponent;
        }
    }

}
