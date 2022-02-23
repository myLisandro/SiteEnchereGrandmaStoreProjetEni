/*
 * Team GrandMa's Squad - ENI
 * Script JS pour affichage page enchère en fonction
 * de l'état de la vente
 */
 var etatVente = document.getElementById("etatVente").value;
 var formulaire = document.getElementById("formulaireEncherir");
 var telephoneVendeur = document.getElementById("telephoneVendeur");
 var pseudoUtilisateur = document.getElementById("pseudoUtilisateur");
 var pseudoEncheriste = document.getElementById("pseudoEncheriste");
 var detailVente = document.getElementById("detailVente");
 var venteRemportee = document.getElementById("venteRemportee");

 if(etatVente == "CLOTURE"){
     if(pseudoUtilisateur == pseudoEncheriste) {
         detailVente.hidden = false;
         venteRemportee.hidden = true;
     } else {
         detailVente.hidden = true;
         venteRemportee.hidden = false;
     }
        formulaire.disabled=true;
     formulaire.hidden = true;
     telephoneVendeur.hidden = false;
     
 } else if (etatVente == "ENCOURS") {
     telephoneVendeur.hidden = true;
     venteRemportee.hidden = true;
     formulaire.disabled= false;
     formulaire.hidden = false;
 }