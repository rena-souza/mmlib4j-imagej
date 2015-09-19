import mmlib4j.gui.WindowImages;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;
import mmlib4j.utils.ImageBuilder;

public class Negativo_ {
	public static void main(String[] args) {
		//abrir  uma imagen
		//	GrayScaleImage => 8bits
		//	ColorImage => Imagem colorida (rgb)
		//	BinaryImage => 1bit
		//	RealImage => imagem de ponto flutuante
		
		GrayScaleImage img;
		// ABRIR A IMAGEM A partir de um arquivo
		img = ImageBuilder.openGrayImage();
		
		//Visualizar imagem
		WindowImages.show(img);
		
		GrayScaleImage imgSaida = ImageFactory.createGrayScaleImage(img.getWidth(), img.getHeight());
		for(int y=0; y < img.getHeight(); y++){
			for(int x  = 0; x < img.getWidth(); x++){
				int valor = 255 - img.getPixel(x,  y);
				imgSaida.setPixel(x, y, valor);
			}
		}
		WindowImages.show(imgSaida);
	}
}
