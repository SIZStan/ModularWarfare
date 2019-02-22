package com.modularwarfare.client.model.animations;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.modularwarfare.api.WeaponAnimation;
import com.modularwarfare.client.anim.AnimStateMachine;
import com.modularwarfare.client.anim.ReloadType;
import com.modularwarfare.client.anim.StateEntry;
import com.modularwarfare.client.anim.StateEntry.MathType;
import com.modularwarfare.client.anim.StateType;
import com.modularwarfare.client.model.ModelGun;

import net.minecraft.util.math.MathHelper;

public class AnimationPistol extends WeaponAnimation {
	
	public AnimationPistol()
	{
		//ammoLoadOffset = new Vector3f(0, -0.35F, 0);
		tiltGunTime = 0.15F;
		unloadAmmoTime = 0.35F;
		loadAmmoTime = 0.35F;
		untiltGunTime = 0.15F;
	}
	
	@Override
	public void onGunAnimation(float tiltProgress, AnimStateMachine animation)
	{
		//Translate X - Forwards/Backwards
		GL11.glTranslatef(0.2F * tiltProgress, 0F, 0F);
		//Translate Y - Up/Down
		GL11.glTranslatef(0F, 0.1F * tiltProgress, 0F);
		//Translate Z - Left/Right
		GL11.glTranslatef(0F, 0F, -0.1F * tiltProgress);
		//Rotate X axis - Rolls Left/Right
		GL11.glRotatef(20F * tiltProgress, 1F, 0F, 0F);
		//Rotate Y axis - Angle Left/Right
		GL11.glRotatef(-10F * tiltProgress, 0F, 1F, 0F);
		//Rotate Z axis - Angle Up/Down
		GL11.glRotatef(25F * tiltProgress, 0F, 0F, 1F);
	}
	

	@Override
	public void onAmmoAnimation(ModelGun gunModel, float ammoProgress, int reloadAmmoCount, AnimStateMachine animation)
	{
		//System.out.println(ammoProgress);
		float multiAmmoPosition = ammoProgress * 1;
		int bulletNum = MathHelper.floor(multiAmmoPosition);
		float bulletProgress = multiAmmoPosition - bulletNum;
			
		//Translate X - Forwards/Backwards
		GL11.glTranslatef(ammoProgress * -0.75F, 0F, 0F);
		//Translate Y - Up/Down
		GL11.glTranslatef(0F, ammoProgress * -8F, 0F);
		//Translate Z - Left/Right
		GL11.glTranslatef(0F, 0F, ammoProgress * 0F);
		//Rotate X axis - Rolls Left/Right
		GL11.glRotatef(30F * ammoProgress, 1F, 0F, 0F);
		//Rotate Y axis - Angle Left/Right
		GL11.glRotatef(0F * ammoProgress, 0F, 1F, 0F);
		//Rotate Z axis - Angle Up/Down
		GL11.glRotatef(-20F * ammoProgress, 0F, 0F, 1F);
	}
	
	@Override
	public ArrayList<StateEntry> getReloadStates(ReloadType reloadType, int reloadCount)
	{
		ArrayList<StateEntry> states = new ArrayList<StateEntry>();
		states.add(new StateEntry(StateType.Tilt, 0.20f, 0f, MathType.Add));
		states.add(new StateEntry(StateType.Unload, 0.20f, 0f, MathType.Add));
		states.add(new StateEntry(StateType.Load, 0.20f, 1f, MathType.Sub));
		states.add(new StateEntry(StateType.Untilt, 0.20f, 1f, MathType.Sub));
		states.add(new StateEntry(StateType.Charge, 0.18f, 1f, MathType.Sub));
		states.add(new StateEntry(StateType.Uncharge, 0.02f, 0f, MathType.Add));
		return states;
	}

}
