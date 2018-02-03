/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author ivan
 */
public class LogSaxHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";
    private boolean inElement = false;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start element: " + qName);
        if (!qName.equals(CLASSNAME)) {
            inElement = true;
        } else {
            System.out.println("Class name: " + attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End element: " + qName);
        inElement = false;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (inElement) {
            System.out.println("Process : " + new String(ch, start, length));
        }
    }

    public Object getObject() {
        return null;
    }
}
