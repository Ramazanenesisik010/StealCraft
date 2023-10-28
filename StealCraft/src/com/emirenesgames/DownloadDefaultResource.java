package com.emirenesgames;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;

public class DownloadDefaultResource {
	
	public File path;
	
	public DownloadDefaultResource(File path) {
		this.path = path;
	}
	
	private ArrayList<URL> urls = new ArrayList<URL>();
	
	public URL getURL(int i) {
		return (URL) urls.get(i);
	}
	
	
	public void run() {
		for(int i = 0; i< urls.size(); i++) {
			URL url = urls.get(i);
			String[] as = url.getFile().split("/");
			String filename = as[as.length - 1];
			System.out.println("Downloading Resources");
			System.out.println("File: " + new File(path,filename) + ", URL: " + url.getHost() + url.getFile());
			try {
				this.downloadResource(url, new File(path,filename), i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error " + e.getMessage() + ", Path: " + DownloadDefaultResource.class.getResource("textures/basicTexture.zip"));
			}
			
		}
	}
	
	public void registerURL(URL url) {
		if(url != null) {
			urls.add(url);
		}
	}
	
	public void downloadResource(URL par1URL, File par2File, long par3) throws IOException
    {
        byte[] var5 = new byte[4096];
        DataInputStream var6 = new DataInputStream(par1URL.openStream());
        DataOutputStream var7 = new DataOutputStream(new FileOutputStream(par2File));
        boolean var8 = false;

        do
        {
            int var9;

            if ((var9 = var6.read(var5)) < 0)
            {
                var6.close();
                var7.close();
                return;
            }

            var7.write(var5, 0, var9);
        }
        while (!false);
    }

}
