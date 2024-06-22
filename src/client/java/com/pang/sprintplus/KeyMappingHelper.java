package com.pang.sprintplus;

import org.lwjgl.glfw.GLFW;

import com.pang.sprintplus.mixin.client.StickyKeyBindingUntoggleInvoker;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyMappingHelper {
    private final static String TRANS_KEY_KEY = "key.sprintPlus.";

    private static boolean shouldSprint = false;

    public static void register() {
        KeyBinding sprintPlus = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TRANS_KEY_KEY + "sprintPlusKey", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, KeyBinding.MOVEMENT_CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (sprintPlus.isPressed() && !shouldSprint) {
                    shouldSprint = true;
                }

                if (!client.options.forwardKey.isPressed() && shouldSprint) {
                    shouldSprint = false;
                }

                if (shouldSprint) {
                    if (!client.player.isSprinting() && !client.options.sprintKey.isPressed()) {
                        client.options.sprintKey.setPressed(shouldSprint);
                    }
                } else {
                    ((StickyKeyBindingUntoggleInvoker) client.options.sprintKey).involkeUntoggle();
                }
            }
        });
    }

}
