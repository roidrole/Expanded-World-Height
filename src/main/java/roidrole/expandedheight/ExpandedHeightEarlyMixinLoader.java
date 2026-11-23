package roidrole.expandedheight;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.Context;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExpandedHeightEarlyMixinLoader implements IFMLLoadingPlugin, IEarlyMixinLoader {
	@Override
	public List<String> getMixinConfigs() {
		return Arrays.asList(
			"mixins.expandedheight.early.vanilla.json",
			"mixins.expandedheight.early.fluidlogged_api.json"
		);
	}

	@Override
	public boolean shouldMixinConfigQueue(Context context) {
		String mixinConfig = context.mixinConfig();
		String modid = mixinConfig.substring("mixins.expandedheight.early.".length(), mixinConfig.length() - ".json".length());
		return modid.equals("vanilla") || context.isModPresent(modid);
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
