package edu.teco.automata.generator.popup.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class AutomataGeneratorWizard extends Wizard implements INewWizard {
	// workbench selection when the wizard was started
	protected IStructuredSelection selection;
	// the workbench instance
	protected IWorkbench workbench;
	protected AutomataGeneratorWizardMainPage page1;
	
	private ConfigStorage config;

	public AutomataGeneratorWizard(ConfigStorage config) {
		//super();
		this.config = config;
	}
	
	@Override
	public boolean performFinish() {
		config.setGenerate(true);
		return true;
	}
	
	@Override
	public boolean performCancel() {
		config.setGenerate(false);
		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	public void addPages() {
		page1 = new AutomataGeneratorWizardMainPage(workbench, selection, config);
		addPage(page1);
	}

}
