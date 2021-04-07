package OOPokemon.Element;

import OOPokemon.Tuple;

import java.util.AbstractMap;
import java.util.HashMap;

public class Element{
    public ElementType elementType;
    private static final HashMap<Tuple<ElementType, ElementType>, Float> tableElementAdv = new HashMap<>();


    Element() {
        elementType = ElementType.None;
    }
    Element(ElementType elementType) {
        this.elementType = elementType;
    }

    public float compareElemenet(Element elementKiri, Element elementKanan) {
        return tableElementAdv.get(new Tuple<>(elementKiri.elementType, elementKanan.elementType));
    }

    @Override
    public boolean equals(Object other) {
        // If the object is compared with itself then return true
        if (other == this) {
            return true;
        }
        if (!(other instanceof Element)) {
            return false;
        }
        Element toCompare = (Element) other;
        return this.elementType == toCompare.elementType;
    }

    @Override
    public String toString() {
        return this.elementType.toString();
    }

    static {
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.Fire),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.Water),(float) 0);
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.Electric),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.Ground), (float)0.5);
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.Ice),(float) 2);
        tableElementAdv.put(new Tuple<>(ElementType.Fire, ElementType.None),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.Water,ElementType.Fire),(float) 2);
        tableElementAdv.put(new Tuple<>(ElementType.Water, ElementType.Water),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Water, ElementType.Electric),(float) 0);
        tableElementAdv.put(new Tuple<>(ElementType.Water, ElementType.Ground),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Water, ElementType.Ice),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Water, ElementType.None),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.Fire),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.Water),(float) 2);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.Electric),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.Ground),(float) 0);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.Ice),(float) 1.5);
        tableElementAdv.put(new Tuple<>(ElementType.Electric, ElementType.None),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.Fire),(float) 1.5);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.Water),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.Electric),(float) 2);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.Ground),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.Ice),(float) 0);
        tableElementAdv.put(new Tuple<>(ElementType.Ground, ElementType.None),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.Fire),(float) 0);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.Water),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.Electric),(float) 0.5);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.Ground),(float) 2);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.Ice),(float) 1);
        tableElementAdv.put(new Tuple<>(ElementType.Ice, ElementType.None),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.Fire),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.Water),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.Electric),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.Ground),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.Ice),(float) -5);
        tableElementAdv.put(new Tuple<>(ElementType.None, ElementType.None),(float) -5);


    }
}
