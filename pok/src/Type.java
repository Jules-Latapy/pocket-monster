public enum Type {
    FEU,
    EAU,
    TERRE,
    INSECTE,
    PLANTE,
    FOUDRE;

    public boolean estFaible(Type t2) {
        switch (this) {
            case FEU:
                if (t2==EAU)
                    return true;
            case EAU:
                if (t2==FOUDRE)
                    return true;
            case FOUDRE:
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
            case EAU:
                if (t2==FEU)
                    return true;
            case FOUDRE:
                if (t2==EAU)
                    return true;
            case TERRE:
                if (t2==FOUDRE)
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
