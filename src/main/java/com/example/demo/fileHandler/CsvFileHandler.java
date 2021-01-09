package com.example.demo.fileHandler;

import com.example.demo.entity.EmployeePostgresql;
import com.example.demo.resources.Constants;
import com.example.demo.services.Implementations.PostgresqlServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CsvFileHandler implements Constants {

    @Autowired
    PostgresqlServiceImplementation postgresqlServiceImplementation;

    public synchronized void readFromFile() {

        try {
            File file = new File( XML_INPUT_FILE_ADDRESS );
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            int iter = 0;
            while ((line = br.readLine()) != null) {
                EmployeePostgresql emp = new EmployeePostgresql();

                tempArr = line.split(",");

                emp.setId( iter );
                emp.setFirstName(tempArr[0]);
                emp.setLastName(tempArr[1]);
                emp.setDateOfBirth(tempArr[2]);
                emp.setExperience(tempArr[3]);

                postgresqlServiceImplementation.save(emp);

                iter++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }




    public void writeToCsv() {

        List<EmployeePostgresql> employeePostgresqls = postgresqlServiceImplementation.findAll();


        for (EmployeePostgresql employee:employeePostgresqls
             ) {

            BufferedReader br=null;
            try {
                br = new BufferedReader(new FileReader(new File(CSV_OUPUT_FILE_ADDRESS)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StringBuffer sb1 = new StringBuffer();

            while (true) {
                String line = null;
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null) break;
                sb1.append(line).append("\n");
            }
            try (FileWriter writer = new FileWriter(CSV_OUPUT_FILE_ADDRESS)) {
                StringBuilder sb = new StringBuilder();
                sb.append(employee.getFirstName());
                sb.append(',');
                sb.append(employee.getLastName());
                sb.append(',');
                sb.append(employee.getDateOfBirth());
                sb.append(',');
                sb.append(employee.getExperience());
                sb.append(",\n");
                sb1.append(sb);
                writer.write(sb1.toString());
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
