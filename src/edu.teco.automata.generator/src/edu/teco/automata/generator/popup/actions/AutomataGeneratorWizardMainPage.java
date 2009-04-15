package edu.teco.automata.generator.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

public class AutomataGeneratorWizardMainPage extends WizardPage implements
		Listener {

	private IWorkbench workbench;
	private IStructuredSelection selection;
	private ConfigStorage config;
	// widgets on this page
	private Text genFolder;
	private Text automataName;
	private Text nsPrefix;
	private Text deviceName;
	private Text msgOutURI;
	private Text msgRspURI;
	private Button gSoapCheckBox;
	private Button automataCheckBox;
	private Button browseButton;
	private Group gSoapGroup;

	public AutomataGeneratorWizardMainPage(IWorkbench workbench,
			IStructuredSelection selection, ConfigStorage config) {
		super("Page 1");
		setTitle("Generator wizard");
		setDescription("Set generation getails");
		this.workbench = workbench;
		this.selection = selection;
		this.config    = config;
	}

	@Override
	public void handleEvent(Event event) {
		
	    if (event.widget == gSoapCheckBox ) {
	        if (gSoapCheckBox.getSelection()) {
	        	nsPrefix.setEnabled(true);
	        	deviceName.setEnabled(true);
	        	msgOutURI.setEnabled(true);
	        	msgRspURI.setEnabled(true);	        	
	        } else {
	        	nsPrefix.setEnabled(false);
	        	deviceName.setEnabled(false);
	        	msgOutURI.setEnabled(false);
	        	msgRspURI.setEnabled(false);
	        }
	    } else if (event.widget == automataCheckBox) {
	    	if (automataCheckBox.getSelection()) {
	    		automataName.setEnabled(true);
	    	} else {
	    		automataName.setEnabled(false);
	    	}
	    } else if (event.widget == browseButton) {
	    	int i;
	    	DirectoryDialog browser = new DirectoryDialog(workbench.getDisplay().getActiveShell());
			i = genFolder.getText().lastIndexOf("/");
	    	browser.setFilterPath(genFolder.getText().substring(0, i));
	    	String selectedDirectory = browser.open();
	    	genFolder.setText(selectedDirectory);
	    }

		// Show the most serious error
		// applyToStatusLine("Error");
		getWizard().getContainer().updateButtons();

	}

	@Override
	public void createControl(Composite parent) {

		// create the composite to hold the widgets
		GridData gd;
		Composite composite = new Composite(parent, SWT.NULL);
		String selectedFile = "";
		String selectedFileFolder = "";
		
	//	if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			IFile selectedIFile = (IFile) structuredSelection.getFirstElement();
			selectedFile = selectedIFile.getName();
			String fullPath = selectedIFile.getLocation().toString();
			int i = fullPath.lastIndexOf("/");
			selectedFileFolder = fullPath.substring(0, i);
	//	}

		// create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 1;
		composite.setLayout(gl);
		
		Composite container2 = new Composite(composite, SWT.NULL);
		gl = new GridLayout();
		gl.numColumns = 3;
		container2.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		container2.setLayoutData(gd);
		
		// 
		new Label(container2, SWT.NONE).setText("Generation folder:");
		genFolder = new Text(container2, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		genFolder.setLayoutData(gd);
		genFolder.setText(selectedFileFolder + "/src-gen");
		
		browseButton = new Button(container2, SWT.PUSH);
		browseButton.setText(" Browse ");

		Composite container1 = new Composite(composite, SWT.NULL);
		gl = new GridLayout();
		gl.numColumns = 2;
		container1.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		container1.setLayoutData(gd);
		
		// 
		automataCheckBox = new Button(container1, SWT.CHECK);
	    automataCheckBox.setText("Automata model name:");
		automataCheckBox.setSelection(false);
		automataName = new Text(container1, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		automataName.setLayoutData(gd);
		automataName.setText(selectedFile.replaceAll("\\.xsd$", ".automata"));
		automataName.setEnabled(false);

		//createLine(composite, ncol);
		
		gSoapGroup = new Group(composite, SWT.SHADOW_NONE);
		gSoapGroup.setText("WS4D/GSOAP");
		//gSoapGroup.setEnabled(false);
		gSoapGroup.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gSoapGroup.setLayoutData(gd);

		gSoapCheckBox = new Button(gSoapGroup, SWT.CHECK);
	    gSoapCheckBox.setText("Generate artefacts");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol;
		gSoapCheckBox.setLayoutData(gd);
		gSoapCheckBox.setSelection(true);		
		
		Composite container3 = new Composite(gSoapGroup, SWT.NULL);
		gl = new GridLayout();
		gl.numColumns = 2;
		container3.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		container3.setLayoutData(gd);
		
		// 
		new Label(container3, SWT.NONE).setText("Device name:");
		deviceName = new Text(container3, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		deviceName.setLayoutData(gd);

		// 
		new Label(container3, SWT.NONE).setText("Namespace prefix:");
		nsPrefix = new Text(container3, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		nsPrefix.setLayoutData(gd);

		// 
		new Label(container3, SWT.NONE).setText("Event message URI:");
		msgOutURI = new Text(container3, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		msgOutURI.setLayoutData(gd);

		new Label(container3, SWT.NONE).setText("Response message URI:");
		msgRspURI = new Text(container3, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		msgRspURI.setLayoutData(gd);

		// set the composite as the control for this page
		setControl(composite);
		addListeners();

	}

	/*
	private void createLine(Composite parent, int ncol) {
		Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}
	*/

	private void addListeners() {
		genFolder.addListener(SWT.KeyUp, this);
		automataName.addListener(SWT.KeyUp, this);
		deviceName.addListener(SWT.KeyUp, this);
		nsPrefix.addListener(SWT.KeyUp, this);
		msgOutURI.addListener(SWT.KeyUp, this);
		msgRspURI.addListener(SWT.KeyUp, this);
		gSoapCheckBox.addListener(SWT.Selection, this);
		automataCheckBox.addListener(SWT.Selection, this);
		browseButton.addListener(SWT.Selection, this);
	}
	
	public boolean isPageComplete() {
		config.setDestinationPath(genFolder.getText());
		config.setAutomataFile(automataName.getText());
		config.setNsPrefix(nsPrefix.getText());
		config.setDeviceName(deviceName.getText());
		config.setMsgOutURI(msgOutURI.getText());
		config.setMsgRspURI(msgRspURI.getText());
		config.setGnerateWS4d(gSoapCheckBox.getSelection());
		config.setGenerate(true);
		return true;
	}
}
