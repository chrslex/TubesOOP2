package OOPokemon.Species;
import java.util.*;

import OOPokemon.Element.Element;
import OOPokemon.Element.ElementType;
import OOPokemon.Occupier.Player;
import OOPokemon.Skill.Skill;
import javafx.scene.image.Image;

import static OOPokemon.Element.ElementType.None;
//import OOPokemon.Skill;
//import OOPokemon.Player;

public class Engimon implements Comparable<Engimon> {
    protected int monLevel;
    protected int monExp;
    protected int monCtvExp;
    protected int baseLevel;
    protected int monLife;
    protected String monName;
    protected String namaSpecies;
    protected Element[] monElements;
    protected Engimon[] monParents;

    protected String imageSource;
    public Skill[] monSkills;

    private void InitComp() {
        this.monName = "";
        this.namaSpecies = "";
        this.monLevel = 1;
        this.baseLevel = 1;
        this.monLife = 3;
        this.monExp = 0;
        this.monCtvExp = 2000;
        this.monSkills = new Skill[4];
        for (int i = 0; i < 4; i++) {
            monSkills[i] = new Skill();
        }
        this.monElements = new Element[2];
        for (int i = 0; i < 2; i++) {
            monElements[i] = new Element();
        }
        this.monParents = null;

        imageSource = "assets/pikachu.png";
    }

    public Engimon() {
        InitComp();
    }

    public Engimon(String monName) {
        InitComp();
        this.monName = monName;
    }

    public Engimon(Engimon other){
        this.monName = other.monName;
        this.namaSpecies = other.namaSpecies;
        this.monLevel = other.monLevel;
        this.baseLevel = other.baseLevel;
        this.monLife = other.monLife;
        this.monExp = other.monExp;
        this.monCtvExp = other.monCtvExp;
        this.monSkills = new Skill[4];
        for (int i = 0; i < 4; i++) {
            monSkills[i] = new Skill(other.monSkills[i]);
        }
        this.monElements = new Element[2];
        for (int i = 0; i < 2; i++) {
            monElements[i] = new Element(other.monElements[i].getElementType());
        }
        this.monParents = (other.monParents != null)? new Engimon[2]: null;
        if (monParents != null){
            for (int i = 0; i < 2; i++) {
                monParents[i] = new Engimon(other.monParents[i]);
            }
        }
        this.imageSource = other.imageSource;
    }

    public Engimon(int monLife) {
        InitComp();
        this.monLife = monLife;
    }

    public boolean isContainSkill(Skill a) {
        for (int i = 0; i < 4; i++) {
            if (a.isSame(monSkills[i])) return true;
        }
        return false;
    }

    public boolean learnSkill(Skill other) {
        Scanner myObj = new Scanner(System.in);
        if (other.getSkillName().equals("AntiAging")) {
            this.monCtvExp += 1000;
            System.out.println("Maximum Exp Bertambah 1000");
            return true;
        }
        if (this.isContainSkill(other)) {
            System.out.println("Skill telah dipelajari");
            return false;
        }
        if (other.skillType.equals("None") || this.monElements[0]
                .getElementType().toString().equals(other.skillType)
                || this.monElements[1].getElementType().toString().equals(other.skillType)) {
            for (int i = 0; i < 4; i++) {
                if (this.monSkills[i].getSkillName().equals("None")) {
                    this.monSkills[i] = other;
                    System.out.println("Skill berhasil dipelajari");
                    return true;
                }
            }
//            System.out.println("Slot skill penuh");
//            System.out.print("Apakah ingin menimpa skill yang ada? (y/n): ");
//            String input = myObj.nextLine();
//            if (input.equals("y") || input.equals("yes")) {
//                printInfoSkill();
//                int inpt = Player.validasiInput ("Pilih skill: ", 0, 4, -1);
//                this.monSkills[inpt - 1] = other;
//                System.out.println("Skill berhasil dipelajari");
//                return true;
//            }
            return false;
        }
        System.out.println("Elemen skill tidak sesuai");
        return false;
    }

    public Engimon(String name, Engimon other1, Engimon other2) {
        InitComp();
        this.monName = name;
        this.monParents = new Engimon[2];
        for (int i = 0; i < 2; i++) {
            monParents[i] = new Engimon();
        }
        this.monParents[0] = new Engimon(other1);
        this.monParents[1] = new Engimon(other2);

        this.monCtvExp = other1.monCtvExp + other2.monCtvExp;

        List<Skill> temporaryskill = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            temporaryskill.add(new Skill(other1.monSkills[i]));
            temporaryskill.add(new Skill(other2.monSkills[i]));
        }
        // Sorting skill
        Collections.sort(temporaryskill);

        this.monSkills = new Skill[4];
        monSkills[0] = temporaryskill.get(0);

        int angka = 1;
        for (int i = 1; i < 7; i++) {
            if (!isContainSkill(temporaryskill.get(i))) monSkills[angka++] = temporaryskill.get(i);
            else {
                for (int j = 0; j < 4; j++)
                {
                    if (monSkills[j].equals(temporaryskill.get(i))) {monSkills[j].increaseMasteryLevel(); break;}

                }
            }
            if (angka == 4) break;
        }

