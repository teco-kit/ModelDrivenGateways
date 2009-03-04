package edu.teco.squery;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.osgi.framework.BundleContext;

public class SQueryEditorPlugin extends AbstractXtextEditorPlugin {
   private static SQueryEditorPlugin plugin;
   public static SQueryEditorPlugin getDefault() {
      return plugin;
   }

   private SQueryUtilities utilities = new SQueryUtilities();
   public LanguageUtilities getUtilities() {
      return utilities;
   }

   public SQueryEditorPlugin() {
      plugin = this;
   }

   public void stop(BundleContext context) throws Exception {
      super.stop(context);
      plugin = null;
   }
}
