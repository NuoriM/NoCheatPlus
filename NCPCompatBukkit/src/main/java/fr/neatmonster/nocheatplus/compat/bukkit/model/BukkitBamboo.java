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

import java.text.DecimalFormat;

public class BukkitBamboo implements BukkitShapeModel {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.000");
    private static final double INSET = 0.375;

    @Override
    public double[] getShape(final BlockCache blockCache, final World world, final int x, final int y, final int z) {
        final Block block = world.getBlockAt(x, y, z);
        double minX = Math.abs(block.getBoundingBox().getMinX()) - Math.abs(block.getLocation().getBlockX());
        double minZ = Math.abs(block.getBoundingBox().getMinZ()) - Math.abs((int) block.getBoundingBox().getMinZ());
        minX = Double.parseDouble(DECIMAL_FORMAT.format(minX));
        minZ = Double.parseDouble(DECIMAL_FORMAT.format(minZ));
        return new double[]{minX + 0.09, 0.0, 1.0 - minZ + 0.09, minX + INSET - 0.09, 1.0, 1.0 - minZ + INSET - 0.09};
    }

    @Override
    public int getFakeData(final BlockCache blockCache, final World world, final int x, final int y, final int z) {
        return 0;
    }

}
