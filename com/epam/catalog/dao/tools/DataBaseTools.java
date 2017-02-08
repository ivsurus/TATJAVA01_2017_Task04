package com.epam.catalog.dao.tools;


import java.io.*;
import java.util.*;

public class DataBaseTools {

    private final String PATH = "C:\\Users\\Ivan_Surus\\IdeaProjects\\Task3\\src\\dataBase.txt";
    private File file = new File(PATH);
    private BufferedWriter writer;
    private BufferedReader reader;
    private static final DataBaseTools instance = new DataBaseTools();

    //singleton
    private DataBaseTools(){}

    public static DataBaseTools getInstance(){
        return instance;
    }

    public void writeToDB(String entityData) throws IOException{
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(entityData);
            writer.close();
    }


    public Set readFromDB() throws IOException{
        Set<String> set = new HashSet<>();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String string;
        while ((string = reader.readLine()) != null) {
            set.add(string);
        }
        reader.close();
        return set;
    }

    public Set delUnnecessaryData(Set<String> set, String identifier){
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().substring(0,1).equals(identifier)) {
                iterator.remove();
            }
        }
        return set;
    }

}

