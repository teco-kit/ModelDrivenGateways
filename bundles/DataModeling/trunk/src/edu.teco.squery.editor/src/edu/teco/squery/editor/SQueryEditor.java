package edu.teco.squery.editor;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.editor.AbstractXtextEditor;

import edu.teco.squery.SQueryEditorPlugin;

public class SQueryEditor extends AbstractXtextEditor {

   public AbstractXtextEditorPlugin getPlugin() {
      return SQueryEditorPlugin.getDefault();
   }
}
