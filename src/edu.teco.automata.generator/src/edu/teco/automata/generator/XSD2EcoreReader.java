/**
 * 
 */
package edu.teco.automata.generator;

import java.io.File;
import java.util.Collection;

import java.util.Iterator;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.workflow.lib.AbstractWorkflowComponent;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;

/**
 * @author riedel
 * 
 * OAW Workflow component to read XSD (and WSDL Files) as ecore Models
 */

public class XSD2EcoreReader extends AbstractWorkflowComponent {
	private String outputSlot;
	   public void setOutputSlot(final String outputSlot) {
		      this.outputSlot = outputSlot;
		   }
	
	private boolean firstElementOnly = true;
	public void setFirstElementOnly(final boolean firstElementOnly) {
		      this.firstElementOnly = firstElementOnly;
		   }
	
	private String xsdPath;
	public void setXsdPath(String xsdPath) {
	      this.xsdPath = xsdPath;
	   }
	
	public XSD2EcoreReader() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.workflow.WorkflowComponent#checkConfiguration(org.openarchitectureware.workflow.issues.Issues)
	 */
	
	@Override
	public void checkConfiguration(Issues issues) {
		File xsdFile = new File(xsdPath);  
		if (!xsdFile.exists()) {
		         issues.addError("File "+xsdPath+" doesn't exist");
		         return;
		      }

	}

	/* (non-Javadoc)
	 * @see org.openarchitectureware.workflow.WorkflowComponent#invoke(org.openarchitectureware.workflow.WorkflowContext, org.openarchitectureware.workflow.monitor.ProgressMonitor, org.openarchitectureware.workflow.issues.Issues)
	 */

	@Override
	public void invoke(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
	      
	      File xsdFile = new File(xsdPath);

	      XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
	      Collection<EObject> eCorePackages = xsdEcoreBuilder.generate(URI
	            .createFileURI(xsdPath));

	      int sufIdx = xsdFile.getName().lastIndexOf(".");
	      Resource r = new ResourceSetImpl().createResource(URI.createURI(xsdFile.getName().substring(0, sufIdx) +
	                                ".automata"));
	      
	      for (Iterator<EObject> iter = eCorePackages.iterator(); iter.hasNext();) {
	         EPackage element = (EPackage) iter.next();
	         r.getContents().add(element);
	       //  slotContents.put("SchemaModel", element);
	      }
	     

	      if (firstElementOnly)
	         ctx.set(outputSlot, r.getContents().get(0));
	      else
	         ctx.set(outputSlot, r.getContents());

	}

}
