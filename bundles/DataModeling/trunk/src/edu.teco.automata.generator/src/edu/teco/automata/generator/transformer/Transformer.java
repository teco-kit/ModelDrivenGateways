package edu.teco.automata.generator.transformer;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.xtend.util.stdlib.GlobalVarExtensions;

import edu.teco.automata.Automata.AutomataFactory;
import edu.teco.automata.Automata.DataType;
import edu.teco.automata.Automata.SimpleState;
import edu.teco.automata.Automata.StartState;
import edu.teco.automata.Automata.State;
import edu.teco.automata.Automata.StateMachine;
import edu.teco.automata.Automata.StopState;
import edu.teco.automata.Automata.TDouble;
import edu.teco.automata.Automata.TInt;
import edu.teco.automata.Automata.TString;
import edu.teco.automata.generator.xml.XmlReader;



public class Transformer extends WorkflowComponentWithModelSlot {
  
   EList<State> states;
   private String outputSlot;
   private boolean firstElementOnly = true;
   private State previousState= null;
   private static int depth = 0;
   
	private String rootElement=null;
	private String rootElementListVar=null;
	
	public void setRootElement(String rootElement) {
	      this.rootElement = rootElement;
	}
	
	public void setRootElementListVar(String rootElementListVar) {
	      this.rootElementListVar = rootElementListVar;
	}
	

   @Override
	public String getComponentName() {
		return "Ecore2AutomataTransformer";
	}



	@SuppressWarnings("unchecked")
	@Override
	public String getLogMessage() {
		
		if(rootElementListVar!=null)
		{
	    	String ret=" ";
			for(String root:(Iterable<String>) GlobalVarExtensions.getGlobalVar(rootElementListVar))
			 ret+=  "generating automata "+ root+ "\n";
			return ret;
		}
		else if(rootElement!=null)
		{
			return "generating automata for "+rootElement+"\n";
		}
		else
		{
			return "generating automata for all root types\n";
		}
	}
	


private Object modelObject;

@Override
protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
		Issues issues) {
	 modelObject = ctx.get(getModelSlot());

	if (modelObject == null) {
        issues.addWarning(this, "content of modelSlot " + getModelSlot() + " is null.");
	}
	
    doModification(ctx, monitor, issues, modelObject);
}

private StateMachine createStateMachine(EReference rootRef, Issues issues)
{
	StateMachine stMachine = AutomataFactory.eINSTANCE.createStateMachine();
    states = stMachine.getStates();
    
    
    StartState start = AutomataFactory.eINSTANCE.createStartState();
    start.setName("Start");
    states.add(start);
    previousState = start;
    depth         = 0;
    
    
    for (EAnnotation ann : rootRef.getEAnnotations()) {
        if (ann.getDetails().get("kind").equals("element")) {
           handleAnnot(rootRef.getName(), 
                       rootRef.getEAnnotations(),
                       rootRef.getEReferenceType().getName(), 
                       rootRef.getEReferenceType().getInstanceClassName(),
                       rootRef.getLowerBound(), rootRef.getUpperBound());
           depth++;
           iterateEContents(rootRef.getEReferenceType());

        }
}



StopState stop = AutomataFactory.eINSTANCE.createStopState();
stop.setName("Stop");
if(previousState==start)
{
	  start.setOut(stop);
	  issues.addWarning(this,"empty StateMachine");
}
else
((SimpleState) previousState).getOut().add(stop);
states.add(stop);

    
    return stMachine;
}



