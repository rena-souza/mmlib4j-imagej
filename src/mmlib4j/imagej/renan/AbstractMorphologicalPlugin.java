package mmlib4j.imagej.renan;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.imagej.types.Channel;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;

public abstract class AbstractMorphologicalPlugin implements PlugInFilter {
	
	protected ImagePlus imgPlus;
	
	public void run(ColorProcessor cp){
		byte pixelsR[] = cp.getChannel(Channel.RED.value());
        byte pixelsG[] = cp.getChannel(Channel.GREEN.value());
        byte pixelsB[] = cp.getChannel(Channel.BLUE.value());
        int width = cp.getWidth();
        int height = cp.getHeight();
        int depth = 8;
        
        
        GrayScaleImage imageR = getGrayScaleImage(depth, pixelsR, width, height);
        GrayScaleImage imageG = getGrayScaleImage(depth, pixelsG, width, height);
        GrayScaleImage imageB = getGrayScaleImage(depth, pixelsB, width, height);
                       
        GrayScaleImage imgOutR = doProcess(imageR);
        GrayScaleImage imgOutG = doProcess(imageG);
        GrayScaleImage imgOutB = doProcess(imageB);
        
        ColorProcessor rgbOutput = new ColorProcessor(cp.getWidth(), cp.getHeight());
		rgbOutput.setRGB((byte[])imgOutR.getPixels(), (byte[])imgOutG.getPixels(), (byte[])imgOutB.getPixels());
		
		show(rgbOutput);
        
  }
	
	private GrayScaleImage getGrayScaleImage(int depth, byte[] pixels, int width, int height){
		return ImageFactory.createGrayScaleImage(depth, pixels, width, height);
	}
	
	public void show(ImageProcessor output){
		System.out.println("Mostrando");
		imgPlus.setProcessor(getFilterName(), output);
		imgPlus.show();
	}
	
	public void run(ByteProcessor bp){
		ImageUtils.initMMorph4J();
		GrayScaleImage output = doProcess(ImageJAdapter.toGrayScaleImage(bp));
		show(ImageJAdapter.toByteProcessor(output));
	}
	
	public abstract String getFilterName();
	
	@Override
	public void run(ImageProcessor arg0) {
		if(arg0.getClass().isAssignableFrom(ij.process.ColorProcessor.class))
			run((ColorProcessor) arg0);
		
		else if(arg0.getClass().isAssignableFrom(ByteProcessor.class))
			run((ByteProcessor) arg0);
		else
			throw new RuntimeException(arg0.getClass() + " Not supported");
	}
	
	public abstract GrayScaleImage doProcess(GrayScaleImage bp);
	
}
