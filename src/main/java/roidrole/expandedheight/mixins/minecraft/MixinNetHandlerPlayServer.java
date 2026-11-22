package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetHandlerPlayServer.class)
public abstract class MixinNetHandlerPlayServer {
	@Shadow
	public EntityPlayerMP player;

	@Redirect(
		method = {
			"processPlayerDigging",
			"processTryUseItemOnBlock"
		},
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/MinecraftServer;getBuildLimit()I"
		)
	)
	private int getWorldHeight(MinecraftServer instance){
		return instance.getWorld(this.player.dimension).getHeight();
	}
}
