package com.pang.sprintplus.mixin.client;

import net.minecraft.client.option.StickyKeyBinding;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StickyKeyBinding.class)
public interface StickyKeyBindingUntoggleInvoker {
	// allow access to untoggle method in order to untoggle sticky key
	@Invoker("untoggle")
	public void involkeUntoggle();
}