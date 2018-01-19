package items;

public class CaptureItem extends Item {
    private double success;

    CaptureItem(String name, String description, double success) {
        super(name, description, "Capture Item");
        this.success = success;
    }

    public double getSuccess(){
        return success;
    }
}
