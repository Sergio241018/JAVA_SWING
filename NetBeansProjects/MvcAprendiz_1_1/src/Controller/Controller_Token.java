package Controller;

import Model.token;
import Model.tokenDAO;
import View.Token;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controller_Token implements ActionListener {

    tokenDAO dao = new tokenDAO();
    token to = new token();
    Token token = new Token();

    public Controller_Token(Token t) {
        this.token = t;
        this.token.btnList.addActionListener(this);
        this.token.btnSave.addActionListener(this);
        this.token.btnSearch.addActionListener(this);
        this.token.btnDelete.addActionListener(this);
        this.token.btnUpdate.addActionListener(this);
        this.token.btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == token.btnList) {
            cleanTable();
            toList(token.tblToken);
            clean();
        }
        if (e.getSource() == token.btnSave) {
            save();
            toList(token.tblToken);
            clean();
        }
        if (e.getSource() == token.btnSearch) {
            int row = token.tblToken.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(token, "Please selected row");
            } else {
                int id = Integer.parseInt(token.tblToken.getValueAt(row, 0).toString());
                String tokenNum = token.tblToken.getValueAt(row, 1).toString();
                String tokenName = token.tblToken.getValueAt(row, 2).toString();
                String campus = token.tblToken.getValueAt(row, 3).toString();
                String city = token.tblToken.getValueAt(row, 4).toString();
                token.txtIdToken.setText(String.valueOf(id));
                token.txtTokenNum.setText(tokenNum);
                token.txtTokenName.setText(tokenName);
                token.txtCampus.setText(campus);
                token.txtCity.setText(city);
                token.txtAprendiz_id.setText(String.valueOf(id));
            }
        }
        if (e.getSource() == token.btnUpdate) {
            update();
            toList(token.tblToken);
            clean();
        }
        if (e.getSource() == token.btnDelete) {
            delete();
            toList(token.tblToken);
            clean();
        }
        if (e.getSource() == token.btnExit) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Do you want to exit the program?", "Message", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    public void toList(JTable tblToken) {
        centerCells(tblToken);
        DefaultTableModel model = (DefaultTableModel) token.tblToken.getModel();
        model.setRowCount(0);
        List<token> list = dao.ToList();
        for (token to : list) {
            Object[] object = {to.getId(), to.getTokenNum(), to.getTokenName(), to.getCampus(), to.getCityT()};
            model.addRow(object);
        }
        tblToken.setModel(model);
    }

    public void save() {
        String tokenNumber = token.txtTokenNum.getText();
        String tokenName = token.txtTokenName.getText();
        String tokenCampus = token.txtCampus.getText();
        String tokenCity = token.txtCity.getText();
        int idt = Integer.parseInt(token.txtAprendiz_id.getText());

        token to = new token();
        to.setTokenNum(tokenNumber);
        to.setTokenName(tokenName);
        to.setCampus(tokenCampus);
        to.setCityT(tokenCity);
        to.setAprendiz_id(idt);

        int r = dao.Add(to);
        if (r == 1) {
            JOptionPane.showMessageDialog(token, "Registered User");
        } else {
            JOptionPane.showMessageDialog(token, "Unregister User");
        }
    }

    public void update() {
        if (token.txtIdToken.getText().equals("")) {
            JOptionPane.showMessageDialog(token, "id not found, select an existing record"); 
        } else {
            int id = Integer.parseInt(token.txtIdToken.getText());
            String tokenNumber = token.txtTokenNum.getText();
            String tokenName = token.txtTokenName.getText();
            String tokenCampus = token.txtCampus.getText();
            String tokenCity = token.txtCity.getText();
            int idtf = Integer.parseInt(token.txtAprendiz_id.getText());

            to.setId(id);
            to.setTokenNum(tokenNumber);
            to.setTokenName(tokenName);
            to.setCampus(tokenCampus);
            to.setCityT(tokenCity);
            to.setAprendiz_id(idtf);

            int r = dao.Update(to);
            if (r == 1) {
                JOptionPane.showMessageDialog(token, "Updated User");
            } else {
                JOptionPane.showMessageDialog(token, "User Not Updated");
            }
        }
    }

    public void delete() {
        int row = token.tblToken.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(token, "Please select a row");
        } else {
            int id = Integer.parseInt(token.tblToken.getValueAt(row, 0).toString());
            dao.Delete(id);
            JOptionPane.showMessageDialog(token, "Deleted Record");
        }
        cleanTable();
    }

    void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) token.tblToken.getModel();
        model.setRowCount(0);
    }

    private void centerCells(JTable tblToken) {
        DefaultTableCellRenderer trc = new DefaultTableCellRenderer();
        trc.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < token.tblToken.getColumnCount(); i++) {
            tblToken.getColumnModel().getColumn(i).setCellRenderer(trc);
        }
    }

    private void clean() {
        token.txtIdToken.setText("");
        token.txtTokenNum.setText("");
        token.txtTokenName.setText("");
        token.txtCampus.setText("");
        token.txtCity.setText("");
        token.txtAprendiz_id.setText("");
    }
}
