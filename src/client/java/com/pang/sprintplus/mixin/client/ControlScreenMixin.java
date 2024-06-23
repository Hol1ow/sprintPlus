package com.pang.sprintplus.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.pang.sprintplus.SprintToggleOption;

import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.OptionListWidget;

@Mixin(ControlsOptionsScreen.class)
public abstract class ControlScreenMixin{

    @Shadow
    private OptionListWidget optionListWidget;
    
    @Inject(at = @At("RETURN"), method = "init()V")
    protected void onInit(CallbackInfo ci) {
        optionListWidget.addAll(SprintToggleOption.sprintPlusToggle);
    }
    
}
