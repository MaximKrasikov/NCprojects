$(".polzunok-4").slider({
    min: 0,
    max: 10,
    value: 5,
    range: "min",
    animate: "fast",
    slide : function(event, ui) {
        $(".polzunok-4 span").text(ui.value);
    },
    change : function(event, ui) {
        $(".polzunok-4 span").text(ui.value);
    }
});
$(".polzunok-4 span").text($(".polzunok-4").slider("value"));
$(".polzunok-4")
    .before("<div class='polzunok-4-left'><i class='fa fa-chevron-left'></i></div>")
    .before("<div class='polzunok-bg-4'></div>")
    .after("<div class='polzunok-4-right'><i class='fa fa-chevron-right'></i></div>");
$(".polzunok-4-left").click(function() {
    $(".polzunok-4").slider( "value", $(".polzunok-4").slider( "value" ) - 1 );
});
$(".polzunok-4-right").click(function() {
    $(".polzunok-4").slider( "value", $(".polzunok-4").slider( "value" ) + 1 );
});