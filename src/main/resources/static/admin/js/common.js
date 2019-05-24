$(function(){
  // ウィジウィグ
  $("#editor").trumbowyg({
    btns: [
        ['viewHTML'],
        ['undo', 'redo'], // Only supported in Blink browsers
        ['formatting'],
        ['strong', 'em', 'del'],
        ['superscript', 'subscript'],
        ['link'],
        ['insertImage'],
        ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
        ['unorderedList', 'orderedList'],
        ['horizontalRule'],
        ['removeformat'],
        ['highlight'],
        ['fullscreen']
    ]
  });

  // テキストをクリックしたときにsubmit
  textSubmit(".text-submit");

  // チェックの選択
  propCheck(".check-delete");
});


// テキストをクリックしたときにsubmitする
function textSubmit(className){
  var className;
  $(className).on('click', function(){
    $(this).parents('form').submit();

    return false;
  });
}

// 選択を押したときにすべての行のチェックボックスのつけ外しを行う
function propCheck(className){
  // 選択が押されたときの挙動
  $(".check-all").on('click', function(){
    // チェックの選択/解除の状態を調べる 0:選択 1:解除
    var value = $(this).attr("data-value");

    // 選択がなかったとき
    if (value == 0) {
      // 全てにチェックを選択
      $(className).prop("checked", true);

      // data-valueを1に変更し、文字列を解除に変更
      $(this).attr("data-value", 1).text("解除");
    }

    // 選択があったとき
    if(value == 1) {
      // 全てのチェックを解除
      $(className).prop("checked", false);

      // data-valueを0に変更、文字列を選択に変更
      $(this).attr("data-value", 0).text("選択");
    }

    return false;
  });

  // チェックボックスが押されたときの挙動
  $(className).on("click", function(){
    // 選択/解除の要素
    var $checkAll = $(this).parents("tbody").prev().find(".check-all");

    // 全てのチェックボックスの値
    $(className).parents("tr").each(function(){
      var check = $(this).find(className).prop("checked");
      // チェックが存在したら1を返す
      if (check == false) {
    	  value = 0;
      } else {
    	  value = 1;
      }
      return value;
    });

    // チェックボックスの選択/解除の状態を調べる 0:選択 1:解除
    if (value == 0) {
    	$checkAll.attr("data-value", 1).text("選択");
    }

    if (value == 1) {
    	$checkAll.attr("data-value", 0).text("解除");
    }

  });
}