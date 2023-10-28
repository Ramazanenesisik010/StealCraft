package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import paulscode.sound.codecs.CodecJOrbis;

public class CodecMus extends CodecJOrbis
{
    protected InputStream openInputStream()
    {
        try
        {
            return new MusInputStream(this, new URL("https://minecraft.net"), new URL("https://minecraft.net").openStream());
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
