package cn.epalmpay.analoy.export;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.epalmpay.analoy.annotation.Column;
import cn.epalmpay.analoy.annotation.Export;

public class BeanUtilsBean {

	/**
	 * 获得一个bean对象中(属性，值)对，以map的形式返回，map的key为属性名，value为属性的值。<br/>
	 * 注意：该方法只返回有getXxx()方法的属性和对应的值，没有get方法的属性不在返回的map中
	 * 
	 * @param bean
	 *            一个bean对象
	 * @return 返回该bean中有get方法的属性值对
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> describe(Object bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bean == null) {
			return (new HashMap<String, Object>());
		}
		BeanInfo info = Introspector.getBeanInfo(bean.getClass());
		Map<String, Object> description = new HashMap<String, Object>();
		PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			Method reader = descriptors[i].getReadMethod();
			if (reader != null) {
				Object[] os = new Object[0];
				Object value = reader.invoke(bean, os);
				description.put(name, value);
			}
		}
		description.remove("class");
		return description;
	}

	/**
	 * 获得bean对象所有的get方法对应的属性和值，如getAbc()这个方法对应的属性为abc，值为bean.getAbc()
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> describeGetMethod(Object bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bean == null) {
			return (new HashMap<String, Object>());
		}

		BeanInfo info = Introspector.getBeanInfo(bean.getClass());
		Map<String, Object> description = new HashMap<String, Object>();
		MethodDescriptor[] descriptors = info.getMethodDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String methodName = descriptors[i].getName();
			if (methodName.startsWith("get")) {
				Method m = descriptors[i].getMethod();
				if (0 != m.getParameterTypes().length) {
					StringBuffer sb = new StringBuffer();
					Class[] parameters = m.getParameterTypes();
					for (int j = 0; j < parameters.length; j++) {
						sb.append(parameters[j].getName() + ",");
					}
					sb.delete(sb.toString().length() - 1, sb.toString().length());
					throw new ParseBeanException("get方法不能有参数: " + methodName + "(" + sb.toString() + ")");
				}
				Object value = m.invoke(bean, new Object[0]);
				String f = methodName.substring(3);
				f = f.substring(0, 1).toLowerCase() + f.substring(1);
				description.put(f, value);
			}
		}
		description.remove("class");
		return description;
	}

	/**
	 * 获得bean对象被注解成@Column的属性或方法的值,返回值的Map的key对应属性或方法，若是方法，如getAbc()，
	 * 那么保存的key为abc
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static Map<String, Object> describeAnnotationFeildOrMethodValue(Object bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (bean == null) {
			return (new HashMap<String, Object>());
		}

		BeanInfo info = Introspector.getBeanInfo(bean.getClass());
		Map<String, Object> description = new HashMap<String, Object>();
		Map<String, Object> getMethodValue = describeGetMethod(bean);

		Class clazz = bean.getClass();
		if (!clazz.isAnnotationPresent(Export.class)) {
			throw new ParseBeanException("没有@Export注解");
		}
		Field[] feilds = clazz.getDeclaredFields();

		for (int i = 0; i < feilds.length; i++) {
			if (feilds[i].isAnnotationPresent(Column.class)) {
				String feildName = feilds[i].getName();
				// String getMethod = feildName.substring(0, 1).toUpperCase() +
				// feildName.substring(1);
				if (!getMethodValue.containsKey(feildName)) {
					throw new ParseBeanException("没有为@Column的属性定义get方法: " + clazz.getName() + ":" + feildName);
				}
				description.put(feildName, getMethodValue.get(feildName));
			}
		}

		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(Column.class)) {
				if (!methods[i].getName().startsWith("get")) {
					throw new ParseBeanException("@Column注解的方法必须以get开头 " + clazz.getName() + "." + methods[i].getName());
				}
				if (methods[i].getParameterTypes().length != 0) {
					Method m = methods[i];
					StringBuffer sb = new StringBuffer();
					Class[] parameters = m.getParameterTypes();
					for (int j = 0; j < parameters.length; j++) {
						sb.append(parameters[j].getName() + ",");
					}
					sb.delete(sb.toString().length() - 1, sb.toString().length());
					throw new ParseBeanException("get方法不能有参数: " + m.getName() + "(" + sb.toString() + ")");
				}
				Object value = methods[i].invoke(bean, new Object[0]);
				String methodName = methods[i].getName().substring(3);
				methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
				description.put(methodName, value);
			}
		}
		return description;
	}

	/**
	 * 解析bean中被注解为Column的属性或方法，该方法不能有参数且返回值不能为void<br/>
	 * 解析后的结果封装成BeanAnnotation对象以List的方式返回
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<BeanAnnotation> parse(Class bean) {
		List<BeanAnnotation> temp = new ArrayList<BeanAnnotation>();
		Class clazz = bean;
		if (clazz.isAnnotationPresent(Export.class)) {
			Export export = (Export) clazz.getAnnotation(Export.class);
			BeanAnnotation beanAnnotation = new BeanAnnotation();
			beanAnnotation.pos = 0;
			beanAnnotation.sheetName = export.sheetName();
			temp.add(beanAnnotation);
		} else {
			throw new ParseBeanException("没有@Export注解");
		}

		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(Column.class)) {
				if (methods[i].getParameterTypes().length != 0) {
					throw new ParseBeanException("注解为Column的方法不能有参数");
				}
				if (methods[i].getReturnType().getName().equals("void")) {
					throw new ParseBeanException("注解为Column的方法返回值不能为void");
				}
				Column column = methods[i].getAnnotation(Column.class);
				BeanAnnotation info = new BeanAnnotation();
				info.pos = column.pos();
				info.head = column.head();
				info.methodName = methods[i].getName();
				temp.add(info);
			}
		}
		Collections.sort(temp);
		return temp;

	}

	/**
	 * 获得bean对应的属性名和类型的map对
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Class> getFeildInfo(Object bean) {
		Map<String, Class> ret = new HashMap<String, Class>();
		if (bean == null) {
			return ret;
		}
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			ret.put(fields[i].getName(), fields[i].getType());
		}
		return ret;
	}

}
