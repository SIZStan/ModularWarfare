package com.modularwarfare;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import com.google.gsonapi.Gson;
import com.google.gsonapi.GsonBuilder;
import com.google.gsonapi.stream.JsonReader;

public class ModConfig {
	
	public transient static ModConfig INSTANCE;
	
	public boolean detailedSkins = true;
	public boolean hitmarkerenabled = true;
	public boolean applyKnockback = false;

	public ModConfig(File configFile)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			if(configFile.exists())
			{
				JsonReader jsonReader = new JsonReader(new FileReader(configFile));
				INSTANCE = gson.fromJson(jsonReader, ModConfig.class);
			} else
			{
				try (Writer writer = new FileWriter(configFile)) 
				{
					gson.toJson(this, writer);
			    }
				INSTANCE = this;
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
