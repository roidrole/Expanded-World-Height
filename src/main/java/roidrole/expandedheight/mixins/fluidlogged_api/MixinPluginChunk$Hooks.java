package roidrole.expandedheight.mixins.fluidlogged_api;

import com.llamalad7.mixinextras.sugar.Local;
import git.jbredwards.fluidlogged_api.mod.asm.iface.IHardcodedCapability;
import git.jbredwards.fluidlogged_api.mod.asm.plugins.vanilla.world.PluginChunk;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import roidrole.expandedheight.fluidlogged.ExtendedHeightFluidStateCapability;

import javax.annotation.Nonnull;

@Mixin(PluginChunk.Hooks.class)
public abstract class MixinPluginChunk$Hooks {
	/**
	 * @author roidrole
	 * @reason Use custom capability
	 */
	@Overwrite(remap = false)
	public static void createCapabilityInstance(@Nonnull Chunk chunk) {
		((IHardcodedCapability)chunk).setFluidStateCapability(new ExtendedHeightFluidStateCapability(chunk));
	}

	@ModifyConstant(
		method = "generateFluidStates",
		constant = @Constant(intValue = 256),
		remap = false
	)
	private static int useWorldHeight(int constant, @Local(argsOnly = true) Chunk chunk){
		return chunk.getWorld().getHeight();
	}
}
