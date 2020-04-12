package me.pikod.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;

public class Bot {
	public class Update extends Thread {
		@Override
		public void run() {
			
		}
	}
	
	public static void main(String args[]) {
		System.out.println("Sistem çalýþtýrýlýyor.");
		
		DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "688370093599621260";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = new club.minnced.discord.rpc.DiscordEventHandlers.OnReady() {
			
			@Override
			public void accept(DiscordUser arg0) {
				System.out.println("Sistem çalýþtýrýldý!");
			}
		}; 
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.largeImageKey = "vendetta";
        presence.details = "Pikod.exe baþlatýlýyor...";
        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        
        new Thread(new Runnable() {
			int q = 0;
			@Override
			public void run() {
				while(true) {
			        presence.details = "Pikod.exe yanýt vermiyor!";
					switch(q) {
						case 0:
							presence.largeImageKey = "instagram";
							presence.largeImageText = "Instagram";
							presence.details = "Instagram | @hzpikod";
							q++;
							break;
						case 1:
							presence.largeImageKey = "facebook";
							presence.largeImageText = "Facebook";
							presence.details = "Facebook | Yahya Batulu";
							q++;
							break;
						case 2:
							presence.largeImageKey = "github";
							presence.largeImageText = "GitHub";
							presence.details = "GitHub | ByPikod";
							q++;
							break;
						case 3:
							presence.largeImageKey = "steam";
							presence.largeImageText = "Steam Community";
							presence.details = "Steam | Pikod";
							q++;
							break;
						case 4:
							presence.largeImageKey = "twitter";
							presence.largeImageText = "Twitter";
							presence.details = "Twitter | @hzpikod";
							q++;
							break;
						case 5:
							presence.largeImageKey = "youtube";
							presence.largeImageText = "YouTube";
							presence.details = "YouTube | Pikod";
							q++;
							break;
						case 6:
							presence.largeImageKey = "twitch";
							presence.largeImageText = "Twitch";
							presence.details = "Twitch | hzpikod";
							q = 0;
							break;
							
					}

			        presence.endTimestamp = (System.currentTimeMillis() / 1000) + (43200 - 2928);
					lib.Discord_UpdatePresence(presence);
					try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException ignored) {}
				}
			}
		}).start();
        
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
            	lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
        
	}
	
}
	