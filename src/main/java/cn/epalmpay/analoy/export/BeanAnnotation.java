package cn.epalmpay.analoy.export;

@SuppressWarnings("rawtypes")
public class BeanAnnotation implements Comparable {
	int pos;
	String head;
	// String format;

	// String feild;

	String sheetName;
	// Class type;

	String methodName;

	public int compareTo(Object o) {
		BeanAnnotation beanAnnotation = (BeanAnnotation) o;
		if (pos == beanAnnotation.pos) {
			return 0;
		} else if (pos > beanAnnotation.pos) {
			return 1;
		} else {
			return -1;
		}
	}

}