package oopokemon.skill;

import javafx.scene.image.Image;
import oopokemon.inventory.Item;

public class Skill implements Comparable<Skill>, Item {
    protected int basePower;
    public int masteryLevel;
    public String skillName;
    public String skillType;
    
    public Skill() {
        this.skillName = "None";
        this.skillType = "None";
        this.basePower = 0;
        this.masteryLevel = 0;
    }
    
    public Skill(String skillName, String skillType, int basePower, int masteryLevel) {
        this.skillName = skillName;
        this.skillType = skillType;
        this.basePower = basePower;
        this.masteryLevel = masteryLevel;
    }

    public Skill(Skill other){
        this.skillName = other.skillName;
        this.skillType = other.skillType;
        this.basePower = other.basePower;
        this.masteryLevel = other.masteryLevel;
    }
    
    public final String getSkillName() {
        return this.skillName;
    }
    
    public final int getBasePower() {
        return this.basePower;
    }
    
    public final void printInfo() {
        System.out.print("skill ");
        System.out.print(this.skillName);
        System.out.println(": ");
        System.out.print("Elemen : ");
        System.out.println(this.skillType);
        System.out.print("Base Power : ");
        System.out.println(this.basePower);    
    }
    
    public final void printInfoAll() {
        this.printInfo();
        System.out.print("Mastery Level : ");
        System.out.println(this.masteryLevel);
    }
    
    public boolean isSame(final Skill c) {
        return (this.skillName.equals(c.skillName) && this.skillType.equals(c.skillType));
    }
        
    public boolean isNotSame(final Skill c) {
        return !this.isSame(c);
    }
    
    public boolean isHigher(final Skill c) {
        return (this.masteryLevel > c.masteryLevel);
    }
        
    public boolean isSameOrHigher(final Skill c) {
        return (this.masteryLevel >= c.masteryLevel);
    }
    
    public boolean isLower(final Skill c) {
        return !this.isSameOrHigher(c);
    }
	
	public boolean isSameOrLower(final Skill c) {
	    return !this.isHigher(c);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Skill)) return false;
        Skill c = (Skill) obj;
        return this.skillName.equals(c.skillName) && this.skillType.equals(c.skillType);
    }

    @Override
    public int compareTo(Skill o) {
//        if (!this.skillType.equals(o.skillType)) {
//            return this.skillType.compareTo(o.skillType);
//        } else {
        return o.masteryLevel - this.masteryLevel;
//        }
    }

    @Override
    public String toString() {
        return "Skill || Nama : " + this.skillName + " || Elemen : " + this.skillType + " || Base Power : " + this.basePower + " || Mastery Level : " + this.masteryLevel;
    }

    @Override
    public int hashCode() {
        return skillName.length() + skillType.length();
    }

    public void increaseMasteryLevel() {
        if (masteryLevel < 3) masteryLevel++;
    }

    @Override
    public String getFirstLine() {
        return skillName;
    }

    @Override
    public String getSecondLine() {
        return "Elemen : " +skillType;
    }

    @Override
    public String getThirdLine() {
        return "Base Power : " + basePower;
    }

    @Override
    public String getFourthLine() {
        return "Mastery Level : " + masteryLevel;
    }

    @Override
    public String getFifthLine() {
        return "";
    }

    @Override
    public Image getImage() {
        return new Image("assets/" + skillName + ".png");
    }
}