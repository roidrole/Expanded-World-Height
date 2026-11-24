package roidrole.expandedheight.mixins.fluidlogged_api;

import git.jbredwards.fluidlogged_api.mod.FluidloggedAPI;
import git.jbredwards.fluidlogged_api.mod.asm.iface.IHardcodedCapability;
import git.jbredwards.fluidlogged_api.mod.common.EventHandler;
import git.jbredwards.fluidlogged_api.mod.common.message.SMessageSyncFluidStates;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkWatchEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EventHandler.class)
public abstract class MixinEventHandler {
	@Inject(
		method = "syncChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V"
		),
		remap = false
	)
	private static void sendAllSections(ChunkWatchEvent.Watch event, CallbackInfo ci){
		Chunk chunk = event.getChunkInstance();
		//No need to check null because the context does that for me
		for (int y = 256; y < chunk.getWorld().getHeight(); y += 256) {
			FluidloggedAPI.WRAPPER.sendTo(
				new SMessageSyncFluidStates(chunk.x, y, chunk.z, ((IHardcodedCapability)chunk).getFluidStateCapability()),
				event.getPlayer()
			);
		}
	}
}
