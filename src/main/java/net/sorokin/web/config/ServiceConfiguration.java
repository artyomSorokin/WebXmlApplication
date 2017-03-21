package net.sorokin.web.config;

import net.sorokin.xmlparser.XmlParser;
import net.sorokin.xmlparser.XmlParserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.sorokin")
public class ServiceConfiguration {

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
