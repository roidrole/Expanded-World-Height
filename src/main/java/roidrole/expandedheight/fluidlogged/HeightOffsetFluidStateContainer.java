package roidrole.expandedheight.fluidlogged;

import git.jbredwards.fluidlogged_api.mod.common.capability.FluidStateCapabilityVanilla;

public class HeightOffsetFluidStateContainer extends FluidStateCapabilityVanilla {
	private final int offset;

	public HeightOffsetFluidStateContainer(int chunkXIn, int chunkZIn, int chunkY) {
		super(chunkXIn, chunkZIn);
		this.offset = chunkY << 8;
	}

	@Override
	public int serializeY(int y) {
		return (y & 0xFF) << 8;
	}

	@Override
	public int deserializeY(char serializedPos) {
		return (serializedPos >> 8) | offset;
	}
}
