package com.pang.sprintplus;

import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public abstract class SprintToggleOption {

    private final static Text TOGGLE_KEY_TEXT = Text.translatable("options.key.toggle");
    private final static Text HOLD_KEY_TEXT = Text.translatable("options.key.hold");

    public final static SimpleOption<Boolean> sprintPlusToggle = new SimpleOption<Boolean>(
            "key.sprintPlus.sprintPlusKey", SimpleOption.emptyTooltip(),
            (optionText, value) -> {
                return value ? TOGGLE_KEY_TEXT : HOLD_KEY_TEXT;
            }, SimpleOption.BOOLEAN, true, (value) -> {
            });
}
