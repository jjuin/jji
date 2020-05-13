
function inputData(){
    var book = $('#form').serialize();
    console.log(book);
    
    $.ajax({
        url: "/book/book2",
        data: book,
        type:"POST",
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        dataType: 'html',
    	success: function (result) {
            if (result){
            	$("#list").html(result);
            	//$("#list").replaceWith(result);
            }
        }

//    }).done(function (fragment) {
//    	
//         $("#list").replaceWith(fragment);
    });
    
}
