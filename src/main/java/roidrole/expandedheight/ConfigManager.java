package roidrole.expandedheight;

//To be turned into actual configurations. To be determined if this can still be per-world...
public class ConfigManager {
	public static int heightMask = 0xFF;
	// The max volume of a chunk primer. Should be 16×16×max_height
	public static int primerSize = 65536;
	public static int primerYBits = 8;
	public static int primerHeight = 256;
	// This is common to all worlds. Use only in NetServerPlayHandler. Deeper depths uses max unconditionally
	public static int buildLimit = 255;
}
