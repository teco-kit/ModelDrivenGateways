
package edu.teco.squery.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewFileWizard;

import edu.teco.squery.SQueryEditorPlugin;

public class NewSQueryFileWizard extends AbstractNewFileWizard {

	@Override
	protected LanguageUtilities getUtilities() {
		return SQueryEditorPlugin.getDefault().getUtilities();
	}
}
