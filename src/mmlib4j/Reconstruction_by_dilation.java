package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.GenericDialog;
import mmlib4j.imagej.filters.AbstractReconstructionPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.representation.tree.componentTree.ReconstructionMorphological;
import mmlib4j.utils.AdjacencyRelation;
/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Reconstruction_by_dilation extends AbstractReconstructionPlugin {
	
	double raio;
	
	@Override
	public GrayScaleImage reconstruct(GrayScaleImage imgF, GrayScaleImage imgG) {
		ReconstructionMorphological rec = new ReconstructionMorphological(imgF, AdjacencyRelation.getCircular(raio), true);
		GrayScaleImage imgOut = rec.reconstructionByDilation(imgG);
		return imgOut;
	}

	@Override
	public boolean initParameters() {
		 GenericDialog gd = new GenericDialog("Morphological reconstruction based on marked image");
         gd.addMessage("This plug-in calculates reconstruction by dilation");


 		int[] wList = WindowManager.getIDList();

 		if (wList == null || wList.length < 2) {
 			IJ.showMessage("Morphological reconstruction based on marked image", "There must be at least two windows open");
 			return false;
 		}  
 		
 		String[] titles = new String[wList.length];
 		for (int i = 0, k = 0; i < wList.length; i++) {
 			ImagePlus imp = WindowManager.getImage(wList[i]);
 			if (null != imp)
 				titles[k++] = imp.getTitle();
 		}

 		gd.addChoice("mask image:", titles, titles[0]);
 		gd.addChoice("marked image:", titles, titles[1]);
 		gd.addNumericField("Adjacency relation (Radius)", 1.5, 1);
 		
 		gd.showDialog();
         if (gd.wasCanceled())
             return false;


 		int i1Index = gd.getNextChoiceIndex();
 		int i2Index = gd.getNextChoiceIndex();
 		raio = gd.getNextNumber();
 		
 		ImagePlus imp1 = WindowManager.getImage(wList[i1Index]);
 		ImagePlus imp2 = WindowManager.getImage(wList[i2Index]);
 		
 		setFisrtImage(imp1.getProcessor());
 		setSecondImage(imp2.getProcessor());
 		
 		return true;
	}

	@Override
	public String getPluginName() {
		return "Reconstruction by dilation";
	}

}

