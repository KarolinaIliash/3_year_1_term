package com.company;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

//Use java transform tools for trnsforming xml into html with xslt
public class Transform {
    public void transform(String fileXSL, String fileXML, String newFileName){
        try{
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(fileXSL));
            transformer.transform(new StreamSource(fileXML),
                                  new StreamResult(newFileName));
        } catch (TransformerException e){
            e.printStackTrace();
        }
    }
}
