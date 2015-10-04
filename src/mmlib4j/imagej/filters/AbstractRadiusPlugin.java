package mmlib4j.imagej.filters;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import mmlib4j.imagej.filters.annotations.Parameter;

public abstract class AbstractRadiusPlugin extends AbstractColorFilterPlugin implements RadiusPlugin {
	
	private double radius;
	private ImagePlus imgPlus;

	@Override
	public boolean initParameters() {
		GenericDialog tela = new GenericDialog(getPluginName());
//		tela.addNumericField("Radius", initialRadius(), 1);
		Class<?> clazz = this.getClass();
		for(Field f : clazz.getDeclaredFields()){
			Parameter parameter = f.getDeclaredAnnotation(Parameter.class);
			if(parameter == null)
				continue;
			
			Class<?> type = parameter.type();
			tela.addNumericField(parameter.name(), parameter.defaultValue(), parameter.digits());
		}
		
		
		tela.showDialog();
		if(tela.wasCanceled()){
			return false;
		}
		
		for(Field f : clazz.getDeclaredFields()){
			Parameter parameter = f.getDeclaredAnnotation(Parameter.class);
			if(parameter == null)
				continue;
			
			f.setAccessible(true);
			try {
				f.set(this, tela.getNextNumber());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		
//		radius = tela.getNextNumber();
		return true;
	}
	
	public double initialRadius(){
		return 1.5;
	}

	@Override
	public double getRadius(){
		return radius;
	}
	
	/* Getters and Setters */
	public void setImgPlus(ImagePlus imgPlus) {
		this.imgPlus = imgPlus;
	}
	
	@Override
	public ImagePlus getImgPlus() {
		return imgPlus;
	}


}
