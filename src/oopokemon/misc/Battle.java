package oopokemon.misc;

import oopokemon.occupier.Enemy;
import oopokemon.occupier.EnemyHandler;
import oopokemon.occupier.Player;
import oopokemon.skill.*;
import oopokemon.species.Engimon;

import java.util.List;
import java.util.Random;

public class Battle {
	public static Player battle(Player myplayer, EnemyHandler enemyHandler) {
		if (myplayer.getEngimon() == null) {
			AlertBox.display("Battle", "Tidak memakai active engimon");
			return myplayer;
		}
		Engimon engimonMusuh = myplayer.getClosestEnemy();
		if (engimonMusuh != null) {
//			ulanglagi:
			Engimon myengimon = myplayer.getEngimon();
			int playerLvl = myplayer.getLevel();
			int enemyLvl = engimonMusuh.getLevel();
			float playerMaxElAdv = Engimon.maxElAdv(myengimon, engimonMusuh);
			float enemyMaxElAdv = Engimon.maxElAdv(engimonMusuh, myengimon);
			float powerPlayer = (playerLvl * playerMaxElAdv) + myengimon.sumSkillPower();
			float powerEnemy = (enemyLvl * enemyMaxElAdv) + engimonMusuh.sumSkillPower();
			System.out.print("Power Engimon Anda: ");
			System.out.print(powerPlayer);
			System.out.print("\n");
			System.out.print("Power Engimon Lawan: ");
			System.out.print(powerEnemy);
			System.out.print("\n");
			if (powerPlayer < powerEnemy) {
				if (myengimon.getLife() == 1){
					AlertBox.display("Battle", "Kalah Power, Engimon anda Mati");
					myplayer.setActiveEngimon(null);
					myplayer.getInventory().removeEngimon(myengimon);

				}
				else {
					AlertBox.display("Battle", "Kalah Power, Life Engimon berkurang");
					myengimon.setLife(myengimon.getLife()-1);
					myplayer.setActiveEngimon(myengimon);
				}
			}
			else {
				AlertBox.display("Battle", "Menang Power, Engimon Liar menjadi milik anda");
				if (!myplayer.getInventory().isFull()) {
					// Buat Input String buuat kasih nama buat engimon baik di inventory maupun battle
					System.out.print("Engimon Menjadi Milik Anda");
					System.out.print("\n");
					System.out.print("Beri Nama Engimon Baru anda : ");
//					String nama;
//					nama = ConsoleInput.readToWhiteSpace(true);
//					engimonMusuh.setName(nama);
					myplayer.getInventory().addEngimon(engimonMusuh);
					Skill skillbaru = dropRandomSkill();
					AlertBox.display("Battle Drop", "Mendapatkan Drop : " + skillbaru.getSkillName());
					if (!myplayer.getInventory().isFull()) {
						myplayer.getInventory().addSkill(skillbaru);
					} else {
						AlertBox.display("Battle Drop", "Inventory Penuh");
					}

				} else {
					AlertBox.display("Battle Drop", "Inventory Penuh");
				}

				for (Enemy enemy : enemyHandler.getEnemyList()){
					if (enemy.getEngimon() == engimonMusuh){
						Random rand = new Random();
						enemy.init(rand.nextInt(8), rand.nextInt(3) + myplayer.getHighestLevel());
						break;
					}
				}
				if (!myplayer.getEngimon().addExp(100)) {
					myplayer.setActiveEngimon(null);
					myplayer.getInventory().removeEngimon(myengimon);
				}
			}
		} else {
			AlertBox.display("Battle", "Tidak ada engimon liar di sekitar");
		}
		return myplayer;
	}


//
	private static Skill dropRandomSkill(){
		Skill skillBaru;
//		tangible.RandomNumbers.seed(time(0));

		Random rand = new Random();
		int random = rand.nextInt(11);
		switch (random){
			case 0:
				skillBaru = new Cataclysm();
				break;
			case 1:
				skillBaru = new Fissure();
				break;
			case 2:
				skillBaru = new IceVortex();
				break;
			case 3:
				skillBaru = new Magnetize();
				break;
			case 4:
				skillBaru = new Nimbus();
				break;
			case 5:
				skillBaru = new SplinterBlast();
				break;
			case 6:
				skillBaru = new StaticStorm();
				break;
			case 7:
				skillBaru = new Sunstrike();
				break;
			case 8:
				skillBaru = new Torrent();
				break;
			case 9:
				skillBaru = new Waveform();
				break;
			case 10:
				skillBaru = new Skill("AntiAging", "None", 1, 1);
			default:
				skillBaru = new Cataclysm();
				break;
			}
		return skillBaru;
		}

}

//
//package tangible;
//public final class RandomNumbers
//{
//	private static java.util.Random r;
//
//	public static int nextNumber()
//	{
//		if (r == null)
//			Seed();
//
//		return r.nextInt();
//	}
//
//	public static int nextNumber(int ceiling)
//	{
//		if (r == null)
//			Seed();
//
//		return r.nextInt(ceiling);
//	}
//
//	public static void seed()
//	{
//		r = new java.util.Random();
//	}
//
//	public static void seed(int seed)
//	{
//		r = new java.util.Random(seed);
//	}
//}




