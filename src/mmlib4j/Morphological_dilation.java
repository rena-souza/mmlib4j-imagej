package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.renan.AbstractRadiusMorphologicalPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_dilation extends AbstractRadiusMorphologicalPlugin {
	
	@Override
	public String getPluginName() {
		return "Dilation";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.dilation(image, AdjacencyRelation.getCircular(getRadius()));
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
