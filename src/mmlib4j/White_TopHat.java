package mmlib4j;



import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.images.GrayScaleImage;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class White_TopHat extends AbstractFilterPlugin {
	
	@Override
	public double initialRadius() {
		return 5D;
	}
	
	@Override
	public String getPluginName() {
		return "White top-hat";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.openingTopHat(image, getAdjancencyRelation());
	}

}
