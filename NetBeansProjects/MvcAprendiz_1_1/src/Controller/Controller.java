package Controller;

import Model.Apprentice;
import Model.AprendizDAO;
import View.Form;
import View.Main_Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener {

    AprendizDAO dao = new AprendizDAO();
    Apprentice ap = new Apprentice();
    Form form;

    public Controller(Form f) {
        this.form = f;
        this.form.btnList.addActionListener(this);
        this.form.btnSave.addActionListener(this);
        this.form.btnSearch.addActionListener(this);
        this.form.btnDelete.addActionListener(this);
        this.form.btnUpdate.addActionListener(this);
        this.form.btnExit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnList) {
            cleanTable();
            toList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnSave) {
            save();
            toList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnSearch) {
            int row = form.tblApprentice.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(form, "Please selected row");
            } else {
                int id = Integer.parseInt(form.tblApprentice.getValueAt(row, 0).toString());
                String docType = form.tblApprentice.getValueAt(row, 1).toString();
                String numerDoc = form.tblApprentice.getValueAt(row, 2).toString();
                String name = form.tblApprentice.getValueAt(row, 3).toString();
                String birthDate = form.tblApprentice.getValueAt(row, 4).toString();
                String gender = form.tblApprentice.getValueAt(row, 5).toString();
                String city = form.tblApprentice.getValueAt(row, 6).toString();

                form.txtId.setText(String.valueOf(id));
                form.cbxType.setSelectedItem(docType);
                form.txtDoc.setText(numerDoc);
                form.txtName.setText(name);
                form.jdcDate.setDate(java.sql.Date.valueOf(birthDate));
                if (gender.equalsIgnoreCase("Male")) {
                    form.rbMale.setSelected(true);
                } else if (gender.equalsIgnoreCase("Female")) {
                    form.rbFemale.setSelected(true);
                }
                form.txtCity.setText(city);
            }
        }
        if (e.getSource() == form.btnUpdate) {
            update();
            toList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnDelete) {
            delete();
            toList(form.tblApprentice);
            clean();
        }
        if (e.getSource() == form.btnExit) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Do you want to exit the program?", "Message", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    public void toList(JTable tblApprentice) {
        centerCells(tblApprentice);
        DefaultTableModel model = (DefaultTableModel) form.tblApprentice.getModel();
        model.setRowCount(0);
        List<Apprentice> list = dao.Listar();
        for (Apprentice appre : list) {
            Object[] object = {appre.getId(), appre.getTipedoc(), appre.getNumerdoc(), appre.getName(), appre.getDate(), appre.getGender(), appre.getCity()};
            model.addRow(object);
        }
        tblApprentice.setModel(model);
    }

    public void save() {
        String typeDoc = form.cbxType.getSelectedItem().toString();
        String numDoc = form.txtDoc.getText();
        String name = form.txtName.getText();
        java.util.Date birthDate = form.jdcDate.getDate();
        String gender = form.rbMale.isSelected() ? "Male" : "Female";
        String city = form.txtCity.getText();

        Apprentice appre = new Apprentice();
        appre.setTipedoc(typeDoc);
        appre.setNumerdoc(numDoc);
        appre.setName(name);
        appre.setDate(birthDate);
        appre.setGender(gender);
        appre.setCity(city);

        int r = dao.Add(appre);
        if (r == 1) {
            JOptionPane.showMessageDialog(form, "Registered user");
        } else {
            JOptionPane.showMessageDialog(form, "Unregistered user");
        }

    }

    public void update() {
        if (form.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "id not found :c, Select an existing record <3");
        } else {
            int id = Integer.parseInt(form.txtId.getText());
            String typeDoc = form.cbxType.getSelectedItem().toString();
            String numDoc = form.txtDoc.getText();
            String name = form.txtName.getText();
            java.util.Date birthDate = form.jdcDate.getDate();
            String gender = form.rbMale.isSelected() ? "Male" : "Female";
            String city = form.txtCity.getText();

            ap.setId(id);
            ap.setTipedoc(typeDoc);
            ap.setNumerdoc(numDoc);
            ap.setName(name);
            ap.setDate(birthDate);
            ap.setGender(gender);
            ap.setCity(city);

            int r = dao.Add(ap);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "Updated user");
            } else {
                JOptionPane.showMessageDialog(form, "User not updated");
            }
        }
    }

    public void delete() {
        int row = form.tblApprentice.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(form, "Please select a row");
        } else {
            int id = Integer.parseInt(form.tblApprentice.getValueAt(row, 0).toString());
            dao.Delete(id);
            JOptionPane.showMessageDialog(form, "Deleted record");
        }
        cleanTable();
    }

    public void centerCells(JTable tblApprentice) {
        DefaultTableCellRenderer trc = new DefaultTableCellRenderer();
        trc.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblApprentice.getColumnCount(); i++) {
            tblApprentice.getColumnModel().getColumn(i).setCellRenderer(trc);
        }
    }

    void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) form.tblApprentice.getModel();
        model.setRowCount(0);
    }

    public void clean() {
        form.txtId.setText("");
        form.cbxType.setSelectedIndex(0);
        form.txtDoc.setText("");
        form.txtName.setText("");
        form.jdcDate.setDate(null);
        form.rbMale.setSelected(false);
        form.rbFemale.setSelected(false);
        form.txtCity.setText("");
    }

}
