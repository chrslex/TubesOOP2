package oopokemon.inventory;

import java.util.*;

import oopokemon.species.Engimon;
import oopokemon.skill.Skill;

public class Inventory {
    public static final int MAX_CAPACITY = 6;

    private final Vector<Skill> skillBag = new Vector<>();
    private final Vector<Engimon> engimonBag = new Vector<>();
    private static HashMap<Skill, Integer> skillDict;

    public Inventory() {}
    public boolean isSkillExist(Skill s) {
        return skillDict.containsKey(s);
    }
    public boolean isEmpty() {
        return this.engimonBag.isEmpty() && this.skillBag.isEmpty();
    }
    public boolean isFull() {
        return (this.engimonBag.size() + this.engimonBag.size() >= MAX_CAPACITY);
    }
    public boolean isEngimonBagEmpty() {
        return !this.engimonBag.isEmpty();
    }
    public boolean isSkillBagEmpty() {
        return this.skillBag.isEmpty();
    }
    public int EngimonBagSize() {
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
        else {
            skillDict.put(s,1);
            this.skillBag.add(s);
            System.out.println("Skill berhasil dimasukkan");
            return true;
        }
        return false;
    }
    public void removeEngimon(int x) {
        if (this.engimonBag.isEmpty()) {
            System.out.println("Tidak ada skill dalam Inventory");
        }
        else if (this.engimonBag.size()<x || x<=0) {
            System.out.println("Angka tidak valid");
        }
        else {
            this.skillBag.remove(x-1);
        }
    }
    public void removeSkill(int x) {
        if (this.skillBag.isEmpty()) {
            System.out.println("Tidak ada skill dalam Inventory");
        }
        else if (this.skillBag.size()<x || x<=0) {
            System.out.println("Angka tidak valid");
        }
        else {
            Skill key = this.skillBag.get(x-1);
            Integer value = skillDict.get(key);
            skillDict.replace(key, value-1);
            this.skillBag.remove(x-1);
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
            int count_skill = 0;
            for (Skill s: skillBag) {
                System.out.println(++count_skill + ". " + s + " || count : " + skillDict.get(s));
            }
        }
    }
    public void printAllEngimonInfo() {
        if(!this.isEngimonBagEmpty()) {
            int count_engimon = 0;
            for (Engimon e: engimonBag) {
                System.out.println(++count_engimon + ". " + e);
            }
        }
    }
    public Bag<Engimon> listEngimon() {
        Bag<Engimon> temp = new Bag<>();
        if (this.isEngimonBagEmpty()) {
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
}
