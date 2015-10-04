package mmlib4j;



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
public class White_TopHat extends AbstractRadiusPlugin {
	
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
		return MorphologicalOperators.openingTopHat(image, AdjacencyRelation.getCircular(getRadius()));
	}

}
