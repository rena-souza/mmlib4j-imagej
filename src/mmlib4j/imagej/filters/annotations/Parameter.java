package mmlib4j.imagej.filters.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {
	
	 Class<?> type();
	 String name();
	 
	 double defaultValue() default 1.0; 
	 int digits() default 3;
}
