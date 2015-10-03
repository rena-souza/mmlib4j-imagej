package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.renan.AbstractMorphologicalPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_dilation extends AbstractMorphologicalPlugin {
	
	double raio;
	
	@Override
	public String getPluginName() {
		return "Dilation";
	}
	
	@Override
	public boolean initParameters() {
		GenericDialog tela = new GenericDialog("Dilation");
		tela.addNumericField("Radius", 1.5, 1);
		tela.showDialog();
		if(tela.wasCanceled()){
			return false;
		}
		raio = tela.getNextNumber();
		return true;
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.dilation(image, AdjacencyRelation.getCircular(raio));
	}
	
	public static void main(String args[]){
		ImagePlus plus = IJ.openImage();
		plus.setProcessor(plus.getProcessor().convertToRGB());
		plus.show();
		Morphological_dilation plugin = new Morphological_dilation();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}

}
