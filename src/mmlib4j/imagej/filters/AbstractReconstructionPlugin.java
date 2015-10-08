package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.imagej.types.Channel;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.images.GrayScaleImage;

/**
 * Abastract class for reconstructions plugins
 * 
 * @author rsouza
 */
public abstract class AbstractReconstructionPlugin implements ReconstructionPlugin{
	
	private ImageProcessor ipF;
	private ImageProcessor ipG;
	private ImagePlus imgPlus;
	
	public void setFisrtImage(ImageProcessor ipf){
		this.ipF = ipf;
	}
	public void setSecondImage(ImageProcessor ip2){
		this.ipG = ip2;
	}
	
	@Override
	public int setup(String arg, ImagePlus imgPlus) {
		if(!initParameters())
			return PlugInFilter.DONE;
		
		setImgPlus(imgPlus);
		return accepts();
	}
	
	@Override
	public int accepts() {
		return PlugInFilter.DOES_8G | PlugInFilter.DOES_RGB;
	};
	
	@Override
	public void run(ImageProcessor op) {
		if(ipG instanceof ColorProcessor && ipF instanceof ColorProcessor)
			run((ColorProcessor) ipF);
		else if(ipF instanceof ByteProcessor && ipG instanceof ByteProcessor)
			run((ByteProcessor) ipF);
		else
			throw new IllegalArgumentException("Not supported: " + ipF.getClass() + " and " + ipG.getClass());
	}
	
	public void run(ColorProcessor cp) {
		//TODO implement multythread code here
		Splitter spliter = new Splitter(cp);

		GrayScaleImage imageR = spliter.getChannel(Channel.RED);
		GrayScaleImage imageG = spliter.getChannel(Channel.GREEN);
		GrayScaleImage imageB = spliter.getChannel(Channel.BLUE);

		ColorProcessor cpB = (ColorProcessor) ipG;
		Splitter spliterG = new Splitter(cpB);

		GrayScaleImage imgOutR = reconstruct(imageR, spliterG.getChannel(Channel.RED));
		GrayScaleImage imgOutG = reconstruct(imageG, spliterG.getChannel(Channel.GREEN));
		GrayScaleImage imgOutB = reconstruct(imageB, spliterG.getChannel(Channel.BLUE));

		ColorProcessor rgbOutput = ImageJAdapter.toColorProcessor(cp.getWidth(), cp.getHeight(), imgOutR, imgOutG, imgOutB);
		show(rgbOutput);
	}
	
	public GrayScaleImage run(ByteProcessor bp) {
		GrayScaleImage image = ImageJAdapter.toGrayScaleImage(bp);
		return reconstruct(image, ImageJAdapter.toGrayScaleImage((ByteProcessor) ipG));
	}

	public void show(ImageProcessor output) {
		ImagePlus plus = new ImagePlus();
		plus.setProcessor(getPluginName(), output);
		plus.show();
	}
	
	/* Getters and Setters */
	public void setImgPlus(ImagePlus imgPlus) {
		this.imgPlus = imgPlus;
	}
	
	@Override
	public ImagePlus getImgPlus() {
		return imgPlus;
	}

}
