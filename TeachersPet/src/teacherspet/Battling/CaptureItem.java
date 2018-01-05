public class CaptureItem extends Item{
    private double success;

    CaptureItem(String name, int success) {
        super(name);
        this.success = success;
    }

    public double getSuccess(){return success;}
}
