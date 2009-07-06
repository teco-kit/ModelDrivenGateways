package org.openarchitectureware.workflow.container;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.openarchitectureware.util.stdlib.GlobalVarExtensions;
import org.openarchitectureware.workflow.WorkflowComponent;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowInterruptedException;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;
import org.openarchitectureware.workflow.util.ComponentPrinter;



public class ForeachComponent extends CompositeComponent{

	public ForeachComponent() {
		super("foreach");
	}

	@Override
	public void invoke(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		internalInvoke(ctx, monitor, issues);
	}

	@SuppressWarnings("unchecked")
	private void internalInvoke(final WorkflowContext model, final ProgressMonitor monitor, final Issues issues) {
			
			Collection list = (Collection) GlobalVarExtensions.getGlobalVar(list_var);
			//HashSet<Object> hash=new HashSet<Object>();
			if(list!=null)
			for (Object  l:list) {
				issues.addWarning(l.toString());
				//if(hash.contains(l)) continue;
				//hash.add(l);
				
				if(iter_var!=null) GlobalVarExtensions.storeGlobalVar(iter_var,l);
				for (final Iterator<WorkflowComponent> iter = components.iterator(); iter.hasNext();) {
					final WorkflowComponent comp = iter.next();
					try {
						log.info(ComponentPrinter.getString(comp));
					}
					catch (final WorkflowInterruptedException wfi) {
						throw wfi;
					}
					catch (final Throwable t) {
						issues.addError(comp, "Error during execution: " + t.getMessage());
						log.error("Exception occured: ", t);
					}
					comp.invoke(model, monitor, issues);
				}
			}
		
	}
	
	private String list_var = null;
	public void setListVar(final String list) {
		this.list_var=list;
	}
	
	private String iter_var = null;
	public void setIterVar(final String iter)
	{
		this.iter_var=iter;
	}

}