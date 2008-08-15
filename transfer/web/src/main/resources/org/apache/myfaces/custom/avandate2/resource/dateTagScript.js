
function getClickedElement(evt) {
	evt = evt || window.event;
	var obj = evt.target || evt.srcElement;
	return obj.nodeName;
}

function bodyClick(evt) {
	var nodeName = getClickedElement(evt);

	if ((nodeName != 'IMG') && (nodeName != 'A'))
		hideAllCalendars();
}

var jscalendarImgDir=""
function jscalendarSetImageDirectory(dir){ 
    jscalendarImgDir = dir;
}

function clearInput(id){
	if(document.getElementById(id))
		document.getElementById(id).value='';
}