package OOPokemon.Element;

public enum  ElementType {
    None {
        @Override
        public String toString() {
            return "None";
        }
    },
    Fire {
        @Override
        public String toString() {
            return "Fire";
        }
    },
    Water {
        @Override
        public String toString() {
            return "Water";
        }
    },
    Electric {
        @Override
        public String toString() {
            return "Electric";
        }
    },
    Ground {
        @Override
        public String toString() {
            return "Ground";
        }
    },
    Ice {
        @Override
        public String toString() {
            return "Ice";
        }
    };
}
