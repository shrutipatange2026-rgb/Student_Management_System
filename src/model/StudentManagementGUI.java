package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class StudentManagementGUI extends JFrame {

    private JTextField txtName, txtAge, txtGender, txtCourse;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDAO dao;

    public StudentManagementGUI() {
        super("Student Management System");
        setLayout(null);
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dao = new StudentDAO();

        
        JLabel lblName = new JLabel("Name:"); lblName.setBounds(20, 20, 80, 25); add(lblName);
        JLabel lblAge = new JLabel("Age:"); lblAge.setBounds(20, 60, 80, 25); add(lblAge);
        JLabel lblGender = new JLabel("Gender:"); lblGender.setBounds(20, 100, 80, 25); add(lblGender);
        JLabel lblCourse = new JLabel("Course:"); lblCourse.setBounds(20, 140, 80, 25); add(lblCourse);

        
        txtName = new JTextField(); txtName.setBounds(100, 20, 150, 25); add(txtName);
        txtAge = new JTextField(); txtAge.setBounds(100, 60, 150, 25); add(txtAge);
        txtGender = new JTextField(); txtGender.setBounds(100, 100, 150, 25); add(txtGender);
        txtCourse = new JTextField(); txtCourse.setBounds(100, 140, 150, 25); add(txtCourse);

        
        btnAdd = new JButton("Add"); btnAdd.setBounds(20, 180, 80, 30); add(btnAdd);
        btnUpdate = new JButton("Update"); btnUpdate.setBounds(110, 180, 80, 30); add(btnUpdate);
        btnDelete = new JButton("Delete"); btnDelete.setBounds(200, 180, 80, 30); add(btnDelete);
        btnRefresh = new JButton("Refresh"); btnRefresh.setBounds(290, 180, 80, 30); add(btnRefresh);

        
        tableModel = new DefaultTableModel(new Object[]{"ID","Name","Age","Gender","Course"},0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(300, 20, 370, 150);
        add(scroll);

        
        loadStudents();

      
        btnAdd.addActionListener(e -> {
            try {
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String gender = txtGender.getText().trim();
                String course = txtCourse.getText().trim();
                if(name.isEmpty() || gender.isEmpty() || course.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill all fields!");
                    return;
                }
                dao.addStudent(new student(0,name,age,gender,course));
                JOptionPane.showMessageDialog(null,"Student added successfully!");
                clearFields();
                loadStudents();
            } catch(Exception ex){ JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage()); }
        });

        btnUpdate.addActionListener(e -> {
            try{
                int selected = table.getSelectedRow();
                if(selected==-1){ JOptionPane.showMessageDialog(null,"Select a student!"); return; }
                int id = (int) tableModel.getValueAt(selected,0);
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String gender = txtGender.getText().trim();
                String course = txtCourse.getText().trim();
                dao.updateStudent(new student(id,name,age,gender,course));
                JOptionPane.showMessageDialog(null,"Student updated successfully!");
                clearFields();
                loadStudents();
            }catch(Exception ex){ JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());}
        });

        btnDelete.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if(selected==-1){ JOptionPane.showMessageDialog(null,"Select a student!"); return; }
            int id = (int) tableModel.getValueAt(selected,0);
            dao.deleteStudent(id);
            JOptionPane.showMessageDialog(null,"Student deleted successfully!");
            clearFields();
            loadStudents();
        });

        btnRefresh.addActionListener(e -> { loadStudents(); clearFields(); });

        
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int selected = table.getSelectedRow();
                txtName.setText((String)tableModel.getValueAt(selected,1));
                txtAge.setText(tableModel.getValueAt(selected,2).toString());
                txtGender.setText((String)tableModel.getValueAt(selected,3));
                txtCourse.setText((String)tableModel.getValueAt(selected,4));
            }
        });

        setVisible(true);
    }

    private void loadStudents(){
        tableModel.setRowCount(0);
        List<student> list = dao.getAllStudents();
        for(student s : list){
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getGender(), s.getCourse()});
        }
    }

    private void clearFields(){
        txtName.setText(""); txtAge.setText(""); txtGender.setText(""); txtCourse.setText("");
    }

    public static void main(String[] args){
        new StudentManagementGUI();
    }
}