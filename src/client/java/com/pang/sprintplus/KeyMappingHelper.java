package com.pang.sprintplus;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyMappingHelper {
    private final static String TRANS_KEY_KEY = "key.sprintPlus.";
    private final static String TRANS_KEY_CAT = "category.sprintPlus.text";

    private static boolean shouldSprint = false;
    private static boolean holdingSprint = false;

    public static void register() {
        KeyBinding sprintPlusToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TRANS_KEY_KEY + "sprintPlusToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, TRANS_KEY_CAT));

        KeyBinding sprintPlusHold = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TRANS_KEY_KEY + "sprintPlusHold", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, TRANS_KEY_CAT));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            //logic for toggle
            if (sprintPlusToggle.wasPressed()) {
                shouldSprint = true;
            }

            if (client.options.forwardKey.wasPressed()) {
                if (shouldSprint && client.player != null && shouldSprint != client.player.isSprinting()) {
                    client.player.setSprinting(shouldSprint);
                }
            } else {
                shouldSprint = false;
            }

            //logic for holding
            if (sprintPlusHold.wasPressed() != client.player.isSprinting()) {
                client.player.setSprinting(holdingSprint);
            }
        });
    }

}
