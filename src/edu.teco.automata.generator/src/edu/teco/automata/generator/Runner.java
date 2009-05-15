package edu.teco.automata.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EPackage;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;


/**
 * @author riedel
 *
 */
public class Runner {
	
	/**
	 * @param properties workflow properties
	 * @param target	transformations to be executed
	 */
	public static void main(Map<String, String> properties,String[] target)
	{
    	final String wfFileAutomata="src/edu/teco/automata/generator/xsdautomata.oaw";
        final String wfFileTargetPrefix="src/edu/teco/automata/generator/targets/";
        final String wfFileTargetSuffix=".oaw";

        
		 for(String t:target)
	        {
	        if(!(new File(wfFileTargetPrefix+t+wfFileTargetSuffix)).exists())
	        {
	      	  System.out.println("target " + wfFileTargetPrefix+t+wfFileTargetSuffix+ " doesn't exist");
	      	  System.exit(1);
	        }
	        }
		 
		 properties.put("outputSlot", "model" ); 
		 
		 org.openarchitectureware.workflow.monitor.ProgressMonitor ProgressMonitor=new NullProgressMonitor();
	      
	      Map<String, EPackage> slotContents = new HashMap<String, EPackage>();
	        
	        WorkflowRunner r=new WorkflowRunner();
	        if(!r.run(wfFileAutomata, ProgressMonitor,
	                properties, slotContents)) System.exit(1);
	        for(String t:target)
	        {
	        	if(!r.run(wfFileTargetPrefix+t+wfFileTargetSuffix, ProgressMonitor,
	                properties, slotContents)) System.exit(1);
	        }
	     
		
	}
    /**
     * @param xsdPath Path to xsd or wsdl file  (will be added to workflow properties)
     * @param genDir target directory  (will be added to workflow properties)
     * @param target same as {@link #main(Map ,String[])}
     * 
     * reads file generator.properties for default properties
     */
    public static void main(String xsdPath,String genDir,String[] target)
    {

        
        Map<String, String> properties = new HashMap<String, String>();
        
        java.util.Properties  propertiesFile = new java.util.Properties();
        
        File p=new File("generator.properties");
        if(p.exists())
        {
        	FileReader rp=null;
			try {
				rp = new FileReader(p);
			
			try {
				propertiesFile.load(rp);
				for(Entry<Object, Object> e:propertiesFile.entrySet())
					properties.put((String)e.getKey(), (String)e.getValue());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			} catch (FileNotFoundException e1) {
				
			}
			finally
			{
				try {
					if(rp!=null)
						rp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
        if(xsdPath!=null)
        	properties.put("schemaFile", xsdPath);
        
        if(genDir!=null)
        	properties.put("outlet_path", genDir);
        
        main(properties,target);
    }
    
	/**
	 * @param args "usage: Runner xsdPath genDir target*"
	 * invokes {@link #main(String ,String ,String[])}
	 */
	public static void main(String args[]) {
      
	  if(args.length<3)
	  {
      	  System.out.println("usage: Runner xsdPath genDir target*");
      	  System.exit(1);
	  }
	  
      main(args[0],args[1],Arrays.copyOfRange(args, 2, args.length));

   }
}
