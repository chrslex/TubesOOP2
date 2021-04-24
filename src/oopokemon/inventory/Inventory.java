package oopokemon.inventory;

import java.util.*;

import oopokemon.skill.*;
import oopokemon.species.*;

public class Inventory {
    public static final int MAX_CAPACITY = 6;

    private final Vector<Skill> skillBag = new Vector<>();
    private final Vector<Engimon> engimonBag = new Vector<>();
    private static final HashMap<Skill, Integer> skillDict = new HashMap<>();

    public Inventory() {}
    public boolean isSkillExist(Skill s) {
        return skillDict.containsKey(s);
    }
    public boolean isEmpty() {
        return this.engimonBag.isEmpty() && this.skillBag.isEmpty();
    }
    public boolean isFull() {
        return (this.engimonBag.size() + this.skillBag.size() >= MAX_CAPACITY);
    }
    public boolean isEngimonBagEmpty() {
        return this.engimonBag.isEmpty();
    }
    public boolean isSkillBagEmpty() {
        return this.skillBag.isEmpty();
    }
    public int engimonBagSize() {
        return this.engimonBag.size();
    }
    public boolean addEngimon(Engimon e) {
        if (this.isFull()) {
            System.out.println("Inventory sudah penuh");
        }
        else {
            this.engimonBag.add(e);
            System.out.println("Engimon berhasil dimasukkan");
            return true;
        }
        return false;
    }
    public boolean addSkill(Skill s) {
        if (this.isFull()) {
            System.out.println("Inventory sudah penuh");
        }
        else if (isSkillExist(s)){
            int x = skillDict.get(s);
            skillDict.put(s, ++x) ;
            System.out.println("Skill berhasil dimasukkan");
            return true;
        }
        else {
            skillDict.put(s, 1) ;
            this.skillBag.add(s);
            System.out.println("Skill berhasil dimasukkan");
            return true;
        }
        return false;
    }
    public void removeEngimon(int x) {
        if (this.engimonBag.isEmpty()) {
            System.out.println("Tidak ada engimon dalam Inventory");
        }
        else if (this.engimonBag.size()<x || x<=0) {
            System.out.println("Angka tidak valid");
        }
        else {
            this.engimonBag.remove(x-1);
        }
    }
    public void removeSkill(int x) {
        if (this.skillBag.isEmpty()) {
            System.out.println("Tidak ada skill dalam Inventory");
            return;
        }
        else if (this.skillBag.size()<x || x<=0) {
            System.out.println("Angka tidak valid");
            return;
        }
        Skill key = this.skillBag.get(x-1);
        Integer val = skillDict.get(key);
        if (val != null) {
            skillDict.replace(key, val-1);
            if (val == 1){
                this.skillBag.remove(x-1);
            }
        }

    }
    public void printItem() {
        if (this.isEmpty()) {
            System.out.println("Bag kosong");
        }
        this.printAllSkillInfo();
        this.printAllEngimonInfo();
    }
    public void printAllSkillInfo() {
        if (!this.isSkillBagEmpty()) {
            Collections.sort(this.skillBag);
            int count_skill = 0;
            for (Skill s: skillBag) {
                System.out.println(++count_skill + ". " + s + " || jumlah : " + skillDict.get(s));
            }
        }
    }
    public void printAllEngimonInfo() {
        if(!this.isEngimonBagEmpty()) {
            Collections.sort(engimonBag);
            int count_engimon = 0;
            for (Engimon e: engimonBag) {
                System.out.println(++count_engimon + ". " + e);
            }
        }
    }
    public Bag<Engimon> listEngimon() {
        Bag<Engimon> temp = new Bag<>();
        if (!this.isEngimonBagEmpty()) {
            for (Engimon e : engimonBag) {
                temp.Add(e);
            }
            return temp;
        }
        return null;
    }
    public Bag<Skill> listSkill() {
        Bag<Skill> temp = new Bag<>();
        if (!this.isSkillBagEmpty()) {
            for (Skill s : skillBag) {
                temp.Add(s);
            }
            return temp;
        }
        return null;
    }
    public int getHighestLevel(){
        if (this.engimonBag.isEmpty()) return 1;
        return engimonBag
                .stream()
                .map(Engimon::getLevel)
                .max(Comparator.naturalOrder())
                .get();
    }

//    public static void main(String[] args) {
//        Engimon dragon = new Dragon("test");
//        Engimon squirt = new Squirtle("squirtle");
//        Engimon air = new Kyogre("anjay");
//        Engimon dragon2 = new Dragon("dragon2");
//        Engimon fireel = new Inferail("infer");
//        dragon2.setLevel(10);
//        squirt.setLevel(20);
//        Skill skill = new Torrent();
//        Skill skill2 = new Fissure();
//        Skill skill4 = new Waveform();
//        Skill skill1 = new Torrent();
//        Skill skill3 = new Fissure();
//        Inventory inv = new Inventory();
//        inv.addEngimon(fireel);
//        inv.addEngimon(squirt);
//        inv.addEngimon(dragon);
//        inv.addEngimon(air);
//        inv.addEngimon(dragon2);
//        System.out.println(inv.getHighestLevel());

//        inv.addSkill(skill);
//        inv.addSkill(skill1);
//        inv.addSkill(skill4);
//        inv.addSkill(skill2);
//        inv.addSkill(skill3);

//        inv.printAllEngimonInfo();
//        inv.printAllSkillInfo();
//        inv.removeSkill(1);
//        inv.printAllSkillInfo();
//        inv.removeSkill(1);
//        inv.printAllSkillInfo();
//        inv.removeEngimon(2);
//        inv.printAllEngimonInfo();
//        inv.removeEngimon(1);
//        inv.printAllEngimonInfo();
//    }
}
