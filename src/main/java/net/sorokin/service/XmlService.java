package net.sorokin.service;


import net.sorokin.entity.XmlEntity;
import java.util.List;

public interface XmlService {

    List<XmlEntity> findAllData(String path) throws Exception;

    void SpliceTwoXmlFiles(String newFilePath) throws Exception;
}
