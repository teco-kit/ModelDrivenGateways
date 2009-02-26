package edu.teco.squery.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.openarchitectureware.xtext.XtextLog;
import org.openarchitectureware.xtext.editor.wizards.ProjectCreator;

public class MyNewSQueryProjectWizard extends NewSQueryProjectWizard {

   @Override
   public boolean performFinish() {
      
      try {
         new WorkspaceModifyOperation() {

            @Override
            protected void execute(IProgressMonitor monitor) 
                  throws CoreException, InvocationTargetException,
                  InterruptedException {
               try {
                  IProject pr = mainPage.getProjectHandle();
                  pr.create(monitor);
                  pr.open(monitor);
                  String modelFileName = "model." + getFileExtension();
                  ProjectCreator.create(pr, new String[] { getDslProjectName(), 
                            getGeneratorProjectName() }, new String[] {},monitor);
                  IContainer srcFolder = pr.getFolder("src");
                  ProjectCreator
                        .createFile(
                              modelFileName,
                              srcFolder,
                              "//model goes here...\n",
                              monitor);
                  ProjectCreator
                        .createFile(
                              pr.getName() + ".oaw",
                              srcFolder,
                              "<workflow>\n\t<component file='"
                                    + getPackageName()
                                    + "generator.oaw'>\n\t\t<modelFile value='"
                                    + modelFileName
                                    + "' />\n\t\t<targetDir value='src-gen' />\n" 
                                    + "\t\t<automataModel value='My.automata' />\n"
                                    + "\t</component>\n</workflow>",
                              monitor);
/*
                  ProjectCreator
                  .createFile(
                        "genAcceptor.oaw",
                        srcFolder,
                        "<workflow>\n"
                       + "<bean class='org.eclipse.mwe.emf.StandaloneSetup'>\n"
                       + "<platformUri value='..'/>\n"
                       + "</bean>\n"
                       + "\t<component file='" 
                      + "edu/teco/automata/generator/acceptor.oaw'>\n"
                      + "<extensions value='edu::teco::squery::generator::gen::AcceptorNew'/>\n"
                      + "<modelFile value='${automataModel}' />\n"
                      + "\t</component>\n</workflow>",
                        monitor);
  */                
               } catch (CoreException e) {
                  mainPage.setErrorMessage(e.getLocalizedMessage());
               }
            }
         }.run(null);
      } catch (InvocationTargetException e) {
         XtextLog.logError(e);
         return false;
      } catch (InterruptedException e) {
         XtextLog.logError(e);
         return false;
      }
      return true;

   }

   private String generatorProjectName;

   private String getGeneratorProjectName() {
      return generatorProjectName;
   }

   @Override
   public void setGeneratorProjectName(String generatorProjectName) {
      this.generatorProjectName = generatorProjectName;
      super.setGeneratorProjectName(generatorProjectName);
   }
}

