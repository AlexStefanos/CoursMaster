$(document).ready(function() {

    $("input").click(function() {

        eval($("#montexte").val());

    });

    $(function() {
        $("#lettrejaune").draggable();
        $("#lettrerouge").draggable();
        $("#lettrebleu").draggable();
        $("#boitejaune" ).droppable({
            drop: function( event, ui ) {
                if(ui.draggable.attr("id") =="lettrejaune"){
                    console.log("Depose dans la boite jaune");
                    $( this ).attr('src','ressources/images/boite_jaune_ouverte.jpg');
                }
              }
        });
        $("#boiterouge" ).droppable({
            drop: function( event, ui ) {
                if(ui.draggable.attr("id") =="lettrerouge"){
                    console.log("Depose dans la boite rouge");
                    $( this ).attr('src','ressources/images/boite_rouge_ouverte.jpg');
                }
              }
        });
        $("#boitebleu" ).droppable({
            drop: function( event, ui ) {
                if(ui.draggable.attr("id") =="lettrebleu"){
                    console.log("Depose dans la boite bleu");
                    $( this ).attr('src','ressources/images/boite_bleu_ouverte.jpg');
                }
              }
        });
    });
});