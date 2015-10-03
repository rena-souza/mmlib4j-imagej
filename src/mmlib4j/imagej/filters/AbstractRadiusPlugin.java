package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.gui.GenericDialog;

public abstract class AbstractRadiusPlugin extends AbstractColorFilterPlugin implements RadiusPlugin {
	
	private double radius;
	private ImagePlus imgPlus;

	@Override
	public boolean initParameters() {
		GenericDialog tela = new GenericDialog(getPluginName());
		tela.addNumericField("Radius", 1.5, 1);
		tela.showDialog();
		if(tela.wasCanceled()){
			return false;
		}
		radius = tela.getNextNumber();
		return true;
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
