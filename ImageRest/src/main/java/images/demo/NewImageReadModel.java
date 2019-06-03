package images.demo;

public class NewImageReadModel {
    NewImageReadModel(String id, int height, int width) {
        Id = id;
        Height = height;
        Width = width;
    }

    public String getId() {
        return Id;
    }

    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }

    private String Id;
    private int Height;
    private int Width;
}
