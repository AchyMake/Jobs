package org.achymake.jobs.handlers;

import org.achymake.jobs.Jobs;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockHandler {
    private PersistentDataContainer getData(Block block) {
        return block.getWorld().getPersistentDataContainer();
    }
    public int getAge(Block block) {
        if (block.getBlockData() instanceof Ageable ageable) {
            return ageable.getAge();
        } else return 0;
    }
    public int getMaxAge(Block block) {
        if (block.getBlockData() instanceof Ageable ageable) {
            return ageable.getMaximumAge();
        } else return 0;
    }
    public boolean isRightAge(Block block) {
        return getAge(block) >= getMaxAge(block);
    }
    public void setPlaced(Block block) {
        var data = getData(block);
        var value = block.getX() + "_" + block.getY() + "_" + block.getZ();
        data.set(Jobs.getInstance().getKey(value),PersistentDataType.BOOLEAN, true);
    }
    public void removePlaced(Block block) {
        var data = getData(block);
        var value = block.getX() + "_" + block.getY() + "_" + block.getZ();
        data.remove(Jobs.getInstance().getKey(value));
    }
    public boolean isPlaced(Block block) {
        var data = getData(block);
        var value = block.getX() + "_" + block.getY() + "_" + block.getZ();
        return data.has(Jobs.getInstance().getKey(value));
    }
}