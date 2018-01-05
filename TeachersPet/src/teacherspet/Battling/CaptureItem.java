public class CaptureItem extends Item{
    private double success;

    CaptureItem(String name, double success) {
        super(name);
        this.success = success;
    }

    public double getSuccess(){
        return success;
    }
}
