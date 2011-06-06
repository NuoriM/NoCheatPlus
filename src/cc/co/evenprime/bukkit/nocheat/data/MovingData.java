package cc.co.evenprime.bukkit.nocheat.data;

import java.util.logging.Level;

import net.minecraft.server.Block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import cc.co.evenprime.bukkit.nocheat.NoCheatData;

public class MovingData {
	public int jumpPhase = 0;
	public int violationsInARow[] =  { 0, 0, 0 }; 
	public double horizFreedom = 0.0D;
	public double vertFreedom = 0.0D;
	public int vertFreedomCounter = 0;
	
	// setbackpoint is a recommendation - try to teleport to first solid block below it
	// for better effect
	public Location setBackPoint = null;
	
	public int summaryTask = -1;
	public Level highestLogLevel = null;
	public double maxYVelocity = 0.0D;
	public int sneakingFreedomCounter = 10;
	public double sneakingLastDistance = 0.0D;

	public int worldChanged = 0;
	public boolean respawned = false;

	// WORKAROUND for changed PLAYER_MOVE logic
	public Location teleportTo = null;
	public Location lastLocation = null;

	public Location teleportInitializedByMe = null;

	// Block types that may need to be treated specially
	public static final int NONSOLID = 0;     // 0x00000000
	public static final int SOLID = 1;        // 0x00000001
	public static final int LIQUID = 2;       // 0x00000010
	public static final int LADDER = 4;       // 0x00000100
	public static final int FENCE = 8;        // 0x00001000

	
	// Until I can think of a better way to determine if a block is solid or not, this is what I'll do
	public static final int types[] = new int[256];
	
	static {

		// Find and define properties of all blocks
		for(int i = 0; i < types.length; i++) {
			
			// Everything is considered nonsolid at first
			types[i] = NONSOLID;
			
			if(Block.byId[i] != null) {
				if(Block.byId[i].material.isSolid()) {
					// solid blocks like STONE, CAKE, TRAPDOORS
					types[i] = SOLID;
				}
				else if(Block.byId[i].material.isLiquid()){
					// WATER, LAVA
					types[i] = LIQUID;
				}
			}
		}
		
		// Special types just for me
		types[Material.LADDER.getId()]= LADDER | SOLID;
		types[Material.FENCE.getId()]= FENCE | SOLID;
	}
	
	public static MovingData get(final Player p) {

		final NoCheatData data = NoCheatData.getPlayerData(p);

		if(data.moving == null) {
			data.moving = new MovingData();
			data.moving.lastLocation = p.getLocation();
		}

		return data.moving;
	}

}
