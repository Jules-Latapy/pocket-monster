public enum Type {
    FEU("Burn"),
    WATER("Flood", "Fall"),
    TERRE("Hid"),
    INSECTE("Poison"),
    PLANTE("Heal"),
    ELECTRIC("Paralysis");

    public String[] getAttribute() {
        return attribute;
    }

    private final String[] attribute;

    Type(String... attribute) {
        this.attribute=attribute;
    }

    public boolean estFaible(Type t2) {
        switch (this) {
            case FEU:
                if (t2== WATER)
                    return true;
            case WATER:
                if (t2== ELECTRIC)
                    return true;
            case ELECTRIC:
                if (t2==TERRE)
                    return true;
            case TERRE:
                if (t2==PLANTE || t2==INSECTE)
                    return true;
            case PLANTE:
            case INSECTE:
                if (t2==FEU)
                    return true;
            default:
                return false;
        }
    }

    public boolean estFort(Type t2) {
        switch (this) {
            case WATER:
                if (t2==FEU)
                    return true;
            case ELECTRIC:
                if (t2== WATER)
                    return true;
            case TERRE:
                if (t2== ELECTRIC)
                    return true;
            case FEU:
                if (t2==PLANTE || t2==INSECTE)
                    return true;
            case PLANTE:
            case INSECTE:
                if (t2==TERRE)
                    return true;
            default:
                return false;
        }
    }
}
