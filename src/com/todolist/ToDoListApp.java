package com.todolist;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ToDoListApp extends JFrame {
	
	    private DefaultListModel<String> taskListModel;
	    private JList<String> taskList;
	    private JTextField taskInput;
	    private final String PLACEHOLDER = "Enter a task here...";

	    public ToDoListApp() {
	        setTitle("✨ To-Do List App");
	        setSize(450, 520);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        mainPanel.setBackground(new Color(245, 245, 245));

	        JLabel titleLabel = new JLabel("My To-Do List");
	        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
	        titleLabel.setHorizontalAlignment(JLabel.CENTER);
	        titleLabel.setForeground(new Color(50, 50, 50));
	        mainPanel.add(titleLabel, BorderLayout.NORTH);

	        taskListModel = new DefaultListModel<>();
	        taskList = new JList<>(taskListModel);
	        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	        taskList.setBackground(Color.WHITE);
	        taskList.setSelectionBackground(new Color(0, 153, 255));
	        JScrollPane scrollPane = new JScrollPane(taskList);
	        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
	        mainPanel.add(scrollPane, BorderLayout.CENTER);

	        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
	        taskInput = new JTextField();
	        taskInput.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	        taskInput.setForeground(Color.GRAY);
	        taskInput.setText(PLACEHOLDER);
	        taskInput.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createLineBorder(new Color(180, 180, 180)),
	                BorderFactory.createEmptyBorder(5, 5, 5, 5)
	        ));

	        taskInput.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (taskInput.getText().equals(PLACEHOLDER)) {
	                    taskInput.setText("");
	                    taskInput.setForeground(Color.BLACK);
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (taskInput.getText().trim().isEmpty()) {
	                    taskInput.setForeground(Color.GRAY);
	                    taskInput.setText(PLACEHOLDER);
	                }
	            }
	        });

	        JButton addButton = createButton("Add", new Color(0, 153, 76));
	        JButton editButton = createButton("Edit", new Color(255, 140, 0));
	        JButton deleteButton = createButton("Delete", new Color(204, 0, 0));

	        inputPanel.add(taskInput, BorderLayout.CENTER);
	        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 5, 0));
	        btnPanel.add(addButton);
	        btnPanel.add(editButton);
	        btnPanel.add(deleteButton);
	        inputPanel.add(btnPanel, BorderLayout.EAST);

	        mainPanel.add(inputPanel, BorderLayout.SOUTH);

	        addButton.addActionListener(e -> {
	            String task = taskInput.getText().trim();
	            if (!task.isEmpty() && !task.equals(PLACEHOLDER)) {
	                taskListModel.addElement("• " + task);
	                taskInput.setText("");
	                taskInput.setForeground(Color.GRAY);
	                taskInput.setText(PLACEHOLDER);
	            } else {
	                JOptionPane.showMessageDialog(ToDoListApp.this, "Please enter a task.");
	            }
	        });

	        deleteButton.addActionListener(e -> {
	            int selectedIndex = taskList.getSelectedIndex();
	            if (selectedIndex != -1) {
	                taskListModel.remove(selectedIndex);
	            } else {
	                JOptionPane.showMessageDialog(ToDoListApp.this, "Please select a task to delete.");
	            }
	        });

	        editButton.addActionListener(e -> {
	            int selectedIndex = taskList.getSelectedIndex();
	            if (selectedIndex != -1) {
	                String currentTask = taskListModel.getElementAt(selectedIndex).replace("• ", "");
	                String newTask = JOptionPane.showInputDialog(ToDoListApp.this, 
	                                                             "Edit Task:", currentTask);
	                if (newTask != null && !newTask.trim().isEmpty()) {
	                    taskListModel.set(selectedIndex, "• " + newTask.trim());
	                }
	            } else {
	                JOptionPane.showMessageDialog(ToDoListApp.this, "Please select a task to edit.");
	            }
	        });

	        add(mainPanel);
	    }

	    private JButton createButton(String text, Color bgColor) {
	        JButton button = new JButton(text);
	        button.setBackground(bgColor);
	        button.setForeground(Color.WHITE);
	        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
	        button.setFocusPainted(false);
	        return button;
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
	    }
	}
