package images.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping({"/images"})
public class ImagesController {

    private IImagesManager imagesManager;

    @Autowired
    public ImagesController(IImagesManager imagesManager) {
        this.imagesManager = imagesManager;
    }

    @PostMapping
    public NewImageReadModel AddNew(HttpServletRequest requestEntity) throws Exception {
        return imagesManager.setImage( requestEntity.getInputStream() );
    }

    @DeleteMapping(path = {"delete/{id}"})
    public void delete(@PathVariable("id") String id) {
        imagesManager.delete( id );
    }

    @GetMapping(path = {"/size/{id}"})
    public ImageSizeReadModel getSize(@PathVariable("id") String id) {
        return imagesManager.size( id );
    }

    @GetMapping(path = {"/scale/{id}/{percent}"}, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getScaledImage(@PathVariable("id") String id, @PathVariable("percent") Integer percent) throws IOException {
        return imagesManager.getScaledImage( id, percent );
    }

    @GetMapping(path = {"/histogram/{id}"})
    public Histogram getHistogram(@PathVariable("id") String id) {
        return imagesManager.getHistogram( id );
    }

    @GetMapping(path = {"/crop/{id}/{start}/{stop}/{width}/{height}"}, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getSection(@PathVariable("id") String id, @PathVariable("start") int start, @PathVariable("stop") int stop,
                             @PathVariable("width") int width, @PathVariable("height") int height) throws IOException, InvalidAttributesException {
        return imagesManager.getSection( id, start, stop, width, height );
    }

    @GetMapping(path = {"/greyscale/{id}"}, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGreyScale(@PathVariable("id") String id) throws IOException {
        return imagesManager.getGreyScale( id );
    }

    @GetMapping(path = {"/blur/{id}/{radius}"}, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getBlur(@PathVariable("id") String id, @PathVariable("radius") int radius) throws Exception {
        return imagesManager.getBlur( id, radius );
    }
}