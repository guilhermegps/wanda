function convertObjToFormData(obj){
	let form_data = new FormData();
	
	for (let key in obj) {
	    form_data.append(key, obj[key]);
	}

    return form_data;
}

function convertFormToObj($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function deleteBlank(data){
	for (propriedade in data) {
		if(isBlank(data[propriedade]))
			delete data[propriedade];
	};

	return data;
}

function isBlank(valor){
	if(typeof valor === 'string')
		valor = valor.trim();
	if(typeof valor === 'boolean')
		return false;
	
	if(valor==null
			|| valor==''
			|| valor==[])
		return true;
	
	return false;
}