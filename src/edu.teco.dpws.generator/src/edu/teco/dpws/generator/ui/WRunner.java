package edu.teco.dpws.generator.ui;

import java.util.HashMap;
import java.util.Map;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitorAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;




public class WRunner extends AbstractHandler implements IActionDelegate {

	private Shell shell;
	IFile selectedFile = null;
	IWorkbenchPart part;
	String baseName;
	String dirName;
	String workspaceURL;
	
	final String wfFile = "wsdl.oaw";
	
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
		run();
	}
	
	public void run(){

		
		final Map<String, String> propertiesSchema = new HashMap<String, String>();
		
		propertiesSchema.put("dirName", dirName);
		propertiesSchema.put("baseName", baseName);
		propertiesSchema.put("platformURI", workspaceURL);
		Plugin.getDefault().getLog().log(new Status(IStatus.INFO,
			      Plugin.PLUGIN_ID, 0,
			      "Generating " +baseName, null));
		
		Log.setLog(Plugin.getDefault().getLog());
		
	    Job job = new Job("Generating code for "+ baseName) {
			     protected IStatus run(IProgressMonitor monitor) {
			    	 
			 		  Thread.currentThread().setContextClassLoader(WRunner.class.getClassLoader());
					  System.setProperty("org.apache.commons.logging.Log", Log.class.getName());
			          return new WorkflowRunner().run(wfFile, new ProgressMonitorAdapter(monitor),
			   				propertiesSchema, null)?Status.OK_STATUS:Status.CANCEL_STATUS; 
			          
			        }
			     };
			  job.setPriority(Job.BUILD);
			  job.schedule();
	}
    
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	
	private void setVars(IStructuredSelection selection)
	{
		IFile f = (IFile) selection.getFirstElement();
		baseName=f.getLocation().removeFileExtension().lastSegment();
		dirName=f.getLocation().removeLastSegments(1).toOSString();
		workspaceURL=ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		
	}
	public void selectionChanged(IAction action, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			setVars((IStructuredSelection) selection);
		}
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection=HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			setVars((IStructuredSelection) selection);
			run();
		}
		return null;
	}
}
