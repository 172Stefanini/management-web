/**
 * At this file we put all functions used for reload the applications combobox
 * */

function changePerson(){
	
	if(dijit.byId('_project_id')){
		$.ajax({
		  url: "/management/timesheets?updateProjectCombo&personId=" + dijit.byId('_person_id').get('value')
		}).done(function( data ) {
			
			clearCombo(dijit.byId("_project_id"));
			
			addDataToComboNameProperty(dijit.byId('_project_id'), data);
			
			if(dijit.byId('_category_id')){
				$.ajax({
					  url: "/management/timesheets?updateCategoryCombo&projectId=" + dijit.byId('_project_id').get('value')
				}).done(function( data ) {
					
					clearCombo(dijit.byId("_category_id"));
					
					addDataToComboDescriptionProperty(dijit.byId('_category_id'), data);
					
					if(dijit.byId('_task_id')){
						$.ajax({
							  url: "/management/timesheets?updateTaskCombo&categoryId=" + dijit.byId('_category_id').get('value')
						}).done(function( data ) {
							
							clearCombo(dijit.byId("_task_id"));
							
							addDataToComboDescriptionProperty(dijit.byId('_task_id'), data);
						});	
					}
				});
			}
		});
	}

	if(dijit.byId('_workGroup_id')){
		$.ajax({
			  url: "/management/timesheets?updateWorkGroupCombo&personId=" + dijit.byId('_person_id').get('value')
		}).done(function( data ) {
			
			clearCombo(dijit.byId("_workGroup_id"));
			
			addDataToComboNameProperty(dijit.byId('_workGroup_id'), data);
		});
	}
}

function changeProject(){
	if(dijit.byId('_category_id')){
		$.ajax({
			  url: "/management/timesheets?updateCategoryCombo&projectId=" + dijit.byId('_project_id').get('value')
		}).done(function( data ) {
			
			clearCombo(dijit.byId("_category_id"));
			
			addDataToComboDescriptionProperty(dijit.byId('_category_id'), data);
			
			if(dijit.byId('_task_id')){
				$.ajax({
					  url: "/management/timesheets?updateTaskCombo&categoryId=" + dijit.byId('_category_id').get('value')
				}).done(function( data ) {
					
					clearCombo(dijit.byId("_task_id"));
					
					addDataToComboDescriptionProperty(dijit.byId('_task_id'), data);
				});	
			}
		});
	}
}

function changeCategory(){
	if(dijit.byId('_task_id')){
		$.ajax({
			  url: "/management/timesheets?updateTaskCombo&categoryId=" + dijit.byId('_category_id').get('value')
		}).done(function( data ) {
			
			clearCombo(dijit.byId("_task_id"));
			
			addDataToComboDescriptionProperty(dijit.byId('_task_id'), data);
		});	
	}
}


function clearCombo(combo){
	var size = combo.store.root.length;
	for(var i=size; i>=0; i--){   
		combo.store.root.remove(combo.store.root.children[size-1]);
	}
}

function addDataToComboDescriptionProperty(combo, data){
	for(var index in data){
		combo.store.root.add(dojo.create("option",{ value: data[index].id, innerHTML: data[index].description}));
	}
	if(data!="")
		combo.set('value', data[0].id); 
	else
		combo.set('value', null);
}

function addDataToComboNameProperty(combo, data){
	for(var index in data){
		combo.store.root.add(dojo.create("option",{ value: data[index].id, innerHTML: data[index].name}));
	}
	if(data!="")
		combo.set('value', data[0].id);
	else
		combo.set('value', null);
}