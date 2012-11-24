package edu.cmusv.lions.petmobile.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtils {

	public static List<String> getStringConstants(Class<?> constantsClass) {
		List<String> stringConstants = new ArrayList<String>();
		for (Field field : constantsClass.getFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				Object obj;
				try {
					obj = field.get(null);
					if (obj instanceof String) {
						stringConstants.add(obj.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return stringConstants;
	}
	
}
