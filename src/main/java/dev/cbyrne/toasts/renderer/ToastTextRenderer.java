package dev.cbyrne.toasts.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

/**
 * A class which handles the rendering of multiple lines of text
 *
 * @see dev.cbyrne.toasts.ToastBase
 */
public class ToastTextRenderer {
    private final int initialTextY;
    private int currentTextY;

    public ToastTextRenderer(int textY) {
        this.currentTextY = textY;
        this.initialTextY = textY;
    }

    public void drawText(MatrixStack matrices, Text text, int x, int color) {
        var renderer = MinecraftClient.getInstance().textRenderer;
        renderer.draw(matrices, text, x, currentTextY, color);

        currentTextY += renderer.fontHeight + 1;
    }

    public void reset() {
        currentTextY = initialTextY;
    }
}
