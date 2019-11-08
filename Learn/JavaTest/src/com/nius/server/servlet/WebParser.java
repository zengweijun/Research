package com.nius.server.servlet;

import com.nius.server.XMLParser.Person;
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

public class WebParser {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/nius/server/servlet/web.xml");
        WebParserHandler ph = new WebParserHandler();
        parser.parse(is, ph);

        WebContext wc = new WebContext(ph.getEntities(), ph.getMappings());
        // 假设输入了 "/login","/reg","/g",
        String className = wc.getClz("/g");
        Class servletClass = Class.forName(className);
        Servlet servlet = (Servlet)servletClass.newInstance();
        servlet.service();
    }
}

class WebParserHandler extends DefaultHandler {
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Mapping> mappings = new ArrayList<Mapping>();
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMapping = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // System.out.println("开始 tagName: " + qName);
        if (null != qName) {
            tag = qName;
            if (tag.equals("servlet")) {
                entity = new Entity();
                isMapping = false;
            } else if (tag.equals("servlet-mapping")) {
                mapping = new Mapping();
                isMapping = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String tagValue = new String(ch, start, length).trim();
        if (null != tag) {
            if (isMapping) {
                if (tag.equals("servlet-name")) {
                    mapping.setName(tagValue);
                } else if (tag.equals("url-pattern")) {
                    mapping.addPattern(tagValue);
                }
            } else {
                if (tag.equals("servlet-name")) {
                    entity.setName(tagValue);
                } else if (tag.equals("servlet-class")) {
                    entity.setClz(tagValue);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // System.out.println("结束 tagName: " + qName);
        if (null != qName) {
            if (qName.equals("servlet")) {
                entities.add(entity);
            } else if (qName.equals("servlet-mapping")) {
                mappings.add(mapping);
            }
        }
        tag = null;
    }

    public List<Entity> getEntities() {
        return entities;
    }
    public List<Mapping> getMappings() {
        return mappings;
    }
}