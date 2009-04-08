package edu.teco.automata.generator.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDiff {

   public static boolean diffXML(String file1, String file2) {

    
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			return false;
		}
         Document doc1,doc2;
		try {
			doc1 = builder.parse(file1);
			doc2 = builder.parse(file2);
		} catch (SAXException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
      

         NodeList nList1 = doc1.getChildNodes();
         NodeList nList2 = doc2.getChildNodes();

         if (!diffNodes(nList1, nList2))
            return false;
   
      return true;
   }
   
   private static boolean diffNodes(NodeList n1, NodeList n2) {
      
      if (n1.getLength() != n2.getLength())
         return false;
      if (n1.getLength() == 0)
         return true;

      for (int i = 0; i < n1.getLength(); i++) {
         if (! n1.item(i).getNodeName().equals( n2.item(i).getNodeName()))
            return false;
         if (n1.item(i).getNodeType() != n2.item(i).getNodeType())
            return false;
         
         if (!diffNodes(n1.item(i).getChildNodes(), n2.item(i).getChildNodes()))
            return false;
      }
      
      return true;
   }
}
