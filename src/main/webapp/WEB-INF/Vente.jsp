<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Vendre un nouvel article</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<div class="container-xxl bg-white p-0">
		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- Spinner End -->

		<!-- Navbar & Hero Start -->
		<div class="container-xxl bg-white py-5">
			<nav
				class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">

				<a href="/ProjetEncheres/AccueilConnecte" class="navbar-brand p-0">
					<img src="img/logo1.gif" alt="Grandma's Store">

				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<div class="navbar-nav ms-auto py-0 pe-4">
						<a href="/ProjetEncheres/AccueilConnecte"
							class="nav-item nav-link">Enchères</a> <a
							href="/ProjetEncheres/NouvelleVente" class="nav-item nav-link">Vendre
							un article</a> <a href="/ProjetEncheres/AfficherPofilServlet"
							class="nav-item nav-link">Mon profil</a>
					</div>

					<form action="AccueilConnecte" method="POST">
						<input type="submit" name="deconnexion" value="Deconnexion"
							class="btn btn-primary py-2 px-4">
					</form>
				</div>
			</nav>
		</div>
		<!-- Navbar & Hero End -->


		<!-- Formulaire nouvel article -->
		<div class="container-xxl py-5 px-0 wow fadeInUp"
			data-wow-delay="0.1s">
			<div class="row g-0">

				<div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
					<h5
						class="section-title ff-secondary text-start text-primary fw-normal">Enchères</h5>
					<h1 class="mb-5">Ajouter un article</h1>


					<form action="NouvelleVente" method="POST">

						<div class="row top-margin px-5">

							<label for="nom_article">Article : <span
								class="text-danger">*</span></label> <input type="text"
								name="nom_article" value="${model.article.nom}" /> <label
								for="description">Description</label>
							<textarea class="form-control" id="description"
								name="description" rows="3"></textarea>
							<label for="categorie"> Categorie: </label> <select
								name="categorie">
								<option></option>
								<option value="1"><a><p>Informatique</p></a></option>
								<option value="2"><a><p>Ameublement</p></a></option>
								<option value="3"><a><p>Vêtement</p></a></option>
								<option value="4"><a><p>Sports et loisirs</p></a></option>
							</select> <label for="photo_article"> Photo de l'article: </label>
							<button type="button" name="photo_article" disabled>Télécharger
							</button>
							<label for="prix_article"> Mise à prix (en point): </label> <input
								type="number" name="prix_article"
								value="${model.article.miseAPrix}" /> <label
								for="debut_enchere"> Début de l'enchère: </label> <input
								type="date" name="debut_enchere"
								value="${model.article.dateDebutEncheres}" /> <label
								for="fin_enchere"> Fin de l'enchère: </label> <input type="date"
								name="fin_enchere" value="${model.article.dateFinEncheres}" />
							<fieldset>
								<legend>Retrait</legend>
								<label for="rue"> Rue: </label> <input type="text" name="rue"
									value="${model.retrait.lieu}" /> <label for="cp"> Code
									postal: </label> <input type="text" name="cp"
									value="${model.retrait.codePostal}" /> <label for="ville">
									Ville: </label> <input type="text" name="ville"
									value="${model.retrait.ville}" />
							</fieldset>

							<div class="row top-margin pt-5">
								<div class="col-sm-6">
									<input type="submit" name="enregistrer"
										class="btn btn-primary w-100 py-3" value="Enregistrer" />
								</div>
								<div class="col-sm-6">
									<input type="submit" name="annuler"
										class="btn btn-primary w-100 py-3" value="Annuler" />
								</div>
								<hr />
								<input type="submit" class="btn btn-primary w-100 py-3"
									name="retour_accueil" value="Retourner aux enchères" />

							</div>
					</form>
					<p>${message}</p>
				</div>
			</div>
		</div>
		<!-- Fin nouvel article -->

		<!-- Footer Start -->
		<div class="container-fluid bg-dark text-light footer wow fadeIn"
			data-wow-delay="0.1s">
			<div class="container">
				<div class="copyright">
					<div class="row">
					<div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
						<a class="border-bottom" href="#">GrandMa'S Store</a>, Tous droits
						réservés.
						
						 Site créé par <a href="https://www.linkedin.com/in/roxane-houlgatte-57334097/" target="_blank"> Roxane - </a> 
								  <a href="https://www.linkedin.com/in/claire-goarnisson/" target="_blank"> Claire - </a> 
								  <a href="https://www.linkedin.com/in/ang%C3%A9lo-fernandes-85b32b226/" target="_blank"> Angélo</a>
						

						<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
						<br>Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML
							Codex</a> Distributed By <a class="border-bottom"
							href="https://themewagon.com" target="_blank">ThemeWagon</a>
					</div>
						<div class="col-md-6 text-center text-md-end">
							<div class="footer-menu">
								<a href="/ProjetEncheres/AccueilConnecte">Enchères</a> <a
									href="/ProjetEncheres/NouvelleVente">Vendre un article</a> <a
									href="/ProjetEncheres/AfficherPofilServlet">Mon profil</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer End -->

		<!-- Back to Top -->
		<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
			class="bi bi-arrow-up"></i></a>

		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="lib/wow/wow.min.js"></script>
		<script src="lib/easing/easing.min.js"></script>
		<script src="lib/waypoints/waypoints.min.js"></script>
		<script src="lib/counterup/counterup.min.js"></script>
		<script src="lib/owlcarousel/owl.carousel.min.js"></script>
		<script src="lib/tempusdominus/js/moment.min.js"></script>
		<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
		<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

		<!-- Template Javascript -->
		<script src="js/main.js"></script>
</body>
</html>