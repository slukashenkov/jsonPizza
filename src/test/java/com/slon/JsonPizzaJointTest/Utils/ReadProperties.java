package com.slon.JsonPizzaJointTest.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ReadProperties {

    final static Logger logger = Logger.getLogger(ReadProperties.class);

    private HashMap<String, Object> properties;
    private Properties prop = null;
    private InputStream input = null;
    Enumeration enumPropNames = null;


    public ReadProperties() {
    }

    public ReadProperties(String properties) {
        if (logger.isDebugEnabled()) {
            logger.debug("As The string comes into ReadProp Class: " + properties);
        }
         //System.out.print("As The string comes into ReadProp Class: "+properties + " \n");

        try {
            resetInput(properties);
            this.prop = new Properties();
            // load a properties file
            this.prop.load(this.input);
            enumPropNames = prop.propertyNames();
            this.properties = new HashMap<>();
            fillMapProp(enumPropNames,
                    prop,
                    this.properties);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
     }
/* =============================================================================
* AdHoc Utils Functions
*/
    private void resetMapBeforeFill(String fileName) throws IOException {

         resetInput(fileName);
         this.prop = new Properties();
        try {
            this.prop.load(this.input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.enumPropNames = this.prop.propertyNames();
    }

    private void resetInput(String fileLocation){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        this.input =loader.getResourceAsStream(fileLocation);
    }

    private void   fillMapProp(Enumeration e,
                                            Properties prop,
                                            Map<String,Object> map){
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                // get the property value and print it out
                map.put(key, prop.getProperty(key));
            }
    }
/*======================================================================================================================
* getters/Setters
*/

    public Object getProperty(String key) throws Exception {
        Object property;
            if(this.prop.containsKey(key)){
                property=this.properties.get(key);
            }else{
                throw new Exception("key is not in found");
            }
        return property;
    }


}
