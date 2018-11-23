function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

(function($) {
  $(".sidenav a").on('click', function() {
		closeNav();
	});

})(jQuery);

