var calendarType = 'gregorian';

var gregorianObj;
var yxMonths;
var yxDays;
var yxLinks;
var dayCorrection;
var localeType;
var calendar_direction;

var fontFace="Tahoma";
var fontSize=11;

var titleWidth=90;
var titleMode=1;
var dayWidth=12;
var dayDigits=1;

var titleColor="#CCCC99";
var daysColor="#EEBB89";
var bodyColor="#FFFFCC";
var dayColor="#FFFF99";
var currentDayColor="#FFCC99";
var footColor="#CCCC99";
var borderColor="#333333";

var titleFontColor = "#333333";
var daysFontColor = "#333333";
var dayFontColor = "#333333";
var currentDayFontColor = "#ffffff";
var footFontColor = "#333333";
var convertStyle = "color:#000;font-family:tahoma;font-size:10px;text-decoration:none;";
var calendarTypeLableTdStyle= "background-color:#fff;border:1px #999999 solid;";
var calFormat = "yyyy/mm/dd";

var weekDay = 0;


var flat = false;

var disableWeekend = false;
// ------

// codes
var calWidth=200, calHeight=200, calOffsetX=0, calOffsetY=0;
var calWin=null;
var winX=0, winY=0;
var cal="cal";
var cals=new Array();
var currentCal=null;
var enableSwitch=true;


var nav=navigator.userAgent.toLowerCase();
var isOpera=(nav.indexOf("opera")!=-1)?true:false;
var isOpera5=(nav.indexOf("opera 5")!=-1 || nav.indexOf("opera/5")!=-1)?true:false;
var isOpera6=(isOpera && parseInt(navigator.appVersion)>=6)?true:false;
var isN6=(nav.indexOf("gecko")!=-1);
var isN4=(document.layers)?true:false;
var isMac=(nav.indexOf("mac")!=-1);
var isIE=(document.all && !isOpera && (!isMac || navigator.appVersion.indexOf("MSIE 4")==-1))?true:false;

if (isN4) {
  fontSize+=2;
}

var span2="</span>";

setMainCalendarParamForCalendarType();

function setMainCalendarParamForCalendarType(){
	if(calendarType == 'gregorian'){
		
		gregorianObj = new Date();	
		if(localeType == 'fa'){
			yxMonths=new Array("ژانویه", "فوریه", "مارچ", "آپریل", "می", "جون", "جولای", "آگوست", "سپتامبر", "اکتبر", "نوامبر", "دسامبر");
			yxDays=new Array("یکشنبه","دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه","شنبه","یکشنبه");
			yxLinks=new Array("[پاک کردن]", "[بستن]");
			toDayLabel = 'امروز';
			calendar_direction='rtl';

		}else{
			yxMonths=new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
			yxDays=new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
			yxLinks=new Array("[close]", "[clear]");
			toDayLabel = 'Today';
			calendar_direction='ltr';
		}
		dayCorrection = 0;
	}else if(calendarType == 'hijri'){
		gregorianObj = new GregorianToSolar(0, 0, 0);
		dayCorrection = 4;
		if(localeType == 'en'){
			yxMonths=new Array("Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar", "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand");
			yxDays=new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
			yxLinks=new Array("[close]", "[clear]");
			toDayLabel = 'Today';
			calendar_direction='ltr';
		}else{
			yxMonths=new Array("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند");
			yxDays=new Array("شنبه","یکشنبه","دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه","شنبه");
			yxLinks=new Array("[پاک کردن]", "[بستن]");
			toDayLabel = 'امروز';
			calendar_direction='rtl';
		}
	}
}
function showCalendar() {
    currentCal.calIFrameContainer.style.visibility = 'visible';
  	currentCal.calDivContainer.style.visibility = 'visible';
}

function hideCalendar() {
    if (! currentCal.flat) {
    currentCal.calIFrameContainer.style.visibility = 'hidden';
  	currentCal.calDivContainer.style.visibility = 'hidden';
  	}
}

function hideAllCalendars() {
   for (var i = 0; i < cals.length; i ++) {
    if (! currentCal.flat) {
    cals[i].calIFrameContainer.style.visibility = 'hidden';
  	cals[i].calDivContainer.style.visibility = 'hidden';
  	}
	}
}

function span1(tag) {
  return "<span class='"+tag+"'>";
}
function spanx(tag, color) {
  return "."+tag+" { font-family:"+fontFace+"; font-size:"+fontSize+"px; color:"+color+"; }\n";
}

