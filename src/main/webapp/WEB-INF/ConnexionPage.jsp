<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Accueil</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
        
        <!-- Navbar & Hero Start -->
        <div class="container-xxl bg-white py-5">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
				<a href="/ProjetEncheres/accueil"
					class="navbar-brand p-0"> <img src="img/logo1.gif"
					alt="Grandma's Store">
				</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0 pe-4">
                        <a href="http://localhost:8080/ProjetEncheres/AccueilServlet" class="nav-item nav-link">Enchères</a>
                    </div>

                </div>
            </nav>
        </div>
        <!-- Navbar & Hero End -->
        
        <!-- Connexion Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Compte</h5>
                    <h1 class="mb-5">S'identifier</h1>
                </div>
                <div class="row g-5">
                    <div class="wow fadeInUp" data-wow-delay="0.2s">
                        <form action="Connexion" method="POST">
                            <div class="row g-3">

                                <div class="col-12">
                                    <div class="top-margin">
                                        <label>Identifiant <span class="text-danger">*</span></label>
                                        <input class="form-control" type="text" name="identifiant" value="${identifiant}" />
                                    </div>
                                    <div class="top-margin">
                                        <label>Mot de passe <span class="text-danger">*</span></label>
                                        <input class="form-control" type="password" name="motDePasse" value="${motDePasse}" />
                                    </div>
                                </div>
                                <div class="col-12">
                                    <b><a href="/ProjetEncheres/MotDePasseOublie" target="_blank">Mot de passe oublié ?</a></b>
                                                                      
                                </div>
                                <div class="col-12">
                                    <input type="submit" name="Connexion" value="Connexion" class="btn btn-primary" />

                                    <input type="submit" name="Creation Compte" value="S'inscrire" class="btn btn-primary">
                               		<p><strong> ${Inscription.messageInscriptionOk } </strong></p>
                                    <p><strong> ${message} </strong></p>
                               
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Connexion End -->
    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer wow fadeIn" data-wow-delay="0.1s">
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
                            <a href="/ProjetEncheres/AccueilConnecte">Enchères</a>
                            <a href="/ProjetEncheres/NouvelleVente">Vendre un article</a>
                            <a href="/ProjetEncheres/AfficherPofilServlet">Mon profil</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/counterup/counterup.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    <!--  SCRIPT POUR DISABLED LES BOUTONS -->
    <script src="lib/eni/checkDisabled.js"></script>


    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>