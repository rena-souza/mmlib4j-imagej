package mmlib4j.imagej.renan;

import ij.gui.GenericDialog;

public abstract class AbstractRadiusMorphologicalPlugin extends AbstractMorphologicalPlugin {
	
	private double radius;
	
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
	
	public double getRadius(){
		return radius;
	}

}
