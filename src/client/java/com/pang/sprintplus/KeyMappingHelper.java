package com.pang.sprintplus;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyMappingHelper {
    private final static String TRANS_KEY_KEY = "key.sprintPlus.";
    private final static String TRANS_KEY_CAT = "key.categories.movement";

    private static boolean shouldSprint = false;
    private final static int INTERVAL = 2;
    private static int repeatance = 0;
    private static int delay = 0;

    public static void register() {
        KeyBinding sprintPlus = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TRANS_KEY_KEY + "sprintPlusKey", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, TRANS_KEY_CAT));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                // logic for toggle
                if (sprintPlus.isPressed()) {
                    shouldSprint = true;
                }

                if (client.options.forwardKey.isPressed()) {
                    if (shouldSprint && shouldSprint != client.player.isSprinting()) {
                        if (delay < 1) {
                            client.player.setSprinting(shouldSprint);
                        } else {
                            delay--;
                        }
                            
                        // redundancy check in case of serve desync
                        if (shouldSprint != client.player.isSprinting()) {
                            repeatance++;
                            delay = INTERVAL * repeatance;
                        } else {
                            repeatance = 0;
                        }
                    }
                } else {
                    shouldSprint = false;
                    //
                }
            }
        });
    }

}
