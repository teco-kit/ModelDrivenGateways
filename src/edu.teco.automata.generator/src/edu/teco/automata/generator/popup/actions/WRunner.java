package edu.teco.automata.generator.popup.actions;

//import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;




public class WRunner implements IObjectActionDelegate {

	private Shell shell;
	IFile selectedFile = null;
	IWorkbenchPart part;
	ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public WRunner() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		this.part = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		ConfigStorage config = new ConfigStorage();
	   //String log4jprops = "edu/teco/automata/generator/log4j.properties";
	   // PropertyConfigurator.;
//		ResourceLoaderFactory.setCurrentThreadResourceLoader(new ResourceLoaderImpl(getClass().getClassLoader()));

		AutomataGeneratorWizard wizard = new AutomataGeneratorWizard(config);
		wizard.init(part.getSite().getWorkbenchWindow().getWorkbench(),
				(IStructuredSelection) selection);

		// Instantiates the wizard container with the wizard and opens it
		WizardDialog dialog = new WizardDialog(part.getSite().getShell(),
				wizard);
		dialog.create();
		dialog.open();

		// wizard was canceled
		if (!config.getGenerate())
			return;

		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
//	    ResourceSet resourceSet = new ResourceSetImpl();
		Collection<EObject> eCorePackages = xsdEcoreBuilder.
		   generate(URI.createFileURI(selectedFile.getLocation().toString()));
	    
		
		String wfFileSchema = "edu/teco/automata/generator/m2mt.oaw";
		Map<String, String> propertiesSchema = new HashMap<String, String>();
		Map<String, EPackage> slotContents = new HashMap<String, EPackage>();
		
	    for (Iterator<EObject> iter = eCorePackages.iterator(); iter.hasNext();) {
	    	EPackage element = (EPackage) iter.next();
	    	slotContents.put("SchemaModel", element);
	    }
		
		//propertiesSchema.put("modelFile", "platform:/resource"
		//		+ selectedFile.getFullPath());
		propertiesSchema.put("srcgen",  
				             selectedFile.getParent().getLocation().toString());
		//new File(config.getDestinationPath()).mkdir();
		propertiesSchema.put("automataFile", config.getAutomataFile());

		new WorkflowRunner().run(wfFileSchema, new NullProgressMonitor(),
				propertiesSchema, slotContents);
		try {
			selectedFile.getParent().refreshLocal(IResource.DEPTH_INFINITE,
					null);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		String wfFileAutomata = "edu/teco/automata/generator/workflow.oaw";
		Map<String, String> properties = new HashMap<String, String>();
		Map<String, EPackage> slotContents2 = new HashMap<String, EPackage>();

		if (!config.getDestinationPath().equals(""))
			properties.put("srcgen", config.getDestinationPath());
		if (!config.getDeviceName().equals(""))
			properties.put("DeviceName", config.getDeviceName());
		if (!config.getNsPrefix().equals(""))
			properties.put("NSPrefix", config.getNsPrefix());
		if (!config.getMsgOutURI().equals(""))
			properties.put("MsgOutURI", config.getMsgOutURI());
		if (!config.getMsgRspURI().equals(""))
			properties.put("MsgRspURI", config.getMsgRspURI());

		// properties.put("modelFile", "platform:/resource"
		// + selectedFile.getFullPath());
		properties.put("modelFile", "platform:/resource"      + 
				       selectedFile.getParent().getFullPath() + "/" +
				       config.getAutomataFile());

		new WorkflowRunner().run(wfFileAutomata, new NullProgressMonitor(),
				properties, slotContents2);
		try {
			// TODO refresh only the folder in which it was generated,
			// if it is in the workspace
			selectedFile.getParent().refreshLocal(IResource.DEPTH_INFINITE,
					null);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		MessageDialog.openInformation(shell, "edu.teco.automata.generator",
				"Code generation was executed.");

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			selectedFile = (IFile) structuredSelection.getFirstElement();

			// System.out.println(selectedFile.getLocation());
			// System.out.println(selectedFile.getFullPath());
		}

	}

//	public static void main(String args[]) {
//		WRunner runner = new WRunner();
//		runner.run(null);
//	}

}
