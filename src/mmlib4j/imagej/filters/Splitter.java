package mmlib4j.imagej.filters;

import ij.process.ColorProcessor;
import mmlib4j.imagej.types.Channel;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;

public class Splitter {
	
	private ColorProcessor cp;
	
	public Splitter(ColorProcessor cp){
		this.cp = cp;
	}
	
	public byte[][] split(){
		byte[] red = cp.getChannel(Channel.RED.value());
		byte[] green = cp.getChannel(Channel.GREEN.value());
		byte[] blue = cp.getChannel(Channel.BLUE.value());		
		
		byte[][] channels = {red, green, blue};
		return channels;
	}
	
	public byte[] channelToByte(Channel channel){
		return cp.getChannel(channel.value());
	}
	
	public GrayScaleImage getChannel(Channel channel){
		byte[] pixels = channelToByte(channel);
		return ImageFactory.createGrayScaleImage(8, pixels, cp.getWidth(), cp.getHeight());
	}

}
