var tableProvinsi = {
	create: function(){
          $.ajax({
          url: '/provinsi/get-all',
          method : 'get',
          contentType:'application/json',
          success: function(res, status, xhr){
          data= "";
          $.each(res, function(i, v){
          data += "<tr>";
          data += "<td>" + v.id + "</td>";
          data += "<td>" + v.namaProvinsi + "</td>";
          data += "<td>" + v.kodeProvinsi + "</td>";
		  data += '<td><a class="btn btn-warning" onClick="formProvinsi.setEditData('+v.id+')">edit</a> <div class="pb-1"></div> <a class="btn btn-danger" onClick="formProvinsi.delete('+v.id+')">delete</a>';
          data += "<tr>";
          });
          $('#table-provinsi').append(data);
            console.log(res)
            },
		error : function(e){
			alert(e);
			console.log(e);
		}
      })
	}
}


var formProvinsi = {
	
	save: function(){
	var actions;
	var provinsi = {};
	
	provinsi["namaProvinsi"] = $('#namaProvinsi').val();
	provinsi["kodeProvinsi"] = $('#kodeProvinsi').val();
	
	
	
	$.ajax({
		method: 'post',
		url : '/provinsi/post',
		contentType : 'application/json',
		data : JSON.stringify(provinsi),
		success: function(res)
	{
	console.log(res)
	}	
  })
 },


	setEditData: function(id) {
		$.ajax({
			method:'get',
			url:'/provinsi/get-by-id/' + id,
			contentType: 'application/json',
			type: 'json',
			success: function(res) {
				console.log(res)
				$('#id').val(res.id)
				$('#kodeProvinsi').val(res.kodeProvinsi)
				$('#namaProvinsi').val(res.namaProvinsi)
				
				$('#btn-submit').off('click').on('click', function() {
					formProvinsi.edit(res.id);
					tableProvinsi.create;
						$('#btn-submit').off('click').on('click', function() {
							formProvinsi.save();
						})
				})
			}
		})
	},

	edit:function(id){
		var provinsi={}
		provinsi["id"] = $('#id').val();
		provinsi["namaProvinsi"] = $('#namaProvinsi').val();
		provinsi["kodeProvinsi"] = $('#kodeProvinsi').val();
		
		$.ajax({
		method: 'put',
		url : '/provinsi/put/'+id,
		contentType : 'application/json',
		data: JSON.stringify(provinsi),
		success: function(res)
	{
	console.log(res);
		$('')
	}	
	})},
	
	
	
	delete:function(id){
		$.ajax({
		method: 'delete',
		url : '/provinsi/delete/'+id,
		success: function(res){
			alert('Deleted!');
			location.reload();
		}
		})
	}
}