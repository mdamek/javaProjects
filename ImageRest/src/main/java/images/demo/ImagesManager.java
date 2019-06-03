package images.demo;

import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import javax.naming.directory.InvalidAttributesException;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class ImagesManager implements IImagesManager {

    private static Map<String, BufferedImage> images = new HashMap<String, BufferedImage>() {};

    @Override
    public NewImageReadModel setImage(InputStream inputStream) throws IOException {
        String id = UUID.randomUUID().toString();
        BufferedImage image = ImageIO.read(inputStream);
        images.put( id, image);
        return new NewImageReadModel( id, image.getHeight(), image.getWidth() );
    }

    private BufferedImage get(String id){
        BufferedImage image = images.get( id );
        if(image == null) throw new ImageNotFoundException();
        return image;
    }

    private byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    @Override
    public void delete(String id) {
        BufferedImage image = images.remove( id );
        if(image == null) throw new ImageNotFoundException();
    }

    @Override
    public ImageSizeReadModel size(String id) {
        BufferedImage image = get( id );
        return new ImageSizeReadModel(image.getHeight(), image.getWidth());
    }

    public byte[] getScaledImage(String id, int scale) throws IOException {
        BufferedImage image = get( id );
        BufferedImage scaledImage = new BufferedImage((image.getWidth() * scale / 100 ),(image.getHeight() * scale /100), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance((double)scale/ 100.0, (double)scale/ 100.0);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage finalImage = ato.filter(image, scaledImage);
        return convertImageToByteArray(finalImage);

    }

    @Override
    public Histogram getHistogram(String id) {
        BufferedImage image = get( id );
        Histogram histogram = new Histogram();
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++) {
                Color color = new Color( image.getRGB( x,y ) ) ;
                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();
                histogram.AddToBlue( blue );
                histogram.AddToGreen( green );
                histogram.AddToRed( red );
            }
        }
        return histogram;
    }

    @Override
    public byte[] getSection(String id, int start, int stop, int width, int height) throws InvalidAttributesException, IOException {
        BufferedImage image = get( id );
        if(start < 0 || stop < 0 || width < 0 || height < 0)
            throw new InvalidAttributesException( "All values must be > 0" );
        BufferedImage section = image.getSubimage( start, stop, width, height );
        return convertImageToByteArray( section );
    }

    @Override
    public byte[] getGreyScale(String id) throws IOException {
        BufferedImage image = get( id );
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage imageFinal = op.filter(image, null);
        return convertImageToByteArray( imageFinal );
    }

    @Override
    public byte[] getBlur(String id, int radius) throws Exception {
        if (radius < 0) throw new Exception( "Radius must be >0" );
        BufferedImage image = get( id );
        int[] filter = new int[radius];
        for ( int i = 0; i < radius; i++ ){
            filter[i] =  i % 5;
        }
        BufferedImage blurred = blur( image, filter, radius);
        return convertImageToByteArray( blurred );
    }


    private BufferedImage blur(BufferedImage image, int[] filter, int filterWidth) {
        if (filterWidth < 0) {
            throw new IllegalArgumentException("Radius must be >0");
        }

        final int width = image.getWidth();
        final int height = image.getHeight();
        final int sum = IntStream.of(filter).sum();

        int[] input = image.getRGB(0, 0, width, height, null, 0, width);

        int[] output = new int[input.length];

        final int pixelIndexOffset = width - filterWidth;
        final int centerOffsetX = filterWidth / 2;
        final int centerOffsetY = filter.length / filterWidth / 2;

        for (int h = height - filter.length / filterWidth + 1, w = width - filterWidth + 1, y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int filterIndex = 0, pixelIndex = y * width + x;
                     filterIndex < filter.length;
                     pixelIndex += pixelIndexOffset) {
                    for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
                        int col = input[pixelIndex];
                        int factor = filter[filterIndex];
                        r += ((col >>> 16) & 0xFF) * factor;
                        g += ((col >>> 8) & 0xFF) * factor;
                        b += (col & 0xFF) * factor;
                    }
                }
                r /= sum;
                g /= sum;
                b /= sum;
                output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
            }
        }
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        result.setRGB(0, 0, width, height, output, 0, width);
        return result;
    }
}
