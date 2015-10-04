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
public class Self_TopHat extends AbstractRadiusPlugin {

	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.selfTopHat(image, AdjacencyRelation.getCircular(getRadius()));
	}

	@Override
	public String getPluginName() {
		return "Self top-hat";
	}
	
	@Override
	public double initialRadius() {
		return 5D;
	}

}
