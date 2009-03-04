package Automata.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		Automata.diagram.preferences.DiagramPrintingPreferencePage
				.initDefaults(store);
		Automata.diagram.preferences.DiagramGeneralPreferencePage
				.initDefaults(store);
		Automata.diagram.preferences.DiagramAppearancePreferencePage
				.initDefaults(store);
		Automata.diagram.preferences.DiagramConnectionsPreferencePage
				.initDefaults(store);
		Automata.diagram.preferences.DiagramRulersAndGridPreferencePage
				.initDefaults(store);
	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return Automata.diagram.part.AutomataDiagramEditorPlugin.getInstance()
				.getPreferenceStore();
	}
}
