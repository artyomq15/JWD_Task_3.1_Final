function toggleThemes(str) {
    var x = document.getElementById(str);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}