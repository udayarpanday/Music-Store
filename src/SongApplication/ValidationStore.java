package SongApplication;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PREDATOR
 */
public class ValidationStore {
    /*Checking if data exists in table and not allowing user to add data if the same record exists*/
    public void validateStoreData(JTable dataTable,ButtonGroup commercialLicenseRadioButton,JRadioButton yesRadioButton,JRadioButton noRadioButton,JTextField serialNumberField,JTextField nameField,JComboBox genreComboBox,JComboBox artistComboBox,JComboBox trackComboBox,JCheckBox additionalCoverCheckBox,JCheckBox posterCheckBox, int selectionPrice,ArrayList<DataModel> tableData){
        int similarCounter = 0;
        yesRadioButton.setActionCommand("Yes");
        noRadioButton.setActionCommand("No");
        String serialNumber = serialNumberField.getText();
        String customerName = nameField.getText();
        String genre = (String) genreComboBox.getSelectedItem();
        String artist = (String) artistComboBox.getSelectedItem();
        String track = (String) trackComboBox.getSelectedItem();
        String accessories = "";
        String commercialLicense = (String) commercialLicenseRadioButton.getSelection().getActionCommand();
        int price = selectionPrice;
        if(additionalCoverCheckBox.isSelected()){
            accessories = additionalCoverCheckBox.getText();
        }
        if(posterCheckBox.isSelected()){
            accessories = posterCheckBox.getText();
        }
        if(additionalCoverCheckBox.isSelected() && posterCheckBox.isSelected()){
            accessories = additionalCoverCheckBox.getText()+", "+posterCheckBox.getText();
        }
        int rowCount = dataTable.getRowCount();
        int colCount = dataTable.getColumnCount();
        String compareValues[] = {serialNumber,customerName,genre,artist,track,accessories,commercialLicense};
        for(int i = 0; i<rowCount;i++){
            for(int j = 0; j<colCount-1; j++){
                if(compareValues[j].trim().equalsIgnoreCase((String) dataTable.getValueAt(i, j)) && price == Integer.parseInt(dataTable.getValueAt(i, 7).toString())){
                        similarCounter++;
                        
                }
            }if(similarCounter == 7){
                    JOptionPane.showMessageDialog(null, "These set of values exist in the table.");
                    return;
            }
        }
        storeData(dataTable,serialNumber,customerName,genre,artist,track,accessories,commercialLicense,price);
        tableData.add(new DataModel(serialNumber,customerName,genre,artist,track,accessories,commercialLicense,price));
    }
    /*Storing data in table and objects in arraylist after validation*/
    public void storeData(JTable dataTable,String serialNumber,String customerName,String genre,String artist,String track,String accessories,String commercialLicense, int price){
        Object row[] = {serialNumber,customerName,genre,artist,track,accessories,commercialLicense,price};
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.addRow(row);
    }
}
