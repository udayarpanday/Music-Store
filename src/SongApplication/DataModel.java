/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SongApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author PREDATOR
 */
public class DataModel {
    public int price,topItemChoice;
    public String serialNumber, name, genre, artist, track, accessories, commercialLicense,item;
    
    /*Constructor to call other methods*/
    public DataModel(){}   
    
    /*Constructor to set values of global variables*/
    public DataModel(String serialNumber, String name, String genre, String artist, String track, String accessories, String commercialLicense, int price){
        this.serialNumber = serialNumber;
        this.name = name;
        this.genre = genre;
        this.artist = artist;
        this.track = track;
        this.accessories = accessories;
        this.commercialLicense = commercialLicense;
        this.price = price;
    }
    
    /*To display the most repeating element in the table using hash map*/
    public String topItemDisplay(ArrayList<DataModel> list, int topItemChoice){
        LinkedList <String> valueList = new LinkedList();
        
        for(int tableDataIndex=0;tableDataIndex<list.size();tableDataIndex++){
            if(topItemChoice == 0){
                valueList.add(list.get(tableDataIndex).genre);
            }else{
                valueList.add(list.get(tableDataIndex).artist);
            }
        }
        
        Map <String,Integer> map = new HashMap<>();
        
        // for loop for populating the map. If key exists, its value is incremented by one, if key does not exist, it is added to the map
        for(int i=0;i<valueList.size();i++){
            String key = valueList.get(i);
            
            if(map.containsKey(key)){
                int frequency = map.get(key);
                frequency++;
                map.put(key,frequency);
            }else{
                map.put(key, 1);
            }
        }
        // for loop for checking the largest amount of frequency (values in the map)
        
        int highestFrequency = 0;
        String topSellingItem = null;
        
        for(Entry<String,Integer> entryOfMap : map.entrySet()){
            if(entryOfMap.getValue()>highestFrequency){
                topSellingItem = entryOfMap.getKey();
                highestFrequency = entryOfMap.getValue();
            }
        }
        return topSellingItem;
    }
    
}