package com.nius.server.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLParse {

    // Sax解析，流解析，不会一次性全部加载到内存中
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/nius/server/XMLParser/person.xml");
        PersonHandler ph = new PersonHandler();
        parser.parse(is, ph);

        for (Person p : ph.getPersons()) {
            System.out.println(p.getName() + " ==> " + p.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person curPerson;

    public List<Person> getPersons() {
        return persons;
    }

    private String curTag;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始解析文档");
        persons = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        curTag = qName;
        if (curTag.equals("person")) {
            curPerson = new Person();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (curTag != null) {
            if (curTag.equals("name")) {
                curPerson.setName(str);
            } else if (curTag.equals("age")) {
                curPerson.setAge(new Integer(str));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            persons.add(curPerson);
        }
        curTag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("结束解析文档");
    }
}