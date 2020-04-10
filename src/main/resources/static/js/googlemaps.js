function autoComplete(){
	
	var input = document.getElementById('address');
	var options = {
      componentRestrictions: {country: "br"}
    };

	var autocomplete = new google.maps.places.Autocomplete(input, options);
}

function initMap() {
	var brasil = {
		lat : -14.239183,
		lng : -51.913726
	};

	var map = new google.maps.Map(document.getElementById('map'), {
		center : brasil,
		scrollwheel : false,
		zoom : 4
	});
}