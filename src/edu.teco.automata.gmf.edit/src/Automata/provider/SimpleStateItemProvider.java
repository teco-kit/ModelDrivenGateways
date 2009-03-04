/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata.provider;


import Automata.AutomataFactory;
import Automata.AutomataPackage;
import Automata.SimpleState;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link Automata.SimpleState} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleStateItemProvider
	extends StateItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleStateItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOutPropertyDescriptor(object);
			addLowerBoundPropertyDescriptor(object);
			addUpperBoundPropertyDescriptor(object);
			addDepthPropertyDescriptor(object);
			addKindPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Out feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleState_out_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleState_out_feature", "_UI_SimpleState_type"),
				 AutomataPackage.Literals.SIMPLE_STATE__OUT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Lower Bound feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLowerBoundPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleState_lowerBound_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleState_lowerBound_feature", "_UI_SimpleState_type"),
				 AutomataPackage.Literals.SIMPLE_STATE__LOWER_BOUND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Upper Bound feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUpperBoundPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleState_upperBound_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleState_upperBound_feature", "_UI_SimpleState_type"),
				 AutomataPackage.Literals.SIMPLE_STATE__UPPER_BOUND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Depth feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDepthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleState_depth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleState_depth_feature", "_UI_SimpleState_type"),
				 AutomataPackage.Literals.SIMPLE_STATE__DEPTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Kind feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimpleState_kind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimpleState_kind_feature", "_UI_SimpleState_type"),
				 AutomataPackage.Literals.SIMPLE_STATE__KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AutomataPackage.Literals.SIMPLE_STATE__TYPE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns SimpleState.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SimpleState"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((SimpleState)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_SimpleState_type") :
			getString("_UI_SimpleState_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(SimpleState.class)) {
			case AutomataPackage.SIMPLE_STATE__LOWER_BOUND:
			case AutomataPackage.SIMPLE_STATE__UPPER_BOUND:
			case AutomataPackage.SIMPLE_STATE__DEPTH:
			case AutomataPackage.SIMPLE_STATE__KIND:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case AutomataPackage.SIMPLE_STATE__TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createcomplexType()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTByte()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTChar()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTShort()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTInt()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTLong()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTFloat()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTDouble()));

		newChildDescriptors.add
			(createChildParameter
				(AutomataPackage.Literals.SIMPLE_STATE__TYPE,
				 AutomataFactory.eINSTANCE.createTString()));
	}

}
