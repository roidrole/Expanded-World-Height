Things requiring design:
- mixinRenderGlobal: renderEntities. Should be fine, but needs testing
- AnvilChunkLoader: writeChunkToNBT and readChunkFromNBT: 
  - maybe be more aggressive, but have a toggle
  - Store chunks in another NBT tag at tail
- PlayerChunkMapEntry update → bit masking
- Actually fill in the provider to give the custom provider
- WorldClient playMoodSoundAndCheckLight → change gameplay effect. config?

Mod integrations (some should probably be delegated to world.getHeight in a PR/issue after release):
- Potential REID incompat of the chunk primer mixin maybe
- MixinIFluidStatePrimer for fluidlogged api
- Celeritas (see Depths Update fork by Mari)
- Nothirium
- OptiFine
- RLTweaker (likely a PR)