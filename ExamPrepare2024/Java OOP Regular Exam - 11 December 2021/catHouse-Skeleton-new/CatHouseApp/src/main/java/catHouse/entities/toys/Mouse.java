package catHouse.entities.toys;

public class Mouse extends BaseToy {
    private static final int BALL_SOFTNESS = 5;
    private static final double MOUSE_PRICE = 15;

    public Mouse() {
        super(BALL_SOFTNESS, MOUSE_PRICE);
    }
}
