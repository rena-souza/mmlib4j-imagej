package mmlib4j;

import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractMorphologicalPlugin;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_closing extends AbstractMorphologicalPlugin {

	@Override
	public String getPluginName() {
		return "Closing";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.closing(image, AdjacencyRelation.getCircular(getRadius()));
	}
		
	public static void main(String args[]){
		ImagePlus plus = ImageUtils.openGrayScale();
		Morphological_closing plugin = new Morphological_closing();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}
}
