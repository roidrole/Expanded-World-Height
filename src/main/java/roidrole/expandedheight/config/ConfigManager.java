package roidrole.expandedheight.config;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

import java.io.*;

public class ConfigManager {
	// The max volume of a chunk primer. Should be 16×16×max_height
	public static final int primerVolume;
	public static final int primerYBits;
	public static final int primerHeight;

	// This is common to all worlds. Use only in NetServerPlayHandler. Deeper depths uses max unconditionally
	public static final int buildLimit;

	// Because of SPacketChunkData and PlayerChunkMapEntry, 512 is the limit
	// Because of Chunk NBT, 4096 is the limit
	// Because of BlockPos.toLong, 16384 is the limit
	public static final int maxHeight = 512;

	public static final Int2IntMap worldHeight;

	public static int getHeightMask(int height){
		if(height == 512){
			return 0xFFFFFFFF;
		}
		return (1 << (height/16)) - 1;
	}

	static {
		worldHeight = new Int2IntOpenHashMap();
		worldHeight.defaultReturnValue(ExpandedHeightConfig.defaultWorldHeight);
		int globalMaxHeight = -1;
		File heightFile = new File("config/expandedheight/heights.json");
		if(!heightFile.exists()){
			File tempFile;
			try {
				tempFile = File.createTempFile("heights", "json");
				tempFile.deleteOnExit();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try (Writer writer = new FileWriter(tempFile)) {
				JsonWriter jsonWriter = new JsonWriter(writer);
				jsonWriter.setIndent("\t");
				jsonWriter.beginObject();
				writer.append("\n\t//This file allows comments. These will not be regenerated if deleted, but won't harm the read if left");
				writer.append("\n\t//Here are overrides for per-dimension height.");
				writer.append("\n\t//Keys correspond to the dimension id, values to the maximal height");
				jsonWriter.name("0");
				jsonWriter.value(512);
				jsonWriter.endObject();
			} catch (IOException e){
				throw new RuntimeException(e);
			}
			try (
				FileInputStream srcStream = new FileInputStream(tempFile);
				FileOutputStream destStream = new FileOutputStream(heightFile)
			){
				destStream.getChannel().transferFrom(srcStream.getChannel(), 0, srcStream.getChannel().size());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}


			globalMaxHeight = ExpandedHeightConfig.defaultWorldHeight;
		} else {
			try (Reader reader = new FileReader(heightFile)) {
				JsonReader jsonReader = new JsonReader(reader);
				jsonReader.setLenient(true);
				jsonReader.beginObject();
				while (jsonReader.peek() != JsonToken.END_OBJECT) {
					int name = Integer.parseInt(jsonReader.nextName());
					int height = jsonReader.nextInt();
					if ((height & 0xF) != 0){
						System.err.println("Trying to set an invalid world height of " + height + "for dimension "+ name +"! Should be a multiple of 16!");
						height |= 0xF;
						height += 1;
					}
					if (height > maxHeight) {
						System.err.println("Trying to set an invalid world height of " + height + "for dimension "+ name +"! Capping it to " + maxHeight);
						height = maxHeight;
					}
					if (height > globalMaxHeight) {
						globalMaxHeight = height;
					}
					worldHeight.put(name, height);
				}
				jsonReader.endObject();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		primerHeight = globalMaxHeight;
		primerVolume = 256 * primerHeight;
		primerYBits = 32 - Integer.numberOfLeadingZeros(primerHeight - 1);
		buildLimit = globalMaxHeight;
	}
}
