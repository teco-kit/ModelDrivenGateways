package edu.teco.squery.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewProjectWizard;

import edu.teco.squery.SQueryEditorPlugin;

public class NewSQueryProjectWizard extends AbstractNewProjectWizard {

	public NewSQueryProjectWizard() {
		super();
		setLangName("sQuery");
		setGeneratorProjectName("edu.teco.squery.generator");
		setDslProjectName("edu.teco.squery");
		setFileExtension("sqr");
		setPackageName("edu/teco/squery/");
	}
	
	@Override
	protected LanguageUtilities getUtilities() {
		return SQueryEditorPlugin.getDefault().getUtilities();
	}
}

