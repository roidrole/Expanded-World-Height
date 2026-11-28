## Expanded World Height

A mod that extends the vanilla world height. A more compatible alternative to Cubic Chunk and Depths Update

It achieves this compatibility with limitations compared to both other mods. Namely:
- Negative heights are out of scope
- No custom worldgen is in this mod
- Some mods will have support PRs

## Mod dev
You shouldn't need to depend on this mod for anything. Just make sure to use World.getHeight(), a vanilla method, instead of hard-coding 256 as the world height. The min height can still be assumed to be 0. If your mod needs to have higher worlds, you may depend on this mod. An API allowing to force per-dimension height will likely come in the future

## Modpack devs
For better results, you should consider overhauling worldgen to benefit from the additional world height. Worldgen mods tend to assume 255 to be the build limit. If they do, make an issue so they can get patched.

## Credits
- __sayys, for creating Depths Update, which was a reference on how to get higher worlds to work
- BriefMariGold, for adding a few patches to Depths Update
- MasterEnderman, for making the wonderful logo
- Karnatour, for helping out with Distant Horizons compat

All the people who tested the early builds of the mod and reported issues:
- Penteractgaming
- BriefMarigold 
- hydratedbean