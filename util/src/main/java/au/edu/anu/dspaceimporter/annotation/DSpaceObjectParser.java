package au.edu.anu.dspaceimporter.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class DSpaceObjectParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(DSpaceObjectParser.class);
	
	
	public Map<String, Set<String>> getDSPaceValues(DSpaceObject record) throws InvocationTargetException, IllegalAccessException {
		Map<String, Set<String>> values = new HashMap<String, Set<String>>();
		
		Class<?> clazz = record.getClass();
		
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(DSpaceField.class)) {
				DSpaceField field = method.getAnnotation(DSpaceField.class);
				Object returnObject = method.invoke(record);
				if (returnObject instanceof String) {
					String returnValue = (String) returnObject;
					Set<String> setVal = new LinkedHashSet<String>();
					setVal.add(returnValue);
					values.put(field.value(), setVal);
				}
				else if (returnObject instanceof List) {
					@SuppressWarnings("unchecked")
					List<String> returnValue = (List<String>) returnObject;
					Set<String> setVal = new LinkedHashSet<String>(returnValue);
					values.put(field.value(), setVal);
				}
			}
		}
		
		return values;
	}
}
