package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.renan.AbstractMorphologicalPlugin;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
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
	public String getFilterName() {
		return "Dilation";
	}
	
	@Override
	public int setup(String arg, ImagePlus imp) {
		GenericDialog tela = new GenericDialog("Dilation");
		tela.addNumericField("Radius", 1.5, 1);
		tela.showDialog();
		if(tela.wasCanceled()){
			return PlugInFilter.DONE;
		}
		imgPlus = imp;
		raio = tela.getNextNumber();
		return PlugInFilter.DOES_8G | PlugInFilter.DOES_RGB;
	}
	
	@Override
	public GrayScaleImage doProcess(GrayScaleImage image) {
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
