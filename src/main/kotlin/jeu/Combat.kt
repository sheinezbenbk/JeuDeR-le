package jeu

import personnage.Personnage

class Combat(
    val jeu :Jeu,
    val monstre: Personnage,
)

{
    var nombreTours: Int = 1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {

        println("\u001B[34m ---Tour de ${this.jeu.joueur.nom} (pv: ${this.jeu.joueur.pointDeVie}) ---")

        println("choissiez une action :")
        val choix = readln().toInt() //saisir une valeur disponible

        when (choix) {
            0 -> {
                "attaquer"
                this.jeu.joueur.attaque(monstre)
                //lorsque la valeur est 0 l'action est attaquer
            }
            1 ->{
                "passer"
                this.jeu.joueur.passer(monstre)

                //l'action sera "defense"
            }
            // ajoutez d'autres actions

            3 ->{
                "boire potion"
                this.jeu.joueur.boirePotion()
                
            //l'action sera "boire une potion"
            }
            else -> "action invalide" //si une valeur ne fait pas parti des actions un message d'erreur sera affiché
        }


        println("\u001b[0m")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("\u001B[31m---Tour de ${monstre.nom} (pv: ${monstre.pointDeVie}) ---")
        //choix d'action pour le monstre
        if (TirageDes(1,100).lance() < 70) {
            println("${monstre.nom} decide de d'attaquer")
            this.monstre.attaque(this.jeu.joueur)  }

        else{
                println("${monstre.nom} decide de passer son tour")
            }

   println("\u001b[0m")

    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${this.jeu.joueur.nom} vs ${monstre.nom}")
        //La vitesse indique qui commence
        var tourJoueur = this.jeu.joueur.vitesse >= this.monstre.vitesse
        //Tant que le joueur et le monstre sont vivants
        while (this.jeu.joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${this.jeu.joueur.nom}: ${this.jeu.joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (this.jeu.joueur.pointDeVie <= 0) {
            println("Game over ! ${this.jeu.joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.jeu.joueur.pointDeVie = this.jeu.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
        }
    }
}