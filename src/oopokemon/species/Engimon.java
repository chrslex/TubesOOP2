package oopokemon.species;
import java.util.*;

import com.google.gson.*;
import oopokemon.element.Element;
import oopokemon.element.ElementType;
import oopokemon.inventory.Item;
import oopokemon.skill.Skill;
import javafx.scene.image.Image;

import static oopokemon.element.ElementType.None;
//import oopokemon.skill;
//import oopokemon.Player;

public class Engimon implements Comparable<Engimon>, Item {
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

    private static final Map<String, Integer> map;

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

    public Engimon(String name, String species, int level, int baselvl,
                   int life, int exp, int maxExp, Skill[] skills,
                   Element[] elements, Engimon[] parents, String _imageSource){
        monName = name;
        namaSpecies = species;
        monLevel = level;
        baseLevel = baselvl;
        monLife = life;
        monExp = exp;
        monCtvExp = maxExp;
        monSkills = skills;
        monElements = elements;
        monParents = parents;
        imageSource = _imageSource;
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
            System.out.println("skill telah dipelajari");
            return false;
        }
        if (other.skillType.equals("None") || this.monElements[0]
                .getElementType().toString().equals(other.skillType)
                || this.monElements[1].getElementType().toString().equals(other.skillType)) {
            for (int i = 0; i < 4; i++) {
                if (this.monSkills[i].getSkillName().equals("None")) {
                    this.monSkills[i] = other;
                    System.out.println("skill berhasil dipelajari");
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
//                System.out.println("skill berhasil dipelajari");
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
        for (int i = 0; i < 4; i++) {
            monSkills[i] = new Skill();
        }

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

        if (maxEladvA >= maxEladvB)
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
            if (maxEladvA >= maxEladvB)
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
        this.imageSource = "assets/" + this.namaSpecies + ".png";
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
//        System.out.println("Anda Mendapatkan " + additionalExp + " exp");
        if (monExp >= monCtvExp)
        {
            return false;
        }
        else if (this.monLevel != ((this.monExp + virtualExp)/ 100))  {
            this.monLevel = ((this.monExp + virtualExp) / 100);
//            System.out.println("LEVEL UP!! Engimon anda naik ke level " + monLevel);
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

    public int getBaseLevel() {
        return baseLevel;
    }

    public int getLife() {
        return monLife;
    }

    public int getExp() {
        return monExp;
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
    static {
        map = new HashMap<>();
        map.put("Squirtle", 1);
        map.put("Raichu", 2);
        map.put("Excadrill", 3);
        map.put("Articuno", 4);
        map.put("Inferail", 5);
        map.put("Kyogre", 6);
        map.put("Seismotoad", 7);
        map.put("Dragon", 8);
        map.put("", 9);

    }

    public static int getTypeInt(String namaSpecies){
        return map.get(namaSpecies);
    }

    public static float maxFloat(float a, float b) {
        return Math.max(a, b);
    }

    public static float maxElAdv(Engimon a, Engimon b) {
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
        String url = "assets/" + this.monElements[0].toString();
        if (this.monElements[1].getElementType() != None) url+= this.monElements[1].toString();
        url+=".png";
        return new Image(url);
    }

    private static String formatter(String string){
        return "\"" + string + "\"";
    }


    public static Engimon fromJson(JsonObject json){

        String nama = json.get("nama").getAsString();
        String spesies = json.get("spesies").getAsString();
        String imageSource = json.get("imageSource").getAsString();
        int level = json.get("level").getAsInt();
        int baseLevel = json.get("baseLevel").getAsInt();
        int exp = json.get("exp").getAsInt();
        int life = json.get("life").getAsInt();
        int maxExp = json.get("maxExp").getAsInt();
        JsonArray skillString = json.get("skills").getAsJsonArray();
        Skill[] skills = new Gson().fromJson(skillString, Skill[].class);
        JsonArray elementString = json.get("elements").getAsJsonArray();
        Element[] elements = new Gson().fromJson(elementString, Element[].class);
        Engimon[] parents = null;

        if (json.has("parents")){
            parents = new Engimon[2];
            int counter = 0;
            JsonArray parentsarray = json.get("parents").getAsJsonArray();
            for (JsonElement parentobj: parentsarray){
                parents[counter++] = Engimon.fromJson(parentobj.getAsJsonObject());
            }
        }


//            public Engimon(String name, String species, int level, int baselvl,
//        int life, int exp, int maxExp, Skill[] skills,
//                Element[] elements, Engimon[] parents, String _imageSource){



        return new Engimon(nama, spesies, level, baseLevel, life, exp, maxExp, skills, elements, parents, imageSource);
    }


    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuilder skillString = new StringBuilder();
        StringBuilder elementsString = new StringBuilder();
        for (Skill skill: monSkills){
            String skill1 = gson.toJson(skill);
            skillString.append(skill1).append(",");
        }

        for (Element element: monElements){
            String els = gson.toJson(element);
            elementsString.append(els).append(",");
        }
        skillString = new StringBuilder(skillString.substring(0, skillString.length() - 1));
        elementsString  = new StringBuilder(elementsString.substring(0, elementsString.length() - 1));

        String engimonJson = String.format("{\n%s:%s, \n%s:%s, \n%s:%s, \n%s:%d, \n%s:%d, \n%s:%d, \n%s:%d, \n%s:%d, \n%s:[%s], \n%s:[%s]",
                formatter("nama"), formatter(monName),
                formatter("spesies"), formatter(namaSpecies),
                formatter("imageSource"), formatter(imageSource),
                formatter("level"), monLevel,
                formatter("baseLevel"), baseLevel,
                formatter("exp"), monExp,
                formatter("maxExp"), monCtvExp,
                formatter("life"), monLife,
                formatter("elements"), elementsString,
                formatter("skills"), skillString);
        if (monParents != null){
            String parent1 = monParents[0].toJson();
            String parent2 = monParents[1].toJson();
            engimonJson += String.format(", \n%s:[\n%s,\n%s]",
                    formatter("parents"), parent1, parent2);
        }
        engimonJson += "\n}";
        return engimonJson;

    }

    @Override
    public String toString() {
        return
        "Engimon || Nama : " + this.getName() + " || Spesies : " + this.getNamaSpecies() + " || Level : " + this.getLevel() + " || Elemen 1 : " + this.monElements[0] + " || Elemen 2 : " + monElements[1];
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

    @Override
    public String getFirstLine() {
        return "Nama : " + monName;
    }

    @Override
    public String getSecondLine() {
        String elemen2 = "";
        if (monElements[1].getElementType() != None){
            elemen2 = "\nElement 2 : " + monElements[1];
        }
        return "Spesies : " + namaSpecies + "\nElement 1 : " + monElements[0] + elemen2;
    }

    @Override
    public String getThirdLine() {
        return "Exp : " + monExp + "/" + monCtvExp + " Lvl : " + monLevel;
    }

    @Override
    public String getFourthLine() {
        StringBuilder skills = new StringBuilder();
        for (Skill skill : monSkills){
            if (!skill.skillName.equals("None")){
                skills.append(skill).append("\n");
            }
        }
        return "" + skills;
    }


    @Override
    public String getFifthLine() {
        if (monParents != null){
            StringBuilder parents = new StringBuilder();
            for (Engimon engimon: monParents){
                parents.append("Parent | Nama: ")
                        .append(engimon.monName).append(" Spesies: ")
                        .append(engimon.namaSpecies).append("\n");
            }
            return parents.toString();
        }
        return "";
    }


//    public static void main(String[] args) {
//        Engimon engimon = new Dragon("okeeee");
//
//        Engimon kyogre = new Kyogre("kyogre");
//
//        Engimon breedTest =new Engimon("test",engimon, kyogre);
//
//        String parsed = breedTest.toJson();
//
////        System.out.println(parsed);
//
//        JsonObject jsonObject = new Gson().fromJson(parsed, JsonObject.class);
//
//        Engimon engimon1 = Engimon.fromJson(jsonObject);
//
//
//        System.out.println(engimon1.toJson());
//
//
//    }
}
