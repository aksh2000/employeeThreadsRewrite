package com.example.demo.fileHandler;

import com.example.demo.entity.EmployeeRedis;
import com.example.demo.resources.Constants;
import com.example.demo.services.Implementations.RedisServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Date;
import java.util.List;


@Component
public class XmlFileHandler implements Constants {


    @Autowired
    RedisServiceImplementation redisServiceImplementation;


    private File file;
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private DocumentBuilder db;
    private Document doc;

    public XmlFileHandler() {
        try {
            this.file = new File( XML_INPUT_FILE_ADDRESS );
            db = dbf.newDocumentBuilder();
        } catch (Exception ex) {
            System.out.println( ex.getMessage() );
        }

    }

    public void read() {
        try {
            doc = db.parse( file );
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName( "employee" );
            for (int iterator = 1; iterator < 100; iterator++) {
                Node node = nodeList.item( iterator );
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    EmployeeRedis emp = new EmployeeRedis();
                    Element element = (Element) node;
                    emp.setId(iterator);
                    emp.setFirstName( element.getElementsByTagName( "firstName" ).item( 0 ).getTextContent() );
                    emp.setLastName( element.getElementsByTagName( "lastName" ).item( 0 ).getTextContent() );
                    String dob = element.getElementsByTagName( "dateOfBirth" ).item( 0 ).getTextContent();

                    emp.setDateOfBirth(dob);
                    emp.setExperience( element.getElementsByTagName( "experience" ).item( 0 ).getTextContent());


                    redisServiceImplementation.save(emp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void write() {


        try {
            doc = db.newDocument();
// root element creation in the doc
            Element rootElement = doc.createElement( "Employees" );
            doc.appendChild( rootElement );

            List<EmployeeRedis> employeeRedisList = redisServiceImplementation.findAll();

            int i = 1;
            for (EmployeeRedis emp : employeeRedisList) {

                EmployeeRedis employeeRedis = new EmployeeRedis();
                employeeRedis.setId(i++);
                employeeRedis.setFirstName( emp.getFirstName() );
                employeeRedis.setLastName( emp.getLastName() );
                employeeRedis.setDateOfBirth("22/02/1994");
                employeeRedis.setExperience( Double.toString(emp.getExperience()));

                Element employee = doc.createElement( "Employee" );
                rootElement.appendChild( employee );

                Element firstName = doc.createElement( "firstName" );
                firstName.appendChild( doc.createTextNode( emp.getFirstName() ) );
                employee.appendChild( firstName );

                Element lastName = doc.createElement( "lastName" );
                lastName.appendChild( doc.createTextNode( emp.getLastName() ) );
                employee.appendChild( lastName );

                Element dateOfBirth = doc.createElement( "dateOfBirth" );
                dateOfBirth.appendChild( doc.createTextNode( emp.getDateOfBirth().toString() ) );
                employee.appendChild( dateOfBirth );

                Element experience = doc.createElement( "experience" );
                experience.appendChild( doc.createTextNode( String.valueOf( emp.getExperience() ) ) );
                employee.appendChild( experience );
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource( doc );
            StreamResult result = new StreamResult( new File( XML_OUTPUT_FILE_ADDRESS ) );
            transformer.transform( source, result );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}