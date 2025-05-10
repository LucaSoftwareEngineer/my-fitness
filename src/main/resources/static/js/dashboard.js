function goTo(url) {
	var idUser = btoa($("#idUser").val());
	location.href = url + "?id=" + idUser;
}