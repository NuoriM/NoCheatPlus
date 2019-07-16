/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.neatmonster.nocheatplus.compat.bukkit.model;

import fr.neatmonster.nocheatplus.utilities.map.BlockCache;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Lantern;

public class BukkitLantern implements BukkitShapeModel {

    private static final double INSET = 0.33;

    @Override
    public double[] getShape(final BlockCache blockCache, final World world, final int x, final int y, final int z) {
        final Block block = world.getBlockAt(x, y, z);
        final BlockData blockData = block.getBlockData();
        if (blockData instanceof Lantern && ((Lantern) blockData).isHanging()) {
            return new double[]{INSET, 0.0625, INSET, 1 - INSET, 1, 1 - INSET};
        }
        return new double[]{INSET, 0.0, INSET, 1.0 - INSET, 0.5625, 1.0 - INSET};
    }

    @Override
    public int getFakeData(final BlockCache blockCache, final World world, final int x, final int y, final int z) {
        return 0;
    }

}