        ElementType elFather1 = other1.getFirstElement();
        ElementType elFather2 = other1.getSecondElement();
        ElementType elMother1 = other2.getFirstElement();
        ElementType elMother2 = other2.getSecondElement();

        float maxEladvA = maxElAdv(other1, other2);
        float maxEladvB = maxElAdv(other2, other1);

        if (maxEladvA > maxEladvB)
        {
            this.namaSpecies = other1.namaSpecies;
        }
        else
        {
            this.namaSpecies = other2.namaSpecies;
        }
        // Kasus single element
        if (elFather2 == None && elMother2 == None)
        {
            if (maxEladvA > maxEladvB)
            {
                this.monElements[0].setElementType(elFather1);
            }
            else this.monElements[0].setElementType(elMother1);
        }
        // Kasus double element
        else if (elFather2 == None)
        {
            this.monElements[0].setElementType(elMother1);
            this.monElements[1].setElementType(elMother2);
        }
        else
        {
            this.monElements[0].setElementType(elFather1);
            this.monElements[1].setElementType(elFather2);
        }
    }

    public void setName(String name) {
        this.monName = name;
    }

    public void setLevel(int level) {
        this.monLevel = level;
        this.baseLevel = level;
    }

    public void setLife(int life) {
        this.monLife = life;
    }

    public boolean addExp(int additionalExp) {
        int virtualExp = baseLevel * 100;
        this.monExp += additionalExp;
        System.out.println("Anda Mendapatkan " + additionalExp + " exp");
        if (monExp >= monCtvExp)
        {
            return false;
        }
        else if (this.monLevel != ((this.monExp + virtualExp)/ 100))  {
            this.monLevel = ((this.monExp + virtualExp) / 100);
            System.out.println("LEVEL UP!! Engimon anda naik ke level " + monLevel);
        }
        return true;
    }
    public String getNamaSpecies() {
        return namaSpecies;
    }

    public String getName() {
        return monName;
    }

    public int getLevel() {
        return monLevel;
    }

    public int getLife() {
        return monLife;
    }

    public ElementType getFirstElement() {
        return this.monElements[0].getElementType();
    }

    public ElementType getSecondElement() {
        return this.monElements[1].getElementType();
    }

    public float sumSkillPower() {
        float temp = 0;
        for (int i = 0; i < 4; i++) {
            temp += (this.monSkills[i].getBasePower() * this.monSkills[i].masteryLevel);
        }
        return temp;
    }

    public void printInfo() {
        System.out.println("Nama : " + this.monName);
        printInfoSafe();
        System.out.println("Exp : " + this.monExp);
        System.out.println("Maximum Exp : " + this.monCtvExp);
        System.out.println("List Elemen : ");
        System.out.println("Elemen 1 : " + this.monElements[0].toString());
        if (this.monElements[1].getElementType() != None) {
            System.out.println("Elemen 2 : " + this.monElements[1].toString());
        }
//        printInfoSkill();
        if (this.monParents != null && (!this.monParents[0].getName().equals(""))) {
            System.out.println("List nama dan spesies Parent :");
            for (int j = 0; j < 2 ; j++)
            {
                System.out.println(this.monParents[j].getName() + " | Spesies : " + this.monParents[j].getNamaSpecies());
            }
        }
    }

    public void printInfoSafe() {
        System.out.println("Nama Spesies : " + this.namaSpecies);
        System.out.println("Level : " + this.monLevel);
    }

    public void printInfoSkill() {
        System.out.println("List skills :");
        System.out.println("-----------------");
        for (int i = 0; i < 4; i++)
        {
            if(!this.monSkills[i].getSkillName().equals("None")){
            monSkills[i].printInfoAll();
                System.out.println("--------" + (i+1) + "--------");
            }
        }
    }

    public float maxFloat(float a, float b) {
        return Math.max(a, b);
    }

    public float maxElAdv(Engimon a, Engimon b) {
        ElementType ela1 = a.getFirstElement();
        ElementType ela2 = a.getSecondElement();
        ElementType elb1 = b.getFirstElement();
        ElementType elb2 = b.getSecondElement();

        float[] elAdvA =
        {
            Element.getElementAdv(ela1, elb1),
            Element.getElementAdv(ela1, elb2),
            Element.getElementAdv(ela2, elb2),
            Element.getElementAdv(ela2, elb1)
        };

        float elAdvAMax = elAdvA[0];
        for (int i = 1; i < 4; i++)
        {
            elAdvAMax = maxFloat(elAdvA[i], elAdvAMax);
        }
        return elAdvAMax;
    }

    public Image getImage(){
        return new Image(imageSource);
    }

    public Image getElementImage() {
        return new Image("assets/dragon.png");
    }

    @Override
    public int compareTo(Engimon o) {
        // kalau kedua element tidak sama, akan mensort elemennya
        if (!o.monElements[0].equals(this.monElements[0])) {
            return o.monElements[0].compareTo(this.monElements[0]);
        }
        // kalau kedua element sama tapi elemen kedua tidak sama akan mensort element kedua
        else if (!o.monElements[1].equals(this.monElements[1])) {
            return o.monElements[1].compareTo(this.monElements[1]);
        }
        // kedua element sama dan kedua element kedua sama
        return o.monLevel - this.monLevel;
    }
}
