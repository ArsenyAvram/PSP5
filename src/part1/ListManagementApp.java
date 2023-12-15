package part1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListManagementApp extends JFrame {
    private DefaultListModel<String> listModel1;
    private JList<String> list1;
    private DefaultListModel<String> listModel2;
    private JList<String> list2;
    private DefaultListModel<String> listModel3;
    private JList<String> list3;
    private JButton moveButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public ListManagementApp() {
        setTitle("Управление списками");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        // Создание списков
        listModel1 = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();
        listModel3 = new DefaultListModel<>();

        list1 = new JList<>(listModel1);
        list2 = new JList<>(listModel2);
        list3 = new JList<>(listModel3);

        // Создание кнопок
        moveButton = new JButton("Переместить");
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedItem();
            }
        });

        addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewItem();
            }
        });

        editButton = new JButton("Редактировать");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedItem();
            }
        });

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedItem();
            }
        });

        // Создание панелей
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Список 1"), BorderLayout.NORTH);
        panel1.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(new JLabel("Список 2"), BorderLayout.NORTH);
        panel2.add(new JScrollPane(list2), BorderLayout.CENTER);
        panel2.add(moveButton, BorderLayout.SOUTH);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(new JLabel("Список 3"), BorderLayout.NORTH);
        panel3.add(new JScrollPane(list3), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Создание контейнера с разделителями
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane1.setDividerLocation(200);
        splitPane1.setResizeWeight(0.5);
        splitPane1.setContinuousLayout(true);

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panel3);
        splitPane2.setDividerLocation(400);
        splitPane2.setResizeWeight(0.5);
        splitPane2.setContinuousLayout(true);

        add(splitPane2, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void moveSelectedItem() {
        if (list1.getSelectedIndex() != -1) {
            String selectedItem = list1.getSelectedValue();
            listModel1.removeElement(selectedItem);
            listModel2.addElement(selectedItem);
        } else if (list2.getSelectedIndex() != -1) {
            String selectedItem = list2.getSelectedValue();
            listModel2.removeElement(selectedItem);
            listModel3.addElement(selectedItem);
        } else if (list3.getSelectedIndex() != -1) {
            String selectedItem = list3.getSelectedValue();
            listModel3.removeElement(selectedItem);
            listModel1.addElement(selectedItem);
        }
    }

    private void addNewItem() {
        String newItem = JOptionPane.showInputDialog(this, "Введите новый элемент:");
        if (newItem != null && !newItem.isEmpty()) {
            listModel2.addElement(newItem);
        }
    }

    private void editSelectedItem() {
        int selectedIndex = list2.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = listModel2.getElementAt(selectedIndex);
            String editedItem = JOptionPane.showInputDialog(this, "Редактировать элемент:", selectedItem);
            if (editedItem != null && !editedItem.isEmpty()) {
                listModel2.setElementAt(editedItem, selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Выберите элемент для редактирования");
        }
    }

    private void deleteSelectedItem() {
        int selectedIndex = list2.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel2.removeElementAt(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Выберите элемент для удаления");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListManagementApp app = new ListManagementApp();
                app.setVisible(true);
            }
        });
    }
}
