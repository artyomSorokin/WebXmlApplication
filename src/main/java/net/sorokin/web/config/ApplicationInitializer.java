package net.sorokin.web.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class ApplicationInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ServiceConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/", "/welcome"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }

}
