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
package fr.neatmonster.nocheatplus.compat;

import fr.neatmonster.nocheatplus.utilities.PotionUtil;
import fr.neatmonster.nocheatplus.utilities.ReflectionUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Bridge1_13 {
    private static final PotionEffectType SLOWFALLING = PotionEffectType.getByName("SLOW_FALLING");
    private static final boolean hasIsRiptiding = ReflectionUtil.getMethodNoArgs(Player.class, "isRiptiding", boolean.class) != null;

    public static boolean hasSlowfalling() {
        return SLOWFALLING != null;
    }

    public static boolean hasIsRiptiding() {
        return hasIsRiptiding;
    }

    /**
     * Test for the 'slowfalling' potion effect.
     *
     * @param player
     * @return Double.NEGATIVE_INFINITY if not present.
     */
    public static double getSlowfallingAmplifier(final Player player) {
        if (SLOWFALLING == null) {
            return Double.NEGATIVE_INFINITY;
        }
        return PotionUtil.getPotionEffectAmplifier(player, SLOWFALLING);
    }

    public static boolean isRiptiding(final Player player) {
        return hasIsRiptiding ? player.isRiptiding() : false;
    }

}
