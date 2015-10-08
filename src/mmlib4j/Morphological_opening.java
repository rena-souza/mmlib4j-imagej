package mmlib4j;

import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_opening extends AbstractFilterPlugin {

	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.opening(image, getAdjancencyRelation());
	}

	@Override
	public String getPluginName() {
		return "Opening";
	}

	public static void main(String args[]){
		ImagePlus plus = ImageUtils.openGrayScale();
		Morphological_opening plugin = new Morphological_opening();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}
}
