public class PopText
{
    double x, y;
    String text;
    long endNanos;

    PopText(double x, double y, String text, long lifetimeNanos)
    {
        this.x = x;
        this.y = y;
        this.text = text;
        this.endNanos = System.nanoTime() + lifetimeNanos;
    }
}