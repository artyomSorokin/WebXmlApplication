package net.sorokin.web;

import net.sorokin.entity.XmlEntity;
import net.sorokin.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

@Controller
public class AppController {

    private static String UPLOADED_FOLDER = "D://temp//temp//";
    private static final String MAIN_XML_FILE = "D:/temp/temp/mainXmlFile/data.xml";
    private XmlService xmlService;

    @Autowired
    public AppController(XmlService xmlService){
        this.xmlService = xmlService;
    }

    public AppController() {
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "upload";
    }


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,HttpSession session) {

        String message = "";

        if (file.isEmpty()) {            
            return "/upload";
        }
        File dir = new File(UPLOADED_FOLDER);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File uploadXmlFile =  new File(UPLOADED_FOLDER + file.getOriginalFilename());
            FileCopyUtils.copy(file.getBytes(), uploadXmlFile);
            xmlService.SpliceTwoXmlFiles(uploadXmlFile.getAbsolutePath());
        } catch (Exception e) {
            message = "Sorry, but our server is temporarily down/r/n"+e.getMessage();
            session.setAttribute("message", message);
        }
        return "redirect:/data/";
    }

    @RequestMapping(value = "data/getDataFromXml", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<XmlEntity>> getXmlData(HttpSession session) {

        String message = "";
        ResponseEntity<List<XmlEntity>> responseEntity = null;
        try {
            System.out.println("Method getContacts");
            List<XmlEntity> data = xmlService.findAllData(MAIN_XML_FILE);
            responseEntity = new ResponseEntity<List<XmlEntity>>(data, HttpStatus.OK);
        } catch (Exception e){
            message = "Sorry, but our server is temporarily down/r/n"+e.getMessage();
            session.setAttribute("message", message);
        }
        return responseEntity;
    }

    @GetMapping("/data/")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value="/downloadXml", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, HttpSession session) {

        String message = "";
        File file = new File(MAIN_XML_FILE);
        try {
            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());

            if (mimeType == null) {
                System.out.println("mimetype is not detectable, will take default");
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputstream = response.getOutputStream();
            FileCopyUtils.copy(inputStream, outputstream);
            inputStream.close();
            outputstream.close();
        } catch (Exception e){
            message = "Sorry, but our server is temporarily down/r/n"+e.getMessage();
            session.setAttribute("message", message);
        }
    }

    @GetMapping("/download")
    public String getDownloadPage() {
        return "downloadPage";
    }


}
