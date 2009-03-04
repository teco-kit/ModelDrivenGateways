package Automata.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class AutomataElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private AutomataElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType StateMachine_1000 = getElementType("edu.teco.automata.gmf.diagram.StateMachine_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SimpleState_2001 = getElementType("edu.teco.automata.gmf.diagram.SimpleState_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType StopState_2002 = getElementType("edu.teco.automata.gmf.diagram.StopState_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType StartState_2003 = getElementType("edu.teco.automata.gmf.diagram.StartState_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TByte_3001 = getElementType("edu.teco.automata.gmf.diagram.TByte_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TChar_3002 = getElementType("edu.teco.automata.gmf.diagram.TChar_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TShort_3003 = getElementType("edu.teco.automata.gmf.diagram.TShort_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TInt_3004 = getElementType("edu.teco.automata.gmf.diagram.TInt_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TLong_3005 = getElementType("edu.teco.automata.gmf.diagram.TLong_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TFloat_3006 = getElementType("edu.teco.automata.gmf.diagram.TFloat_3006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TDouble_3007 = getElementType("edu.teco.automata.gmf.diagram.TDouble_3007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TString_3008 = getElementType("edu.teco.automata.gmf.diagram.TString_3008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ComplexType_3009 = getElementType("edu.teco.automata.gmf.diagram.ComplexType_3009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType StartStateOut_4001 = getElementType("edu.teco.automata.gmf.diagram.StartStateOut_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SimpleStateOut_4002 = getElementType("edu.teco.automata.gmf.diagram.SimpleStateOut_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return Automata.diagram.part.AutomataDiagramEditorPlugin
						.getInstance().getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(StateMachine_1000, Automata.AutomataPackage.eINSTANCE
					.getStateMachine());

			elements.put(SimpleState_2001, Automata.AutomataPackage.eINSTANCE
					.getSimpleState());

			elements.put(StopState_2002, Automata.AutomataPackage.eINSTANCE
					.getStopState());

			elements.put(StartState_2003, Automata.AutomataPackage.eINSTANCE
					.getStartState());

			elements.put(TByte_3001, Automata.AutomataPackage.eINSTANCE
					.getTByte());

			elements.put(TChar_3002, Automata.AutomataPackage.eINSTANCE
					.getTChar());

			elements.put(TShort_3003, Automata.AutomataPackage.eINSTANCE
					.getTShort());

			elements.put(TInt_3004, Automata.AutomataPackage.eINSTANCE
					.getTInt());

			elements.put(TLong_3005, Automata.AutomataPackage.eINSTANCE
					.getTLong());

			elements.put(TFloat_3006, Automata.AutomataPackage.eINSTANCE
					.getTFloat());

			elements.put(TDouble_3007, Automata.AutomataPackage.eINSTANCE
					.getTDouble());

			elements.put(TString_3008, Automata.AutomataPackage.eINSTANCE
					.getTString());

			elements.put(ComplexType_3009, Automata.AutomataPackage.eINSTANCE
					.getcomplexType());

			elements.put(StartStateOut_4001, Automata.AutomataPackage.eINSTANCE
					.getStartState_Out());

			elements.put(SimpleStateOut_4002,
					Automata.AutomataPackage.eINSTANCE.getSimpleState_Out());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(StateMachine_1000);
			KNOWN_ELEMENT_TYPES.add(SimpleState_2001);
			KNOWN_ELEMENT_TYPES.add(StopState_2002);
			KNOWN_ELEMENT_TYPES.add(StartState_2003);
			KNOWN_ELEMENT_TYPES.add(TByte_3001);
			KNOWN_ELEMENT_TYPES.add(TChar_3002);
			KNOWN_ELEMENT_TYPES.add(TShort_3003);
			KNOWN_ELEMENT_TYPES.add(TInt_3004);
			KNOWN_ELEMENT_TYPES.add(TLong_3005);
			KNOWN_ELEMENT_TYPES.add(TFloat_3006);
			KNOWN_ELEMENT_TYPES.add(TDouble_3007);
			KNOWN_ELEMENT_TYPES.add(TString_3008);
			KNOWN_ELEMENT_TYPES.add(ComplexType_3009);
			KNOWN_ELEMENT_TYPES.add(StartStateOut_4001);
			KNOWN_ELEMENT_TYPES.add(SimpleStateOut_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
