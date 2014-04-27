/*******************************************************************************
 * PageFlow Dynamic Workflow Engine
 ******************************************************************************/
package controller;

import pageflow.model.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JsonView {

    @Bean
    public MarshallingHttpMessageConverter marshallingMessageConverter() {
        return new MarshallingHttpMessageConverter(
                jaxb2Marshaller(),
                jaxb2Marshaller()
        );
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(new Class[]{
            Process.class
        });
        return marshaller;
    }

    private MarshallingHttpMessageConverter xmlConverter;

    /**
     * Json conversion
     */
    public ModelAndView renderJSON(Object model,
            HttpServletResponse response) {

        MappingJacksonHttpMessageConverter converter
                = new MappingJacksonHttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_JSON;
        try {
            response.addHeader("Accept:", "application/json");
            converter.write(model, mediaType, new ServletServerHttpResponse(
                    response));
        } catch (HttpMessageNotWritableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * XStream conversion
     */
    public ModelAndView renderXML(Object model,
            HttpServletResponse response) {

        MarshallingHttpMessageConverter converter
                = new MarshallingHttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_XML;
        try {
            response.addHeader("Accept:", "application/xml");
            xmlConverter.write(model, mediaType,
                    new ServletServerHttpResponse(response));
            /*converter.write(model, mediaType, new ServletServerHttpResponse(response));*/
        } catch (HttpMessageNotWritableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MarshallingHttpMessageConverter getXmlConverter() {
        return xmlConverter;
    }

    @Required
    @Autowired
    public void setXmlConverter(MarshallingHttpMessageConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }
}
