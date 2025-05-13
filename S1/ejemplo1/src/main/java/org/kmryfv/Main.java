package org.kmryfv;
import org.kmryfv.models.Student;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        /*Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            System.out.println("Student #" + (i+1));
            Student st = new Student();
            System.out.println("CIF: ");
            String cif = sc.nextLine();
            st.setCif(cif);
            System.out.println("Name: ");
            String name = sc.nextLine();
            st.setName(name);
            System.out.println("Surname: ");
            String surname = sc.nextLine();
            st.setSurname(surname);
            System.out.println("Email: ");
            String email = sc.nextLine();
            st.setEmail(email);
            System.out.println("Major: ");
            String major = sc.nextLine();
            st.setMajor(major);
            students.add(st);
        }

        for (Student student : students){
            System.out.println(student.toString());
        }*/

        int op = 0;
        while (op != 3){
            op = Integer.parseInt(JOptionPane.showInputDialog("1. Add students\n" +
                    "2. Show students\n" +
                    "3. Exit"));
            switch(op){
                case 1:
                    String cif = JOptionPane.showInputDialog("CIF");
                    String name = JOptionPane.showInputDialog("Name");
                    String surname = JOptionPane.showInputDialog("Surname");
                    String email = JOptionPane.showInputDialog("Email");
                    String major = JOptionPane.showInputDialog("Major");
                    Student student = new Student(cif, name, surname, email, major);
                    students.add(student);
                    break;
                case 2:
                    String list = "";
                    for(Student e: students){
                        list += e.getCif() + " " + e.getName() + " " + e.getSurname() + " " + e.getEmail() + " " + e.getMajor() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, list);
                    break;
            }
        }
    }
}