package com.slon.JsonPizzaJointTest;


import com.slon.JsonPizzaJointTest.Utils.ReadProperties;
import com.slon.JsonPizzaJointTest.Utils.VarTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import  org.junit.Ignore;

/**
 * Unit test for simple JsonPizzaJointTest.
 */

@RunWith(JUnitParamsRunner.class)
public class JsonPizzaJointTest  {

    private JsonPizzaJoint openJoint;
    private Order testOrder =null;

    private String fullPath;
    private List<String> positiveParams;
    private List<String> negativeParams;
    private ReadProperties props;

    //Test  data file names boundaries
    private int positiveDataRecords;
    private int positiveDataStart;
    private int negativeDataRecords;
    private int negativeDataStart;
    private String dataFilesLocationNameTmpl;
    private String dataFilesLocationNamePostFix;

    public JsonPizzaJointTest(){
        this.openJoint = new JsonPizzaJoint();
        this.props=new ReadProperties("defaultTests.properties");
        try {
            this.positiveDataRecords=Integer.valueOf((String) this.props.getProperty("positiveDataRecords"));
            this.positiveDataStart= Integer.valueOf((String) this.props.getProperty("positiveDataStart"));
            this.negativeDataRecords=Integer.valueOf((String) this.props.getProperty("negativeDataRecords"));
            this.negativeDataStart=Integer.valueOf((String) this.props.getProperty("negativeDataStart"));
            this.dataFilesLocationNameTmpl=(String) this.props.getProperty("dataFilesLocationNameTmpl");
            this.dataFilesLocationNamePostFix =(String) this.props.getProperty("dataFilesLocationNamePostFix");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String relativePath = Paths.get("").toAbsolutePath().toString();
        //System.out.println("Current relative path is: " + relativePath);
        fullPath = relativePath + this.dataFilesLocationNameTmpl;
    }


        @org.junit.Test
        @Parameters(method = "negativeValues")
        //@Ignore

        public void JsonPizzaJointTest01(String jsonIn) throws  Exception {

            testOrder=openJoint.acceptOrder(jsonIn).serveOrder();
            assertFalse(testOrder.isOrderStatus());
            assertTrue(VarTests.checkSubStr("Incompatible ingredients", this.testOrder.getReasonForStatus()));
        }

        @org.junit.Test
        @Parameters(method = "positiveValues")
        //@Ignore
        public void JsonPizzaJointTest02(String jsonIn) throws  Exception {

            this.testOrder=this.openJoint.acceptOrder(jsonIn).serveOrder();
            assertTrue(this.testOrder.isOrderStatus());
            assertTrue("Wrong message for ",VarTests.checkSubStr("Used ingredients", this.testOrder.getReasonForStatus()));
        }

        private Object[] negativeValues() {
            negativeParams = new LinkedList<>();

            for(int i= negativeDataStart; i<negativeDataRecords; i++) {
                String path = fullPath+i+dataFilesLocationNamePostFix;
                File file = new File(path);
                try {
                    try (InputStream jsonInput = new FileInputStream(file)) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    negativeParams.add(readFileToString(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return negativeParams.toArray();
        }

        private Object[] positiveValues() {
            positiveParams = new LinkedList<>();

            for (int i = positiveDataStart; i < positiveDataRecords; i++) {
                String path = fullPath + i + dataFilesLocationNamePostFix;
                File file = new File(path);
                try {
                    try (InputStream jsonInput = new FileInputStream(file)) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    positiveParams.add(readFileToString(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return positiveParams.toArray();
        }
}
