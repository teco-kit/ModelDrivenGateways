package edu.teco.automata.generator.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.*;
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


		int i1,i2;
		for ( i1 = 0,i2=0; i1 < n1.getLength()&& i2 < n2.getLength();) {

			Node node1=n1.item(i1);
			Node node2=n2.item(i2);
			
			switch(node1.getNodeType())
			{
			case Node.ELEMENT_NODE:
			case Node.ATTRIBUTE_NODE:
				break;
			case Node.TEXT_NODE:
				if (node1.getNodeValue()==null||node1.getNodeValue().trim().isEmpty())
				{
					i1++;
					continue;
				}
				break;
				
			 default:
				 i1++;
				continue;
			}
			
			switch(node2.getNodeType())
			{
			case Node.ELEMENT_NODE:
			case Node.ATTRIBUTE_NODE:
				break;
			case Node.TEXT_NODE:
				if (node2.getNodeValue()==null||node2.getNodeValue().trim().isEmpty())
				{
					i2++;
					continue;
				}
				break;
				
			 default:
				 i1++;
				continue;
			}
			


			if (node1.getNodeType() != node2.getNodeType())
				return false;
			switch(node1.getNodeType())
			{
			case Node.ELEMENT_NODE:
			{
				String name1=node1.getLocalName();
				if(name1==null) name1=node1.getNodeName().replaceFirst(".+:",""); 
				String name2=node2.getLocalName();
				if(name2==null) name2=node2.getNodeName().replaceFirst(".+:",""); 
			
				if (! name1.equals(name2)) //TODO is not very strict -> name space
					return false;
				if (!diffNodes(n1.item(i1).getChildNodes(), n2.item(i2).getChildNodes()))
				 return false;
				break;
			}
			case Node.ATTRIBUTE_NODE:
				if (! node1.getLocalName().equals(node2.getLocalName()))//TODO is not very strict -> name space
					return false;
			case Node.TEXT_NODE:
			{
				String val1=node1.getNodeValue().trim();
				String val2=node2.getNodeValue().trim();
				if (! val1.equals(val2))
					return false;
			}
				break;
			default: 
				return false; //assert never reached
			}

			i1++;
			i2++;
		}

		while(i1<n1.getLength())
		{
			Node node1=n1.item(i1);
			switch(node1.getNodeType())
			{
			case Node.ELEMENT_NODE:
			case Node.ATTRIBUTE_NODE:
				return false;
			case Node.TEXT_NODE:
				if (node1.getNodeValue()==null||node1.getNodeValue().trim().isEmpty())
				{
					i1++;
					continue;
				}
				else
					return false;		
			 default:
				 i1++;
				continue;
			}
		}
		
		while(i2<n2.getLength())
		{
			Node node2=n2.item(i2);
			switch(node2.getNodeType())
			{
			case Node.ELEMENT_NODE:
			case Node.ATTRIBUTE_NODE:
				return false;
			case Node.TEXT_NODE:
				if (node2.getNodeValue()==null||node2.getNodeValue().trim().isEmpty())
				{
					i2++;
					continue;
				}
				else
					return false;		
			 default:
				 i2++;
				continue;
			}
		}

		return true;
	}
}
