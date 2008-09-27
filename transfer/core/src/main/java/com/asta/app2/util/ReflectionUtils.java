/* *Class created on [ Sep 7, 2008 | 2:16:29 PM ] */ 
package com.asta.app2.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.asta.app2.exception.CoreException;
import com.asta.app2.model.BaseObject;
import com.asta.app2.model.enums.CarType;
import com.asta.app2.model.enums.Distance;
import com.asta.app2.model.enums.Gender;
import com.asta.app2.model.enums.Quality;
import com.asta.app2.model.enums.RateType;
import com.asta.app2.model.enums.SooratType;
import com.asta.app2.model.enums.Weekday;
	
/**
 * @author Hossein Rahmani
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ReflectionUtils {
	
	static Class[] basicClasses = new Class[] {
		Byte.class,
		Short.class,
		Integer.class,
		Long.class,
		Float.class,
		Double.class,
		Character.class,
		String.class,
		Boolean.class,
		java.util.Date.class,
		Weekday.class,
		Quality.class,
		CarType.class,
		Distance.class,
		Gender.class,
		RateType.class,
		SooratType.class
	};
	
	public static Map<String,BaseObject> getNotNullEntityFields(BaseObject entity) {
		Map<String,BaseObject> r = new HashMap<String,BaseObject>();
		if (entity == null)
			return r;
		for (Method method : entity.getClass().getMethods()) {
			if ( 
					BaseObject.class.isAssignableFrom(method.getReturnType())
					&& method.getName().startsWith("get")
					&& (method.getParameterTypes() == null || method
							.getParameterTypes().length == 0)
					) {
				try {
					BaseObject related = (BaseObject) method.invoke(entity);
					if (related != null) {
						String propertyName = method.getName().substring("get".length());
						propertyName = propertyName.substring(0,1).toLowerCase() + propertyName.substring(1,propertyName.length());
						r.put(propertyName,related);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}
	
	public static Map<String,Object> getNotNullSimpleFields(BaseObject entity) {
		Map<String,Object> r = new HashMap<String,Object>();
		if (entity == null)
			return r;
		for (Method method : entity.getClass().getMethods()) {
			if (!BaseObject.class.isAssignableFrom(method.getReturnType())
					&& isBasicClass(method.getReturnType())
					&& method.getName().startsWith("get")
					&& (method.getParameterTypes() == null || method.getParameterTypes().length == 0)
					&& !Collection.class.isAssignableFrom(method.getReturnType())
			) {
				try {
					Object value = method.invoke(entity);
					if (value != null) {
						String propertyName = method.getName().substring("get".length());
						propertyName = propertyName.substring(0,1).toLowerCase() + propertyName.substring(1,propertyName.length());
						r.put(propertyName,value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}
	
	public static boolean isBasicClass(Class clazz) {
		for (int i = 0; i < basicClasses.length;i++ ) 
			if (basicClasses[i].equals(clazz))
				return true;
		return false;
	}
	
	public static void invokeSetter(Object obj, String name, Object value) {

		Class clazz = obj.getClass();
		Method[] methods = clazz.getMethods();
		// Log.debug(obj.getClass());
		// Log.debug(name);
		Method method = null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(makeSetterMethod(name)))
				method = methods[i];
		}
		try {
			method.invoke(obj, new Object[] { value });
		} catch (IllegalArgumentException e) {
			throw new CoreException(e);
		} catch (IllegalAccessException e) {
			throw new CoreException(e);
		} catch (InvocationTargetException e) {
			throw new CoreException(e);
		}
	}

	public static Object invokeGetter(Object obj, String name) {
		Object result = null;
		Class c = obj.getClass();
		Class[] parameterTypes = new Class[] {};
		Method concatMethod;
		Object[] arguments = new Object[] {};
		try {
			if (name.indexOf(".") > 0) {
				String firstStr = name.substring(0, name.indexOf("."));
				String rest = name.substring(name.indexOf(".") + 1, name
						.length());
				Object object = invokeGetter(obj, firstStr);
				if (object == null) {
					return null;
				}
				result = invokeGetter(object, rest);
				return result;
			}
			String getMethod = makeGetterMethod(name);
			// Log.info("gemethod is " + getMethod);
			concatMethod = c.getMethod(getMethod, parameterTypes);
			result = (Object) concatMethod.invoke(obj, arguments);
			// Log.info("result of reflection is : " + result);
			// Log.info(name + " : " + result);
		} catch (NoSuchMethodException e) {

			e.printStackTrace(); // To change body of catch statement use
			// File |
			return null;
			// Settings | File Templates.
		} catch (IllegalAccessException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File |
			return null;
			// Settings | File Templates.
		} catch (InvocationTargetException e) {
			e.printStackTrace(); // To change body of catch statement use
			// File |
			return null;
			// Settings | File Templates.
		}
		return result;

	}
	private static String makeGetterMethod(String name) {
		String result = "get";
		result += (name.toUpperCase().substring(0, 1))
				+ name.substring(1, name.length());
		return result;
	}
	private static String makeSetterMethod(String name) {
		String result = "set";
		result += (name.toUpperCase().substring(0, 1))
				+ name.substring(1, name.length());
		return result;
	}

}


