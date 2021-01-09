package com.example.demo.fileHandler;

import com.example.demo.entity.EmployeeMongodb;
import com.example.demo.resources.Constants;
import com.example.demo.services.Implementations.MongodbServiceImplementation;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class JsonFileHandler implements Constants {

    @Autowired
    MongodbServiceImplementation mongodbServiceImplementation;

    private JSONArray employee = new JSONArray();
    private int lastNum = 0;

    public  synchronized void readFromFile(){
        JSONParser parse = new JSONParser();
        try {
            FileReader myFile = new FileReader(new File(JSON_INPUT_FILE_ADDRESS));
            employee = (JSONArray) parse.parse(myFile);
            employee.forEach(emp -> {


                parseEmployee((JSONObject) emp,lastNum++);
            });

            myFile.close();
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private EmployeeMongodb parseEmployee(JSONObject emp, int id) {
        EmployeeMongodb myEmployee = new EmployeeMongodb();
        myEmployee.setId(id);
        myEmployee.setFirstName((String) emp.get("firstName"));
        myEmployee.setLastName((String) emp.get("lastName"));
        myEmployee.setDateOfBirth((String) emp.get("dateOfBirth"));
        myEmployee.setExperience(emp.get("experience") + "");
        mongodbServiceImplementation.save(myEmployee);
        return myEmployee;
    }


    public synchronized void writeToFile(){


        List<EmployeeMongodb> employeeMongodbList = mongodbServiceImplementation.findAll();


        for (EmployeeMongodb emp:employeeMongodbList
             ) {
            JSONParser parse = new JSONParser();
            JSONObject object = new JSONObject();
            object.put("firstName", emp.getFirstName());
            object.put("lastName", emp.getLastName());
            object.put("dateOfBirth", emp.getDateOfBirth());
            object.put("experience", emp.getExperience());
            JSONArray employeeArray = new JSONArray();
            try {
                FileReader myFile = new FileReader(new File(JSON_OUTPUT_FILE_ADDRESS));
                employeeArray = (JSONArray) parse.parse(myFile);
                employeeArray.add(object);
            } catch (IOException | ParseException ex) {
                System.out.println(ex.getMessage());
            }
            try (FileWriter writer = new FileWriter(JSON_OUTPUT_FILE_ADDRESS)) {
                writer.write(employeeArray.toJSONString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
}
