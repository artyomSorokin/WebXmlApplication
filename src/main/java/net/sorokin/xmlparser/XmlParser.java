package net.sorokin.xmlparser;


import net.sorokin.entity.XmlEntity;
import java.util.List;

public interface XmlParser {

    List<XmlEntity> parseXmlFile(String path) throws Exception;

    void writeEntityToXmlFile(List<XmlEntity> xmlEntities) throws Exception;
}
