package net.minecraft.src;

import java.util.concurrent.Callable;

import com.emirenesgames.ConfigClass;

class CallableMinecraftVersion implements Callable
{
    /** Reference to the CrashReport object. */
    final CrashReport theCrashReport;

    CallableMinecraftVersion(CrashReport par1CrashReport)
    {
        this.theCrashReport = par1CrashReport;
    }

    /**
     * The current version of Minecraft
     */
    public String minecraftVersion()
    {
        return ConfigClass.VERSION;
    }

    public Object call()
    {
        return this.minecraftVersion();
    }
}
