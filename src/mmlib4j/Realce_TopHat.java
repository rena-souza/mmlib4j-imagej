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
public class Realce_TopHat extends AbstractRadiusPlugin {
	
	@Override
	public String getPluginName() {
		return "Realce top-hat";
	}
	
	@Override
	public double initialRadius() {
		return 5D;
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.realceTopHat(image, AdjacencyRelation.getCircular(getRadius()));
	}
	
}
