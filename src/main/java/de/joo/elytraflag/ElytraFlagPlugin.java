package de.joo.elytraflag;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.session.SessionManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Paths;

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
public class ElytraFlagPlugin extends JavaPlugin{
    public static ElytraFlagConfig config;
    @Override
    public void onEnable() {
        config = new ElytraFlagConfig(Paths.get(getDataFolder() + "/config.yml"));
        try {
            config.loadAndSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SessionManager manager = WorldGuard.getInstance().getPlatform().getSessionManager();
        if(manager == null) {
            getLogger().warning("Session Manager not found. Flag not registered");
            return;
        }
        manager.registerHandler(ElytraFlag.FACTORY, null);
    }

    @Override
    public void onLoad() {
        try {
            WorldGuard.getInstance().getFlagRegistry().register(ElytraFlag.ELYTRA_FLAG);
        } catch (FlagConflictException e) {
            getLogger().severe("Elytra Flag bereits registriert!");
            return;
        }
    }
}
