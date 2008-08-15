// [dFilter] - A Numerical Input Mask for JavaScript
// Written By Dwayne Forehand - March 27th, 2003
// Please reuse & redistribute while keeping this notice.
 
var dFilterStep
var inputValue = '';

function padLeft(str, padder,fixSize){
	var newStr=str;
	for(i=0;i<fixSize-str.length;i++){
		newStr=padder+newStr;
	}
	return newStr;
}

function updateHiddenDateValue(hiddenFieldId){
	
	hiddenObj = document.getElementById(hiddenFieldId);
	y_Obj = document.getElementById(hiddenFieldId+'_y');
	m_Obj = document.getElementById(hiddenFieldId+'_m');
	d_Obj = document.getElementById(hiddenFieldId+'_d');
	h_Obj = document.getElementById(hiddenFieldId+'_h');
	n_Obj = document.getElementById(hiddenFieldId+'_n');
	s_Obj = document.getElementById(hiddenFieldId+'_s');
	if(m_Obj && m_Obj.value.length == 1){
		m_Obj.value = '0'+m_Obj.value;
	}
	if(d_Obj && d_Obj.value.length == 1){
		d_Obj.value = '0'+ d_Obj.value;
	}
	if(y_Obj){
		hiddenObj.value = y_Obj.value;
	}
	if(m_Obj){
		hiddenObj.value = hiddenObj.value + "/" + m_Obj.value;
	}
	if(d_Obj){
		hiddenObj.value = hiddenObj.value + "/" + d_Obj.value;
	}
	if(!y_Obj){ 
		hiddenObj.value='';
	}
	if(h_Obj && h_Obj.value.length == 1){
		h_Obj.value = '0'+ h_Obj.value;
	}
	if(n_Obj && n_Obj.value.length == 1){
		n_Obj.value = '0'+ n_Obj.value;
	}
	if(s_Obj && s_Obj.value.length == 1){
		s_Obj.value = '0'+ s_Obj.value;
	}
	if(h_Obj){
		if(hiddenObj.value!='')
			hiddenObj.value=hiddenObj.value+" ";
		hiddenObj.value=hiddenObj.value+padLeft(h_Obj.value,'0',2);
	}
	if(n_Obj){
		hiddenObj.value=hiddenObj.value+":"+padLeft(n_Obj.value,'0',2);
	}
	if(s_Obj){
		hiddenObj.value=hiddenObj.value+":"+padLeft(s_Obj.value,'0',2);
	}
}

function keyFilter(e,textbox){
	if (window.event)
		key = window.event.keyCode;
	else
		key=e.which;
	
	if((key<47 || key> 58) && key!=8 && key!=9 && key!=37 && key!=39 && key !=46){
		if (window.event)
			window.event.keyCode=null;
		else{
			e.preventDefault();
		}
	}
	if(document.selection && document.selection.createRange().text != ''){ // IS IE
		return;
	}
	
	else{ // Not IE.. assume Mozilla?
		var startPos = textbox.selectionStart;
		var endPos = textbox.selectionEnd;
		if(startPos!= endPos)
			return;
	}
	
	if((key>47 && key< 58)){
		var newValue=textbox.value+(key-48);
		var textBoxType = textbox.name.substring((textbox.name.length-1),textbox.name.length);
		if((textBoxType=='m' && newValue>12)
			||(textBoxType=='d' && newValue>31)
			||(textBoxType=='h' && newValue>23)
			||(textBoxType=='n' && newValue>59)
			||(textBoxType=='s' && newValue>59)){
			if (window.event)
				window.event.keyCode=null;
			else{
				e.preventDefault();
			}
		}
	}
}

function keyEnter(key,textbox,size)
{
	if(document.selection && document.selection.createRange().text != ''){ // IS IE
		return;
	}
	
	else{ // Not IE.. assume Mozilla?
	var startPos = textbox.selectionStart;
	var endPos = textbox.selectionEnd;
	if(startPos!= endPos)
		return;
	}
	
	var key;
	if (window.event)
	    key = window.event.keyCode;
	if(key>95 && key<106)
		key=key-48;
	var textBoxMainName = textbox.name.substring(0,(textbox.name.length-1));
	var textBoxType = textbox.name.substring((textbox.name.length-1),textbox.name.length);
	var value=textbox.value;
	
	if (value.length == size && key>47 && key<58){
		if(textBoxType == 'y'){
			document.getElementById(textBoxMainName+'m').focus();
		}else if(textBoxType == 'm'){
			document.getElementById(textBoxMainName+'d').focus();				
		}else if(textBoxType == 'd'){
			var h_input=document.getElementById(textBoxMainName+'h');
			if(h_input)h_input.focus();				
		}else if(textBoxType == 'h'){
			var n_input=document.getElementById(textBoxMainName+'n');
			if(n_input)n_input.focus();				
		}else if(textBoxType == 'n'){
			var s_input=document.getElementById(textBoxMainName+'s');
			if(s_input)s_input.focus();				
		}
	}else if (value.length == 0 && key == 8){
		textbox.value='';
		if(textBoxType == 'd'){
			m_input=document.getElementById(textBoxMainName+'m');
			m_input.focus();				
			m_input.value=m_input.value.substring(0,2);
		}else if(textBoxType == 'm'){
			y_input=document.getElementById(textBoxMainName+'y');
			y_input.focus();				
			y_input.value=y_input.value.substring(0,4);
		}else if(textBoxType == 's'){
			n_input=document.getElementById(textBoxMainName+'n');
			n_input.focus();				
			n_input.value=n_input.value.substring(0,2);
		}else if(textBoxType == 'n'){
			h_input=document.getElementById(textBoxMainName+'h');
			h_input.focus();				
			h_input.value=h_input.value.substring(0,2);
		}else if(textBoxType == 'h'){
			d_input=document.getElementById(textBoxMainName+'d');
			d_input.focus();				
			d_input.value=d_input.value.substring(0,2);
		}
	}
}

