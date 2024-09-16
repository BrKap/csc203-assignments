public class CircleTest {
    public static void main(String[] args) {
//        Circle c2 = new Circle(0); // Try with different radius values (positive, zero, negative)

        try {
            Circle c1 = new Circle(-3); // Try with different radius values (positive, zero, negative)
            System.out.println(c1);
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage() + ": " + e.radius());
        } catch (ZeroRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("In finally.");
        }
        System.out.println("Done.");
    }
}
