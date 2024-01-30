import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TallyChart {

    private JFrame frame;
    private DefaultTableModel model;
    private JTable staffChartTable;
    private JTextField searchField;
    public TallyChart() {
        frame = new JFrame("Staff Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Staff Name");
        model.addColumn("Number of Shifts");
        model.addColumn("Availability");

        for (int i = 1; i <= 120; i++) {
            model.addRow(new Object[]{" ", i, "Available/Unavailable"});
        }

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

        JButton addRowButton = new JButton("Add Row");
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
            }
        });

        JButton deleteRowButton = new JButton("Delete Row");
        deleteRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
            }
        });

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

    private void addRow() {
        model.addRow(new Object[]{" ", model.getRowCount() + 1, "Available/Unavailable"});
    }

    private void deleteRow() {
        int selectedRow = staffChartTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        }
    }

    private class HighlightRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String searchTerm = searchField.getText().toLowerCase();

            if (!searchTerm.isEmpty()) {
                String staffName = model.getValueAt(row, 0).toString().toLowerCase();

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