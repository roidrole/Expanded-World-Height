package roidrole.expandedheight.fluidlogged;

import git.jbredwards.fluidlogged_api.api.capability.IFluidStateContainer;
import git.jbredwards.fluidlogged_api.api.util.FluidState;
import it.unimi.dsi.fastutil.chars.CharSet;
import it.unimi.dsi.fastutil.chars.CharSets;

import javax.annotation.Nonnull;

public final class EmptyFluidStateContainer implements IFluidStateContainer {
	public static final EmptyFluidStateContainer INSTANCE = new EmptyFluidStateContainer();

	private EmptyFluidStateContainer(){}

	@Override
	public boolean hasFluidState(char c) {
		return false;
	}

	@Nonnull
	@Override
	public FluidState getFluidState(char c, @Nonnull FluidState fluidState) {
		return FluidState.EMPTY;
	}

	@Override
	public boolean setFluidState(char c, @Nonnull FluidState fluidState) {
		return false;
	}

	@Override
	public void clearFluidStates() {

	}

	@Nonnull
	@Override
	public CharSet getSerializedPositions() {
		return CharSets.EMPTY_SET;
	}

	@Override
	public void forEach(@Nonnull ContainerAction containerAction) {

	}

	@Override
	public int serializeX(int i) {
		return 0;
	}

	@Override
	public int serializeY(int i) {
		return 0;
	}

	@Override
	public int serializeZ(int i) {
		return 0;
	}

	@Override
	public int deserializeX(char c) {
		return 0;
	}

	@Override
	public int deserializeY(char c) {
		return 0;
	}

	@Override
	public int deserializeZ(char c) {
		return 0;
	}
}
