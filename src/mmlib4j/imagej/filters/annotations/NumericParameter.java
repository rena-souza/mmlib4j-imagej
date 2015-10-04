package mmlib4j.imagej.filters.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NumericParameter {
	 String name();

	 double defaultValue() default 1.5; 
	 int digits() default 1;
}
