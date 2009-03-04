package edu.teco.automata.generator;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;

public class Runner {
   public static void main(String args[]) {
      String xsdPath = args[0];
      String genDir  = args[1];
      
      File xsdFile = new File(xsdPath);
      if (!xsdFile.exists()) {
         System.out.println("File doesn't exist");
         return;
      }

      XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
      Collection<EObject> eCorePackages = xsdEcoreBuilder.generate(URI
            .createFileURI(xsdPath));

      String wfFileSchema = "edu/teco/automata/generator/m2mt.oaw";
      Map<String, String> propertiesSchema = new HashMap<String, String>();
      Map<String, EPackage> slotContents = new HashMap<String, EPackage>();

      for (Iterator<EObject> iter = eCorePackages.iterator(); iter.hasNext();) {
         EPackage element = (EPackage) iter.next();
         slotContents.put("SchemaModel", element);
      }
      int sufIdx = xsdFile.getName().lastIndexOf(".");
      String automataFileName = xsdFile.getName().substring(0, sufIdx) +
                                ".automata";  

      // propertiesSchema.put("modelFile", "platform:/resource"
      // + selectedFile.getFullPath());
      propertiesSchema.put("srcgen", genDir);

      new File(genDir).mkdirs();
      propertiesSchema.put("automataFile", automataFileName);

      new WorkflowRunner().run(wfFileSchema, new NullProgressMonitor(),
            propertiesSchema, slotContents);

      String wfFileAutomata = "edu/teco/automata/generator/workflow.oaw";
      Map<String, String> properties = new HashMap<String, String>();
      Map<String, EPackage> slotContents2 = new HashMap<String, EPackage>();

      properties.put("srcgen", genDir);
      properties.put("modelFile", genDir + "/" + automataFileName);
//         properties.put("DeviceName", config.getDeviceName());
//         properties.put("NSPrefix", config.getNsPrefix());
//         properties.put("MsgOutURI", config.getMsgOutURI());
//         properties.put("MsgRspURI", config.getMsgRspURI());

      new WorkflowRunner().run(wfFileAutomata, new NullProgressMonitor(),
            properties, slotContents2);

   }
}
