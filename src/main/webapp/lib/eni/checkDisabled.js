/*
 * Team GrandMa's Squad - ENI
 * Script JS pour disabled mes Checkboxs 
 * selon selection du Radio Bouton (Vente / Achat)

 */
	var achats = document.getElementById("achats");
	var ventes = document.getElementById("ventes");

    var enchOuvertes = document.getElementById("enchOuvertes");
    var mesEnch = document.getElementById("mesEnch");
    var enchRemportees = document.getElementById("enchRemportees");

    var ventesEnCours = document.getElementById("ventesEnCours");
    var ventesNonDebut = document.getElementById("ventesNonDebut");
    var ventesTerminees = document.getElementById("ventesTerminees");

	achats.checked = true;
	ventes.checked = false;
    ventesEnCours.disabled = true;
    ventesNonDebut.disabled = true;
    ventesTerminees.disabled = true;
	
function onClicCheckAchat() {
	achats.checked = true;
	ventes.checked = false;
	
}

function onClicCheckVente(){
	achats.checked = false;
	ventes.checked = true;
}

function onClicCheckVente() {
	achats.checked = false;
	vente.checked = true;
}

function onClickVentes() {
  	enchOuvertes.checked = false;
 	enchOuvertes.disabled = true;
 	
	mesEnch.checked = false;
    mesEnch.disabled = true;
	 
 	enchRemportees.checked = false;
    enchRemportees.disabled = true;


    ventesEnCours.disabled = false;
    ventesNonDebut.disabled = false;
    ventesTerminees.disabled = false;


}


function onClickAchats() {
	ventesEnCours.checked = false;
    ventesEnCours.disabled = true;

	ventesNonDebut.checked = false;
    ventesNonDebut.disabled = true;
	
	ventesTerminees.checked = false;
    ventesTerminees.disabled = true;
    
	enchOuvertes.disabled = false;
    mesEnch.disabled = false;
    enchRemportees.disabled = false;

}