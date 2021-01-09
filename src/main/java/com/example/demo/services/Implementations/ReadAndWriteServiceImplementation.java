package com.example.demo.services.Implementations;


import com.example.demo.services.ReadAndWriteService;
import com.example.demo.threads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAndWriteServiceImplementation implements ReadAndWriteService {


    @Autowired
    CsvToPostgresqlRead csvToPostgresqlRead;

    @Autowired
    PostgresqlToCsv postgresqlToCsv;

    @Autowired
    JsonToMongo jsonToMongo;

    @Autowired
    MongoToJson mongoToJson;


    @Autowired
    XmlToRedisRead xmlToRedis;


    @Autowired
    RedisToXmlWrite redisToXml;

    @Override
    public void readAndWrite() {


        // read threads block
        csvToPostgresqlRead.start();
        jsonToMongo.start();
        xmlToRedis.start();

        try {
            csvToPostgresqlRead.join();
            jsonToMongo.join();
            xmlToRedis.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        postgresqlToCsv.start();
        mongoToJson.start();
        redisToXml.start();


        try {
            postgresqlToCsv.join();
            mongoToJson.join();
            redisToXml.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // write threads block
    }
}
