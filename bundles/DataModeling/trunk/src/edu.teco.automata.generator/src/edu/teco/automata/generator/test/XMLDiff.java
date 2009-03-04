package edu.teco.automata.generator.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLDiff {

   public static boolean diffXML(String file1, String file2) {

      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc1 = builder.parse(file1);
         Document doc2 = builder.parse(file2);

         NodeList nList1 = doc1.getChildNodes();
         NodeList nList2 = doc2.getChildNodes();

         if (!diffNodes(nList1, nList2))
            return false;

      } catch (Exception e) {
         return false;
      }
      return true;
   }
   
   private static boolean diffNodes(NodeList n1, NodeList n2) {
      
      if (n1.getLength() != n2.getLength())
         return false;
      if (n1.getLength() == 0)
         return true;

      for (int i = 0; i < n1.getLength(); i++) {
         if (n1.item(i).getNodeName() != n2.item(i).getNodeName())
            return false;
         if (n1.item(i).getNodeType() != n2.item(i).getNodeType())
            return false;
         
         if (!diffNodes(n1.item(i).getChildNodes(), n2.item(i).getChildNodes()))
            return false;
      }
      
      return true;
   }
}
