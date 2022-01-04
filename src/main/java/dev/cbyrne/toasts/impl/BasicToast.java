package dev.cbyrne.toasts.impl;

import dev.cbyrne.toasts.ToastBase;
import dev.cbyrne.toasts.handler.ToastDecayHandler;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * A basic toast that can be used to display a text message to the player.
 */
public class BasicToast extends ToastBase {
    private final Text title;
    private final Text text;
    private final ToastDecayHandler decayHandler;
    private final Identifier icon;
    private final long displayTime;

    private boolean didExecuteDecayHandler = false;

    /**
     * A toast that will be displayed for a certain amount of time and has a handler that will be called when the toast
     * is dismissing.
     *
     * @param title        The title of your toast
     * @param description  The description of your toast
     * @param decayHandler The handler to call when the toast is dismissing
     * @param displayTime  The amount of time to display the toast for
     */
    public BasicToast(Text title, Text description, long displayTime, ToastDecayHandler decayHandler, Identifier icon) {
        this.title = title.shallowCopy().setStyle(title.getStyle().withBold(true));
        this.text = description;
        this.displayTime = displayTime;
        this.decayHandler = decayHandler;
        this.icon = icon;
    }

    @Override
    public void show() {
        this.didExecuteDecayHandler = false;
        super.show();
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        super.drawBackground(matrices, manager);

        if (this.icon != null)
            super.drawTexture(icon, matrices, manager, 7, 7, 18, 18);

        var textX = this.icon == null ? 7 : 30;
        if (this.title != null)
            super.drawText(matrices, title, textX, 0xFFFFFF);

        super.drawText(matrices, text, textX, 0xFFFFFF);

        if (startTime >= displayTime) {
            if (!didExecuteDecayHandler && decayHandler != null) {
                decayHandler.onDecay();
                didExecuteDecayHandler = true;
            }

            return Visibility.HIDE; // The imposter is hiding from us... ඞ
        }

        return Visibility.SHOW;
    }
}
