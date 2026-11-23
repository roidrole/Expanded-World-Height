package roidrole.expandedheight;

import zone.rong.mixinbooter.Context;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

public class ExpandedHeightLateMixinLoader implements ILateMixinLoader {
	@Override
	public List<String> getMixinConfigs() {
		return Arrays.asList(
			"mixins.expandedheight.celeritas.json",
			"mixins.expandedheight.fbp.json",
			"mixins.expandedheight.nothirium.json",
			"mixins.expandedheight.rltweaker.json",
			"mixins.expandedheight.rtg.json"
		);
	}

	@Override
	public boolean shouldMixinConfigQueue(Context context) {
		String mixinConfig = context.mixinConfig();
		String modid = mixinConfig.substring("mixins.expandedheight.".length(), mixinConfig.length() - ".json".length());
		return context.isModPresent(modid);
	}
}