function a1(tag) {
  return "<a class='"+tag+"' href=";
}

function ax(tag, color) {
  return "."+tag+" { text-decoration:none; color:"+color+"; }\n";
}

function calOBJ(name, title, field, form) {

  this.name = name;
  this.title = title;
  this.field = field;
  this.formName = form;
  this.form = null;
  this.flat = flat;
	
	var tmpIframe = document.createElement("IFRAME");
	tmpIframe.id = 'frm' + name;
	tmpIframe.name = 'frm' + name;
	tmpIframe.style.visibility = 'hidden';
  tmpIframe.style.position = 'absolute';
  tmpIframe.style.top = 0;
  tmpIframe.style.left = 0;
  tmpIframe.width = 0;
  tmpIframe.height = 0;
  tmpIframe.style.border = 'none';
  tmpIframe.style.zIndex = 10000;
  document.body.appendChild(tmpIframe);

	var tmpContainer = document.createElement("DIV");
	tmpContainer.id = 'div' + name;
	tmpContainer.name = 'div' + name;
	tmpContainer.style.visibility = 'hidden';
  tmpContainer.style.position = 'absolute';
  tmpContainer.style.top = 0;
  tmpContainer.style.left = 0;
  tmpContainer.width = 0;
  tmpContainer.height = 0;
  tmpContainer.style.border = 'none';
  tmpContainer.style.zIndex = 10001;
  tmpContainer.innerHTML = 'Hooman Behmanesh';
  document.body.appendChild(tmpContainer);
  
  this.calIFrameContainer = tmpIframe;
  this.calDivContainer = tmpContainer;
}

function findPosX()
{
	var fField = eval('document.' + currentCal.formName + '.' + currentCal.field);

	var curleft = 0;
	if (fField.offsetParent)
	{
		while (fField.offsetParent)
		{
			curleft += fField.offsetLeft;
			fField = fField.offsetParent;
		}
	}
	else if (fField.x)
		curleft += fField.x;
	return curleft;
}

function findPosY()
{
	var fField = eval('document.' + currentCal.formName + '.' + currentCal.field);
	var fHeight = fField.offsetHeight;
	
	var curtop = 0;
	if (fField.offsetParent)
	{
		while (fField.offsetParent)
		{
			curtop += fField.offsetTop;
			fField = fField.offsetParent;
		}
	}
	else if (fField.y)
		curtop += fField.y;
	return curtop + fHeight;
}

// Find positioning
function getOffset(obj, dim) {
  var oLeft, oTop, oWidth, oHeight;
  
  
  
  if(dim=="left") 
  {     
    oLeft = obj.offsetLeft;  
    while(obj.offsetParent!=null) 
    {    
      oParent = obj.offsetParent;
      oLeft += oParent.offsetLeft;
      obj = oParent;
    }
    return oLeft;
  }
  else if(dim=="top")
  {
    oTop = obj.offsetTop;
    while(obj.offsetParent!=null) 
    {
      oParent = obj.offsetParent;
      oTop += oParent.offsetTop;
      obj = oParent;
    }
    return oTop;
  }
  else if(dim=="width")
  {
    oWidth = obj.offsetWidth;
    return oWidth;
  }  
  else if(dim=="height")
  {
    oHeight = obj.offsetHeight;
    return oHeight;
  }    
  else
  {
    alert("Error: invalid offset dimension '" + dim + "' in getOffset()");
    return false;
  }
}

function setFont(font, size) {
  if (font != "") {
    fontFace=font;
  }
  if (size > 0) {
    fontSize=size;

    if (isN4) {
      fontSize+=2;
    }
  }
}

function setWidth(tWidth, tMode, dWidth, dDigits) {
  if (tWidth > 0) {
    titleWidth=tWidth;
  }
  if (tMode == 1 || tMode == 2) {
    titleMode=tMode;
  }
  if (dWidth > 0) {
    dayWidth=dWidth;
  }
  if (dDigits > 0) {
    dayDigits=dDigits;
  }
}

function setColor(tColor, dsColor, bColor, dColor, cdColor, fColor, bdColor) {
  if (tColor != "") {
    titleColor=tColor;
  }
  if (dsColor != "") {
    daysColor=dsColor;
  }
  if (bColor != "") {
    bodyColor=bColor;
  }
  if (dColor != "") {
    dayColor=dColor;
  }
  if (cdColor != "") {
    currentDayColor=cdColor;
  }
  if (fColor != "") {
    footColor=fColor;
  }
  if (bdColor != "") {
    borderColor=bdColor;
  }
}

