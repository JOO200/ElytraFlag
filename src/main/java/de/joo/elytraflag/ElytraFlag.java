package de.joo.elytraflag;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.BukkitPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by JOO200 on 30.10.2017.
 * *
 * *
 * This file is part of ElytraFlag.
 * Copyright (C) 2017
 * *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

public class ElytraFlag extends FlagValueChangeHandler<StateFlag.State> {

    public static final StateFlag ELYTRA_FLAG = new StateFlag("elytra-use", true);
    public static final Handler.Factory<ElytraFlag> FACTORY = new Handler.Factory<ElytraFlag>() {
        @Override
        public ElytraFlag create(Session session) {
            return new ElytraFlag(session);
        }
    };

    public ElytraFlag(Session session) {
        super(session, ELYTRA_FLAG);
    }


    @Override
    public boolean onCrossBoundary(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Set<ProtectedRegion> entered, Set<ProtectedRegion> exited, MoveType moveType) {
        if(player.hasPermission("elytraflag.bypass")) return true;
        if(toSet.queryValue(player, ELYTRA_FLAG) != StateFlag.State.DENY) return true;
        for(ProtectedRegion region:toSet) {
            if(region.isMember(player) || region.isOwner(player)) return true;
        }
        if(!(player instanceof BukkitPlayer)) {
            return true;
        }
        Player p = ((BukkitPlayer) player).getPlayer();
        if(p.isGliding()) {
            p.setGliding(false);
            BukkitAdapter.adapt(p).setOnGround(BukkitAdapter.adapt(p).getLocation());
            p.sendMessage(ElytraFlagPlugin.config.getEnterFly());
        }
        return true;
    }

    @Override
    protected void onInitialValue(LocalPlayer localPlayer, ApplicableRegionSet applicableRegionSet, StateFlag.State state) {

    }

    @Override
    protected boolean onSetValue(LocalPlayer localPlayer, Location location, Location location1, ApplicableRegionSet applicableRegionSet, StateFlag.State state, StateFlag.State t1, MoveType moveType) {
        return true;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer localPlayer, Location location, Location location1, ApplicableRegionSet applicableRegionSet, StateFlag.State state, MoveType moveType) {
        return true;
    }
}
