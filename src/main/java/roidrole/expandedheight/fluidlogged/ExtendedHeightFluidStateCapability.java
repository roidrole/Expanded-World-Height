package roidrole.expandedheight.fluidlogged;

import git.jbredwards.fluidlogged_api.api.capability.IFluidStateCapability;
import git.jbredwards.fluidlogged_api.api.capability.IFluidStateContainer;
import git.jbredwards.fluidlogged_api.mod.common.capability.FluidStateCapabilityVanilla;
import net.minecraft.nbt.*;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import roidrole.expandedheight.config.ConfigManager;

import javax.annotation.Nonnull;

public class ExtendedHeightFluidStateCapability implements IFluidStateCapability {
	private final FluidStateCapabilityVanilla[] parts;
	private final int offsetX;
	private final int offsetZ;


	public ExtendedHeightFluidStateCapability(Chunk chunk) {
		this.offsetX = chunk.x << 4;
		this.offsetZ = chunk.z << 4;
		World world = chunk.getWorld();
		int height;
		if(world == null){
			height = ConfigManager.primerHeight;
		} else {
			height = world.getHeight();
		}
		int partCount = ((height - 1)/256) + 1;
		this.parts = new FluidStateCapabilityVanilla[partCount];
		for (int i = 0; i < partCount; i++) {
			parts[i] = new FluidStateCapabilityVanilla(chunk.x, chunk.z);
		}
	}

	@Nonnull
	@Override
	public IFluidStateContainer getContainer(int i) {
		i = i >> 8;
		if(i < 0 || i >= parts.length){
			return EmptyFluidStateContainer.INSTANCE;
		}
		return parts[i];
	}

	@Override
	public NBTBase serializeNBT() {
		NBTTagList list = new NBTTagList();
		for (FluidStateCapabilityVanilla part : parts){
			list.appendTag(part.serializeNBT());
		}
		return list;
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		if(!(nbt instanceof NBTTagList)){
			return;
		}
		NBTTagList list = (NBTTagList) nbt;
		int length = list.tagCount();
		for (int i = 0; i < length; i++) {
			FluidStateCapabilityVanilla cap = new FluidStateCapabilityVanilla(this.offsetX, this.offsetZ);
			cap.deserializeNBT(list.get(i));
			parts[i] = cap;
		}
	}
}
