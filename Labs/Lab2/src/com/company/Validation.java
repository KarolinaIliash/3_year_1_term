package com.company;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

//Use java Validator to validate xml with xsd schema
public class Validation {
    public void validate(String language, String fileName, String schemaName) throws SAXException, IOException{
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(fileName);
        validator.validate(source);
        System.out.print(fileName + " is valid");
    }
}
