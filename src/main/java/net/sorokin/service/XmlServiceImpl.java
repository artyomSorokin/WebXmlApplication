package net.sorokin.service;


import net.sorokin.entity.XmlEntity;
import net.sorokin.xmlparser.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("xmlService")
public class XmlServiceImpl implements XmlService{

    private XmlParser xmlParser;

    @Autowired
    public XmlServiceImpl(XmlParser xmlParser) {
        this.xmlParser = xmlParser;
        System.out.println(xmlParser.getClass());
    }

    public XmlServiceImpl() {
    }

    public List<XmlEntity> findAllData(String path) throws Exception{
        List<XmlEntity> entityList = xmlParser.parseXmlFile(path);
        return entityList;
    }

    public void SpliceTwoXmlFiles(String newFilePath) throws Exception{
        List<XmlEntity> xmlEntities = findAllData(newFilePath);
        xmlParser.writeEntityToXmlFile(xmlEntities);
    }
}
