package net.sorokin.xmlparser;

import net.sorokin.entity.XmlEntity;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XmlParserImpl implements XmlParser {

    private static final String TOTAL_XML_FILE = "file/data.xml";
    private static final String MAIN_XML_FILE = "D:/temp/temp/mainXmlFile/data.xml";
    private static final String TITLE = "TITLE";
    private static final String ARTIST = "ARTIST";
    private static final String COUNTRY = "COUNTRY";
    private static final String COMPANY = "COMPANY";
    private static final String PRICE = "PRICE";
    private static final String YEAR = "YEAR";


    public List<XmlEntity> parseXmlFile(String path) throws Exception{
        File xmlFile = new File(path);
        List<XmlEntity> entityList = new ArrayList<XmlEntity>();
        SAXBuilder builder = new SAXBuilder();
        XmlEntity xmlEntity;
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootElement = document.getRootElement();
            List<Element> cdElements = rootElement.getChildren();
            for (int i = 0; i < cdElements.size(); i++) {
                Element rootNode = cdElements.get(i);
                String title = rootNode.getChildText(TITLE);
                String artist = rootNode.getChildText(ARTIST);
                String country = rootNode.getChildText(COUNTRY);
                String company = rootNode.getChildText(COMPANY);
                String price = rootNode.getChildText(PRICE);
                String year = rootNode.getChildText(YEAR);

                xmlEntity = new XmlEntity();
                xmlEntity.setTitle(title);
                xmlEntity.setArtist(artist);
                xmlEntity.setCountry(country);
                xmlEntity.setCompany(company);
                xmlEntity.setPrice(Double.parseDouble(price));
                xmlEntity.setYear(Integer.parseInt(year));

                entityList.add(xmlEntity);
                }
        } catch (IOException | JDOMException e) {
            throw new Exception("Problem in parseXmlFile method" + e);
        }
        return entityList;
    }

    public void writeEntityToXmlFile(List<XmlEntity> xmlEntities) throws Exception{
        List<XmlEntity> elements = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(MAIN_XML_FILE);
        if(!xmlFile.exists()){
            copyFile();
        }
        try{
            Document document = (Document) builder.build(xmlFile);
            Element rootElement = document.getRootElement();
            List<Element> cdElements = rootElement.getChildren();
            for(XmlEntity xmlEntity : xmlEntities) {
                for (int i = 0; i < cdElements.size(); i++) {
                    Element cdElement = cdElements.get(i);
                    String title = cdElement.getChildText(TITLE);
                    if (title.equalsIgnoreCase(xmlEntity.getTitle())) {
                        cdElement.removeContent();
                        cdElement = createNewCdElement(xmlEntity, cdElement);
                        elements.add(xmlEntity);
                    }
                }
                if(!elements.contains(xmlEntity)){
                    Element newCdElement = new Element("CD");
                    Element cd = createNewCdElement(xmlEntity, newCdElement);
                    rootElement.addContent(cd);
                }
            }
            document.setContent(rootElement);
            FileWriter writer = new FileWriter(xmlFile.getAbsolutePath());
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, writer);
            writer.close();
        } catch (IOException | JDOMException e){
            throw new Exception("Problem in parseXmlFile method" + e);
        }
    }

    private Element createNewCdElement(XmlEntity xmlEntity, Element cd){
        cd.addContent(new Element(TITLE).setText(xmlEntity.getTitle()));
        cd.addContent(new Element(ARTIST).setText(xmlEntity.getArtist()));
        cd.addContent(new Element(COUNTRY).setText(xmlEntity.getCountry()));
        cd.addContent(new Element(COMPANY).setText(xmlEntity.getCompany()));
        cd.addContent(new Element(PRICE).setText(String.valueOf(xmlEntity.getPrice())));
        cd.addContent(new Element(YEAR).setText(String.valueOf(xmlEntity.getYear())));
        return cd;
    }

    private void copyFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File templateXmlFile = new File(classLoader.getResource(TOTAL_XML_FILE).getFile());
        File newXmlFile = new File(MAIN_XML_FILE);
        newXmlFile.getParentFile().mkdirs();
        newXmlFile.createNewFile();

        try(InputStream is = new FileInputStream(templateXmlFile);
            OutputStream os = new FileOutputStream(newXmlFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new IOException("Problem in writeEntityToXmlFile method" + e);
        }
    }

}