function setFontColor(tColorFont, dsColorFont, dColorFont, cdColorFont, fColorFont) {
  if (tColorFont != "") {
    titleFontColor=tColorFont;
  }
  if (dsColorFont != "") {
    daysFontColor=dsColorFont;
  }
  if (dColorFont != "") {
    dayFontColor=dColorFont;
  }
  if (cdColorFont != "") {
    currentDayFontColor=cdColorFont;
  }
  if (fColorFont != "") {
    footFontColor=fColorFont;
  }
}

function setFormat(format) {
  calFormat = format;
}

function setFlat(flatStatus) {
	flat = flatStatus;
}

function setSize(width, height, ox, oy) {
  if (width > 0) {
    calWidth=width;
  }
  if (height > 0) {
    calHeight=height;
  }

  calOffsetX=ox;
  calOffsetY=oy;
}

function setWeekDay(wDay) {
  if (wDay == 0 || wDay == 1) {
    weekDay = wDay;
  }
}

function setMonthNames(janName, febName, marName, aprName, mayName, junName, julName, augName, sepName, octName, novName, decName) {
  if (janName != "") {
    yxMonths[0] = janName;
  }
  if (febName != "") {
    yxMonths[1] = febName;
  }
  if (marName != "") {
    yxMonths[2] = marName;
  }
  if (aprName != "") {
    yxMonths[3] = aprName;
  }
  if (mayName != "") {
    yxMonths[4] = mayName;
  }
  if (junName != "") {
    yxMonths[5] = junName;
  }
  if (julName != "") {
    yxMonths[6] = julName;
  }
  if (augName != "") {
    yxMonths[7] = augName;
  }
  if (sepName != "") {
    yxMonths[8] = sepName;
  }
  if (octName != "") {
    yxMonths[9] = octName;
  }
  if (novName != "") {
    yxMonths[10] = novName;
  }
  if (decName != "") {
    yxMonths[11] = decName;
  }
}

function setDayNames(sunName, monName, tueName, wedName, thuName, friName, satName) {
  if (sunName != "") {
    yxDays[0] = sunName;
    yxDays[7] = sunName;
  }
  if (monName != "") {
    yxDays[1] = monName;
  }
  if (tueName != "") {
    yxDays[2] = tueName;
  }
  if (wedName != "") {
    yxDays[3] = wedName;
  }
  if (thuName != "") {
    yxDays[4] = thuName;
  }
  if (friName != "") {
    yxDays[5] = friName;
  }
  if (satName != "") {
    yxDays[6] = satName;
  }
}

function setLinkNames(closeLink, clearLink) {
  if (closeLink != "") {
    yxLinks[0] = closeLink;
  }
  if (clearLink != "") {
    yxLinks[1] = clearLink;
  }
}

function addCalendar(name, title, field, form) {
	
  cals[cals.length] = new calOBJ(name, title, field, form);
  
  if (flat){
  	
  		showCal(name);
  }
}

function findCalendar(name) {
  for (var i = 0; i < cals.length; i++) {
    if (cals[i].name == name) {
      if (cals[i].form == null) {
        if (cals[i].formName == "") {
          if (document.forms[0]) {
            cals[i].form = document.forms[0];
          }
        }
        else if (document.forms[cals[i].formName]) {
          cals[i].form = document.forms[cals[i].formName];
        }
      }

      return cals[i];
    }
  }

  return null;
}

function getDayName(y,m,d) {
  var wd=new Date(y,m,d);
  return yxDays[wd.getDay()].substring(0,3);
}

function getMonthFromName(m3) {
  for (var i = 0; i < yxMonths.length; i++) {
    if (yxMonths[i].toLowerCase().substring(0,3) == m3.toLowerCase()) {
      return i;
    }
  }

  return 0;
}

