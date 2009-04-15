package edu.teco.automata.generator.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.teco.automata.generator.Runner;

public class XSD2Automata extends TestCase {
   
   String genDir  = "genFolder";
   String testDir = "src/edu/teco/automata/generator/test";

   public void testPrs74() {
      String automataOrg = testDir + "/prs74.automata";
      String automataNew = genDir  + "/prs74.automata";
      String xsdPath     = testDir + "/prs74.xsd";
      File genDirFile    = new File(genDir);
      
      assertTrue("clean up", 
    		  DeleteDir.deleteDirectory(genDirFile) &&
    		  genDirFile.mkdir());
      
      generateAutomata(xsdPath,automataNew);
      compareAutomata(automataOrg, automataNew);
   }
   
   public void testSensorX() {
      String automataOrg = testDir + "/SensorX-Schema.automata";
      String automataNew = genDir  + "/SensorX-Schema.automata";
      String xsdPath     = testDir + "/SensorX-Schema.xsd";
      File genDirFile    = new File(genDir);
      
      assertTrue("clean up", 
    		  DeleteDir.deleteDirectory(genDirFile) &&
    		  genDirFile.mkdir());

      generateAutomata(xsdPath,automataNew);
      compareAutomata(automataOrg, automataNew);
      DeleteDir.deleteDirectory(genDirFile);

   }
   
   private void generateAutomata(String xsdPath,String automataPath) {
	  Map<String, String> properties = new HashMap<String, String>();
	  properties.put("schemaFile", xsdPath);
	  properties.put("writeAutomataFile", "true");
	  properties.put("automataFile", automataPath);
      Runner.main(properties,new String[] {});
   }
   
   private void compareAutomata(String automataOrg, 
                                String automataNew) {
   

      File fileOrg = new File(automataOrg);
      File fileNew = new File(automataNew);
      assertTrue(fileNew.exists() && fileOrg.exists());
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document docOrg = builder.parse(automataOrg);
         Document docNew = builder.parse(automataNew);

         assertTrue(docNew.getFirstChild().getNodeName().
                    equals("Automata:StateMachine"));
         NodeList nListOrg = docOrg.getElementsByTagName("states");
         NodeList nListNew = docNew.getElementsByTagName("states");
         
         assertTrue(nListNew.getLength() == nListOrg.getLength());
         
         for (int i = 0; i < nListNew.getLength(); i++) {
            
            NamedNodeMap attr = nListNew.item(i).getAttributes();
            Node stName = attr.getNamedItem("xsi:type");
            
            if (stName.getNodeValue().equals("Automata:StartState")) {
               
            } else if (stName.getNodeValue().equals("Automata:SimpleState")) {
               Node orgNode = 
                  getOrgNode(nListOrg, attr.getNamedItem("name").getNodeValue());
               assertTrue(orgNode != null);
               
               NamedNodeMap attrOrg = orgNode.getAttributes();
               String attrList[] = { "lowerBound", "upperBound", "depth" };
               
               for (String curAttrName: attrList)
               {
                  if (attrOrg.getNamedItem(curAttrName) != null)
                     assertEquals("SimpleState attributes " + curAttrName + " are not equal",
                                  attrOrg.getNamedItem(curAttrName).getNodeValue(), 
                                  attr.getNamedItem(curAttrName).getNodeValue());
                  else
                     assertTrue("SimpleState attributes " + curAttrName + " are not equal",
                                attr.getNamedItem(curAttrName) == null);
               }
               
               NodeList childrenNew = nListNew.item(i).getChildNodes();
               NodeList childrenOrg = orgNode.getChildNodes();
               Node typeNodeNew = null;
               Node typeNodeOrg = null;

               for (int j = 0; j < childrenNew.getLength(); j++)
               {
                  if (childrenNew.item(j) == null)
                     continue;
                  if (childrenNew.item(j).getNodeName().equals("type"))
                     typeNodeNew = childrenNew.item(j); 
               }
               for (int j = 0; j < childrenOrg.getLength(); j++)
               {
                  if (childrenOrg.item(j) == null)
                     continue;
                  if (childrenOrg.item(j).getNodeName().equals("type"))
                     typeNodeOrg = childrenOrg.item(j); 
               }
               assertTrue(typeNodeNew != null);

               String typeName = "xsi:type";
               assertEquals(typeNodeNew.getAttributes().
                               getNamedItem(typeName).getNodeValue(),
                            typeNodeOrg.getAttributes().
                               getNamedItem(typeName).getNodeValue());
               
               String typeAttrList[] = {"max", "min", "length",
                                        "fractionDigits", "stepping"};
               for (String attribute : typeAttrList)
               {
                  if (typeNodeNew.getAttributes().getNamedItem(attribute) != null)
                  {  
                     assertEquals(typeNodeNew.getAttributes().
                                     getNamedItem(attribute).getNodeValue(),
                                  typeNodeOrg.getAttributes().
                                     getNamedItem(attribute).getNodeValue());
                  }
                  else
                     assertTrue(typeNodeOrg.getAttributes().
                                  getNamedItem(attribute) == null);
               }
            }
         }

      } catch (SAXException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ParserConfigurationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
   
   private Node getOrgNode(NodeList list, String name) {
      for (int i = 0; i < list.getLength(); i++) {
         NamedNodeMap attr = list.item(i).getAttributes();
         if (attr.getNamedItem("name").getNodeValue().equals(name))
            return list.item(i);
      }
      
      return null;
   }

}
