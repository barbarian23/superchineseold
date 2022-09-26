package konec.superchinese.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import konec.superchinese.commons.ScreenNameInterface;

@Controller
public class SiteMapController implements ScreenNameInterface {

	@GetMapping(value = "/sitemap.xml")
    public void serveSiteMapFile(HttpServletResponse response) throws IOException, URISyntaxException {

		URL resource = getClass().getClassLoader().getResource("sitemap.xml");
        response.setContentType("application/xml");
        try (BufferedReader bufferedReader = 
                new BufferedReader(new FileReader(new File(resource.toURI())))) {
            String line;
            StringBuilder siteMapBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                siteMapBuilder.append(line);
            }
            ServletOutputStream outStream = response.getOutputStream();
            outStream.println(siteMapBuilder.toString());
            outStream.flush();
            outStream.close();
        }
    }
}