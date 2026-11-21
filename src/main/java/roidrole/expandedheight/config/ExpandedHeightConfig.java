package roidrole.expandedheight.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import net.minecraftforge.common.config.Config;

@Config(modid = "expandedheight", name = "expandedheight/general")
public class ExpandedHeightConfig {
	public static int defaultWorldHeight = 256;

	static {
		ConfigAnytime.register(ExpandedHeightConfig.class);
	}
}
