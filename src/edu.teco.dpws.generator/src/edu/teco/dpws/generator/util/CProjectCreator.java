package edu.teco.dpws.generator.util;

import java.io.File;

import org.eclipse.cdt.core.*;
import org.eclipse.cdt.managedbuilder.core.*;
import org.eclipse.cdt.managedbuilder.internal.core.ManagedBuildInfo;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.mwe.utils.StandaloneSetup;

public class CProjectCreator extends
		org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent2 {

	String prjName;

	public void setPrjName(final String name) {
		this.prjName = name;
	}

	@Override
	public String getLogMessage() {
		return "creating C Project '" + prjName + "'";
	}

	@Override
	protected void invokeInternal(
			final org.eclipse.emf.mwe.core.WorkflowContext model,
			final org.eclipse.emf.mwe.core.monitor.ProgressMonitor monitor,
			final org.eclipse.emf.mwe.core.issues.Issues issues) {
		if (ResourcesPlugin.getPlugin() != null)
			createManagedProject(prjName);
	}

	@Override
	protected void checkConfigurationInternal(
			final org.eclipse.emf.mwe.core.issues.Issues issues) {
		if (prjName == null) {
			issues.addError(this, "No project specified!");
			return;
		}
		if (ResourcesPlugin.getPlugin() == null) {
			issues.addWarning(this, "Cannot access eclipse Workspace");
			if (!(new File( StandaloneSetup.getPlatformRootPath() +File.separator+ prjName + File.separator+ ".cproject").exists()))
				issues.addError(this, "CDT project "+ StandaloneSetup.getPlatformRootPath() +File.separator+ prjName + " not found");
		}
	}

	IProject proj = null;
	IManagedProject mproj = null;

	void createManagedProject(String name) {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		proj = root.getProject(name);

		if (proj.exists() && null!=ManagedBuildManager.getBuildInfo(proj)) {
			mproj = ManagedBuildManager.getBuildInfo(proj).getManagedProject();
		} else {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceDescription workspaceDesc = workspace.getDescription();
			workspaceDesc.setAutoBuilding(false);
			try {
				workspace.setDescription(workspaceDesc);
				proj = CCorePlugin.getDefault().createCProject(
						workspace.newProjectDescription(proj.getName()), proj,
						new org.eclipse.core.runtime.NullProgressMonitor(),
						ManagedBuilderCorePlugin.MANAGED_MAKE_PROJECT_ID);

				// add ManagedBuildNature
				IManagedBuildInfo info = ManagedBuildManager
						.createBuildInfo(proj);
				info.setValid(true);
				ManagedCProjectNature.addManagedNature(proj, null);
				ManagedCProjectNature.addManagedBuilder(proj, null);

				/*
				 * ICDescriptor desc =
				 * CCorePlugin.getDefault().getCProjectDescription(proj, true);
				 * desc.remove(CCorePlugin.BUILD_SCANNER_INFO_UNIQ_ID);
				 * desc.create(CCorePlugin.BUILD_SCANNER_INFO_UNIQ_ID,
				 * ManagedBuildManager.INTERFACE_IDENTITY);
				 * desc.saveProjectData();
				 */
			} catch (CoreException e) {
				// TODO
			}

			if (ManagedBuildManager.getDefinedProjectTypes() == null)
				; // Call this function just to avoid init problems in
					// getProjectType();
			IProjectType projType = ManagedBuildManager
					.getProjectType("cdt.managedbuild.target.gnu.lib"); //$NON-NLS-1$
			if (projType == null) {
				return; //TODO throw
			}

			try {
				mproj = ManagedBuildManager
						.createManagedProject(proj, projType);
				
				IConfiguration activeConfig = ManagedBuildManager.getBuildInfo(
						proj).getDefaultConfiguration();
				if(activeConfig!=null)
				{
				  activeConfig.setManagedBuildOn(true);
				}
				else
				{
					//TODO
				}
				ManagedBuildManager.saveBuildInfo(proj, true);
			} catch (BuildException e) {
			}

			ManagedBuildManager.setNewProjectVersion(proj);

			IConfiguration[] cfgs = projType.getConfigurations();
			IConfiguration defcfg = cfgs.length > 0 ? mproj
					.createConfiguration(cfgs[0], projType.getId() + ".0") : null; //$NON-NLS-1$

			for (int i = 1; i < cfgs.length; ++i) { // sic ! from 1
				mproj.createConfiguration(cfgs[i], projType.getId() + "." + i); //$NON-NLS-1$
			}
			ManagedBuildManager.setDefaultConfiguration(proj, defcfg);
		}
		// open project w/o progress monitor; no action performed if it's opened
		try {
			proj.open(null);
		} catch (CoreException e) {
		}
	}

}
