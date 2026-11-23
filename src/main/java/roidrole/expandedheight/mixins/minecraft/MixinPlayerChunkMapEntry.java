package roidrole.expandedheight.mixins.minecraft;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import roidrole.expandedheight.config.ConfigManager;

@Mixin(PlayerChunkMapEntry.class)
public abstract class MixinPlayerChunkMapEntry {
	@Shadow
	@Final
	private PlayerChunkMap playerChunkMap;

	@Shadow
	private int changes;
	@Shadow
	@Final
	private ChunkPos pos;

	@Shadow
	public abstract void sendPacket(Packet<?> packetIn);

	@Unique
	private int[] expandedheight_changedBlock = new int[64];

	@ModifyConstant(
		method = {"sendToPlayer", "sendToPlayers"},
		constant = @Constant(
			intValue = 65535
		)
	)
	private int useActualHeight(int constant){
		return ConfigManager.getHeightSectionMask(this.playerChunkMap.getWorldServer().getHeight());
	}

	//Use an int array to have more bits so height can be packed in 24 bits instead of 8
	@Inject(
		method = "blockChanged",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/server/management/PlayerChunkMapEntry;changedSectionFilter:I",
			shift = At.Shift.AFTER,
			opcode = Opcodes.PUTFIELD
		),
		cancellable = true
	)
	private void useIntArray(int x, int y, int z, CallbackInfo ci){
		//Allow 24 bits for y and use ints instead of short
		int packed = x << 28 | z << 24 | y;

		for (int i = 0; i < this.changes; ++i)
		{
			if (this.expandedheight_changedBlock[i] == packed)
			{
				ci.cancel();
				return;
			}
		}
		if (this.changes == this.expandedheight_changedBlock.length)
			this.expandedheight_changedBlock = java.util.Arrays.copyOf(this.expandedheight_changedBlock, this.expandedheight_changedBlock.length << 1);
		this.expandedheight_changedBlock[this.changes++] = packed;
		ci.cancel();
	}

	@Redirect(
		method = "update",
		at = @At(
			value = "NEW",
			target = "(III)Lnet/minecraft/util/math/BlockPos;",
			ordinal = 0
		)
	)
	private BlockPos readIntArray(int x, int y, int z){
		int i = (this.expandedheight_changedBlock[0] >> 28 & 0xF) + this.pos.x * 16;
		int j = this.expandedheight_changedBlock[0] & 0xFFFFFF;
		int k = (this.expandedheight_changedBlock[0] >> 24 & 0xF) + this.pos.z * 16;
		return new BlockPos(i, j, k);
	}

	@Redirect(
		method = "update",
		at = @At(
			value = "NEW",
			target = "(III)Lnet/minecraft/util/math/BlockPos;",
			ordinal = 1
		)
	)
	private BlockPos readIntArray2(int x, int y, int z, @Local(name = "l") int l){
		int i = (this.expandedheight_changedBlock[l] >> 28 & 0xF) + this.pos.x * 16;
		int j = this.expandedheight_changedBlock[l] & 0xFFFFFF;
		int k = (this.expandedheight_changedBlock[l] >> 24 & 0xF) + this.pos.z * 16;
		BlockPos pos = new BlockPos(i, j, k);
		this.sendPacket(new SPacketBlockChange(this.playerChunkMap.getWorldServer(), pos));
		return pos;
	}

	@Redirect(
		method = "update",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/management/PlayerChunkMapEntry;sendPacket(Lnet/minecraft/network/Packet;)V",
			ordinal = 2
		)
	)
	private void stopSendingPackets(PlayerChunkMapEntry instance, Packet<?> packetIn){
		//NO-OP, the packets are sent individually
	}


}
