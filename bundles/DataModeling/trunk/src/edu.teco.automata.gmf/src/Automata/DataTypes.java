/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package Automata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Data Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see Automata.AutomataPackage#getDataTypes()
 * @model
 * @generated
 */
public enum DataTypes implements Enumerator {
   /**
    * The '<em><b>Complex Type</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #COMPLEX_TYPE_VALUE
    * @generated
    * @ordered
    */
   COMPLEX_TYPE(0, "complexType", "complexType"),

   /**
    * The '<em><b>Byte</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #BYTE_VALUE
    * @generated
    * @ordered
    */
   BYTE(1, "byte", "byte"),

   /**
    * The '<em><b>Char</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #CHAR_VALUE
    * @generated
    * @ordered
    */
   CHAR(2, "char", "char"),

   /**
    * The '<em><b>Short</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #SHORT_VALUE
    * @generated
    * @ordered
    */
   SHORT(3, "short", "short"),

   /**
    * The '<em><b>Int</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #INT_VALUE
    * @generated
    * @ordered
    */
   INT(4, "int", "int"),

   /**
    * The '<em><b>Long</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #LONG_VALUE
    * @generated
    * @ordered
    */
   LONG(5, "long", "long"),

   /**
    * The '<em><b>Float</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #FLOAT_VALUE
    * @generated
    * @ordered
    */
   FLOAT(6, "float", "float"),

   /**
    * The '<em><b>Double</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #DOUBLE_VALUE
    * @generated
    * @ordered
    */
   DOUBLE(7, "double", "double"),

   /**
    * The '<em><b>String</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #STRING_VALUE
    * @generated
    * @ordered
    */
   STRING(8, "String", "String");

   /**
    * The '<em><b>Complex Type</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Complex Type</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #COMPLEX_TYPE
    * @model name="complexType"
    * @generated
    * @ordered
    */
   public static final int COMPLEX_TYPE_VALUE = 0;

   /**
    * The '<em><b>Byte</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Byte</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #BYTE
    * @model name="byte"
    * @generated
    * @ordered
    */
   public static final int BYTE_VALUE = 1;

   /**
    * The '<em><b>Char</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Char</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #CHAR
    * @model name="char"
    * @generated
    * @ordered
    */
   public static final int CHAR_VALUE = 2;

   /**
    * The '<em><b>Short</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Short</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #SHORT
    * @model name="short"
    * @generated
    * @ordered
    */
   public static final int SHORT_VALUE = 3;

   /**
    * The '<em><b>Int</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Int</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #INT
    * @model name="int"
    * @generated
    * @ordered
    */
   public static final int INT_VALUE = 4;

   /**
    * The '<em><b>Long</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Long</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #LONG
    * @model name="long"
    * @generated
    * @ordered
    */
   public static final int LONG_VALUE = 5;

   /**
    * The '<em><b>Float</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Float</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #FLOAT
    * @model name="float"
    * @generated
    * @ordered
    */
   public static final int FLOAT_VALUE = 6;

   /**
    * The '<em><b>Double</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>Double</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #DOUBLE
    * @model name="double"
    * @generated
    * @ordered
    */
   public static final int DOUBLE_VALUE = 7;

   /**
    * The '<em><b>String</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>String</b></em>' literal object isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @see #STRING
    * @model name="String"
    * @generated
    * @ordered
    */
   public static final int STRING_VALUE = 8;

   /**
    * An array of all the '<em><b>Data Types</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static final DataTypes[] VALUES_ARRAY =
      new DataTypes[] {
         COMPLEX_TYPE,
         BYTE,
         CHAR,
         SHORT,
         INT,
         LONG,
         FLOAT,
         DOUBLE,
         STRING,
      };

   /**
    * A public read-only list of all the '<em><b>Data Types</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final List<DataTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Data Types</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static DataTypes get(String literal) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
         DataTypes result = VALUES_ARRAY[i];
         if (result.toString().equals(literal)) {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Data Types</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static DataTypes getByName(String name) {
      for (int i = 0; i < VALUES_ARRAY.length; ++i) {
         DataTypes result = VALUES_ARRAY[i];
         if (result.getName().equals(name)) {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Data Types</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static DataTypes get(int value) {
      switch (value) {
         case COMPLEX_TYPE_VALUE: return COMPLEX_TYPE;
         case BYTE_VALUE: return BYTE;
         case CHAR_VALUE: return CHAR;
         case SHORT_VALUE: return SHORT;
         case INT_VALUE: return INT;
         case LONG_VALUE: return LONG;
         case FLOAT_VALUE: return FLOAT;
         case DOUBLE_VALUE: return DOUBLE;
         case STRING_VALUE: return STRING;
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final int value;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final String name;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private final String literal;

   /**
    * Only this class can construct instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private DataTypes(int value, String name, String literal) {
      this.value = value;
      this.name = name;
      this.literal = literal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public int getValue() {
     return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName() {
     return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getLiteral() {
     return literal;
   }

   /**
    * Returns the literal value of the enumerator, which is its string representation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString() {
      return literal;
   }
   
} //DataTypes
