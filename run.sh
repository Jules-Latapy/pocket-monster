javac     "./pok/src/Attaque.java"    "./pok/src/Choix.java"    "./pok/src/Combat.java"    "./pok/src/Console.java"    "./pok/src/Etat.java"    "./pok/src/Joueur.java"    "./pok/src/Lecteur.java"    "./pok/src/Main.java"    "./pok/src/Monster.java"    "./pok/src/Objet.java"    "./pok/src/RawMonster.java"    "./pok/src/Type.java" -d ./out/production/pok

cd "./out/" || exit 1
java -classpath . Main
cd ".."