function getFormat() {
  var calF = calFormat;

  calF = calF.replace(/\\/g, '\\\\');
  calF = calF.replace(/\//g, '\\\/');
  calF = calF.replace(/\[/g, '\\\[');
  calF = calF.replace(/\]/g, '\\\]');
  calF = calF.replace(/\(/g, '\\\(');
  calF = calF.replace(/\)/g, '\\\)');
  calF = calF.replace(/\{/g, '\\\{');
  calF = calF.replace(/\}/g, '\\\}');
  calF = calF.replace(/\</g, '\\\<');
  calF = calF.replace(/\>/g, '\\\>');
  calF = calF.replace(/\|/g, '\\\|');
  calF = calF.replace(/\*/g, '\\\*');
  calF = calF.replace(/\?/g, '\\\?');
  calF = calF.replace(/\+/g, '\\\+');
  calF = calF.replace(/\^/g, '\\\^');
  calF = calF.replace(/\$/g, '\\\$');

  calF = calF.replace(/dd/i, '\\d\\d');
  calF = calF.replace(/mm/i, '\\d\\d');
  calF = calF.replace(/yyyy/i, '\\d\\d\\d\\d');
  calF = calF.replace(/day/i, '\\w\\w\\w');
  calF = calF.replace(/mon/i, '\\w\\w\\w');

  return new RegExp(calF);
}

function getDateNumbers(date) {
  var y, m, d;

  var yIdx = calFormat.search(/yyyy/i);
  var mIdx = calFormat.search(/mm/i);
  var m3Idx = calFormat.search(/mon/i);
  var dIdx = calFormat.search(/dd/i);

  y=date.substring(yIdx,yIdx+4)-0;
  if (mIdx != -1) {
    m=date.substring(mIdx,mIdx+2)-1;
  }
  else {
    var m = getMonthFromName(date.substring(m3Idx,m3Idx+3));
  }
  d=date.substring(dIdx,dIdx+2)-0;
  return new Array(y,m,d);
}

function getLeftIE(x,m) {
  var dx=0;
  if (x.tagName=="TD"){
    dx=x.offsetLeft;
  }
  else if (x.tagName=="TABLE") {
    dx=x.offsetLeft;
    if (m) { dx+=(x.cellPadding!=""?parseInt(x.cellPadding):2); m=false; }
  }
  return dx+(x.parentElement.tagName=="BODY"?0:getLeftIE(x.parentElement,m));
}
function getTopIE(x,m) {
  var dy=0;
  if (x.tagName=="TR"){
    dy=x.offsetTop;
  }
  else if (x.tagName=="TABLE") {
    dy=x.offsetTop;
    if (m) { dy+=(x.cellPadding!=""?parseInt(x.cellPadding):2); m=false; }
  }
  return dy+(x.parentElement.tagName=="BODY"?0:getTopIE(x.parentElement,m));
}

function getLeftN4(l) { return l.pageX; }
function getTopN4(l) { return l.pageY; }

function getLeftN6(l) { return l.offsetLeft; }
function getTopN6(l) { return l.offsetTop; }

function lastDay(d) {
	
	if(calendarType == 'gregorian'){
		var yy=d.getFullYear(), mm=d.getMonth() ;
		  for (var i=31; i>=28; i--) {
			var nd=new Date(yy,mm,i);
			if (mm == nd.getMonth() ) {
			  return i;
			}
		  }

	}else if(calendarType == 'hijri'){
		var yy=d.getFullYear(), mm=d.getMonth() + 1;
		if (mm >= 1 && mm <= 6) return 31;
		if (mm >= 7 && mm <= 11) return 30;
		if (mm == 12)
			if (IsLeapYear(dkSolar, yy)) return 30;
		else return 29;

	}  
	
}

function DateDemo(y, m, d)
{
  var d, day, x, s = "Today is: ";
  d = new Date(y, m, d);
  day = d.getDay();
  s += (d.getFullYear() + "/" + (12 - d.getMonth()) + "/" + d.getDate() + " ");
  return(s += x[day]);
}

function firstDay(d) {
	
	if(calendarType == 'gregorian'){
	  var yy=d.getFullYear(), mm=d.getMonth()  ;
	  var fd=new Date(yy,mm,1);
	  var dd = fd.getDay();
	  
	  return dd;
	}else if(calendarType == 'hijri'){
	  var yy=d.getFullYear(), mm=d.getMonth() + 1;
	  var fd=SolarToGregorian(yy, mm, 1);
	  var xxx = new Array("Sunday", "Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday");
	  var dd = fd.getDay() - 3;
	  return dd;
	}  
}

function dayDisplay(i) {
  if (dayDigits == 0) {
    return yxDays[i];
  }
  else {
    return yxDays[i].substring(0,dayDigits);
  }
}

function calTitle(d) {
  var yy=d.getFullYear(), mm=yxMonths[d.getMonth()];
  var s;

  if (titleMode == 2) {
    s="<tr align='center' bgcolor='"+titleColor+"'><td colspan='7'>\n<table dir="+calendar_direction+" cellpadding='0' cellspacing='0' border='0'><tr align='center' valign='middle'><td align='right'>"+span1("title")+"<b>"+a1("titlea")+"'javascript: moveYear(-10)'>&nbsp;&#171;</a>&nbsp;"+a1("titlea")+"'javascript:moveYear(-1)'>&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><b>"+span1("title")+getFarsiNumber(yy)+span2+"</b></td><td align='left'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:moveYear(1)'>&nbsp;&#155;</a>&nbsp;"+a1("titlea")+"'javascript:moveYear(10)'>&#187;&nbsp;</a></b>"+span2+"</td></tr><tr align='center' valign='middle'><td align='right'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:prepMonth("+d.getMonth()+")'>&nbsp;&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><b>"+span1("title")+mm+span2+"</b></td><td align='left'>"+span1("title")+"<b>"+a1("titlea")+"'javascript:nextMonth("+d.getMonth()+")'>&nbsp;&#155;&nbsp;</a></b>"+span2+"</td></tr></table>\n</td></tr><tr align='center' bgcolor='"+daysColor+"'>";
  }
  else {
    s="<tr align='center' bgcolor='"+titleColor+"'><td colspan='7'>\n<table dir="+calendar_direction+" cellpadding='0' cellspacing='0' border='0'><tr align='center' valign='middle'><td>"+span1("title")+"<b>"+a1("titlea")+"'javascript:moveYear(-1)'>&nbsp;&#171;</a>&nbsp;"+a1("titlea")+"'javascript:prepMonth("+d.getMonth()+")'>&#139;&nbsp;</a></b>"+span2+"</td><td width='"+titleWidth+"'><nobr><b>"+span1("title")+mm+" "+getFarsiNumber(yy)+span2+"</b></nobr></td><td>"+span1("title")+"<b>"+a1("titlea")+"'javascript:nextMonth("+d.getMonth()+")'>&nbsp;&#155;</a>&nbsp;"+a1("titlea")+"'javascript:moveYear(1)'>&#187;&nbsp;</a></b>"+span2+"</td></tr></table>\n</td></tr><tr align='center' bgcolor='"+daysColor+"'>";
  }

  for (var i = weekDay; i < weekDay + 7; i ++) {
    s+="<td width='"+dayWidth+"'>"+span1("days")+dayDisplay(i)+span2+"</td>";
  }

  s+="</tr>";

  return s;
}
function showNewCalendar(){
	currentCalValue = currentCal.form[currentCal.field].value;
	if(currentCalValue.substring(0,4) < 1500){
		calendarType == 'hijri';
	}else{
		calendarType == 'gregorian';
	}
	if(calendarType == 'hijri'){
		calendarType = 'gregorian';	
//		newDate = SolarToGregorian(currentCalValue.substring(0,4),currentCalValue.substring(5,7),currentCalValue.substring(8,10));		
//		alert(newDate.getYear());
		currentCal.form[currentCal.field].value = '';
	}else{
		calendarType = 'hijri';	
//		newDate = GregorianToSolar(currentCalValue.substring(0,4),currentCalValue.substring(5,7),currentCalValue.substring(8,10));		
//		alert(newDate);
		currentCal.form[currentCal.field].value = '';
	}
	setMainCalendarParamForCalendarType();
	hideCalendar();
	
	showCal(currentCal.name);
	showCalendar();
}
function calHeader() {
  var header = "<table dir="+calendar_direction+" align='left' border='0' bgcolor='"+borderColor+"' cellspacing='0' cellpadding='1'><tr><td>\n<style type='text/css'>\n"+spanx("title",titleFontColor)+spanx("days",daysFontColor)+spanx("foot",footColor)+spanx("day",dayFontColor)+spanx("currentDay",currentDayFontColor)+ax("titlea",titleFontColor)+ax("daya",dayFontColor)+ax("currenta",currentDayFontColor)+ax("foota",footFontColor)+"</style>\n<table dir="+calendar_direction+" cellspacing='1' cellpadding='3' border='0'>";
  return header;
}

function calFooterClick(d) {
	var cd = gregorianObj;

	cM = cd.getMonth();
	cY = cd.getFullYear();

	pickDate(d);
}

function calFooter() {
	var s = '';
	var cd = gregorianObj;

	var mm=yxMonths[cd.getMonth()];

	s += toDayLabel +':';
	
	s += getFarsiNumber(cd.getDate()) + '&nbsp;';
	s += mm + '&nbsp;';
	s += getFarsiNumber(cd.getFullYear());
	if(localeType == 'fa'){
		if(calendarType == 'hijri'){
			calendarTypeLableVS = 'تبدیل تقویم به میلادی';
		}else{
			calendarTypeLableVS = 'تبدیل به هجری شمسی';
		}
	}else{
		if(calendarType == 'hijri'){
			calendarTypeLableVS = 'Change  to gregorian';
		}else{
			calendarTypeLableVS = 'Change calendar to hijri';
		}
		
	}
	var customFooter= "<tr bgcolor='"+footColor+"'><td colspan='7' align='center'>"+span1("foot")+"<b>"+a1("foota")+"'javascript: calFooterClick("+cd.getDate()+")'>"+s+"</a></b>"+span2;
	if(enableSwitch)
customFooter=customFooter+"<hr>"+"<a href=javascript:void(0); onClick='showNewCalendar()' style='"+convertStyle+"' > <img src='"+jscalendarImgDir+"switch.gif' border='0' align='right'  />"+calendarTypeLableVS+"</a>";
	customFooter=customFooter+"</td></tr></table>\n</td></tr><tr><td style='"+calendarTypeLableTdStyle+"'></td></tr></table>\n";
  return customFooter;
}

function calBody(d,day) {
  var s="", dayCount=1, fd=firstDay(d), ld=lastDay(d);
  
	var arrCal = new Array(6);
	var i, j, k;
	var blnRowHasValue = false;

  if (weekDay > 0 && fd == 0) {
    fd = 7;
  }

  for (i = 0; i < 6; i++) {
    s = "<tr align='center' bgcolor='"+bodyColor+"'>";
		for (j = 0; j < 7; j ++)
        s += "<td>"+span1("day")+"&nbsp;"+span2+"</td>";
    s += "</tr>";

    arrCal[i] = s;
	}

  k = 0;
  for (i = 0; i < 6; i++) {
    blnRowHasValue = false;
    s = "<tr align='center' bgcolor='"+bodyColor+"'>";
    for (j = weekDay - dayCorrection; j < weekDay + 7 - dayCorrection; j++) {
      if (i * 7 + j < fd || dayCount > ld) {
        s += "<td>"+span1("day")+"&nbsp;"+span2+"</td>";
      }
      else {
      	blnRowHasValue = true;
        var bgColor=dayColor;
        var fgTag="day";
        var fgTagA="daya";
        if (dayCount==day) {
          bgColor=currentDayColor;
          fgTag="currentDay";
          fgTagA="currenta";
        }

        if ((j == (weekDay + 6 - dayCorrection)) && (disableWeekend))
        	s += "<td bgcolor='"+bgColor+"'>"+span1(fgTag)+ getFarsiNumber(dayCount++) + span2 + "</td>";
        else
        	s += "<td bgcolor='"+bgColor+"'>"+span1(fgTag)+a1(fgTagA)+"'javascript: pickDate("+dayCount+")'>"+ getFarsiNumber(dayCount++) + "</a>"+span2+"</td>";
      }
    }
    s += "</tr>";

    if (blnRowHasValue)
    	arrCal[k ++] = s;
  }

  s = '';
  for (i = 0; i < 6; i++) {
    s += arrCal[i];
	}

  return s;
}

function moveYear(dy) {
  cY+=dy;
  var nd=new Date(cY,cM,1);
  changeCal(nd);
}

function prepMonth(m) {
  cM=m-1;
  if (cM<0) { cM=11; cY--; }
  var nd=new Date(cY,cM,1);
  changeCal(nd);
  
  
}

function nextMonth(m) {
  cM=m+1;
  if (cM>11) { cM=0; cY++;}
  var nd=new Date(cY,cM,1);
  changeCal(nd);
}

function changeCal(d) {
  var dd = 0;

  if (currentCal != null) {
    var calRE = getFormat();

    if (currentCal.form[currentCal.field].value!="" && calRE.test(currentCal.form[currentCal.field].value)) {
      var cd = getDateNumbers(currentCal.form[currentCal.field].value);
      if (cd[0] == d.getFullYear() && cd[1] == d.getMonth()) {
        dd=cd[2];
      }
    }
    else {
      var cd = gregorianObj;
      if (cd.getFullYear() == d.getFullYear() && cd.getMonth() == d.getMonth()) {
        dd=cd.getDate();
      }
    }
  }

  var calendar=calHeader()+calTitle(d)+calBody(d,dd)+calFooter();

	currentCal.calDivContainer.innerHTML = calendar;
}

function markClick(e) {
  if (isIE() || isOpera6) {
    winX=event.screenX;
    winY=event.screenY;
  }
  else if (isN4 || isN6) {
    winX=e.screenX;
    winY=e.screenY;

    document.routeEvent(e);
  }

  if (isN4 || isN6) {
    document.routeEvent(e);
  }
  else {
    event.cancelBubble=false;
  }

  return true;
}

function getCalendarDivWidth (){
	var tableWidth = "" + currentCal.calDivContainer.getElementsByTagName("table").item(0).offsetWidth;
	if(tableWidth.indexOf('px') > -1){
			return parseInt(tableWidth.substring(0, tableWidth.infexOf('px')));
	} else {
			return tableWidth;
	}
}

function getCalendarDivHight (){
	var tableHeight = "" + currentCal.calDivContainer.getElementsByTagName("table").item(0).offsetHeight;
	if(tableHeight.indexOf('px') > -1){
			return parseInt(tableHeight.substring(0, tableHeight.infexOf('px')));
	} else {
			return tableHeight;
	}
}

function showCal(name) {
  
  var lastCal=currentCal;
  var d, hasCal=false;
  
  

  currentCal = findCalendar(name);

	if (currentCal != null)
		if (currentCal.calDivContainer.style.visibility == 'visible')
			{ hideCalendar(); return; }

	hideAllCalendars();
	
  if (currentCal != null && currentCal.form != null && currentCal.form[currentCal.field+'_y']) {
	  if(currentCal != null && currentCal.form[currentCal.field+'_y'].value.substring(0,4) != ''){
		  if(currentCal.form[currentCal.field+'_y'].value.substring(0,4) <1500){
		  	calendarType = 'hijri';
		  }else{
		  	calendarType = 'gregorian';
		  }
	  }
	  setMainCalendarParamForCalendarType();
    var calRE = getFormat();

    if (currentCal.form[currentCal.field].value!="" && calRE.test(currentCal.form[currentCal.field].value)) {
      var cd = getDateNumbers(currentCal.form[currentCal.field].value);
      d = new Date(cd[0], cd[1], cd[2]);

      cY=cd[0];
      cM=cd[1];
      dd=cd[2];
    }
    else {
      d = gregorianObj;

      cY=d.getFullYear();
      cM=d.getMonth();
      dd=d.getDate();
    }  
    var calendar=calHeader()+calTitle(d)+calBody(d,dd)+calFooter();
    var fieldObject = document.getElementsByName(currentCal.field+"_y")[0];
    
  	var fieldPosX = getOffset(fieldObject, "left");
  	var fieldPosY = getOffset(fieldObject, "top");
  	var fieldPosW = getOffset(fieldObject, "width");
  	var fieldPosH = getOffset(fieldObject, "height");
    
    currentCal.calIFrameContainer.style.left = fieldPosX+"px";
    currentCal.calIFrameContainer.style.top = fieldPosY + fieldPosH+"px";

    currentCal.calDivContainer.style.left = fieldPosX+"px";
    currentCal.calDivContainer.style.top = fieldPosY + fieldPosH+"px";


    currentCal.calIFrameContainer.style.border = 'none';

    currentCal.calDivContainer.style.left = fieldPosX;
    currentCal.calDivContainer.style.top = fieldPosY + fieldPosH;

		currentCal.calDivContainer.innerHTML = calendar;
		
		currentCal.calIFrameContainer.width = parseInt(getCalendarDivWidth(), 10);
    currentCal.calIFrameContainer.height = parseInt(getCalendarDivHight(), 10);

    currentCal.calIFrameContainer.style.visibility = 'visible';
    currentCal.calDivContainer.style.visibility = 'visible';
  }
  else {
    if (currentCal == null) {
      window.status = "Calendar ["+name+"] not found.";
    }
    else if (!currentCal.form) {
      window.status = "Form ["+currentCal.formName+"] not found.";
    }
    else if (!currentCal.form[currentCal.field]) {
      window.status = "Form Field ["+currentCal.formName+"."+currentCal.field+"] not found.";
    }

    if (lastCal != null) {

      currentCal = lastCal;
    }
  }
}

function get2Digits(n) {
  return ((n<10)?"0":"")+n;
}

function clearDate() {
  currentCal.form[currentCal.field].value="";
  hideCalendar();
}

function pickDate(d) {
  hideCalendar();

  var dDate=calFormat;
  dDate = dDate.replace(/yyyy/i, cY);
  dDate = dDate.replace(/mm/i, get2Digits(cM+1));
  dDate = dDate.replace(/MON/, yxMonths[cM].substring(0,3).toUpperCase());
  dDate = dDate.replace(/Mon/i, yxMonths[cM].substring(0,3));
  dDate = dDate.replace(/dd/i, get2Digits(d));
  dDate = dDate.replace(/DAY/, getDayName(cY,cM,d).toUpperCase());
  dDate = dDate.replace(/day/i, getDayName(cY,cM,d));
  var gd = new Date();
  currentCal.form[currentCal.field+'_y'].value= cY;
  currentCal.form[currentCal.field+'_m'].value= cM+1;
  if(currentCal.form[currentCal.field+'_d'])
    currentCal.form[currentCal.field+'_d'].value= d;
  currentCal.form[currentCal.field].value= dDate /*+ ' ' + get2Digits(gd.getHours()) + ':' + get2Digits(gd.getMinutes()) + ':' + get2Digits(gd.getSeconds())*/;

  // IE5/Mac needs focus to show the value, weird.
  if(currentCal.form[currentCal.field+'_d'])
    currentCal.form[currentCal.field+'_d'].focus();

}
// ------

// user functions
function checkDate(name) {
  var thisCal = findCalendar(name);

  if (thisCal != null && thisCal.form != null && thisCal.form[thisCal.field]) {
    var calRE = getFormat();

    if (calRE.test(thisCal.form[thisCal.field].value)) {
      return 0;
    }
    else {
      return 1;
    }
  }
  else {
    return 2;
  }
}

function getCurrentDate() {
  var dDate=calFormat, d =gregorianObj;
  dDate = dDate.replace(/yyyy/i, d.getFullYear());
  dDate = dDate.replace(/mm/i, get2Digits(d.getMonth()+1));
  dDate = dDate.replace(/dd/i, get2Digits(d.getDate()));

  return dDate;
}

function compareDates(date1, date2) {
  var calRE = getFormat();
  var d1, d2;

  if (calRE.test(date1)) {
    d1 = getNumbers(date1);
  }
  else {
    d1 = getNumbers(getCurrentDate());
  }

  if (calRE.test(date2)) {
    d2 = getNumbers(date2);
  }
  else {
    d2 = getNumbers(getCurrentDate());
  }

  var dStr1 = d1[0] + "" + d1[1] + "" + d1[2];
  var dStr2 = d2[0] + "" + d2[1] + "" + d2[2];

  if (dStr1 == dStr2) {
    return 0;
  }
  else if (dStr1 > dStr2) {
    return 1;
  }
  else {
    return -1;
  }
}

function getFarsiNumber(num){
	if(localeType=='fa'){
	
		var res = '';
		var sNum = num.toString(10);
	
		for (i = 0; i < sNum.length; i ++)
			res += ('&#' + (sNum.charCodeAt(i) + 1728) + ';');
	
		return res;
	}else{
		return num;
	}
}

function getNumbers(date) {
  var calRE = getFormat();
  var y, m, d;

  if (calRE.test(date)) {
    var yIdx = calFormat.search(/yyyy/i);
    var mIdx = calFormat.search(/mm/i);
    var m3Idx = calFormat.search(/mon/i);
    var dIdx = calFormat.search(/dd/i);

    y=date.substring(yIdx,yIdx+4);
    if (mIdx != -1) {
      m=date.substring(mIdx,mIdx+2);
    }
    else {
      var mm=getMonthFromName(date.substring(m3Idx,m3Idx+3))+1;
      m=(mm<10)?("0"+mm):(""+mm);
    }
    d=date.substring(dIdx,dIdx+2);

    return new Array(y,m,d);
  }
  else {
    return new Array("", "", "");
  }
}
// ------

if (isN4 || isN6) {
  document.captureEvents(Event.CLICK);
}
document.onclick=markClick;
