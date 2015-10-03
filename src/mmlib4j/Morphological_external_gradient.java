package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractRadiusPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_external_gradient extends AbstractRadiusPlugin {
	
	@Override
	public String getPluginName() {
		return "Morphological Gradient (external)";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.gradientExternal(image, AdjacencyRelation.getCircular(getRadius()));
	}
	
	public static void main(String args[]){
		ImagePlus plus = IJ.openImage();
		Morphological_external_gradient plugin = new Morphological_external_gradient();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}
}
