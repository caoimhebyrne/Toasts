package dev.cbyrne.toasts.impl.builder;

import dev.cbyrne.toasts.handler.ToastDecayHandler;
import dev.cbyrne.toasts.impl.BasicToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * A builder for the {@link BasicToast} class.
 * <p>
 * Note: you must call {@link BasicToastBuilder#title(Text)} or {@link BasicToastBuilder#title(String)}.
 * <p>
 * Note: you must also call {@link BasicToastBuilder#description(Text)} or {@link BasicToastBuilder#description(String)}
 *
 * @see BasicToast
 */
public class BasicToastBuilder {
    private static final long DEFAULT_DISPLAY_TIME = 5000;

    private Text _title;
    private Text _description;
    private long _displayTime = DEFAULT_DISPLAY_TIME;
    private ToastDecayHandler _decayHandler;
    private Identifier _icon;

    public BasicToast build() {
        return new BasicToast(_title, _description, _displayTime, _decayHandler, _icon);
    }

    /**
     * The title of your toast
     *
     * @see BasicToast
     */
    public BasicToastBuilder title(String title) {
        this._title = new LiteralText(title);
        return this;
    }

    /**
     * The title of your toast
     *
     * @see BasicToast
     */
    public BasicToastBuilder title(Text title) {
        this._title = title;
        return this;
    }

    /**
     * The description of your toast
     *
     * @see BasicToast
     */
    public BasicToastBuilder description(@NotNull String description) {
        this._description = new LiteralText(description);
        return this;
    }

    /**
     * The description of your toast
     *
     * @see BasicToast
     */
    public BasicToastBuilder description(@NotNull Text description) {
        this._description = description;
        return this;
    }

    /**
     * How long your toast will display for (default 5000ms)
     *
     * @see BasicToast
     */
    public BasicToastBuilder displayTime(long displayTime) {
        this._displayTime = displayTime;
        return this;
    }

    /**
     * Run code when your toast is 'decaying' (when the display time has been reached)
     *
     * @see BasicToast
     */
    public BasicToastBuilder decayHandler(ToastDecayHandler decayHandler) {
        this._decayHandler = decayHandler;
        return this;
    }

    /**
     * Set an icon for your toast, this must be a resource asset
     *
     * @see BasicToast
     * @see Identifier
     */
    public BasicToastBuilder icon(Identifier icon) {
        this._icon = icon;
        return this;
    }
}
