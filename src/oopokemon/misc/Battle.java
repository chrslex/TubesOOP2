package oopokemon.misc;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oopokemon.occupier.Enemy;
import oopokemon.occupier.EnemyHandler;
import oopokemon.occupier.Player;
import oopokemon.skill.*;
import oopokemon.species.Engimon;

import java.util.List;
import java.util.Optional;
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
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Battle");
			alert.setHeaderText("Engimon Liar : " + engimonMusuh.toString() +
					"\nPower Anda : " + powerPlayer + "\nPower Musuh : " + powerEnemy);
			alert.setContentText("Lanjutkan?");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();


			stage.getIcons().add(new Image("assets/oopokemon.png"));

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK){
			} else {
				return myplayer;
			}
			if (powerPlayer < powerEnemy) {
				if (myengimon.getLife() == 1){
					AlertBox.display("Battle", "Kalah Power, Engimon Anda Mati");
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
				AlertBox.display("Battle", "Menang Power, Engimon Liar Menjadi Milik Anda");
				if (!myplayer.getInventory().isFull()) {
					// Buat Input String buuat kasih nama buat engimon baik di inventory maupun battle
//					AlertBox.display("Battle");
//					String nama;
//					nama = ConsoleInput.readToWhiteSpace(true);
					String nama = InputBox.inputName("Battle", "Beri Nama Pada Engimon Baru Anda!");
					engimonMusuh.setName(nama);
					myplayer.getInventory().addEngimon(engimonMusuh);
					Skill skillbaru = dropRandomSkill();
					Skill skillBaru2 = new Skill(engimonMusuh.monSkills[0]);
					AlertBox.display("Battle Drop", "Mendapatkan Drop :\n" + skillBaru2.getSkillName() +
							"\n" + skillbaru.getSkillName());
					if (!myplayer.getInventory().isFull()) {
						myplayer.getInventory().addSkill(skillBaru2);
						if (!myplayer.getInventory().isFull()) {
							myplayer.getInventory().addSkill(skillbaru);
						} else {
							AlertBox.display("Battle Drop", "Inventory Penuh");
						}
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
			AlertBox.display("Battle", "Tidak Ada Engimon Liar Di Sekitar");
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




