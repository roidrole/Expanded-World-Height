package roidrole.expandedheight.mixins.minecraft;

import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerChunkMapEntry.class)
public class MixinPlayerChunkMapEntry {
	@Shadow
	@Final
	private PlayerChunkMap playerChunkMap;

	@ModifyConstant(
		method = {"sendToPlayer", "sendToPlayers"},
		constant = @Constant(
			intValue = 65535
		)
	)
	private int useActualHeight(int constant){
		return this.playerChunkMap.getWorldServer().getHeight() * 256 - 1;
	}
}
