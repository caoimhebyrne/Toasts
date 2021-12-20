package dev.cbyrne.toasts;

import dev.cbyrne.toasts.handler.ToastDecayHandler;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

/**
 * A basic toast that can be used to display a text message to the player.
 */
public class BasicToast extends ToastBase {
    private static final long DEFAULT_DISPLAY_TIME = 5000;

    private final Text text;
    private final long displayTime;
    private final ToastDecayHandler decayHandler;

    private boolean didExecuteDecayHandler = false;

    /**
     * A toast that will be displayed for a certain amount of time and has a handler that will be called when the toast
     * is dismissing.
     *
     * @param text         The text to display to the user
     * @param decayHandler The handler to call when the toast is dismissing
     * @param displayTime  The amount of time to display the toast for
     */
    public BasicToast(Text text, long displayTime, ToastDecayHandler decayHandler) {
        this.text = text;
        this.displayTime = displayTime;
        this.decayHandler = decayHandler;
    }

    /**
     * Creates a toast that will be displayed for 5000ms (5s).
     *
     * @param text The text to display to the user
     */
    public static BasicToast create(Text text) {
        return new BasicToast(text, DEFAULT_DISPLAY_TIME, null);
    }

    /**
     * Creates a toast that will be displayed for 5000ms (5s).
     *
     * @param text The text to display to the user
     */
    public static BasicToast create(String text) {
        return new BasicToast(new LiteralText(text), DEFAULT_DISPLAY_TIME, null);
    }

    /**
     * Creates a toast that will be displayed for a certain amount of time.
     *
     * @param text        The text to display to the user
     * @param displayTime The amount of time to display the toast for
     */
    public static BasicToast create(Text text, long displayTime) {
        return new BasicToast(text, displayTime, null);
    }

    /**
     * Creates a toast that will be displayed for a certain amount of time.
     *
     * @param text        The text to display to the user
     * @param displayTime The amount of time to display the toast for
     */
    public static BasicToast create(String text, long displayTime) {
        return new BasicToast(new LiteralText(text), displayTime, null);
    }

    /**
     * Creates a toast that has a handler that will be called when the toast is dismissing.
     *
     * @param text         The text to display to the user
     * @param decayHandler The handler to call when the toast is dismissing
     */
    public static BasicToast create(Text text, ToastDecayHandler decayHandler) {
        return new BasicToast(text, DEFAULT_DISPLAY_TIME, decayHandler);
    }

    /**
     * Creates a toast that has a handler that will be called when the toast is dismissing.
     *
     * @param text         The text to display to the user
     * @param decayHandler The handler to call when the toast is dismissing
     */
    public static BasicToast create(String text, ToastDecayHandler decayHandler) {
        return new BasicToast(new LiteralText(text), DEFAULT_DISPLAY_TIME, decayHandler);
    }

    /**
     * Creates a toast that will be displayed for a certain amount of time and has a handler that will be called when
     * the toast is dismissing.
     *
     * @param text         The text to display to the user
     * @param decayHandler The handler to call when the toast is dismissing
     * @param displayTime  The amount of time to display the toast for
     */
    public static BasicToast create(Text text, long displayTime, ToastDecayHandler decayHandler) {
        return new BasicToast(text, displayTime, decayHandler);
    }

    /**
     * Creates a toast that will be displayed for a certain amount of time and has a handler that will be called when
     * the toast is dismissing.
     *
     * @param text         The text to display to the user
     * @param decayHandler The handler to call when the toast is dismissing
     * @param displayTime  The amount of time to display the toast for
     */
    public static BasicToast create(String text, long displayTime, ToastDecayHandler decayHandler) {
        return new BasicToast(new LiteralText(text), displayTime, decayHandler);
    }

    @Override
    public void show() {
        this.didExecuteDecayHandler = false;
        super.show();
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        super.drawBackground(matrices, manager);
        manager.getClient().textRenderer.draw(matrices, text, 7, 7, 0xFFFFFF);

        if (startTime >= displayTime) {
            if (!didExecuteDecayHandler && decayHandler != null) {
                decayHandler.onDecay();
                didExecuteDecayHandler = true;
            }

            return Visibility.HIDE;
        }

        return Visibility.SHOW;
    }
}
