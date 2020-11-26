/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SongApplication;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author PREDATOR
 */
public class Search {
    /*To search existing price using binary search*/
    public DataModel binarySearch(ArrayList<DataModel>list,int low, int high, int searchValue){
        if(low<=high){
            int midIndex =(low+high)/2;
            if(list.get(midIndex).price == searchValue){
                return list.get(midIndex);
            }
            else if(searchValue>list.get(midIndex).price){
                return binarySearch(list,midIndex+1,high,searchValue);      //Recurring method
            }
            else{
                return binarySearch(list,low,midIndex-1,searchValue);
            }
        }
        else{
            return null;
        }   
    }
    
    /*Searching price using binary search present in binarysearch method*/
    public void searchByPrice(JTextField searchByPriceField,ArrayList<DataModel> tableData){
        try{
            Integer.parseInt(searchByPriceField.getText());

        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Price should be a number greater than 0.","Wrong Input",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int enteredValue = Integer.parseInt(searchByPriceField.getText());
        DataModel searchPrice = binarySearch(tableData,0,tableData.size()-1,enteredValue);
        if(searchPrice == null){
                JOptionPane.showMessageDialog(null,"No purchase found for searched price.","Result Not Found",JOptionPane.INFORMATION_MESSAGE);
            }else{
                String customerName = searchPrice.name;
                String genre= searchPrice.genre;
                String artist= searchPrice.artist;
                String track= searchPrice.track;
                String accessories = searchPrice.accessories;
                String commercialLicense = searchPrice.commercialLicense;
                JOptionPane.showMessageDialog(null, "Name: "+customerName+"\nGenre: "+genre+"\nArtist: "+artist+" \nTrack: "+track+"\nAccessories: "+accessories+"\nCommercial License: "+commercialLicense,"Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
    }
    
    /*Searching existing genre in table using linear search*/
    public void searchByGenre(JTextField searchByGenreField,JTable dataTable,ArrayList<DataModel> tableData){
        String searchGenre = searchByGenreField.getText().trim().toLowerCase();
        if(searchGenre.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null,"Please Enter A Value In The Search Field","Field Is Empty",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = 0;
        String soldTracks = null;
        LinkedList <String> recentSearchResult = new LinkedList();
        int rowCount = dataTable.getRowCount();
        //This for loop checks if the genre exists, and if it exists, tracks are concatenated to the search result
        for(int i=0;i<rowCount;i++){
            DataModel model = tableData.get(i);
            if(searchGenre.equals(model.genre.trim().toLowerCase())){
                if(recentSearchResult.isEmpty()){
                    if(soldTracks != null){
                        soldTracks = "\n"+soldTracks + "\n"+ model.track + " By " + model.artist;
                    }else{
                        soldTracks = model.track + " By " +model.artist;
                    }
                    recentSearchResult.add(model.track);
                    
                }else{
                    for(int j=0;j<recentSearchResult.size();j++){                        
                        if(!recentSearchResult.get(j).equalsIgnoreCase(model.track)){
                            soldTracks = "\n"+soldTracks + "\n"+ model.track + " By " + model.artist;
                        }
                    }                                              
                }
               result++;
            }
        }
        if(result > 0){
            JOptionPane.showMessageDialog(null, "Songs Sold For "+searchGenre+" are "+soldTracks," Search Result",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"There Are No Purchases Linked With The Searched Genre ","Result Not Found",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
