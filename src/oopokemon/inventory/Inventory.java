package oopokemon.inventory;

import java.util.*;
import java.util.stream.Collectors;

import oopokemon.skill.*;
import oopokemon.species.*;

public class Inventory {
    public static final int MAX_CAPACITY = 30;

    private Vector<Skill> skillBag;
    private final Vector<Engimon> engimonBag;
    private final HashMap<Skill, Integer> skillDict;

    public Inventory() {
        skillBag = new Vector<>();
        engimonBag = new Vector<>();
        skillDict = new HashMap<>();
    }
    public boolean isSkillExist(Skill s) {
        if (skillDict.containsKey(s)){
            return skillDict.get(s) != 0;
        }
        return false;
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
    public void removeEngimon(Engimon toRemove){
        engimonBag.remove(toRemove);
    }

    public void removeSkill(int x) {
        if (this.skillBag.isEmpty()) {
//            System.out.println("Tidak ada skill dalam Inventory");
            return;
        }
        else if (this.skillBag.size()<x || x<=0) {
//            System.out.println("Angka tidak valid");
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

    public int getCountSkill(Skill skill){
        if (skillBag.contains(skill)){
            return skillDict.get(skill);
        }
        return 0;
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
    public List<Engimon> listEngimon() {
        Collections.sort(engimonBag);
        return engimonBag;
    }
    public List<Skill> listSkill() {
        Collections.sort(skillBag);
        return skillBag;
    }

    public List<Skill> listAllSkill(){
        Collections.sort(skillBag);
//        Vector<Skill> skilsCopy = new Vector<>(skillBag);
        List<Skill> toReturn = new ArrayList<>();
        while (!skillBag.isEmpty()){
            toReturn.add(skillBag.get(0));
            removeSkill(1);
        }
        for (Skill skill : toReturn){
            addSkill(skill);
        }
//        skillBag = skilsCopy;
        return toReturn;
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
