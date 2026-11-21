package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
	/**
	 * @author roidrole
	 * @reason Use per-world configurable max build height
	 */
	@Overwrite
	public int getBuildLimit() {
		return ConfigManager.buildLimit;
	}
}
