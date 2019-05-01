function myFunction() {
  var x = document.getElementById("chatframe");
  var y = document.getElementById("mybutton");

  if (x.style.display === "none") {
    x.style.display = "block";
    y.src="/img/cancel.png";
    y.height = "20";
    y.width = "20";
  } else {
    x.style.display = "none";
    y.src="/img/planetch.png";
    y.height = "50";
    y.width = "50";
  }

}