@SuppressWarnings("unchecked")
protected void doModification(WorkflowContext ctx, ProgressMonitor monitor,
         Issues issues, Object model) {
      EPackage ecoreP = (EPackage) model;
      EClassifier docRoot = getElement("DocumentRoot", ecoreP.getEClassifiers());
      
      if(rootElement!=null)
      {
          for (EObject obj : docRoot.eContents()) {
         	 
              if (obj.eClass().getInstanceClass() == EReference.class && ((EReference) obj).getName().equals(rootElement)) {
            	  Resource r = new ResourceSetImpl().createResource(URI.createURI("uri"));
                  r.getContents().add(createStateMachine((EReference) obj, issues));
              
                 if (firstElementOnly)
                     ctx.set(outputSlot, r.getContents().get(0));
                 else
                     ctx.set(outputSlot, r.getContents());
              
              }
          }
      }
          else if (rootElementListVar!=null)
      {
        	  if(GlobalVarExtensions.getGlobalVar(rootElementListVar)==null)
        	  {
        		  issues.addError("rootElementListVar '"+rootElementListVar+"' is not set");
        	      return;
        	  }
        	  
        	  ArrayList<Object> slotContents=new ArrayList<Object>();
        	  
        	  for(String _rootElement:(Iterable<String>) GlobalVarExtensions.getGlobalVar(rootElementListVar))
        	  {
        	   boolean found=false;
               for (EObject obj : docRoot.eContents()) {
        		 {
        	    	 if (obj.eClass().getInstanceClass() == EReference.class && ((EReference) obj).getName().toLowerCase().equals(_rootElement.toLowerCase())) {
        	    		found=true;
             			Resource r = new ResourceSetImpl().createResource(URI.createURI("uri"));
                        r.getContents().add(createStateMachine((EReference) obj, issues)); 
                        if (firstElementOnly)
                        	slotContents.add(r.getContents().get(0));
                        else
                        	slotContents.add(r.getContents());
        	    	 }	
        		}
        	  }
               if(!found){issues.addWarning(this.getContainer(),"root element '" +_rootElement + "' not found", this); }
        	  }
              
        	 
        	    ctx.set(outputSlot,slotContents);
        	  
      }
      else
      {
    	  ArrayList<Object> slotContents=new ArrayList<Object>();
    	  
    	  for (EObject obj : docRoot.eContents())  if (obj.eClass().getInstanceClass() == EReference.class) {
         			Resource r = new ResourceSetImpl().createResource(URI.createURI("uri"));
                    r.getContents().add(createStateMachine((EReference) obj, issues)); 
                    	slotContents.add(r.getContents());
    	    	 }
   
          if(slotContents.isEmpty()){issues.addWarning(this.getContainer(),"no root element found", this); }
    	  ctx.set(outputSlot,slotContents); 
      }
      
   }

   // This method is called for simple types
   // e.g. string with a length restriction
   private void handleEDataType(EList<EAnnotation> annList) {
      CHandler spContent = new CHandler();
      
      for (EAnnotation ann : annList) {
         // TeCo special types
         if (ann.getSource() == "http://www.teco.edu/SimpleTypePrecision") {
            if (ann.getDetails().containsKey("appinfo")) {
               String appInfo = ann.getDetails().get("appinfo");

               XmlReader parser = new XmlReader(spContent,null);
               parser.parse(appInfo);
            }
            
         } else if (ann.getSource() == "http:///org/eclipse/emf/ecore/util/ExtendedMetaData") {

            if (ann.getDetails().containsKey("baseType")) {
               String baseType = ann.getDetails().get("baseType");

               if (baseType.contains("string")) {
                  ((TString) ((SimpleState) previousState).getType())
                        .setLength(Integer.parseInt(ann.getDetails().get(
                              "length")));
               } else if (baseType.contains("decimal")) {

                  ((TDouble) ((SimpleState) previousState).getType())
                        .setFractionDigits(Integer.parseInt(ann.getDetails()
                              .get("fractionDigits")));

                  ((TDouble) ((SimpleState) previousState).getType())
                        .setMin(Integer.parseInt(ann.getDetails().get(
                              "minInclusive")));

                  ((TDouble) ((SimpleState) previousState).getType())
                        .setMax(Integer.parseInt(ann.getDetails().get(
                              "maxInclusive")));
                  if (spContent.getStepping() != -1)
                     ((TDouble) ((SimpleState) previousState).getType())
                        .setStepping(spContent.getStepping());

               } else if (baseType.contains("integer")) {

                  ((TInt) ((SimpleState) previousState).getType())
                        .setMin(Integer.parseInt(ann.getDetails().get(
                              "minInclusive")));

                  ((TInt) ((SimpleState) previousState).getType())
                        .setMax(Integer.parseInt(ann.getDetails().get(
                              "maxInclusive")));
                  
                  if (spContent.getStepping() != -1)
                     ((TInt) ((SimpleState) previousState).getType())
                        .setStepping((int)spContent.getStepping());

               }
            }
         }

      }
   }

   private boolean handleAnnot(String eName, 
                               EList<EAnnotation> annList,
                               String type, 
                               String pType, 
                               int lowB, 
                               int upB                      ) {

      for (Iterator<EAnnotation> annIt = annList.iterator(); annIt.hasNext();)
      {
         EAnnotation ann = (EAnnotation)annIt.next();
      
         if (ann.getSource() == "http:///org/eclipse/emf/ecore/util/ExtendedMetaData") {

            SimpleState state = AutomataFactory.eINSTANCE.createSimpleState();
            state.setName(ann.getDetails().get("name"));
            state.setKind(ann.getDetails().get("kind"));

            if (upB != -2) {
               state.setLowerBound(lowB);
               state.setUpperBound(upB);
            } else {
               state.setLowerBound(1);
               state.setUpperBound(1);
            }
            state.setDepth(depth);

            DataType automataType = DataTypes.getType(type);
            if (automataType == null)
               automataType = DataTypes.getType(pType);

            if (automataType == null)
               automataType = DataTypes.getComplexType();

            state.setType(automataType);

            if (previousState != null) {
               if (previousState instanceof SimpleState)
                  ((SimpleState) previousState).getOut().add(state);
               else if (previousState instanceof StartState)
                  ((StartState) previousState).setOut(state);
            }
            previousState = state;

            states.add(state);
            return true;

         }
      }
      return false;
   }

   public void setOutputSlot(final String outputSlot) {
      this.outputSlot = outputSlot;
   }

   public void setFirstElementOnly(final boolean firstElementOnly) {
      this.firstElementOnly = firstElementOnly;
   }

   private void iterateEContents(EClass e) {
      EList<EObject> sortedList = sortContents(e.eContents());

      for (EObject obj : sortedList) {
         if (obj.eClass().getInstanceClass() == EAttribute.class) {
            EAttribute attr = (EAttribute) obj;

            boolean ret = handleAnnot(attr.getName(), 
                                      attr.getEAnnotations(),
                                      attr.getEAttributeType().getName(), 
                                      attr.getEAttributeType().getInstanceClassName(), 
                                      attr.getLowerBound(), attr.getUpperBound());
            if (ret)
               handleEDataType(attr.getEAttributeType().getEAnnotations());
         } else if (obj.eClass().getInstanceClass() == EReference.class) {
            EReference ref = (EReference) obj;

            handleAnnot(ref.getName(), 
                        ref.getEAnnotations(), 
                        ref.getEReferenceType().getName(), 
                        ref.getEReferenceType().getInstanceClassName(), 
                        ref.getLowerBound(), 
                        ref.getUpperBound());

            depth++;
            iterateEContents(ref.getEReferenceType());
            depth--;
         }
      }
   }

   private EClassifier getElement(String name, EList<EClassifier> list) {

      for (Iterator<EClassifier> classIt = list.iterator(); classIt.hasNext();) {
         EClassifier classifier = classIt.next();

         if (classifier.getName().equals(name))
            return classifier;
      }

      return null;

   }
   
   private EList<EObject> sortContents(EList<EObject> objList) {
      EList<EObject> sortedList = new BasicEList<EObject>();
      
      // this is only needed because in XML are elements and attributes
      EList<EObject> objListAttr = new BasicEList<EObject>();
      EList<EObject> objListElem = new BasicEList<EObject>();
      for (EObject obj : objList) {
         
         EList<EAnnotation> annList;
         if (obj.eClass().getInstanceClass() == EAttribute.class) {
            EAttribute attr = (EAttribute) obj;
            annList         = attr.getEAnnotations();
         } else if (obj.eClass().getInstanceClass() == EReference.class) {
            EReference ref = (EReference) obj;
            annList        = ref.getEAnnotations();
         } else {
            continue;
         }
            
         for (EAnnotation ann : annList) {
            String kind = ann.getDetails().get("kind");
            if(kind!=null)
            {
            if (kind.equals("attribute"))
               objListAttr.add(obj);
            else if (kind.equals("element"))
               objListElem.add(obj);
            }
         }
      }
      
      sortedList.addAll(objListAttr);
      sortedList.addAll(objListElem);
      
      
      return sortedList;
   }

	
}
