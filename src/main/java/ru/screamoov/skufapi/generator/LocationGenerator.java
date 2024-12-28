package ru.screamoov.skufapi.generator;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public class LocationGenerator {
    public static Location generate(World world, int borders) {
        Random random = new Random();
        int x = random.nextBoolean() ? random.nextInt(borders) : -random.nextInt(borders);
        int z = random.nextBoolean() ? random.nextInt(borders) : -random.nextInt(borders);
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
}
