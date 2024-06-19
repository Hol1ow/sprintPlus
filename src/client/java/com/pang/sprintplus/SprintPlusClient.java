package com.pang.sprintplus;

import net.fabricmc.api.ClientModInitializer;

public class SprintPlusClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//register keys
		KeyMappingHelper.register();
	}
}