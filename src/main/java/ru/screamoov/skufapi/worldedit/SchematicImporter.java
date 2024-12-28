package ru.screamoov.skufapi.worldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class SchematicImporter {
    private File sch;

    public SchematicImporter(File schematic) {
        this.sch = schematic;
    }

    public String getName() {
        return this.sch.getName().substring(0, this.getSchematic().getName().length() - 10);
    }

    public File getSchematic() {
        return this.sch;
    }

    public void load(Location loc) {
        Object es;
        try {
            es = Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats").getMethod("findByFile", File.class).invoke(Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats"), this.getSchematic());
            Method m = es.getClass().getMethod("getReader", InputStream.class);
            m.setAccessible(true);
            Object reader = m.invoke(es, new FileInputStream(this.getSchematic()));
            Object clipboard = reader.getClass().getMethod("read").invoke(reader);
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession((World) Class.forName("com.sk89q.worldedit.bukkit.BukkitAdapter").getMethod("adapt", org.bukkit.World.class).invoke(Class.forName("com.sk89q.worldedit.bukkit.BukkitAdapter"), loc.getWorld()), -1);
            Object operation = Class.forName("com.sk89q.worldedit.session.ClipboardHolder").getConstructor(Clipboard.class).newInstance(clipboard);
            operation = operation.getClass().getMethod("createPaste", Extent.class).invoke(operation, editSession);
            Object vector = Class.forName("com.sk89q.worldedit.math.BlockVector3").getMethod("at", Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(Class.forName("com.sk89q.worldedit.math.BlockVector3"), (int) loc.getX(), (int) loc.getY(), (int) loc.getZ());
            operation = operation.getClass().getMethod("to", Class.forName("com.sk89q.worldedit.math.BlockVector3")).invoke(operation, vector);
            operation = operation.getClass().getMethod("ignoreAirBlocks", Boolean.TYPE).invoke(operation, false);
            operation = operation.getClass().getMethod("build").invoke(operation);
            Class.forName("com.sk89q.worldedit.function.operation.Operations").getMethod("complete", Class.forName("com.sk89q.worldedit.function.operation.Operation")).invoke(Class.forName("com.sk89q.worldedit.function.operation.Operations"), operation);
            editSession.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    public void load(Location loc, Player p) {
        Object es;
        try {
            es = Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats").getMethod("findByFile", File.class).invoke(Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats"), this.getSchematic());
            Method m = es.getClass().getMethod("getReader", InputStream.class);
            m.setAccessible(true);
            Object reader = m.invoke(es, new FileInputStream(this.getSchematic()));
            Object clipboard = reader.getClass().getMethod("read").invoke(reader);
            this.load(loc);
        } catch (Exception var9) {
            var9.printStackTrace();
        }
    }
}