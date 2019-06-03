package images.demo;

import javax.naming.directory.InvalidAttributesException;
import java.io.IOException;
import java.io.InputStream;

public interface IImagesManager {
    NewImageReadModel setImage(InputStream inputStream) throws IOException;
    void delete(String id);
    ImageSizeReadModel size(String id);
    byte[] getScaledImage(String id, int scale) throws IOException;
    Histogram getHistogram(String id);
    byte[] getSection(String id, int start, int stop, int width, int height) throws InvalidAttributesException, IOException;
    byte[] getGreyScale(String id) throws IOException;
    byte[] getBlur(String id, int radius) throws Exception;
}
