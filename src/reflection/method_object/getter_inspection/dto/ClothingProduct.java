package reflection.method_object.getter_inspection.dto;

public class ClothingProduct extends Product {
    private Size size;
    private String color;

    public Size getSize() {
        return size;
    }

//    public void setSize(Size size) {
//        this.size = size;
//    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
